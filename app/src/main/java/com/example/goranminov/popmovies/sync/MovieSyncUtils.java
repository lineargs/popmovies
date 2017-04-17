package com.example.goranminov.popmovies.sync;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

/**
 * Created by goranminov on 15/04/2017.
 */

public class MovieSyncUtils {

    public static void startImmediateSync(@NonNull final Context context) {
        Intent intentToSync = new Intent(context, MovieSyncIntentService.class);
        context.startService(intentToSync);
    }
}
