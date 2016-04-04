package cx.aphex.slackteamviewer.models;

/**
 * Created by aphex on 4/4/16.
 */

import android.graphics.Color;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

/**
 * Converts strings like #ff0000 to the corresponding color ints.
 */
public class ColorAdapter {
    @ToJson
    String toJson(@HexColor int rgb) {
        return String.format("#%06x", rgb);
    }

    @FromJson
    @HexColor
    int fromJson(String rgb) {
        return Color.parseColor("#" + rgb);
    }
}