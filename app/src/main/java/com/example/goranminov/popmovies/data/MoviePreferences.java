package com.example.goranminov.popmovies.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.goranminov.popmovies.R;

/**
 * Created by goranminov on 11/04/2017.
 */

public class MoviePreferences {

    public static final String DEFAULT_SORT_BY = "popular";

    public static void setSortBy (Context context, String sortBy) {

    }

    public static void resetSortBy (Context context) {

    }

    public static String getPreferredSortBy (Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String keyForSorting = context.getString(R.string.pref_sort_key);
        String defaultSorting = context.getString(R.string.pref_sort_popular_label);
        return sharedPreferences.getString(keyForSorting, defaultSorting);
    }

    public static String getSortBy (Context context) {
        return getDefaultSortBy();
    }

    public static String getDefaultSortBy() {
        return DEFAULT_SORT_BY;
    }
}
