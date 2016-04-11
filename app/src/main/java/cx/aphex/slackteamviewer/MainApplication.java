package cx.aphex.slackteamviewer;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

import cx.aphex.slackteamviewer.interfaces.SlackApiEndpointInterface;
import cx.aphex.slackteamviewer.services.ApiService;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by aphex on 4/7/16.
 */

public class MainApplication extends Application {
    private static SlackApiEndpointInterface sApiService;

    public static SlackApiEndpointInterface getApiService() {
        if (sApiService == null) {
            // Picasso with custom RequestHandler for loading from Layer MessageParts.
            sApiService = ApiService.create();
        }
        return sApiService;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Lato-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

    }
}
