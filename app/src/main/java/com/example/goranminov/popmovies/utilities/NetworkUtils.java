package com.example.goranminov.popmovies.utilities;

import android.content.Context;
import android.net.Uri;

import com.example.goranminov.popmovies.BuildConfig;
import com.example.goranminov.popmovies.data.MoviePreferences;

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

    private static final String MDB_BASE_URL = "http://api.themoviedb.org/3/movie/";
    private static final String APPID_PARAM = "api_key";
    private static final String TRAILERS_PATH = "trailers";

    public static URL buildMainUrl(Context context) {
        String sortingQuery = MoviePreferences.getPreferredSortBy(context);
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

    public static URL buildDetailUrl(String movieId) {
        Uri builtUri = Uri.parse(MDB_BASE_URL).buildUpon()
                .appendPath(movieId)
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

    public static URL buildTrailersUrl (String movieId) {
        Uri builtUri = Uri.parse(MDB_BASE_URL).buildUpon()
                .appendPath(movieId)
                .appendPath(TRAILERS_PATH)
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

    public static String getMainResponseFromHttpUrl(URL url) {
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
