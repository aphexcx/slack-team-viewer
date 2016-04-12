package cx.aphex.slackteamviewer.models;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

import fj.data.Option;

/**
 * Created by aphex on 4/12/16.
 */

public class OptionAdapter {
    @ToJson
    String toJson(@NonNull Option<String> stringOption) {
        return stringOption.orSome("");
    }

    @FromJson
    Option<String> fromJson(@Nullable String string) {
        return Option.fromNull(string);
    }
}