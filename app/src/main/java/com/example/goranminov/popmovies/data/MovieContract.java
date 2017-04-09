package com.example.goranminov.popmovies.data;

import android.provider.BaseColumns;

/**
 * Created by goranminov on 09/04/2017.
 */

public class MovieContract {

    public static final class MovieEntry implements BaseColumns {

        public static final String TABLE_NAME = "movies";

        public static final String COLUMN_POSTER_PATH = "poster_path";

        public static final String COLUMN_ORIGINAL_TITLE = "original_title";

        public static final String COLUMN_OVERVIEW = "overview";

        public static final String COLUMN_VOTE_AVERAGE = "vote_average";

        public static final String COLUMN_RELEASE_DATE = "release_date";

        public static final String COLUMN_MOVIE_ID = "movie_id";

        public static final String COLUMN_BACKDROP_PATH = "backdrop_path";
    }
}
