package com.example.goranminov.popmovies.syncMovie;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

/**
 * Created by goranminov on 23/04/2017.
 */

public class MovieSyncUtils {

    /**
     * Helper method to perform a sync immediately using an IntentService for asynchronous
     * execution.
     *
     * @param context The Context used to start the IntentService for the sync.
     */
    public static void startImmediateSync(@NonNull final Context context) {
        Intent intent = new Intent(context, MovieSyncIntentService.class);
        context.startService(intent);
    }
}
