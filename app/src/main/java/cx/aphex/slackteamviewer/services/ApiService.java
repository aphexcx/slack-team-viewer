package cx.aphex.slackteamviewer.services;

import android.support.annotation.NonNull;

import com.squareup.moshi.Moshi;

import cx.aphex.slackteamviewer.BuildConfig;
import cx.aphex.slackteamviewer.interfaces.SlackApiEndpointInterface;
import cx.aphex.slackteamviewer.models.ColorAdapter;
import cx.aphex.slackteamviewer.models.OptionAdapter;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * Created by aphex on 4/11/16.
 */
public class ApiService {

    // Trailing slash is needed
    private static final String BASE_URL = "https://slack.com/api/";

    public static SlackApiEndpointInterface create() {
        return getRetrofit(getHttpClient(true))
                .create(SlackApiEndpointInterface.class);
    }

    @NonNull
    private static OkHttpClient getHttpClient(boolean includePresence) {
        return new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    HttpUrl newUrl = chain.request().url().newBuilder()
                            .addQueryParameter("token", BuildConfig.AUTH_TOKEN)
                            .addQueryParameter("presence", includePresence ? "1" : "0")
                            .build();
                    Request newRequest = chain.request().newBuilder().url(newUrl).build();
                    return chain.proceed(newRequest);
                })
                .build();
    }

    @NonNull
    private static Retrofit getRetrofit(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(getMoshi()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();
    }

    @NonNull
    private static Moshi getMoshi() {
        return new Moshi.Builder()
                .add(new ColorAdapter())
                .add(new OptionAdapter())
                .build();
    }
}
