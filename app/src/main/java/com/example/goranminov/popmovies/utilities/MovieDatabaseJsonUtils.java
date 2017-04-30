package com.example.goranminov.popmovies.utilities;

import android.content.ContentValues;
import android.content.Context;
<<<<<<< HEAD
import android.net.NetworkInfo;

import com.example.goranminov.popmovies.data.PopularMoviesContract;
||||||| merged common ancestors
import android.util.Log;
=======
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
>>>>>>> udacity

import com.example.goranminov.popmovies.data.MovieContract;

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

<<<<<<< HEAD
    /**
     * Take the String representing the complete details in JSON format and
     * pull out the data we need to construct the Strings needed.
     *
     * @param stringMovies String representing the complete details in JSON format.
     *
     * @return Strings needed for the wireframes.
     */
    public static ContentValues[] getMovieContentValuesFromJson(Context context, String stringMovies) throws JSONException {
||||||| merged common ancestors
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
=======
    public static ContentValues[] getMoviesContentValuesFromJson(Context context, String moviesJsonString) throws JSONException{
        final String MDB_RESULTS = "results";
        final String MDB_POSTER_PATH = "poster_path";
        final String MDB_MOVIE_ID = "id";
        final String MDB_ORIGINAL_TITLE = "original_title";
        final String MDB_OVERVIEW = "overview";
        final String MDB_VOTE_AVERAGE = "vote_average";
        final String MDB_RELEASE_DATE = "release_date";
        final String MDB_BACKDROP_PATH = "backdrop_path";
>>>>>>> udacity

<<<<<<< HEAD
        JSONObject movieOverviewJson = new JSONObject(stringMovies);
        JSONArray jsonMovieOverviewArray = movieOverviewJson.getJSONArray(MDB_RESULTS);

        ContentValues[] movieOverviewContentValues = new ContentValues[jsonMovieOverviewArray.length()];

        for (int i = 0; i < jsonMovieOverviewArray.length(); i++) {
            double voteAverage;
            String releaseDate;
||||||| merged common ancestors
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
=======
        JSONObject moviesJson = new JSONObject(moviesJsonString);
        JSONArray jsonMoviesArray = moviesJson.getJSONArray(MDB_RESULTS);
        ContentValues[] movieContentValues = new ContentValues[jsonMoviesArray.length()];
        for (int i = 0; i < jsonMoviesArray.length(); i++) {
>>>>>>> udacity
            String posterPath;
<<<<<<< HEAD
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
||||||| merged common ancestors
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
            resultString[i] = posterPath + "£" + movieId;
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
            resultString[0] = posterPath + "£" + movieId + "!" + originalTitle + "@"
                    + overview + "#" + voteAverage +
                    "$" + releaseDate;
        return resultString;
=======
            int movieId;
            String releaseDate;
            String voteAverage;
            String originalTitle;
            String overview;
            String backdropPath;

            JSONObject movieObject = jsonMoviesArray.getJSONObject(i);
            posterPath = PopMoviesUtils.posterPath(movieObject.getString(MDB_POSTER_PATH));
            backdropPath = PopMoviesUtils.posterPath(movieObject.getString(MDB_BACKDROP_PATH));
            movieId = movieObject.getInt(MDB_MOVIE_ID);
            originalTitle = movieObject.getString(MDB_ORIGINAL_TITLE);
            overview = movieObject.getString(MDB_OVERVIEW);
            voteAverage = PopMoviesUtils.getNormalizedVoteAverage(movieObject.getString(MDB_VOTE_AVERAGE));
            releaseDate = PopMoviesUtils.getNormalizedReleasedDate(movieObject.getString(MDB_RELEASE_DATE));
            ContentValues movieValues = new ContentValues();
            movieValues.put(MovieContract.MovieEntry.COLUMN_MOVIE_ID, movieId);
            movieValues.put(MovieContract.MovieEntry.COLUMN_ORIGINAL_TITLE, originalTitle);
            movieValues.put(MovieContract.MovieEntry.COLUMN_POSTER_PATH, posterPath);
            movieValues.put(MovieContract.MovieEntry.COLUMN_OVERVIEW, overview);
            movieValues.put(MovieContract.MovieEntry.COLUMN_VOTE_AVERAGE, voteAverage);
            movieValues.put(MovieContract.MovieEntry.COLUMN_RELEASE_DATE, releaseDate);
            movieValues.put(MovieContract.MovieEntry.COLUMN_BACKDROP_PATH, backdropPath);
            movieContentValues[i] = movieValues;
        }
        return movieContentValues;
    }

    public static ContentValues[] getTrailersContentValuesFromJson(Context context, String moviesJsonString) throws JSONException{
        final String MDB_RESULTS = "results";
        final String TRAILER_MOVIE_ID = "id";
        final String TRAILER_NAME = "name";
        final String TRAILER_KEY = "key";
        final String TRAILER_TYPE = "type";

        JSONObject moviesJson = new JSONObject(moviesJsonString);
        int movieId = moviesJson.getInt(TRAILER_MOVIE_ID);
        JSONArray jsonMoviesArray = moviesJson.getJSONArray(MDB_RESULTS);
        ContentValues[] movieContentValues = new ContentValues[jsonMoviesArray.length()];
        for (int i = 0; i < jsonMoviesArray.length(); i++) {
            String trailerName;
            String trailerKey;
            String trailertype;

            JSONObject movieObject = jsonMoviesArray.getJSONObject(i);
            trailerName = movieObject.getString(TRAILER_NAME);
            trailerKey = movieObject.getString(TRAILER_KEY);
            trailertype = movieObject.getString(TRAILER_TYPE);
            ContentValues movieValues = new ContentValues();
            movieValues.put(MovieContract.MovieTrailer.COLUMN_MOVIE_ID, movieId);
            movieValues.put(MovieContract.MovieTrailer.COLUMN_NAME, trailerName);
            movieValues.put(MovieContract.MovieTrailer.COLUMN_KEY, trailerKey);
            movieValues.put(MovieContract.MovieTrailer.COLUMN_TYPE, trailertype);
            movieContentValues[i] = movieValues;
        }
        return movieContentValues;
    }

    public static ContentValues[] getReviewsContentValuesFromJson(Context context, String moviesJsonString) throws JSONException{
        final String MDB_RESULTS = "results";
        final String REVIEW_MOVIE_ID = "id";
        final String REVIEW_AUTHOR = "author";
        final String REVIEW_CONTENT = "content";

        JSONObject moviesJson = new JSONObject(moviesJsonString);
        int movieId = moviesJson.getInt(REVIEW_MOVIE_ID);
        JSONArray jsonMoviesArray = moviesJson.getJSONArray(MDB_RESULTS);
        ContentValues[] movieContentValues = new ContentValues[jsonMoviesArray.length()];
        for (int i = 0; i < jsonMoviesArray.length(); i++) {
            String reviewAuthor;
            String reviewContent;

            JSONObject movieObject = jsonMoviesArray.getJSONObject(i);
            reviewAuthor = movieObject.getString(REVIEW_AUTHOR);
            reviewContent = movieObject.getString(REVIEW_CONTENT);
            ContentValues movieValues = new ContentValues();
            movieValues.put(MovieContract.MovieReview.COLUMN_MOVIE_ID, movieId);
            movieValues.put(MovieContract.MovieReview.COLUMN_AUTHOR, reviewAuthor);
            movieValues.put(MovieContract.MovieReview.COLUMN_CONTENT, reviewContent);
            movieContentValues[i] = movieValues;
        }
        return movieContentValues;
    }

    public static void addToFavorites(Context context, Uri uri) {
        final String[] projection = {
                MovieContract.MovieEntry.COLUMN_MOVIE_ID,
                MovieContract.MovieEntry.COLUMN_BACKDROP_PATH,
                MovieContract.MovieEntry.COLUMN_ORIGINAL_TITLE,
                MovieContract.MovieEntry.COLUMN_POSTER_PATH,
                MovieContract.MovieEntry.COLUMN_RELEASE_DATE,
                MovieContract.MovieEntry.COLUMN_VOTE_AVERAGE,
                MovieContract.MovieEntry.COLUMN_OVERVIEW,
        };
        final int INDEX_FAVORITE_MOVIE_ID = 0;
        final int INDEX_FAVORITE_BACKDROP_PATH = 1;
        final int INDEX_FAVORITE_ORIGINAL_TITLE = 2;
        final int INDEX_FAVORITE_POSTER_PATH = 3;
        final int INDEX_FAVORITE_RELEASE_DATE = 4;
        final int INDEX_FAVORITE_VOTE_AVERAGE = 5;
        final int INDEX_FAVORITE_OVERVIEW = 6;
        Cursor cursor = context.getContentResolver().query(MovieContract.MovieEntry.buildMovieUriWithId(Long.parseLong(uri.getLastPathSegment())),
                projection,
                null,
                null,
                null);
        cursor.moveToFirst();
        ContentValues[] movieContentValues = new ContentValues[1];
        ContentValues contentValues = new ContentValues();
        contentValues.put(MovieContract.MovieFavorite.COLUMN_MOVIE_ID, cursor.getInt(INDEX_FAVORITE_MOVIE_ID));
        contentValues.put(MovieContract.MovieFavorite.COLUMN_BACKDROP_PATH, cursor.getString(INDEX_FAVORITE_BACKDROP_PATH));
        contentValues.put(MovieContract.MovieFavorite.COLUMN_ORIGINAL_TITLE, cursor.getString(INDEX_FAVORITE_ORIGINAL_TITLE));
        contentValues.put(MovieContract.MovieFavorite.COLUMN_POSTER_PATH, cursor.getString(INDEX_FAVORITE_POSTER_PATH));
        contentValues.put(MovieContract.MovieFavorite.COLUMN_RELEASE_DATE, cursor.getString(INDEX_FAVORITE_RELEASE_DATE));
        contentValues.put(MovieContract.MovieFavorite.COLUMN_VOTE_AVERAGE, cursor.getString(INDEX_FAVORITE_VOTE_AVERAGE));
        contentValues.put(MovieContract.MovieFavorite.COLUMN_OVERVIEW, cursor.getString(INDEX_FAVORITE_OVERVIEW));
        movieContentValues[0] = contentValues;
        context.getContentResolver().bulkInsert(MovieContract.MovieFavorite.CONTENT_URI,
                movieContentValues);
        cursor.close();
    }

    public static void removeFromFavorites(Context context, Uri uri) {
        String idMovie = uri.getLastPathSegment();
        String[] selectionArgs = new String[]{idMovie};
        context.getContentResolver().delete(MovieContract.MovieFavorite.buildFavoriteUriWithId(Long.parseLong(uri.getLastPathSegment())),
                null,
                selectionArgs);
    }

    public static String getTrailerForShare (Context context, Uri uri) {
        final String YOU_TUBE = "http://www.youtube.com/watch?v=";
        String idMovie = uri.getLastPathSegment();
        String[] selectionArgs = new String[]{idMovie};
        String[] projection = new String[]{MovieContract.MovieTrailer.COLUMN_KEY};
        final int INDEX__KEY = 0;
        Cursor cursor = context.getContentResolver().query(MovieContract.MovieTrailer.buildTrailerUriWithId(Long.parseLong(uri.getLastPathSegment())),
                projection,
                MovieContract.MovieTrailer.COLUMN_KEY,
                selectionArgs,
                null);
        cursor.moveToFirst();
        String trailerKey = cursor.getString(INDEX__KEY);
        cursor.close();
       return YOU_TUBE + trailerKey;
>>>>>>> udacity
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
