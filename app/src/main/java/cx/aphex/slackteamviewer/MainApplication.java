package cx.aphex.slackteamviewer;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by aphex on 4/7/16.
 */

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
