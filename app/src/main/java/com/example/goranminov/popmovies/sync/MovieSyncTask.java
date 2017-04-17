package com.example.goranminov.popmovies.sync;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;

import com.example.goranminov.popmovies.data.PopularMoviesContract;
import com.example.goranminov.popmovies.utilities.MovieDatabaseJsonUtils;
import com.example.goranminov.popmovies.utilities.NetworkUtils;

import java.net.URL;

/**
 * Created by goranminov on 15/04/2017.
 */

public class MovieSyncTask {

    synchronized public static void syncMovies (Context context) {
        try {
            URL movieRequestUrl = NetworkUtils.buildMainUrl(context);
            String jsonMovieResponse = NetworkUtils.getMainResponseFromHttpUrl(movieRequestUrl);
            ContentValues[] movieValues = MovieDatabaseJsonUtils.getMovieContentValuesFromJson(context,
                    jsonMovieResponse);
            if (movieValues != null && movieValues.length != 0) {
                ContentResolver movieContentResolver = context.getContentResolver();

                movieContentResolver.delete(PopularMoviesContract.MovieOverview.CONTENT_URI,
                        null,
                        null);
                movieContentResolver.bulkInsert(PopularMoviesContract.MovieOverview.CONTENT_URI,
                        movieValues);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
