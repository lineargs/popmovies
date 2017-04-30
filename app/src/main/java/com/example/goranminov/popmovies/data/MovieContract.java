package com.example.goranminov.popmovies.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by goranminov on 09/04/2017.
 */

/**
 * Defines table and column names for the movie database.
 */
public class MovieContract {

    /*
     * The "Content authority" is a name for the entire content provider, similar to the
     * relationship between a domain name and its website. A convenient string to use for the
     * content authority is the package name for the app, which is guaranteed to be unique on the
     * Play Store.
     */
    public static final String CONTENT_AUTHORITY = "com.example.goranminov.popmovies";

    /*
     * Use CONTENT_AUTHORITY to create the base of all URI's which apps will use to contact
     * the content provider for PopMovies.
     */
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    /*
     * Possible paths that can be appended to BASE_CONTENT_URI to form valid URI's that PopMovies
     * can handle. For instance,
     *
     *     content://com.example.goranminov.popmovies/movie/
     *     [           BASE_CONTENT_URI             ][ PATH_MOVIE ]
     *
     * is a valid path for looking at movie data.
     *
     *      content://com.example.goranminov.popmovies/givemeroot/
     *
     * will fail, as the ContentProvider hasn't been given any information on what to do with
     * "givemeroot".
     */
    public static final String PATH_MOVIE = "movie";

    /*
     * Possible paths that can be appended to BASE_CONTENT_URI to form valid URI's that PopMovies
     * can handle. For instance,
     *
     *     content://com.example.goranminov.popmovies/trailer/
     *     [           BASE_CONTENT_URI             ][ PATH_TRAILER ]
     *
     * is a valid path for looking at trailer data.
     */
    public static final String PATH_TRAILER = "trailer";

    /*
     * Possible paths that can be appended to BASE_CONTENT_URI to form valid URI's that PopMovies
     * can handle. For instance,
     *
     *     content://com.example.goranminov.popmovies/review/
     *     [           BASE_CONTENT_URI             ][ PATH_REVIEW ]
     *
     * is a valid path for looking at review data.
     */
    public static final String PATH_REVIEW = "review";

    /*
     * Possible paths that can be appended to BASE_CONTENT_URI to form valid URI's that PopMovies
     * can handle. For instance,
     *
     *     content://com.example.goranminov.popmovies/favorite/
     *     [           BASE_CONTENT_URI             ][ PATH_FAVORITE ]
     *
     * is a valid path for looking at trailer data.
     */
    public static final String PATH_FAVORITE = "favorite";

    /* Inner class that defines the table contents of the movie table */
    public static final class MovieEntry implements BaseColumns {

        /* The base CONTENT_URI used to query the movies table from the content provider */
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(PATH_MOVIE)
                .build();
        /* Used internally as the name of our movies table. */
        public static final String TABLE_NAME = "movies";

        /* The poster path as returned by API*/
        public static final String COLUMN_POSTER_PATH = "poster_path";

        /* The original title as returned by API*/
        public static final String COLUMN_ORIGINAL_TITLE = "original_title";

        /* The overview as returned by API*/
        public static final String COLUMN_OVERVIEW = "overview";

        /* The backdrop path as returned by API*/
        public static final String COLUMN_BACKDROP_PATH = "backdrop_path";

        /* The vote average as returned by API*/
        public static final String COLUMN_VOTE_AVERAGE = "vote_average";

        /* The release date as returned by API*/
        public static final String COLUMN_RELEASE_DATE = "release_date";

        /* The movie ID as returned by API*/
        public static final String COLUMN_MOVIE_ID = "movie_id";

        /**
         * Builds a URI that adds the movie id to the end of the movie content URI path.
         * This is used to query details about a single movie entry by id. This is what we
         * use for the detail view query.
         *
         * @param id Movie ID
         * @return Uri to query details about a single movie entry
         */
        public static Uri buildMovieUriWithId (long id) {
            return CONTENT_URI.buildUpon()
                    .appendPath(Long.toString(id))
                    .build();
        }
    }

    /* Inner class that defines the table contents of the trailer table */
    public static final class MovieTrailer implements BaseColumns {

        /* The base CONTENT_URI used to query the trailers table from the content provider */
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(PATH_TRAILER)
                .build();

        /* Used internally as the name of our trailers table. */
        public static final String TABLE_NAME = "trailers";

