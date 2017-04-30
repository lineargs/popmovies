package com.example.goranminov.popmovies.utilities;

/**
 * Created by goranminov on 13/04/2017.
 */

/* Contains useful utilities before we use our ContentProvider to insert
 * the data in our database
 */
public final class PopMoviesUtils {

    private static final String MDB_BASE = "http://image.tmdb.org/t/p/w370/";

<<<<<<< HEAD
    public static String getPosterPath(String movieData) {
        return MDB_BASE + movieData;
||||||| merged common ancestors
    public static String posterPath (String movieData) {
        String posterPath = movieData.substring(0, movieData.indexOf("£"));
        return MDB_BASE + posterPath;
=======
    /**
     * This method will return formatted path string used
     * to display the movie posters using Picasso
     * @param movieData The data used
     * @return Formatted path
     */
    public static String posterPath (String movieData) {
        return MDB_BASE + movieData;
>>>>>>> udacity
    }

    /**
     * This method returns the substring of the release date we get from
     * the API. Assuming we always get the date in format YYYY-MM-DD we use
     * substring just to extract the year
     * @param movieData Release date from API
     * @return Formatted string for the year
     */
    public static String getNormalizedReleasedDate (String movieData) {
        //2017-03-16
<<<<<<< HEAD
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
||||||| merged common ancestors
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
=======
        return movieData.substring(0, 4);
    }

    /**
     * This method will return formatted string from the vote average. Assuming we
     * always get the vote average from the API in Double numbers format, we just simply
     * add /10 to show the average.
     * @param movieData Vote Average from API
     * @return Formatted vote average String
     */
    public static String getNormalizedVoteAverage (String movieData) {
        return movieData + "/10";
    }

>>>>>>> udacity
}
