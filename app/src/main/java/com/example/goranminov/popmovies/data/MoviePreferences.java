package com.example.goranminov.popmovies.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.goranminov.popmovies.R;

/**
 * Created by goranminov on 11/04/2017.
 */

public class MoviePreferences {

<<<<<<< HEAD
    public static final String DEFAULT_SORT_BY = "popular";

    public static void setSortBy (Context context, String sortBy) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String keyForSorting = context.getString(R.string.pref_sort_key);
        editor.putString(keyForSorting, sortBy);
        editor.apply();
    }

    public static void resetSortBy (Context context) {

    }

||||||| merged common ancestors
    public static final String DEFAULT_SORT_BY = "popular";

    public static void setSortBy (Context context, String sortBy) {

    }

    public static void resetSortBy (Context context) {

    }

=======
    /**
     * Returns the sort currently set in Preferences. The default sorting this method
     * will return is "popular".
     *
     * @param context Context used to access SharedPreferences
     * @return Sort The current user has set in SharedPreferences. Will default to
     * "popular" if SharedPreferences have not been implemented yet.
     */
>>>>>>> udacity
    public static String getPreferredSortBy (Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String keyForSorting = context.getString(R.string.pref_sort_key);
        String defaultSorting = context.getString(R.string.pref_sort_popular_key);
        return sharedPreferences.getString(keyForSorting, defaultSorting);
    }
}
