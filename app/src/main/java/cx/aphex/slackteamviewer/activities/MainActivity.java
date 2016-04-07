package cx.aphex.slackteamviewer.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.squareup.moshi.Moshi;

import au.com.gridstone.rxstore.RxStore;
import au.com.gridstone.rxstore.converters.GsonConverter;
import butterknife.Bind;
import butterknife.ButterKnife;
import cx.aphex.slackteamviewer.R;
import cx.aphex.slackteamviewer.adapters.SlackUserAdapter;
import cx.aphex.slackteamviewer.interfaces.SlackApiEndpointInterface;
import cx.aphex.slackteamviewer.models.ColorAdapter;
import cx.aphex.slackteamviewer.models.UsersList;
import cx.aphex.slackteamviewer.views.SlackBottomSheet;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    // Trailing slash is needed
    public static final String BASE_URL = "https://slack.com/api/";
    @Bind(R.id.rvUsers) RecyclerView rvUsers;
    @Bind(R.id.bottom_sheet) SlackBottomSheet bottomSheet;
    @Bind(R.id.loadingAnimation) SimpleDraweeView loadingAnimation;
    private String TAG = this.getClass().getSimpleName();
    private SlackUserAdapter slackUserAdapter;
    private BottomSheetBehavior<FrameLayout> sheetBehavior;
    private RxStore rxStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setupLoadingAnimation();

        setupUserRecyclerView();

        setupBottomSheet();

        initRxStore();

        loadUsersListFromApi(createApiService());
    }

    private void initRxStore() {
        rxStore = RxStore.withContext(this)
                .using(new GsonConverter());
    }

    private SlackApiEndpointInterface createApiService() {
        OkHttpClient client = getHttpClient(getString(R.string.AUTH_TOKEN), true);

        Moshi moshi = new Moshi.Builder()
                .add(new ColorAdapter())
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();

        return retrofit.create(SlackApiEndpointInterface.class);
    }

    private void setupUserRecyclerView() {
        slackUserAdapter = new SlackUserAdapter();

        //Grab the Recycler View and list all conversation objects in a vertical list
        rvUsers.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvUsers.setItemAnimator(new SlideInUpAnimator());
        rvUsers.setAdapter(slackUserAdapter);
    }

    private void setupBottomSheet() {
        sheetBehavior = BottomSheetBehavior.from(bottomSheet);

        slackUserAdapter.memberClicks.subscribe(member -> {
            Log.d(TAG, "Member clicked: " + member);
            bottomSheet.setMember(member);
            sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        });

        sheetBehavior.setPeekHeight(100);
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

    private void loadUsersListFromApi(SlackApiEndpointInterface apiService) {
        rvUsers.setVisibility(View.INVISIBLE);
        apiService.getUsersList()
                .flatMap(usersList -> rxStore.put("usersList", usersList))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onUsersListReceived,
                        this::onConnectionError);
    }

    private void loadUsersListFromCache() {
        rxStore.get("usersList", UsersList.class)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onUsersListReceived,
                        this::onConnectionError);
    }

    private void onUsersListReceived(UsersList usersList) {
        if (usersList.isOk()) {
            rvUsers.setVisibility(View.VISIBLE);
            slackUserAdapter.setItems(usersList.getMembers());
        } else {
            Log.e(TAG, "Users list received was not OK! ");
        }
    }

    private void onConnectionError(Throwable throwable) {
        Log.e(TAG, "Connection Error:");
        throwable.printStackTrace();

        loadUsersListFromCache();
        //TODO: Snackbar
    }

    @NonNull
    private OkHttpClient getHttpClient(String auth_token, boolean include_presence) {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        clientBuilder.addInterceptor(chain -> {
            HttpUrl newUrl = chain.request().url().newBuilder()
                    .addQueryParameter("token", auth_token)
                    .addQueryParameter("presence", include_presence ? "1" : "0")
                    .build();
            Request newRequest = chain.request().newBuilder().url(newUrl).build();
            return chain.proceed(newRequest);
        });
        return clientBuilder.build();
    }
}
