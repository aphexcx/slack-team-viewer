package cx.aphex.slackteamviewer.interfaces;

import cx.aphex.slackteamviewer.models.UsersList;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by aphex on 3/30/16.
 */
public interface SlackApiEndpointInterface {
    // Request method and URL specified in the annotation
    // Callback for the parsed response is the last parameter

    @GET("users.list")
    Observable<UsersList> getUsersList();
}
