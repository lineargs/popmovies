<<<<<<< HEAD
package com.example.goranminov.popmovies.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

/**
 * Created by goranminov on 15/04/2017.
 */

public class MovieProvider extends ContentProvider {

    public static final int CODE_MOVIES = 100;
    public static final int CODE_MOVIES_WITH_ID = 101;
    public static final int CODE_TRAILER = 200;
    public static final int CODE_TRAILER_WITH_ID = 201;
    public static final int CODE_REVIEW = 300;
    public static final int CODE_REVIEW_WITH_ID = 301;

    private static final UriMatcher sUriMatcher = buildUriMather();
    private MovieDbHelper mMovieDbHelper;

    public static UriMatcher buildUriMather() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = PopularMoviesContract.CONTENT_AUTHORITY;
        matcher.addURI(authority, PopularMoviesContract.PATH_MOVIE, CODE_MOVIES);
        matcher.addURI(authority, PopularMoviesContract.PATH_MOVIE + "/#", CODE_MOVIES_WITH_ID);
        matcher.addURI(authority, PopularMoviesContract.PATH_TRAILER, CODE_TRAILER);
        matcher.addURI(authority, PopularMoviesContract.PATH_TRAILER + "/#", CODE_TRAILER_WITH_ID);
        matcher.addURI(authority, PopularMoviesContract.PATH_REVIEW, CODE_REVIEW);
        matcher.addURI(authority, PopularMoviesContract.PATH_REVIEW + "/#", CODE_REVIEW_WITH_ID);
        return matcher;
    }

    @Override
    public boolean onCreate() {
        mMovieDbHelper = new MovieDbHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        final SQLiteDatabase sqLiteDatabase = mMovieDbHelper.getReadableDatabase();

        Cursor retCursor;

        switch (sUriMatcher.match(uri)) {
            case CODE_MOVIES_WITH_ID:
                String idMovie = uri.getLastPathSegment();
                String[] selectionArgumentsMovie = new String[]{idMovie};
                retCursor = sqLiteDatabase.query(PopularMoviesContract.MovieOverview.TABLE_NAME,
                        projection,
                        PopularMoviesContract.MovieOverview.COLUMN_MOVIE_ID + " = ? ",
                        selectionArgumentsMovie,
                        null,
                        null,
                        sortOrder);
                break;
            case CODE_MOVIES:
                retCursor = sqLiteDatabase.query(PopularMoviesContract.MovieOverview.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            case CODE_TRAILER_WITH_ID:
                String idTrailer = uri.getLastPathSegment();
                String[] selectionArgumentsTrailer = new String[]{idTrailer};
                retCursor = sqLiteDatabase.query(PopularMoviesContract.MovieTrailer.TABLE_NAME,
                        projection,
                        PopularMoviesContract.MovieTrailer.COLUMN_MOVIE_ID + " = ? ",
                        selectionArgumentsTrailer,
                        null,
                        null,
                        sortOrder);
                break;

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;
    }

    @Override
    public String getType(@NonNull Uri uri) {
        throw new RuntimeException("We are not implementing getType.");
    }

    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {
        final SQLiteDatabase sqLiteDatabase = mMovieDbHelper.getWritableDatabase();

        switch (sUriMatcher.match(uri)) {
            case CODE_MOVIES:
                sqLiteDatabase.beginTransaction();
                int moviesInserted = 0;
                try {
                    for (ContentValues value : values) {
                        long _id = sqLiteDatabase.insert(PopularMoviesContract.MovieOverview.TABLE_NAME,
                                null, value);
                        if (_id != -1) {
                            moviesInserted++;
                        }
                    }
                    sqLiteDatabase.setTransactionSuccessful();
                } finally {
                    sqLiteDatabase.endTransaction();
                }

                if (moviesInserted > 0) {
                    getContext().getContentResolver().notifyChange(uri, null);
                }
                return moviesInserted;
            case CODE_TRAILER:
                sqLiteDatabase.beginTransaction();
                int trailersInserted = 0;
                try {
                    for (ContentValues value : values) {
                        long _id = sqLiteDatabase.insert(PopularMoviesContract.MovieTrailer.TABLE_NAME,
                                null, value);
                        if (_id != -1) {
                            trailersInserted++;
                        }
                    }
                    sqLiteDatabase.setTransactionSuccessful();
                } finally {
                    sqLiteDatabase.endTransaction();
                }

                if (trailersInserted > 0) {
                    getContext().getContentResolver().notifyChange(uri, null);
                }
                return trailersInserted;

            default:
                return super.bulkInsert(uri, values);
        }
    }

    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        throw new RuntimeException("We are not implementing insert, use BulkInsert instead.");
    }

    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase sqLiteDatabase = mMovieDbHelper.getWritableDatabase();
        int rowsDeleted;

        switch (sUriMatcher.match(uri)) {
            case CODE_MOVIES:
                rowsDeleted = sqLiteDatabase.delete(PopularMoviesContract.MovieOverview.TABLE_NAME,
                        "_id=?", selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        if (rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        throw new RuntimeException("Not yet implemented.");
    }
}
||||||| merged common ancestors
=======
package com.example.goranminov.popmovies.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;

/**
 * Created by goranminov on 23/04/2017.
 */

/**
 * This class serves as the ContentProvider for all of PopMovies's data. This class allows us to
 * bulkInsert data, query data, and delete data.
 * <p>
 * Although ContentProvider implementation requires the implementation of additional methods to
 * perform single inserts, updates, and the ability to get the type of the data from a URI.
 * However, here, they are not implemented.
 */
public class MovieProvider extends ContentProvider {

    /*
     * These constants will be used to match URIs with the data they are looking for. We will take
     * advantage of the UriMatcher class to make that matching MUCH easier than doing something
     * ourselves, such as using regular expressions.
     */

    public static final int CODE_MOVIES = 100;
    public static final int CODE_MOVIES_WITH_ID = 101;
    public static final int CODE_TRAILERS = 200;
    public static final int CODE_TRAILERS_WITH_ID = 201;
    public static final int CODE_REVIEWS = 300;
    public static final int CODE_REVIEWS_WITH_ID = 301;
    public static final int CODE_FAVORITES = 400;
    public static final int CODE_FAVORITES_WITH_ID = 401;

    /*
     * The URI Matcher used by this content provider. The leading "s" in this variable name
     * signifies that this UriMatcher is a static member variable of WeatherProvider and is a
     * common convention in Android programming.
     */
    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private MovieDbHelper mMovieDbHelper;

    /**
     * Creates the UriMatcher that will match each URI to the constants defined above.
     * UriMatcher does all the hard work for you. You just have to tell it which code to match
     * with which URI, and it does the rest automatically.
     *
     * @return A UriMatcher that correctly matches the constants above
     */
    public static UriMatcher buildUriMatcher() {

        /*
         * All paths added to the UriMatcher have a corresponding code to return when a match is
         * found. The code passed into the constructor of UriMatcher here represents the code to
         * return for the root URI. It's common to use NO_MATCH as the code for this case.
         */
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = MovieContract.CONTENT_AUTHORITY;

        /*
         * For each type of URI you want to add, create a corresponding code. Preferably, these are
         * constant fields in your class so that you can use them throughout the class and you know
         * they aren't going to change.
         */

        /* This URI is content://com.example.goranminov.popmovies/movie/ */
        matcher.addURI(authority, MovieContract.PATH_MOVIE, CODE_MOVIES);

        /*
         * This URI would look something like content://com.example.goranminov.popmovies/movie/123567
         * The "/#" signifies to the UriMatcher that if PATH_MOVIE is followed by ANY number,
         * that it should return the CODE_MOVIES_WITH_ID code
         */
        matcher.addURI(authority, MovieContract.PATH_MOVIE + "/#", CODE_MOVIES_WITH_ID);

        /* This URI is content://com.example.goranminov.popmovies/trailer/ */
        matcher.addURI(authority, MovieContract.PATH_TRAILER, CODE_TRAILERS);

        /*
         * This URI would look something like content://com.example.goranminov.popmovies/trailer/123567
         * The "/#" signifies to the UriMatcher that if PATH_TRAILER is followed by ANY number,
         * that it should return the CODE_TRAILERS_WITH_ID code
         */
        matcher.addURI(authority, MovieContract.PATH_TRAILER + "/#", CODE_TRAILERS_WITH_ID);

        /* This URI is content://com.example.goranminov.popmovies/review/ */
        matcher.addURI(authority, MovieContract.PATH_REVIEW, CODE_REVIEWS);

        /*
         * This URI would look something like content://com.example.goranminov.popmovies/review/123567
         * The "/#" signifies to the UriMatcher that if PATH_REVIEW is followed by ANY number,
         * that it should return the CODE_REVIEWS_WITH_ID code
         */
        matcher.addURI(authority, MovieContract.PATH_REVIEW + "/#", CODE_REVIEWS_WITH_ID);

        /* This URI is content://com.example.goranminov.popmovies/favorite/ */
        matcher.addURI(authority, MovieContract.PATH_FAVORITE, CODE_FAVORITES);

        /*
         * This URI would look something like content://com.example.goranminov.popmovies/favorite/123567
         * The "/#" signifies to the UriMatcher that if PATH_FAVORITE is followed by ANY number,
         * that it should return the CODE_FAVORITES_WITH_ID code
         */
        matcher.addURI(authority, MovieContract.PATH_FAVORITE + "/#", CODE_FAVORITES_WITH_ID);

        return matcher;
    }

    /**
     * In onCreate, we initialize our content provider on startup. This method is called for all
     * registered content providers on the application main thread at application launch time.
     * It must not perform lengthy operations, or application startup will be delayed.
     *
     * Nontrivial initialization (such as opening, upgrading, and scanning
     * databases) should be deferred until the content provider is used (via {@link #query},
     * {@link #bulkInsert(Uri, ContentValues[])}, etc).
     *
     * Deferred initialization keeps application startup fast, avoids unnecessary work if the
     * provider turns out not to be needed, and stops database errors (such as a full disk) from
     * halting application launch.
     *
     * @return true if the provider was successfully loaded, false otherwise
     */
    @Override
    public boolean onCreate() {

        /*
         * As noted in the comment above, onCreate is run on the main thread, so performing any
         * lengthy operations will cause lag in your app. Since MovieDbHelper's constructor is
         * very lightweight, we are safe to perform that initialization here.
         */
        mMovieDbHelper = new MovieDbHelper(getContext());
        return true;
    }

    /**
     * Handles query requests from clients. We will use this method in PopMovies to query for all
     * of our movie, trailer and review data as well as to query for a particular movie ID.
     *
     * @param uri           The URI to query
     * @param projection    The list of columns to put into the cursor. If null, all columns are
     *                      included.
     * @param selection     A selection criteria to apply when filtering rows. If null, then all
     *                      rows are included.
     * @param selectionArgs You may include ?s in selection, which will be replaced by
     *                      the values from selectionArgs, in order that they appear in the
     *                      selection.
     * @param sortOrder     How the rows in the cursor should be sorted.
     * @return A Cursor containing the results of the query.
     */
    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        /* The returning cursor*/
        Cursor retCursor;

        /*
         * Here's the switch statement that, given a URI, will determine what kind of request is
         * being made and query the database accordingly.
         */
        switch (sUriMatcher.match(uri)) {

            /*
             * When sUriMatcher's match method is called with a URI that looks EXACTLY like this
             *
             *      content://com.example.goranminov.popmovies/movie/
             *
             * sUriMatcher's match method will return the code that indicates to us that we need
             * to return all of the movies in our movie table.
             *
             * In this case, we want to return a cursor that contains every row of movie data
             * in our movie table.
             */
            case CODE_MOVIES:
                retCursor = mMovieDbHelper.getReadableDatabase().query(

                        /* Table we are going to query */
                        MovieContract.MovieEntry.TABLE_NAME,

                        /*
                         * A projection designates the columns we want returned in our Cursor.
                         * Passing null will return all columns of data within the Cursor.
                         * However, if you don't need all the data from the table, it's best
                         * practice to limit the columns returned in the Cursor with a projection.
                         */
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;

            /*
             * When sUriMatcher's match method is called with a URI that looks something like this
             *
             *      content://com.example.goranminov.popmovies/movie/123567
             *
             * sUriMatcher's match method will return the code that indicates to us that we need
             * to return the movie for a particular movie ID. The ID in this code is at the very
             * end of the URI (123567) and can be accessed programmatically using Uri's getLastPathSegment method.
             *
             * In this case, we want to return a cursor that contains one row of movie data for
             * a particular movie ID.
             */
            case CODE_MOVIES_WITH_ID:

                /*
                 * In order to determine the movie ID associated with this URI, we look at the last
                 * path segment. In the comment above, the last path segment is 123567 and
                 * represents the movie ID.
                 */
                String idMovie = uri.getLastPathSegment();

                /*
                 * The query method accepts a string array of arguments, as there may be more
                 * than one "?" in the selection statement. Even though in our case, we only have
                 * one "?", we have to create a string array that only contains one element
                 * because this method signature accepts a string array.
                 */
                String[] selectionArguments = new String[]{idMovie};
                retCursor = mMovieDbHelper.getReadableDatabase().query(

                        /* Table we are going to query */
                        MovieContract.MovieEntry.TABLE_NAME,

                        /*
                         * A projection designates the columns we want returned in our Cursor.
                         * Passing null will return all columns of data within the Cursor.
                         * However, if you don't need all the data from the table, it's best
                         * practice to limit the columns returned in the Cursor with a projection.
                         */
                        projection,

                        /*
                         * The URI that matches CODE_MOVIES_WITH_ID contains an ID at the end
                         * of it. We extract that ID and use it with these next two lines to
                         * specify the row of movie we want returned in the cursor. We use a
                         * question mark here and then designate selectionArguments as the next
                         * argument for performance reasons. Whatever Strings are contained
                         * within the selectionArguments array will be inserted into the
                         * selection statement by SQLite under the hood.
                         */
                        MovieContract.MovieEntry.COLUMN_MOVIE_ID + " = ? ",
                        selectionArguments,
                        null,
                        null,
                        sortOrder);
                break;

            /*
             * When sUriMatcher's match method is called with a URI that looks EXACTLY like this
             *
             *      content://com.example.goranminov.popmovies/trailer/
             *
             * sUriMatcher's match method will return the code that indicates to us that we need
             * to return all of the trailers in our trailer table.
             *
             * In this case, we want to return a cursor that contains every row of trailer data
             * in our trailer table.
             *
             */
            case CODE_TRAILERS:
                retCursor = mMovieDbHelper.getReadableDatabase().query(

                        /* Table we are going to query */
                        MovieContract.MovieTrailer.TABLE_NAME,

                        /*
                         * A projection designates the columns we want returned in our Cursor.
                         * Passing null will return all columns of data within the Cursor.
                         * However, if you don't need all the data from the table, it's best
                         * practice to limit the columns returned in the Cursor with a projection.
                         */
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;

            /*
             * When sUriMatcher's match method is called with a URI that looks something like this
             *
             *      content://com.example.goranminov.popmovies/trailer/123567
             *
             * sUriMatcher's match method will return the code that indicates to us that we need
             * to return the trailer for a particular movie ID. The ID in this code is at the very
             * end of the URI (123567) and can be accessed programmatically using Uri's getLastPathSegment method.
             *
             * In this case, we want to return a cursor that contains one row of trailer data for
             * a particular movie ID.
             */
            case CODE_TRAILERS_WITH_ID:

                /*
                 * In order to determine the movie ID associated with this URI, we look at the last
                 * path segment. In the comment above, the last path segment is 123567 and
                 * represents the movie ID.
                 */
                String idMovieTrailer = uri.getLastPathSegment();

                /*
                 * In order to return just the trailer with Trailer type.
                 */
                String trailerType = "Trailer";

                /*
                 * The query method accepts a string array of arguments. In our case, we have
                 * two "?", we have to create a string array that contains two elements.
                 */
                String[] selectionTrailer = new String[]{idMovieTrailer, trailerType};
                retCursor = mMovieDbHelper.getReadableDatabase().query(

                        /* Table we are going to query */
                        MovieContract.MovieTrailer.TABLE_NAME,

                        /*
                         * A projection designates the columns we want returned in our Cursor.
                         * Passing null will return all columns of data within the Cursor.
                         * However, if you don't need all the data from the table, it's best
                         * practice to limit the columns returned in the Cursor with a projection.
                         */
                        projection,

                        /*
                         * The URI that matches CODE_TRAILERS_WITH_ID contains an ID at the end
                         * of it. We extract that ID and the trailer Type and use them with these
                         * next two lines to specify the row of trailer and the type we want
                         * returned in the cursor. We use a question mark here, the word AND and
                         * another question mark and then designate selectionTrailer as the next
                         * arguments for performance reasons. Whatever Strings are contained
                         * within the selectionTrailer array will be inserted into the
                         * selection statement by SQLite under the hood.
                         *
                         * Please note that the order of the selectionTrailer MUST match with
                         * the below line.
                         */
                        MovieContract.MovieTrailer.COLUMN_MOVIE_ID + " = ? AND " + MovieContract.MovieTrailer.COLUMN_TYPE + " = ? ",
                        selectionTrailer,
                        null,
                        null,
                        sortOrder);
                break;

            /*
             * When sUriMatcher's match method is called with a URI that looks EXACTLY like this
             *
             *      content://com.example.goranminov.popmovies/review/
             *
             * sUriMatcher's match method will return the code that indicates to us that we need
             * to return all of the reviews in our review table.
             *
             * In this case, we want to return a cursor that contains every row of review data
             * in our review table.
             *
             */
            case CODE_REVIEWS:
                retCursor = mMovieDbHelper.getReadableDatabase().query(

                        /* Table we are going to query */
                        MovieContract.MovieReview.TABLE_NAME,

                        /*
                         * A projection designates the columns we want returned in our Cursor.
                         * Passing null will return all columns of data within the Cursor.
                         * However, if you don't need all the data from the table, it's best
                         * practice to limit the columns returned in the Cursor with a projection.
                         */
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;

            /*
             * When sUriMatcher's match method is called with a URI that looks something like this
             *
             *      content://com.example.goranminov.popmovies/review/123567
             *
             * sUriMatcher's match method will return the code that indicates to us that we need
             * to return the review for a particular movie ID. The ID in this code is at the very
             * end of the URI (123567) and can be accessed programmatically using Uri's getLastPathSegment method.
             *
             * In this case, we want to return a cursor that contains one row of review data for
             * a particular movie ID.
             */
            case CODE_REVIEWS_WITH_ID:

                /*
                 * In order to determine the movie ID associated with this URI, we look at the last
                 * path segment. In the comment above, the last path segment is 123567 and
                 * represents the movie ID.
                 */
                String idMovieReview = uri.getLastPathSegment();

                /*
                 * The query method accepts a string array of arguments, as there may be more
                 * than one "?" in the selection statement. Even though in our case, we only have
                 * one "?", we have to create a string array that only contains one element
                 * because this method signature accepts a string array.
                 */
                String[] selectionReview = new String[]{idMovieReview};
                retCursor = mMovieDbHelper.getReadableDatabase().query(

                        /* Table we are going to query */
                        MovieContract.MovieReview.TABLE_NAME,

                        /*
                         * A projection designates the columns we want returned in our Cursor.
                         * Passing null will return all columns of data within the Cursor.
                         * However, if you don't need all the data from the table, it's best
                         * practice to limit the columns returned in the Cursor with a projection.
                         */
                        projection,

                        /*
                         * The URI that matches CODE_REVIEWS_WITH_ID contains an ID at the end
                         * of it. We extract that ID and use it with these next two lines to
                         * specify the row of review we want returned in the cursor. We use a
                         * question mark here and then designate selectionReview as the next
                         * argument for performance reasons. Whatever Strings are contained
                         * within the selectionReview array will be inserted into the
                         * selection statement by SQLite under the hood.
                         */
                        MovieContract.MovieReview.COLUMN_MOVIE_ID + " = ? ",
                        selectionReview,
                        null,
                        null,
                        sortOrder);
                break;

            /*
             * When sUriMatcher's match method is called with a URI that looks EXACTLY like this
             *
             *      content://com.example.goranminov.popmovies/favorite/
             *
             * sUriMatcher's match method will return the code that indicates to us that we need
             * to return all of the favorite in our favorite table.
             *
             * In this case, we want to return a cursor that contains every row of favorite data
             * in our favorite table.
             */
            case CODE_FAVORITES:
                retCursor = mMovieDbHelper.getReadableDatabase().query(

                        /* Table we are going to query */
                        MovieContract.MovieFavorite.TABLE_NAME,

                        /*
                         * A projection designates the columns we want returned in our Cursor.
                         * Passing null will return all columns of data within the Cursor.
                         * However, if you don't need all the data from the table, it's best
                         * practice to limit the columns returned in the Cursor with a projection.
                         */
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;

            /*
             * When sUriMatcher's match method is called with a URI that looks something like this
             *
             *      content://com.example.goranminov.popmovies/favorite/123567
             *
             * sUriMatcher's match method will return the code that indicates to us that we need
             * to return the favorite for a particular movie ID. The ID in this code is at the very
             * end of the URI (123567) and can be accessed programmatically using Uri's getLastPathSegment method.
             *
             * In this case, we want to return a cursor that contains one row of favorite data for
             * a particular movie ID.
             */
            case CODE_FAVORITES_WITH_ID:

                /*
                 * In order to determine the movie ID associated with this URI, we look at the last
                 * path segment. In the comment above, the last path segment is 123567 and
                 * represents the movie ID.
                 */
                String idMovieFavorite = uri.getLastPathSegment();

                /*
                 * The query method accepts a string array of arguments, as there may be more
                 * than one "?" in the selection statement. Even though in our case, we only have
                 * one "?", we have to create a string array that only contains one element
                 * because this method signature accepts a string array.
                 */
                String[] selectionFavorite = new String[]{idMovieFavorite};
                retCursor = mMovieDbHelper.getReadableDatabase().query(

                        /* Table we are going to query */
                        MovieContract.MovieFavorite.TABLE_NAME,

                        /*
                         * A projection designates the columns we want returned in our Cursor.
                         * Passing null will return all columns of data within the Cursor.
                         * However, if you don't need all the data from the table, it's best
                         * practice to limit the columns returned in the Cursor with a projection.
                         */
                        projection,

                        /*
                         * The URI that matches CODE_FAVORITES_WITH_ID contains an ID at the end
                         * of it. We extract that ID and use it with these next two lines to
                         * specify the row of favorite we want returned in the cursor. We use a
                         * question mark here and then designate selectionFavorite as the next
                         * argument for performance reasons. Whatever Strings are contained
                         * within the selectionFavorite array will be inserted into the
                         * selection statement by SQLite under the hood.
                         */
                        MovieContract.MovieFavorite.COLUMN_MOVIE_ID + " = ? ",
                        selectionFavorite,
                        null,
                        null,
                        sortOrder);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;
    }


    @Override
    public String getType(@NonNull Uri uri) {
        throw new RuntimeException("We are not implementing getType.");
    }

    /**
     * Handles requests to insert a set of new rows. In PopMovies, we are going to be
     * inserting multiple rows of data at a time from a movie database.
     *
     * @param uri    The content:// URI of the insertion request.
     * @param values An array of sets of column_name/value pairs to add to the database.
     *               This must not be {@code null}.
     *
     * @return The number of values that were inserted.
     */
    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {
        final SQLiteDatabase db = mMovieDbHelper.getWritableDatabase();
        int rowsInserted = 0;
        switch (sUriMatcher.match(uri)) {
            case CODE_MOVIES:
                db.beginTransaction();
                try {
                    for (ContentValues value : values) {
                        long _id = db.insert(MovieContract.MovieEntry.TABLE_NAME, null, value);
                        if (_id != -1) {
                            rowsInserted++;
                        }
                    }
                    db.setTransactionSuccessful();
                } finally {
                    db.endTransaction();
                }

                if (rowsInserted > 0) {
                    getContext().getContentResolver().notifyChange(uri, null);
                }
                return rowsInserted;
            case CODE_TRAILERS:
                db.beginTransaction();
                try {
                    for (ContentValues value: values) {
                        long _id = db.insert(MovieContract.MovieTrailer.TABLE_NAME, null, value);
                        if (_id != -1) {
                            rowsInserted++;
                        }
                    }
                    db.setTransactionSuccessful();
                } finally {
                    db.endTransaction();
                }

                if (rowsInserted > 0) {
                    getContext().getContentResolver().notifyChange(uri, null);
                }
                return rowsInserted;
            case CODE_REVIEWS:
                db.beginTransaction();
                try {
                    for (ContentValues value: values) {
                        long _id = db.insert(MovieContract.MovieReview.TABLE_NAME, null, value);
                        if (_id != -1) {
                            rowsInserted++;
                        }
                    }
                    db.setTransactionSuccessful();
                } finally {
                    db.endTransaction();
                }

                if (rowsInserted > 0) {
                    getContext().getContentResolver().notifyChange(uri, null);
                }
                return rowsInserted;
            case CODE_FAVORITES:
                db.beginTransaction();
                try {
                    for (ContentValues value: values) {
                        long _id = db.insert(MovieContract.MovieFavorite.TABLE_NAME, null, value);
                        if (_id != -1) {
                            rowsInserted++;
                        }
                    }
                    db.setTransactionSuccessful();
                } finally {
                    db.endTransaction();
                }

                if (rowsInserted > 0) {
                    getContext().getContentResolver().notifyChange(uri, null);
                }
                return rowsInserted;
            default:
                return super.bulkInsert(uri, values);
        }
    }


    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        throw new RuntimeException("We are not implementing insert, use bulkInsert instead.");
    }

    /**
     * Deletes data at a given URI with optional arguments for more fine tuned deletions.
     *
     * @param uri           The full URI to query
     * @param selection     An optional restriction to apply to rows when deleting.
     * @param selectionArgs Used in conjunction with the selection statement
     * @return The number of rows deleted
     */
    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {

        /* Users of the delete method will expect the number of rows deleted to be returned. */
        int numRowsDeleted;

        /*
         * If we pass null as the selection to SQLiteDatabase#delete, our entire table will be
         * deleted. However, if we do pass null and delete all of the rows in the table, we won't
         * know how many rows were deleted. According to the documentation for SQLiteDatabase,
         * passing "1" for the selection will delete all rows and return the number of rows
         * deleted, which is what the caller of this method expects.
         */
        if (null == selection) selection = "1";

        switch (sUriMatcher.match(uri)) {
            case CODE_MOVIES:
                numRowsDeleted = mMovieDbHelper.getWritableDatabase().delete(
                        MovieContract.MovieEntry.TABLE_NAME,
                        selection,
                        selectionArgs);
                break;
            case CODE_MOVIES_WITH_ID:
                numRowsDeleted = mMovieDbHelper.getWritableDatabase().delete(
                        MovieContract.MovieEntry.TABLE_NAME,
                        MovieContract.MovieEntry._ID + " = ? ",
                        selectionArgs);
                break;
            case CODE_TRAILERS:
                numRowsDeleted = mMovieDbHelper.getWritableDatabase().delete(
                        MovieContract.MovieTrailer.TABLE_NAME,
                        selection,
                        selectionArgs);
                break;
            case CODE_TRAILERS_WITH_ID:
                numRowsDeleted = mMovieDbHelper.getWritableDatabase().delete(
                        MovieContract.MovieTrailer.TABLE_NAME,
                        MovieContract.MovieTrailer.COLUMN_MOVIE_ID + " = ? ",
                        selectionArgs);
                break;
            case CODE_REVIEWS:
                numRowsDeleted = mMovieDbHelper.getWritableDatabase().delete(
                        MovieContract.MovieReview.TABLE_NAME,
                        selection,
                        selectionArgs);
                break;
            case CODE_REVIEWS_WITH_ID:
                numRowsDeleted = mMovieDbHelper.getWritableDatabase().delete(
                        MovieContract.MovieReview.TABLE_NAME,
                        MovieContract.MovieReview.COLUMN_MOVIE_ID + " = ? ",
                        selectionArgs);
                break;
            case CODE_FAVORITES:
                numRowsDeleted = mMovieDbHelper.getWritableDatabase().delete(
                        MovieContract.MovieFavorite.TABLE_NAME,
                        selection,
                        selectionArgs);
                break;
            case CODE_FAVORITES_WITH_ID:
                numRowsDeleted = mMovieDbHelper.getWritableDatabase().delete(
                        MovieContract.MovieFavorite.TABLE_NAME,
                        MovieContract.MovieFavorite.COLUMN_MOVIE_ID + " = ? ",
                        selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        /* If we actually deleted any rows, notify that a change has occurred to this URI */
        if (numRowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return numRowsDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        throw new RuntimeException("We are not implementing update.");
    }
}
>>>>>>> udacity
