package cx.aphex.slackteamviewer.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import cx.aphex.slackteamviewer.R;
import cx.aphex.slackteamviewer.interfaces.SlackApiEndpointInterface;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OkHttpClient client = getHttpClient(getString(R.string.AUTH_TOKEN), true);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();

        SlackApiEndpointInterface apiService =
                retrofit.create(SlackApiEndpointInterface.class);

        apiService.getUsersList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(usersList -> {
                    Log.wtf("users!", usersList.getMembers().toString());
                });
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
