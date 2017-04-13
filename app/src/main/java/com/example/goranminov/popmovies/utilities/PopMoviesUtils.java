package com.example.goranminov.popmovies.utilities;

/**
 * Created by goranminov on 13/04/2017.
 */

public final class PopMoviesUtils {

    public static final String MDB_BASE = "http://image.tmdb.org/t/p/w185/";

    public static String posterPath (String movieData) {
        String posterPath = movieData.substring(0, movieData.indexOf("£"));
        return MDB_BASE + posterPath;
    }

    public static String getNormalizedReleasedDate (String movieData) {
        //2017-03-16
        String year = movieData.substring(0, 4);
        String month = movieData.substring(5, 7);
        String day = movieData.substring(8, 10);
        return day + "/" + month + "/" + year;
    }

    public static String movieTitle (String movieData) {
        String movieTitle = movieData.substring(movieData.indexOf("!") + 1, movieData.indexOf("@"));
        return movieTitle;
    }

    public static String movieOverview (String movieData) {
        String movieOverview = movieData.substring(movieData.indexOf("@") + 1, movieData.indexOf("#"));
        return movieOverview;
    }
    public static String movieAverageVote (String movieData) {
        String averageVote = movieData.substring(movieData.indexOf("#") + 1, movieData.indexOf("$"));
        return averageVote;
    }

    public static String movieReleasedDate (String movieData) {
        String releaseDate = movieData.substring(movieData.indexOf("$") + 1, movieData.length());
        return releaseDate;
    }
}
