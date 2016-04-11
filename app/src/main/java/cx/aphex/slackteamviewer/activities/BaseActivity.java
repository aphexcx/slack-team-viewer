package cx.aphex.slackteamviewer.activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import cx.aphex.slackteamviewer.MainApplication;
import cx.aphex.slackteamviewer.interfaces.SlackApiEndpointInterface;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by aphex on 4/11/16.
 */
public class BaseActivity extends AppCompatActivity {

    protected SlackApiEndpointInterface getApiService() {
        return MainApplication.getApiService();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
