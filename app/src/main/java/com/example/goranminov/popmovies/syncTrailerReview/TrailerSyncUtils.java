package com.example.goranminov.popmovies.syncTrailerReview;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;

/**
 * Created by goranminov on 25/04/2017.
 */

public class TrailerSyncUtils {

    /**
     * Helper method to perform a sync immediately using an IntentService for asynchronous
     * execution.
     *
     * @param context The Context used to start the IntentService for the sync.
     */
    public static void startImmediateSync(@NonNull final Context context, @NonNull final Uri uri) {
        Intent intent = new Intent(context, TrailerSyncIntentService.class);
        intent.setData(uri);
        context.startService(intent);
    }
}
