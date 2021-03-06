package cx.aphex.slackteamviewer.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.FrameLayout;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import au.com.gridstone.rxstore.RxStore;
import butterknife.Bind;
import butterknife.ButterKnife;
import cx.aphex.slackteamviewer.R;
import cx.aphex.slackteamviewer.adapters.SlackUserAdapter;
import cx.aphex.slackteamviewer.helpers.CustomAnimations;
import cx.aphex.slackteamviewer.models.UsersList;
import cx.aphex.slackteamviewer.views.SlackBottomSheet;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseActivity {
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.rvUsers) RecyclerView rvUsers;
    @Bind(R.id.bottom_sheet) SlackBottomSheet bottomSheet;
    @Bind(R.id.loadingAnimation) SimpleDraweeView loadingAnimation;
    @Bind(R.id.swipeRefreshLayout) SwipeRefreshLayout swipeRefreshLayout;
    private String TAG = this.getClass().getSimpleName();
    private SlackUserAdapter slackUserAdapter;
    private BottomSheetBehavior<FrameLayout> sheetBehavior;
    private RxStore rxStore;
    private Animator hideAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        setupLoadingAnimation();

        setupUserRecyclerView();

        setupBottomSheet();

        loadUsersListFromApi();
    }


    private void setupUserRecyclerView() {
        slackUserAdapter = new SlackUserAdapter();

        //Grab the Recycler View and list all conversation objects in a vertical list
        rvUsers.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvUsers.setItemAnimator(new SlideInUpAnimator());
        rvUsers.setAdapter(slackUserAdapter);

        swipeRefreshLayout.setColorSchemeResources(
                R.color.colorAccent,
                R.color.colorPrimary,
                R.color.colorPrimaryDark);

        swipeRefreshLayout.setOnRefreshListener(this::loadUsersListFromApi);
    }

    private void setupBottomSheet() {
        sheetBehavior = BottomSheetBehavior.from(bottomSheet);

        sheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

        slackUserAdapter.memberClicks.subscribe(member -> {
            Log.d(TAG, "Member clicked: " + member);
            bottomSheet.setMember(member);
            sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        });

    }

    private void setupLoadingAnimation() {
        ImageRequest imageRequest = ImageRequestBuilder
                .newBuilderWithResourceId(R.drawable.loading)
                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(imageRequest.getSourceUri())
                .setAutoPlayAnimations(true)
                .build();
        loadingAnimation.setController(controller);
    }

    private void loadUsersListFromApi() {
        hideUsersList();
        getApiService().getUsersList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onUsersListReceived,
                        this::onConnectionError,
                        this::onLoadComplete);
    }


    private void onUsersListReceived(UsersList usersList) {
        if (usersList.isOk()) {
            slackUserAdapter.setItems(usersList.getMembers());
        } else {
            Log.e(TAG, "Users list received was not OK! ");
        }
    }

    private void onConnectionError(Throwable throwable) {
        Log.e(TAG, "Connection Error:");
        throwable.printStackTrace();

        //TODO: Snackbar
    }

    private void onLoadComplete() {
        swipeRefreshLayout.setRefreshing(false);
        showUsersList();
    }

    private void hideUsersList() {
        final Runnable hide = () -> {
            hideAnim = CustomAnimations.circularHide(rvUsers);
            hideAnim.start();
        };

        if (!rvUsers.isAttachedToWindow()) {
            rvUsers.post(hide);
        } else {
            hide.run();
        }
    }

    private void showUsersList() {
        if (hideAnim != null && hideAnim.isRunning()) {
            // Whoa, the hiding animation's still not finished!
            // Cancel it in its tracks and start a reveal.
            hideAnim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationCancel(Animator animation) {
                    rvUsers.post(() -> CustomAnimations.circularReveal(rvUsers).start());
                }
            });
            hideAnim.cancel();
        } else {
            CustomAnimations.circularReveal(rvUsers).start();
        }
    }

}
