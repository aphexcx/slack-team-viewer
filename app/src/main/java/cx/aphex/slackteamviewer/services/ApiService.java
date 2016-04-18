package cx.aphex.slackteamviewer.services;

import android.support.annotation.NonNull;
import android.util.Log;

import com.squareup.moshi.Moshi;

import java.io.File;

import au.com.gridstone.rxstore.RxStore;
import au.com.gridstone.rxstore.converters.GsonConverter;
import cx.aphex.slackteamviewer.BuildConfig;
import cx.aphex.slackteamviewer.interfaces.SlackApiEndpointInterface;
import cx.aphex.slackteamviewer.models.ColorAdapter;
import cx.aphex.slackteamviewer.models.OptionAdapter;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;


/**
 * Created by aphex on 4/11/16.
 */
public class ApiService {

    // Trailing slash is needed
    private static final String BASE_URL = "https://slack.com/api/";
    private static RxStore rxStore =
            RxStore.with(new File("/data/data/" + BuildConfig.APPLICATION_ID + "/cache"))
                    .using(new GsonConverter());

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
                }).addInterceptor(chain -> {
                    Request request = chain.request();
                    Response response = null;
                    boolean success = false;
                    try {
                        response = chain.proceed(request);
                        success = response.isSuccessful();
                    } catch (Exception e) {
                        Log.d("OkHttpInterceptor", "Request failed:");
                        e.printStackTrace();
                    }
                    if (success) {
                        // if request succeeeded, cache the json response
                        String jsonString = response.body().string();
                        rxStore.put("jsonCache", jsonString).subscribe(js -> Log.d("rxStore", "Cached: " + js));

                        // Return the response, but recreate the body because it was consumed.
                        return response.newBuilder()
                                .body(ResponseBody.create(MediaType.parse(jsonString), jsonString))
                                .build();
                    } else {
                        // else, retrieve the cached response and pass it forward
                        String cachedBody = rxStore.get("jsonCache", String.class)
                                .toBlocking()
                                .first();
                        return new Response.Builder()
                                .protocol(Protocol.HTTP_1_1)
                                .request(request)
                                .body(ResponseBody.create(MediaType.parse(cachedBody), cachedBody))
                                .code(203) //203 Non-Authoritative Information
                                .build();
                    }
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
