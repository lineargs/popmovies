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
