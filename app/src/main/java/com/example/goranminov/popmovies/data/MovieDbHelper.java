package com.example.goranminov.popmovies.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by goranminov on 09/04/2017.
 */

public class MovieDbHelper extends SQLiteOpenHelper {

    /*
     * This is the name of our database. Database names should be descriptive and end with the
     * .db extension.
     */
    public static final String DATABASE_NAME = "movie.db";

<<<<<<< HEAD
    private static final int DATABASE_VERSION = 1;
||||||| merged common ancestors
    private static final int DATABASE_VERSION = 3;
=======
    /*
     * If you change the database schema, you must increment the database version or the onUpgrade
     * method will not be called.
     */
    private static final int DATABASE_VERSION = 8;
>>>>>>> udacity

    public MovieDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Called when the database is created for the first time. This is where the creation of
     * tables and the initial population of the tables should happen.
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
<<<<<<< HEAD
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
||||||| merged common ancestors
        final String SQL_CREATE_MOVIE_TABLE =
                "CREATE TABLE " + MovieContract.MovieEntry.TABLE_NAME + " (" +
                        MovieContract.MovieEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        MovieContract.MovieEntry.COLUMN_MOVIE_ID + " INTEGER NOT NULL, " +
                        MovieContract.MovieEntry.COLUMN_ORIGINAL_TITLE + " TEXT NOT NULL, " +
                        MovieContract.MovieEntry.COLUMN_RELEASE_DATE + " INTEGER NOT NULL, " +
                        MovieContract.MovieEntry.COLUMN_OVERVIEW + " TEXT NOT NULL, " +
                        MovieContract.MovieEntry.COLUMN_VOTE_AVERAGE + " REAL NOT NULL, " +
                        MovieContract.MovieEntry.COLUMN_POSTER_PATH + " TEXT NOT NULL, " +
                        MovieContract.MovieEntry.COLUMN_BACKDROP_PATH + " TEXT NOT NULL, " +
                        "UNIQUE (" + MovieContract.MovieEntry.COLUMN_MOVIE_ID + ") ON CONFLICT REPLACE);";
        db.execSQL(SQL_CREATE_MOVIE_TABLE);
=======

        /*
         * This String will contain a simple SQL statement that will create a table that will
         * cache our movie data.
         */
        final String SQL_CREATE_MOVIE_TABLE =

                "CREATE TABLE " + MovieContract.MovieEntry.TABLE_NAME + " (" +

                        /*
                         * MovieEntry did not explicitly declare a column called "_ID". However,
                         * MovieEntry implements the interface, "BaseColumns", which does have a field
                         * named "_ID". We use that here to designate our table's primary key.
                         */
                        MovieContract.MovieEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +

                        MovieContract.MovieEntry.COLUMN_MOVIE_ID + " INTEGER NOT NULL, " +

                        MovieContract.MovieEntry.COLUMN_ORIGINAL_TITLE + " TEXT NOT NULL, " +

                        MovieContract.MovieEntry.COLUMN_RELEASE_DATE + " TEXT NOT NULL, " +

                        MovieContract.MovieEntry.COLUMN_OVERVIEW + " TEXT NOT NULL, " +

                        MovieContract.MovieEntry.COLUMN_VOTE_AVERAGE + " TEXT NOT NULL, " +

                        MovieContract.MovieEntry.COLUMN_POSTER_PATH + " TEXT NOT NULL, " +

                        MovieContract.MovieEntry.COLUMN_BACKDROP_PATH + " TEXT NOT NULL, " +

                        /*
                         * To ensure this table can only contain one movie entry per date, we declare
                         * the movie_id column to be unique. We also specify "ON CONFLICT REPLACE". This tells
                         * SQLite that if we have a movie entry with a certain movie_id and we attempt to
                         * insert another movie entry with that movie_id, we replace the old movie entry.
                         */
                        "UNIQUE (" + MovieContract.MovieEntry.COLUMN_MOVIE_ID + ") ON CONFLICT REPLACE);";
>>>>>>> udacity

        /*
         * This String will contain a simple SQL statement that will create a table that will
         * cache our trailer data.
         */
        final String SQL_CREATE_TRAILER_TABLE =

                "CREATE TABLE " + MovieContract.MovieTrailer.TABLE_NAME + " (" +

                        /*
                         * MovieTrailer did not explicitly declare a column called "_ID". However,
                         * MovieTrailer implements the interface, "BaseColumns", which does have a field
                         * named "_ID". We use that here to designate our table's primary key.
                         */
                        MovieContract.MovieTrailer._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +

                        /* This movie_id is the same as the movie_id in movie table. We can use this
                         * as a REFERENCE key, but in this case we do not need to complicate our tables
                         */
                        MovieContract.MovieTrailer.COLUMN_MOVIE_ID + " INTEGER NOT NULL, " +

