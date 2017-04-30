package com.example.goranminov.popmovies.syncMovie;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;

import com.example.goranminov.popmovies.data.MovieContract;
import com.example.goranminov.popmovies.utilities.MovieDatabaseJsonUtils;
import com.example.goranminov.popmovies.utilities.NetworkUtils;

import java.net.URL;

/**
 * Created by goranminov on 23/04/2017.
 */

public class MovieSyncTask {

    /**
     * Performs the network request for updated movie, parses the JSON from that request, and
     * inserts the new movie information into our ContentProvider.
     *
     * @param context Used to access utility methods and the ContentResolver
     */
    synchronized public static void syncMovies(Context context) {

        try {

            /*
             * The buildMainUrl method will return the URL that we need to get the JSON for the
             * movies.
             */
            URL requestUrl = NetworkUtils.buildMainUrl(context);

            /* Use the URL to retrieve the JSON */
            String jsonMovieResponse = NetworkUtils.getMainResponseFromHttpUrl(requestUrl);

            /* Parse the JSON into a list of movie values */
            ContentValues[] movieValues = MovieDatabaseJsonUtils.getMoviesContentValuesFromJson(context, jsonMovieResponse);

            /*
             * In cases where our JSON contained an error code, getMoviesContentValuesFromJson
             * would have returned null. We need to check for those cases here to prevent any
             * NullPointerExceptions being thrown. We also have no reason to insert fresh data if
             * there isn't any to insert.
             */
            if (movieValues != null && movieValues.length != 0) {

                /* Get a handle on the ContentResolver to delete and insert data */
                ContentResolver movieContentResolver = context.getContentResolver();

                /* Delete old movie data because we don't need to keep multiple movies' data */
                movieContentResolver.delete(MovieContract.MovieEntry.CONTENT_URI,
                        null,
                        null);

                /* Insert our new movies data into PopMovies' ContentProvider */
                movieContentResolver.bulkInsert(MovieContract.MovieEntry.CONTENT_URI,
                        movieValues);

                /* If the code reaches this point, we have successfully performed our sync */
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
