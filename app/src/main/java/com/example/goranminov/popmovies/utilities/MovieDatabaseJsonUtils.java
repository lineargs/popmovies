package com.example.goranminov.popmovies.utilities;

import android.content.Context;

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
    public static String[] getMovieDataFromJson(Context context, String stringMovies) throws JSONException {

            /*
             * The names of the JSON objects that we need to extract.
             */
        final String MDB_RESULTS = "results";
        final String MDB_ORIGINAL_TITLE = "original_title";
        final String MDB_POSTER_PATH = "poster_path";
        final String MDB_OVERVIEW = "overview";
        final String MDB_VOTE_AVERAGE = "vote_average";
        final String MDB_RELEASE_DATE = "release_date";

            /*
             * Object from the returned string and use that Object to create an Array from
             * the parent MDB_RESULTS.
             */
        JSONObject moviesResultJsonObject = new JSONObject(stringMovies);
        JSONArray moviesResultJsonArray = moviesResultJsonObject.getJSONArray(MDB_RESULTS);

            /*
             * The String Array to be returned.
             */
        String[] resultString = new String[20];

        for (int i = 0; i < moviesResultJsonArray.length(); i++) {
            String originalTitle;
            String posterPath;
            String overview;
            String voteAverage;
            String releaseDate;

                /*
                 * Get the JSONObject.
                 */
            JSONObject moviesResults = moviesResultJsonArray.getJSONObject(i);
            originalTitle = moviesResults.getString(MDB_ORIGINAL_TITLE);
            posterPath = moviesResults.getString(MDB_POSTER_PATH);
            overview = moviesResults.getString(MDB_OVERVIEW);
            voteAverage = moviesResults.getString(MDB_VOTE_AVERAGE);
            releaseDate = moviesResults.getString(MDB_RELEASE_DATE);

                /* This is implemented this way so that will be easy when we will need
                 * to extract the data later.
                 */
            resultString[i] = posterPath + "!" + originalTitle + "@"
                    + overview + "#" + voteAverage +
                    "£" + releaseDate;
        }
        return resultString;
    }
}