                        MovieContract.MovieTrailer.COLUMN_NAME + " TEXT NOT NULL, " +

                        MovieContract.MovieTrailer.COLUMN_TYPE + " TEXT NOT NULL, " +

                        MovieContract.MovieTrailer.COLUMN_KEY + " TEXT NOT NULL);";

        /*
         * This String will contain a simple SQL statement that will create a table that will
         * cache our review data.
         */
        final String SQL_CREATE_REVIEW_TABLE =

                "CREATE TABLE " + MovieContract.MovieReview.TABLE_NAME + " (" +

                        /*
                         * MovieReview did not explicitly declare a column called "_ID". However,
                         * MovieReview implements the interface, "BaseColumns", which does have a field
                         * named "_ID". We use that here to designate our table's primary key.
                         */
                        MovieContract.MovieReview._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +

                        /* This movie_id is the same as the movie_id in movie table. We can use this
                         * as a REFERENCE key, but in this case we do not need to complicate our tables
                         */
                        MovieContract.MovieReview.COLUMN_MOVIE_ID + " INTEGER NOT NULL, " +

                        MovieContract.MovieReview.COLUMN_AUTHOR + " TEXT NOT NULL, " +

                        MovieContract.MovieReview.COLUMN_CONTENT + " TEXT NOT NULL);";

        /*
         * This String will contain a simple SQL statement that will create a table that will
         * cache our favorite data.
         */
        final String SQL_CREATE_FAVORITE_TABLE =

                "CREATE TABLE " + MovieContract.MovieFavorite.TABLE_NAME + " (" +

                        /*
                         * MovieFavorite did not explicitly declare a column called "_ID". However,
                         * MovieFavorite implements the interface, "BaseColumns", which does have a field
                         * named "_ID". We use that here to designate our table's primary key.
                         */
                        MovieContract.MovieFavorite._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +

                        /* In our favorite table we do not declare the movie_id to be UNIQUE, simply
                         * because we first check if we have that movie_id in the table. If we do we
                         * delete that row, if not we insert.
                         */
                        MovieContract.MovieFavorite.COLUMN_MOVIE_ID + " INTEGER NOT NULL, " +

                        MovieContract.MovieFavorite.COLUMN_ORIGINAL_TITLE + " TEXT NOT NULL, " +

                        MovieContract.MovieFavorite.COLUMN_RELEASE_DATE + " TEXT NOT NULL, " +

                        MovieContract.MovieFavorite.COLUMN_OVERVIEW + " TEXT NOT NULL, " +

                        MovieContract.MovieFavorite.COLUMN_VOTE_AVERAGE + " TEXT NOT NULL, " +

                        MovieContract.MovieFavorite.COLUMN_POSTER_PATH + " TEXT NOT NULL, " +

                        MovieContract.MovieFavorite.COLUMN_BACKDROP_PATH + " TEXT NOT NULL);";

        /*
         * After we've spelled out our SQLite table creation statements above, we actually execute
         * that SQLs with the execSQL method of our SQLite database object.
         */
        db.execSQL(SQL_CREATE_MOVIE_TABLE);
        db.execSQL(SQL_CREATE_TRAILER_TABLE);
        db.execSQL(SQL_CREATE_REVIEW_TABLE);
        db.execSQL(SQL_CREATE_FAVORITE_TABLE);
    }

    /**
     * The movie, trailer and review tables are only a cache for online data, so its upgrade policy is simply to discard
     * the data and call through to onCreate to recreate the table. Note that this only fires if
     * you change the version number for your database (in our case, DATABASE_VERSION). It does NOT
     * depend on the version number for your application found in your app/build.gradle file. If
     * you want to update the schema without wiping data, commenting out the current body of this
     * method should be your top priority before modifying this method.
     *
     * Please note that after the upgrade of our database we can not delete the favorite table
     * as that is cached only locally. If we upgrade the database we need to
     * be careful not to delete that one as it is user favorite's.
     *
     * @param db Database that is being upgraded
     * @param oldVersion     The old database version
     * @param newVersion     The new database version
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
<<<<<<< HEAD
//        db.execSQL("DROP TABLE IF EXISTS " + PopularMoviesContract.MovieOverview.TABLE_NAME);
//        onCreate(db);
||||||| merged common ancestors
        db.execSQL("DROP TABLE IF EXISTS " + MovieContract.MovieEntry.TABLE_NAME);
        onCreate(db);
=======
        db.execSQL("DROP TABLE IF EXISTS " + MovieContract.MovieEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MovieContract.MovieTrailer.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MovieContract.MovieReview.TABLE_NAME);
        onCreate(db);
>>>>>>> udacity
    }
}
