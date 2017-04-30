package com.example.goranminov.popmovies.syncTrailerReview;

import android.app.IntentService;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.example.goranminov.popmovies.data.MovieContract;

/**
 * Created by goranminov on 25/04/2017.
 */

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 */
public class TrailerSyncIntentService extends IntentService {

    public TrailerSyncIntentService () {
        super("TrailerSyncIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Uri uri = intent.getData();
        String id = MovieContract.MovieTrailer.getMovieIdFromUri(uri);
        TrailerSyncTask.syncTrailers(this, id);
        TrailerSyncTask.syncReviews(this, id);
    }
}
