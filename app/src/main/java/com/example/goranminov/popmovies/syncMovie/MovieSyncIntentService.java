package com.example.goranminov.popmovies.syncMovie;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by goranminov on 23/04/2017.
 */

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 */
public class MovieSyncIntentService extends IntentService {

    public MovieSyncIntentService() {
        super("MovieSyncIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        MovieSyncTask.syncMovies(this);
    }
}
