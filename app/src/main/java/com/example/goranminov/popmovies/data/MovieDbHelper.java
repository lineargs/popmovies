package com.example.goranminov.popmovies.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by goranminov on 09/04/2017.
 */

public class MovieDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "movie.db";

    private static final int DATABASE_VERSION = 1;

    public MovieDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_MOVIE_OVERVIEW_TABLE =
                "CREATE TABLE " + PopularMoviesContract.MovieOverview.TABLE_NAME + " (" +
                        PopularMoviesContract.MovieOverview._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        PopularMoviesContract.MovieOverview.COLUMN_MOVIE_ID + " INTEGER NOT NULL, " +
                        PopularMoviesContract.MovieOverview.COLUMN_ORIGINAL_TITLE + " TEXT NOT NULL, " +
                        PopularMoviesContract.MovieOverview.COLUMN_RELEASE_DATE + " TEXT NOT NULL, " +
                        PopularMoviesContract.MovieOverview.COLUMN_OVERVIEW + " TEXT NOT NULL, " +
                        PopularMoviesContract.MovieOverview.COLUMN_VOTE_AVERAGE + " REAL NOT NULL, " +
                        PopularMoviesContract.MovieOverview.COLUMN_POSTER_PATH + " TEXT NOT NULL, " +
                        PopularMoviesContract.MovieOverview.COLUMN_BACKDROP_PATH + " TEXT NOT NULL, " +
                        "UNIQUE (" + PopularMoviesContract.MovieOverview.COLUMN_MOVIE_ID + ") ON CONFLICT REPLACE);";

//        final String SQL_CREATE_MOVIE_TRAILER_TABLE =
//                "CREATE TABLE " + PopularMoviesContract.MovieTrailer.TABLE_NAME + " (" +
//                        PopularMoviesContract.MovieTrailer._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                        PopularMoviesContract.MovieTrailer.COLUMN_MOVIE_ID + " INTEGER NOT NULL, " +
//                        PopularMoviesContract.MovieTrailer.COLUMN_NAME + " TEXT NOT NULL, " +
//                        PopularMoviesContract.MovieTrailer.COLUMN_SOURCE + " TEXT NOT NULL, " +
//                        PopularMoviesContract.MovieTrailer.COLUMN_TYPE + " TEXT NOT NULL, " +
//                        "FOREIGN KEY (" + PopularMoviesContract.MovieTrailer.COLUMN_MOVIE_ID +
//                        ") REFERENCES " + PopularMoviesContract.MovieOverview.COLUMN_MOVIE_ID + ");";

        db.execSQL(SQL_CREATE_MOVIE_OVERVIEW_TABLE);
//        db.execSQL(SQL_CREATE_MOVIE_TRAILER_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS " + PopularMoviesContract.MovieOverview.TABLE_NAME);
//        onCreate(db);
    }
}
