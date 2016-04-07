package cx.aphex.slackteamviewer.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.FrameLayout;

import com.squareup.moshi.Moshi;

import butterknife.Bind;
import butterknife.ButterKnife;
import cx.aphex.slackteamviewer.R;
import cx.aphex.slackteamviewer.adapters.SlackUserAdapter;
import cx.aphex.slackteamviewer.interfaces.SlackApiEndpointInterface;
import cx.aphex.slackteamviewer.models.ColorAdapter;
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
    @Bind(R.id.bottom_sheet) FrameLayout bottomSheet;
    private String TAG = this.getClass().getSimpleName();
    private SlackUserAdapter slackUserAdapter;
    private BottomSheetBehavior<FrameLayout> sheetBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        slackUserAdapter = new SlackUserAdapter();

        //Grab the Recycler View and list all conversation objects in a vertical list
        rvUsers.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvUsers.setItemAnimator(new SlideInUpAnimator());
        rvUsers.setAdapter(slackUserAdapter);

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

        SlackApiEndpointInterface apiService =
                retrofit.create(SlackApiEndpointInterface.class);

        populateUsersList(apiService);

        sheetBehavior = BottomSheetBehavior.from(bottomSheet);

        slackUserAdapter.memberClicks.subscribe(member -> {
            Log.d(TAG, "Member clicked: " + member);
            sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        });

        sheetBehavior.setPeekHeight(100);


    }

    private void populateUsersList(SlackApiEndpointInterface apiService) {
        apiService.getUsersList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(usersList -> {
                    if (usersList.isOk()) {
                        slackUserAdapter.setItems(usersList.getMembers());
                    } else {
                        Log.e(TAG, "Users list received was not OK! ");
                    }
                }, this::onConnectionError);
    }

    private void onConnectionError(Throwable throwable) {
        Log.e(TAG, "Connection Error:");
        throwable.printStackTrace();
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
