package com.example.goranminov.popmovies.utilities;

/**
 * Created by goranminov on 13/04/2017.
 */

public final class PopMoviesUtils {

    public static final String MDB_BASE = "http://image.tmdb.org/t/p/w185/";

    public static String getPosterPath(String movieData) {
        return MDB_BASE + movieData;
    }

    public static String getNormalizedReleasedDate (String movieData) {
        //2017-03-16
        String year = movieData.substring(0, 4);
        String month = movieData.substring(5, 7);
        String day = movieData.substring(8, 10);
        int monthInt = Integer.parseInt(month);
        switch (monthInt) {
            case 1:
                month = "January";
                break;
            case 2:
                month = "February";
                break;
            case 3:
                month = "March";
                break;
            case 4:
                month = "April";
                break;
            case 5:
                month = "May";
                break;
            case 6:
                month = "June";
                break;
            case 7:
                month = "July";
                break;
            case 8:
                month = "August";
                break;
            case 9:
                month = "September";
                break;
            case 10:
                month = "October";
                break;
            case 11:
                month = "November";
                break;
            case 12:
                month = "December";
                break;
            default:
                throw new IllegalArgumentException("Month needs to be between 01 and 12.");
        }
        return day + " " + month + " " + year;
    }
}
