package com.example.goranminov.popmovies.utilities;

import android.content.Context;
import android.net.Uri;

import com.example.goranminov.popmovies.BuildConfig;
<<<<<<< HEAD
import com.example.goranminov.popmovies.data.MoviePreferences;
||||||| merged common ancestors
=======
import com.example.goranminov.popmovies.DetailActivity;
import com.example.goranminov.popmovies.data.MoviePreferences;
>>>>>>> udacity

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by goranminov on 02/04/2017.
 */

public class NetworkUtils {

    /*Static variables used to build the Url's*/
    private static final String MDB_BASE_URL = "http://api.themoviedb.org/3/movie/";
    private static final String APPID_PARAM = "api_key";
<<<<<<< HEAD
    private static final String TRAILERS_PATH = "trailers";

    public static URL buildMainUrl(Context context) {
        String sortingQuery = MoviePreferences.getPreferredSortBy(context);
||||||| merged common ancestors

    public static URL buildMainUrl(String sortingQuery) {
=======
    private static final String TRAILERS_PATH = "videos";
    private static final String REVIEWS_PATH = "reviews";

    /**
     * Retrieves the proper URL to query for the movie data.
     *
     * @param context used to access other Utility methods
     * @return URL to query the MovieDatabase server
     */
    public static URL buildMainUrl(Context context) {
        String sortingQuery = MoviePreferences.getPreferredSortBy(context);
>>>>>>> udacity
        Uri builtUri = Uri.parse(MDB_BASE_URL).buildUpon()
                .appendPath(sortingQuery)
                .appendQueryParameter(APPID_PARAM, BuildConfig.MOVIE_DATABASE_API_KEY)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

<<<<<<< HEAD
    public static URL buildTrailersUrl (String movieId) {
        Uri builtUri = Uri.parse(MDB_BASE_URL).buildUpon()
||||||| merged common ancestors
    public static URL buildDetailUrl(String movieId) {
        Uri builtUri = Uri.parse(MDB_BASE_URL).buildUpon()
=======
    /**
     * Retrieves the proper URL to query for the trailer data.
     *
     * @param context used to access other Utility methods
     * @param movieId the ID of the movie
     * @return URL to query the MovieDatabase server
     */
    public static URL buildTrailerUrl(Context context, String movieId) {
        Uri buildUri = Uri.parse(MDB_BASE_URL).buildUpon()
>>>>>>> udacity
                .appendPath(movieId)
                .appendPath(TRAILERS_PATH)
                .appendQueryParameter(APPID_PARAM, BuildConfig.MOVIE_DATABASE_API_KEY)
                .build();
        URL url = null;
        try {
            url = new URL(buildUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    /**
     * Retrieves the proper URL to query for the review data.
     *
     * @param context used to access other Utility methods
     * @param movieId the ID of the movie
     * @return URL to query the MovieDatabase server
     */
    public static URL buildReviewUrl(Context context, String movieId) {

        Uri buildUri = Uri.parse(MDB_BASE_URL).buildUpon()
                .appendPath(movieId)
                .appendPath(REVIEWS_PATH)
                .appendQueryParameter(APPID_PARAM, BuildConfig.MOVIE_DATABASE_API_KEY)
                .build();
        URL url = null;
        try {
            url = new URL(buildUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response, null if no response
     * @throws IOException Related to network and stream reading
     */
    public static String getMainResponseFromHttpUrl(URL url) throws IOException {
        /*
             * These two need to be declared outside the try/catch
             * so that they can be closed in the finally block.
             */
        HttpURLConnection urlConnection = null;
        BufferedReader bufferedReader = null;

        // Will contain the raw JSON response as a string.
        String moviesJsonString = null;

        try {
            // Create the request to theMovieDB, and open the connection.
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String.
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer stringBuffer = new StringBuffer();

            if (inputStream == null) {

                // Nothing to do.
                return null;
            }

            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                stringBuffer.append(line + "\n");
            }
            if (stringBuffer.length() == 0) {

                // Stream was empty.  No point in parsing.
                return null;
            }

            moviesJsonString = stringBuffer.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

                /* If the code didn't successfully get the data, there's no point in attemping
                 * to parse it.
                 */
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return moviesJsonString;
    }
}
