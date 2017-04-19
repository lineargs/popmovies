package com.example.goranminov.popmovies.utilities;

import android.content.ContentValues;
import android.content.Context;
import android.net.NetworkInfo;

import com.example.goranminov.popmovies.data.PopularMoviesContract;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;

/**
 * Created by goranminov on 02/04/2017.
 */

public final class MovieDatabaseJsonUtils {

    private static final String MDB_RESULTS = "results";
    private static final String MDB_ORIGINAL_TITLE = "original_title";
    private static final String MDB_POSTER_PATH = "poster_path";
    private static final String MDB_OVERVIEW = "overview";
    private static final String MDB_VOTE_AVERAGE = "vote_average";
    private static final String MDB_RELEASE_DATE = "release_date";
    private static final String MDB_MOVIE_ID = "id";
    private static final String MDB_BACKDROP_PATH = "backdrop_path";

    private static final String MDB_TRAILER_MOVIE_ID = "id";
    private static final String MDB_TRAILER_NAME = "name";
    private static final String MDB_TRAILER_TYPE = "type";
    private static final String MDB_TRAILER_SOURCE = "source";

    /**
     * Take the String representing the complete details in JSON format and
     * pull out the data we need to construct the Strings needed.
     *
     * @param stringMovies String representing the complete details in JSON format.
     *
     * @return Strings needed for the wireframes.
     */
    public static ContentValues[] getMovieContentValuesFromJson(Context context, String stringMovies) throws JSONException {

        JSONObject movieOverviewJson = new JSONObject(stringMovies);
        JSONArray jsonMovieOverviewArray = movieOverviewJson.getJSONArray(MDB_RESULTS);

        ContentValues[] movieOverviewContentValues = new ContentValues[jsonMovieOverviewArray.length()];

        for (int i = 0; i < jsonMovieOverviewArray.length(); i++) {
            double voteAverage;
            String releaseDate;
            String posterPath;
            String backdropPath;
            String originalTitle;
            int movieId;
            String overview;

            JSONObject movieJsonObject = jsonMovieOverviewArray.getJSONObject(i);
            voteAverage = movieJsonObject.getDouble(MDB_VOTE_AVERAGE);
            releaseDate = PopMoviesUtils.getNormalizedReleasedDate(movieJsonObject.getString(MDB_RELEASE_DATE));
            posterPath = PopMoviesUtils.getPosterPath(movieJsonObject.getString(MDB_POSTER_PATH));
            backdropPath = movieJsonObject.getString(MDB_BACKDROP_PATH);
            originalTitle = movieJsonObject.getString(MDB_ORIGINAL_TITLE);
            movieId = movieJsonObject.getInt(MDB_MOVIE_ID);
            overview = movieJsonObject.getString(MDB_OVERVIEW);
            ContentValues movieOverviewValues = new ContentValues();
            movieOverviewValues.put(PopularMoviesContract.MovieOverview.COLUMN_BACKDROP_PATH, backdropPath);
            movieOverviewValues.put(PopularMoviesContract.MovieOverview.COLUMN_MOVIE_ID, movieId);
            movieOverviewValues.put(PopularMoviesContract.MovieOverview.COLUMN_ORIGINAL_TITLE, originalTitle);
            movieOverviewValues.put(PopularMoviesContract.MovieOverview.COLUMN_OVERVIEW, overview);
            movieOverviewValues.put(PopularMoviesContract.MovieOverview.COLUMN_POSTER_PATH, posterPath);
            movieOverviewValues.put(PopularMoviesContract.MovieOverview.COLUMN_RELEASE_DATE, releaseDate);
            movieOverviewValues.put(PopularMoviesContract.MovieOverview.COLUMN_VOTE_AVERAGE, voteAverage);
            movieOverviewContentValues[i] = movieOverviewValues;
        }
        return movieOverviewContentValues;
    }

//    public static String[] getMovieTrailersFromJson (Context context, String stringMovie) throws JSONException {
//
//        JSONObject movieResultsJsonObject = new JSONObject(stringMovie);
//        JSONArray movieResultsJsonArray = movieResultsJsonObject.getJSONArray(MDB_YOUTUBE_RESULTS);
//        String[] resultString = new String[movieResultsJsonArray.length()];
//
//        for (int i = 0; i < movieResultsJsonArray.length(); i++) {
//            String source;
//            String name;
//
//            JSONObject movieResults = movieResultsJsonArray.getJSONObject(i);
//                name = movieResults.getString(MDB_NAME);
//                source = movieResults.getString(MDB_SOURCE);
//                resultString[i] = name + "£" + source;
//            }
//        return resultString;
//    }
}
