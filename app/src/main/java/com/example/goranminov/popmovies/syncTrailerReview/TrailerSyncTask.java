package com.example.goranminov.popmovies.syncTrailerReview;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;

import com.example.goranminov.popmovies.data.MovieContract;
import com.example.goranminov.popmovies.utilities.MovieDatabaseJsonUtils;
import com.example.goranminov.popmovies.utilities.NetworkUtils;

import java.net.URL;

/**
 * Created by goranminov on 24/04/2017.
 */

public class TrailerSyncTask {

    /**
     * Performs the network request for updated trailer, parses the JSON from that request, and
     * inserts the new trailer information into our ContentProvider.
     *
     * @param context Used to access utility methods and the ContentResolver
     */
    synchronized public static void syncTrailers(Context context, String movieId) {

        try {

            /*
             * The buildTrailerUrl method will return the URL that we need to get the JSON for the
             * trailers.
             */
            URL requestUrl = NetworkUtils.buildTrailerUrl(context, movieId);

            /* Use the URL to retrieve the JSON */
            String jsonMovieResponse = NetworkUtils.getMainResponseFromHttpUrl(requestUrl);

            /* Parse the JSON into a list of trailer values */
            ContentValues[] movieValues = MovieDatabaseJsonUtils.getTrailersContentValuesFromJson(context, jsonMovieResponse);

            /*
             * In cases where our JSON contained an error code, getTrailersContentValuesFromJson
             * would have returned null. We need to check for those cases here to prevent any
             * NullPointerExceptions being thrown. We also have no reason to insert fresh data if
             * there isn't any to insert.
             */
            if (movieValues != null && movieValues.length != 0) {

                /* Get a handle on the ContentResolver to delete and insert data */
                ContentResolver movieContentResolver = context.getContentResolver();

                /* Delete old trailer data because we don't need to keep multiple trailers' data */
                movieContentResolver.delete(MovieContract.MovieTrailer.CONTENT_URI,
                        null,
                        null);

                /* Insert our new trailer data into PopMovies' ContentProvider */
                movieContentResolver.bulkInsert(MovieContract.MovieTrailer.CONTENT_URI,
                        movieValues);

                /* If the code reaches this point, we have successfully performed our sync */
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Performs the network request for updated review, parses the JSON from that request, and
     * inserts the new review information into our ContentProvider.
     *
     * @param context Used to access utility methods and the ContentResolver
     */
    synchronized public static void syncReviews(Context context, String movieId) {

        try {

            /*
             * The buildReviewUrl method will return the URL that we need to get the JSON for the
             * reviews.
             */
            URL requestUrl = NetworkUtils.buildReviewUrl(context, movieId);

            /* Use the URL to retrieve the JSON */
            String jsonMovieResponse = NetworkUtils.getMainResponseFromHttpUrl(requestUrl);

            /* Parse the JSON into a list of review values */
            ContentValues[] movieValues = MovieDatabaseJsonUtils.getReviewsContentValuesFromJson(context, jsonMovieResponse);

            /*
             * In cases where our JSON contained an error code, getReviewsContentValuesFromJson
             * would have returned null. We need to check for those cases here to prevent any
             * NullPointerExceptions being thrown. We also have no reason to insert fresh data if
             * there isn't any to insert.
             */
            if (movieValues != null && movieValues.length != 0) {

                /* Get a handle on the ContentResolver to delete and insert data */
                ContentResolver contentResolver = context.getContentResolver();

                /* Delete old review data because we don't need to keep multiple reviews' data */
                contentResolver.delete(MovieContract.MovieReview.CONTENT_URI,
                        null,
                        null);

                /* Insert our new trailer data into PopMovies' ContentProvider */
                contentResolver.bulkInsert(MovieContract.MovieReview.CONTENT_URI,
                        movieValues);

                /* If the code reaches this point, we have successfully performed our sync */
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
