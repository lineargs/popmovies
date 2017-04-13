package com.example.goranminov.popmovies.utilities;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by goranminov on 02/04/2017.
 */

public class MovieDatabaseJsonUtils {

    /**
     * Take the String representing the complete details in JSON format and
     * pull out the data we need to construct the Strings needed.
     *
     * @param stringMovies String representing the complete details in JSON format.
     *
     * @return Strings needed for the wireframes.
     */
    public static String[] getMovieIdFromJson(Context context, String stringMovies) throws JSONException {

            /*
             * The name of the JSON objects that we need to extract.
             */
        final String MDB_RESULTS = "results";
        final String MDB_POSTER_PATH = "poster_path";
        final String MDB_MOVIE_ID = "id";

            /*
             * Object from the returned string and use that Object to create an Array from
             * the parent MDB_RESULTS.
             */
        JSONObject moviesResultJsonObject = new JSONObject(stringMovies);
        JSONArray moviesResultJsonArray = moviesResultJsonObject.getJSONArray(MDB_RESULTS);

            /*
             * The String Array to be returned.
             */
        String[] resultString = new String[moviesResultJsonArray.length()];

        for (int i = 0; i < moviesResultJsonArray.length(); i++) {
            String posterPath;
            String movieId;

                /*
                 * Get the JSONObject.
                 */
            JSONObject moviesResults = moviesResultJsonArray.getJSONObject(i);
            movieId = moviesResults.getString(MDB_MOVIE_ID);
            posterPath = moviesResults.getString(MDB_POSTER_PATH);

                /* This is implemented this way so that will be easy when we will need
                 * to extract the data later.
                 */
            resultString[i] = posterPath + "-" + movieId;
        }
        return resultString;
    }

    public static String[] getMovieDetailFromJson (Context context, String stringMovies) throws JSONException{
        PopMoviesUtils popMoviesUtils = new PopMoviesUtils();
        /*
             * The names of the JSON objects that we need to extract.
             */
        final String MDB_ORIGINAL_TITLE = "original_title";
        final String MDB_POSTER_PATH = "poster_path";
        final String MDB_OVERVIEW = "overview";
        final String MDB_VOTE_AVERAGE = "vote_average";
        final String MDB_RELEASE_DATE = "release_date";
        final String MDB_MOVIE_ID = "id";

            /*
             * Object from the returned string and use that Object to create an Array from
             * the parent MDB_RESULTS.
             */
        JSONObject moviesResultJsonObject = new JSONObject(stringMovies);

            /*
             * The String Array to be returned.
             */
        String[] resultString = new String[1];

            String originalTitle;
            String posterPath;
            String overview;
            String voteAverage;
            String releaseDate;
            String movieId;

                /*
                 * Get the JSONObject.
                 */
            movieId = moviesResultJsonObject.getString(MDB_MOVIE_ID);
            originalTitle = moviesResultJsonObject.getString(MDB_ORIGINAL_TITLE);
            posterPath = moviesResultJsonObject.getString(MDB_POSTER_PATH);
            overview = moviesResultJsonObject.getString(MDB_OVERVIEW);
            voteAverage = moviesResultJsonObject.getString(MDB_VOTE_AVERAGE);
            releaseDate = PopMoviesUtils.getNormalizedReleasedDate(
                    moviesResultJsonObject.getString(MDB_RELEASE_DATE));

                /* This is implemented this way so that will be easy when we will need
                 * to extract the data later.
                 */
            resultString[0] = posterPath + "-" + movieId + "-" + originalTitle + "-"
                    + overview + "-" + voteAverage +
                    "-" + releaseDate;
        return resultString;
    }
}
