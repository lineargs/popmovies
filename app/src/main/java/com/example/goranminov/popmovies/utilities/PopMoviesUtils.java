package com.example.goranminov.popmovies.utilities;

/**
 * Created by goranminov on 13/04/2017.
 */

public final class PopMoviesUtils {

    public static final String MDB_BASE = "http://image.tmdb.org/t/p/w185/";

    public static String posterPath (String path) {
        String posterPath = path.substring(0, path.indexOf("-"));
        return MDB_BASE + posterPath;
    }

    public static String getNormalizedReleasedDate (String movieDate) {
        //2017-03-16
        String year = movieDate.substring(0, movieDate.indexOf("-"));
        String month = movieDate.substring(movieDate.indexOf("-"),
                movieDate.length() - 1);
        month = movieDate.substring(0, movieDate.indexOf("-"));
        String day = movieDate.substring(movieDate.indexOf("-"),
                movieDate.length() - 1);
        day = movieDate.substring(movieDate.indexOf("-"),
                movieDate.length() - 1);
        return day + "/" + month + "/" + year;
    }
}