        /* The movie ID as returned by API*/
        public static final String COLUMN_MOVIE_ID = "movie_id";

        /* The trailer name as returned by API*/
        public static final String COLUMN_NAME = "name";

        /* The trailer key as returned by API*/
        public static final String COLUMN_KEY = "key";

        /* The trailer type as returned by API*/
        public static final String COLUMN_TYPE = "type";

        /**
         * Builds a URI that adds the movie id to the end of the trailer content URI path.
         * This is used to query details about a single trailer entry by movie id. This is what we
         * use for the detail view query.
         *
         * @param id Movie ID
         * @return Uri to query details about a single trailer entry
         */
        public static Uri buildTrailerUriWithId (long id) {
            return CONTENT_URI.buildUpon()
                    .appendPath(Long.toString(id))
                    .build();
        }

        /**
         * Returns the movie ID as a String from the Uri.
         * This URI would look something like content://com.example.goranminov.popmovies/trailer/123567
         * @param uri trailer content URI path followed by any number
         * @return String
         */
        public static String getMovieIdFromUri (Uri uri) {
            return uri.getPathSegments().get(1);
        }
    }

    /* Inner class that defines the table contents of the review table */
    public static final class MovieReview implements BaseColumns {

        /* The base CONTENT_URI used to query the reviews table from the content provider */
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(PATH_REVIEW)
                .build();

        /* Used internally as the name of our reviews table. */
        public static final String TABLE_NAME = "reviews";

        /* The movie ID as returned by API*/
        public static final String COLUMN_MOVIE_ID = "movie_id";

        /* The review author as returned by API*/
        public static final String COLUMN_AUTHOR = "author";

        /* The review content as returned by API*/
        public static final String COLUMN_CONTENT = "content";

        /**
         * Builds a URI that adds the movie id to the end of the review content URI path.
         * This is used to query details about a single review entry by movie id. This is what we
         * use for the detail view query.
         *
         * @param id Movie ID
         * @return Uri to query details about a single review entry
         */
        public static Uri buildReviewUriWithId (long id) {
            return CONTENT_URI.buildUpon()
                    .appendPath(Long.toString(id))
                    .build();
        }

        /**
         * Returns the movie ID as a String from the Uri.
         * This URI would look something like content://com.example.goranminov.popmovies/review/123567
         * @param uri review content URI path followed by any number
         * @return String
         */
        public static String getMovieIdFromUri (Uri uri) {
            return uri.getPathSegments().get(1);
        }
    }

    /* Inner class that defines the table contents of the favorite table */
    public static final class MovieFavorite implements BaseColumns {

        /* The base CONTENT_URI used to query the favorites table from the content provider */
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(PATH_FAVORITE)
                .build();

        /* Used internally as the name of our favorites table. */
        public static final String TABLE_NAME = "favorites";

        /* The poster path as queried by movies table*/
        public static final String COLUMN_POSTER_PATH = "poster_path";

        /* The original title as queried by movies table*/
        public static final String COLUMN_ORIGINAL_TITLE = "original_title";

        /* The overview as queried by movies table*/
        public static final String COLUMN_OVERVIEW = "overview";

        /* The backdrop path as queried by movies table*/
        public static final String COLUMN_BACKDROP_PATH = "backdrop_path";

        /* The vote average as queried by movies table*/
        public static final String COLUMN_VOTE_AVERAGE = "vote_average";

        /* The release date as queried by movies table*/
        public static final String COLUMN_RELEASE_DATE = "release_date";

        /* The movie ID as queried by movies table*/
        public static final String COLUMN_MOVIE_ID = "movie_id";

        /**
         * Builds a URI that adds the movie id to the end of the favorite content URI path.
         * This is used to query details about a single favorite entry by movie id. This is what we
         * use for the detail view query.
         *
         * @param id Movie ID
         * @return Uri to query details about a single favorite entry
         */
        public static Uri buildFavoriteUriWithId (long id) {
            return CONTENT_URI.buildUpon()
                    .appendPath(Long.toString(id))
                    .build();
        }

        /**
         * Returns the movie ID as a String from the Uri.
         * This URI would look something like content://com.example.goranminov.popmovies/favorite/123567
         * @param uri favorite content URI path followed by any number
         * @return String
         */
        public static String getMovieIdFromUri (Uri uri) {
            return uri.getPathSegments().get(1);
        }
    }
}
