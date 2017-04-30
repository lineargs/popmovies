package com.example.goranminov.popmovies;

import android.content.Intent;
<<<<<<< HEAD
import android.database.Cursor;
import android.net.Uri;
||||||| merged common ancestors
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
=======
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.net.Uri;
import android.preference.PreferenceManager;
>>>>>>> udacity
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

<<<<<<< HEAD
import com.example.goranminov.popmovies.data.PopularMoviesContract;
import com.example.goranminov.popmovies.sync.MovieSyncUtils;
||||||| merged common ancestors
import com.example.goranminov.popmovies.data.MoviePreferences;
import com.example.goranminov.popmovies.utilities.MovieDatabaseJsonUtils;
import com.example.goranminov.popmovies.utilities.NetworkUtils;

import java.net.URL;
=======
import com.example.goranminov.popmovies.data.MovieContract;
import com.example.goranminov.popmovies.syncMovie.MovieSyncUtils;
>>>>>>> udacity

/*
 * I have followed the examples from the Sunshine app that was provided during my Nanodegree course.
 * I have followed the AsyncTask class the documentation on android.developer Website
 * as well as the AsyncTask class from the Sunshine app.
 */
<<<<<<< HEAD
public class MainActivity extends AppCompatActivity implements MoviesPosterAdapter.MovieAdapterOnClickHandler,
        LoaderManager.LoaderCallbacks<Cursor> {
||||||| merged common ancestors
public class MainActivity extends AppCompatActivity implements MoviesPosterAdapter.MovieAdapterOnClickHandler,
        LoaderManager.LoaderCallbacks<String[]>,
        SharedPreferences.OnSharedPreferenceChangeListener {
=======
public class MainActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor> {
>>>>>>> udacity

    private RecyclerView mRecyclerView;
    private MainAdapter mMovieAdapter;
    private ProgressBar mLoadingData;
<<<<<<< HEAD
    private static final int MOVIE_DATABASE_LOADER_ID = 29;
    private int mPosition = RecyclerView.NO_POSITION;

    public static final String[] MAIN_PROJECTION = {
            PopularMoviesContract.MovieOverview.COLUMN_POSTER_PATH,
            PopularMoviesContract.MovieOverview.COLUMN_MOVIE_ID
    };
    public static final int INDEX_MOVIE_POSTER_PATH = 0;
    public static final int INDEX_MOVIE_ID = 1;
||||||| merged common ancestors
    private static final int MOVIE_DATABASE_LOADER_ID = 29;
    private static boolean PREFERENCE_HAVE_BEEN_UPDATED = false;
=======
    private static final int MOVIE_LOADER_ID = 29;
    private static final int MOVIE_FAVORITE_LOADER_ID = 33;
    private int mPosition = RecyclerView.NO_POSITION;
    private SharedPreferences sharedPreferences;

    public static final String[] MAIN_PROJECTION = {
            MovieContract.MovieEntry.COLUMN_POSTER_PATH,
            MovieContract.MovieEntry.COLUMN_MOVIE_ID,
    };

    public static final int INDEX_MOVIE_POSTER_PATH = 0;
    public static final int INDEX_MOVIE_ID = 1;

>>>>>>> udacity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setElevation(0f);
        /*
         * We get the reference to our RecyclerView so we can later attach the adapter.
         */
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_movies_poster);

        /*
         * We get the reference to our ProgressBar so we will indicate to the user that we are
          * loading the data.
         */
        mLoadingData = (ProgressBar) findViewById(R.id.loading_data_progress_bar);

        /*
         * We attach GridLayoutManager to our RecyclerView as we need to display our results
         * in Grid style.
         */
        GridLayoutManager layoutManager = new GridLayoutManager(this, numberOfColumns());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        /*
         * The MainAdapter is responsible to attach our data and display it.
         */
        mMovieAdapter = new MainAdapter(this);
        mRecyclerView.setAdapter(mMovieAdapter);

<<<<<<< HEAD
        showLoading();

        getSupportLoaderManager().initLoader(MOVIE_DATABASE_LOADER_ID, null, this);
||||||| merged common ancestors
        LoaderManager.LoaderCallbacks<String[]> callbacks = MainActivity.this;
        Bundle bundle = null;
        getSupportLoaderManager().initLoader(MOVIE_DATABASE_LOADER_ID, bundle, callbacks);
=======
        showLoading();

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
>>>>>>> udacity

<<<<<<< HEAD
        MovieSyncUtils.initialize(this);
||||||| merged common ancestors
        PreferenceManager.getDefaultSharedPreferences(this)
                .registerOnSharedPreferenceChangeListener(this);
=======
        getSupportLoaderManager().initLoader(MOVIE_LOADER_ID, null, this);

        getSupportLoaderManager().initLoader(MOVIE_FAVORITE_LOADER_ID, null, this);

        MovieSyncUtils.startImmediateSync(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (sharedPreferences.getString(getString(R.string.pref_sort_key), "").contains(getString(R.string.pref_sort_favorites_key))) {
            getSupportLoaderManager().restartLoader(MOVIE_FAVORITE_LOADER_ID, null, this);
        } else {
            MovieSyncUtils.startImmediateSync(this);
        }
>>>>>>> udacity
    }

    private int numberOfColumns() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int widthDivider = 600;
        int width = displayMetrics.widthPixels;
        int nColumns = width / widthDivider;
        if (nColumns < 2) {
            return 2;
        }
        return nColumns;
    }

<<<<<<< HEAD
    /**
     * Override the onClick method so we will be able to handle the RecyclerView item clicks.
     *
     * @param id The information for the movie that was clicked.
     */
||||||| merged common ancestors
    private boolean isOnline() {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }

    /*
     * Method used to set the error message as visible and the RecyclerView
     * as invisible.
     */
    private void showErrorData() {
        Toast.makeText(MainActivity.this, R.string.error_message_display, Toast.LENGTH_LONG).show();
    }

    private void notOnlineData() {
        Toast.makeText(MainActivity.this, R.string.not_online_error_message_display, Toast.LENGTH_LONG).show();
    }

    /**
     * Override the onClick method so we will be able to handle the RecyclerView item clicks.
     *
     * @param selectedMovie The information for the movie that was clicked.
     */
=======
>>>>>>> udacity
    @Override
<<<<<<< HEAD
    public void onClick(long id) {
        Intent intent = new Intent(this, DetailActivity.class);
        Uri uri = PopularMoviesContract.MovieOverview.buildMovieUriWithId(id);
        intent.setData(uri);
        startActivity(intent);
||||||| merged common ancestors
    public void onClick(String selectedMovie) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(Intent.EXTRA_TEXT, selectedMovie);
        startActivity(intent);
=======
    public Loader<Cursor> onCreateLoader(int loaderId, Bundle args) {

        switch (loaderId) {
            case MOVIE_LOADER_ID:
                return new CursorLoader(this,
                        MovieContract.MovieEntry.CONTENT_URI,
                        MAIN_PROJECTION,
                        null,
                        null,
                        null);
            case MOVIE_FAVORITE_LOADER_ID:
                return new CursorLoader(this,
                        MovieContract.MovieFavorite.CONTENT_URI,
                        MAIN_PROJECTION,
                        null,
                        null,
                        null);
            default:
                throw new RuntimeException("Loader not Implemented: " + loaderId);
        }
>>>>>>> udacity
    }

    @Override
<<<<<<< HEAD
    public Loader<Cursor> onCreateLoader(int loaderId, Bundle bundle) {

        switch (loaderId) {
            case MOVIE_DATABASE_LOADER_ID:
                Uri movieQueryUri = PopularMoviesContract.MovieOverview.CONTENT_URI;

                return new CursorLoader(this,
                        movieQueryUri,
                        MAIN_PROJECTION,
                        null,
                        null,
                        null);
            default:
                throw new RuntimeException("Loader not implemented: " + loaderId);
||||||| merged common ancestors
    public Loader<String[]> onCreateLoader(int id, final Bundle args) {

        return new AsyncTaskLoader<String[]>(this) {

            String[] mMovieData = null;

            @Override
            protected void onStartLoading() {
                if (mMovieData != null) {
                    deliverResult(mMovieData);
                } else if (isOnline()) {
                    mLoadingData.setVisibility(View.VISIBLE);
                    forceLoad();
                } else {
                    notOnlineData();
                }
            }

            @Override
            public String[] loadInBackground() {
                String sortingQuery = MoviePreferences.getPreferredSortBy(MainActivity.this);
                URL url = NetworkUtils.buildMainUrl(sortingQuery);
                try {
                    String moviesJsonString = NetworkUtils.getMainResponseFromHttpUrl(url);
                    String[] movieData = MovieDatabaseJsonUtils.getMovieIdFromJson(MainActivity.this,
                            moviesJsonString);
                    return movieData;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }

            public void deliverResult(String[] data) {
                mMovieData = data;
                super.deliverResult(data);
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<String[]> loader, String[] data) {
        mLoadingData.setVisibility(View.INVISIBLE);
        mMovieAdapter.setMoviesData(data);
        if (data == null) {
            showErrorData();
            mMovieAdapter.setMoviesData(data);
=======
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        switch (loader.getId()) {
            case MOVIE_LOADER_ID:
                mMovieAdapter.swapCursor(data);
                if (data != null && data.getCount() != 0) {
                    data.moveToFirst();
                }
                if (data.getCount() != 0) showMovieDataView();
                break;
            case MOVIE_FAVORITE_LOADER_ID:
                mMovieAdapter.swapCursor(data);
                if (data != null && data.getCount() != 0) {
                    data.moveToFirst();
                }
                if (data.getCount() != 0) showMovieDataView();
                break;

            default:
                throw new RuntimeException("Loader not Implemented: " + loader.getId());
>>>>>>> udacity
        }
    }

    @Override
<<<<<<< HEAD
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mMovieAdapter.swapCursor(data);
        if (mPosition == RecyclerView.NO_POSITION) mPosition = 0;
        mRecyclerView.smoothScrollToPosition(mPosition);
        if (data.getCount() != 0) showMovieDataView();
||||||| merged common ancestors
    public void onLoaderReset(Loader<String[]> loader) {

=======
    public void onLoaderReset(Loader<Cursor> loader) {
        mMovieAdapter.swapCursor(null);
>>>>>>> udacity
    }

<<<<<<< HEAD
    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mMovieAdapter.swapCursor(null);
    }
||||||| merged common ancestors
    @Override
    protected void onStart() {
        super.onStart();
        if (PREFERENCE_HAVE_BEEN_UPDATED) {
            getSupportLoaderManager().restartLoader(MOVIE_DATABASE_LOADER_ID, null, this);
            PREFERENCE_HAVE_BEEN_UPDATED = false;
        }
    }
=======
>>>>>>> udacity

<<<<<<< HEAD
    private void showMovieDataView() {
        /* First, hide the loading indicator */
        mLoadingData.setVisibility(View.INVISIBLE);
        /* Finally, make sure the weather data is visible */
        mRecyclerView.setVisibility(View.VISIBLE);
||||||| merged common ancestors
    @Override
    protected void onDestroy() {
        super.onDestroy();
        PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this);
=======
    /*
     * Method used to set the error message as visible and the RecyclerView
     * as invisible.
     */
    private void showMovieDataView() {
        /* First, hide the loading indicator */
        mLoadingData.setVisibility(View.INVISIBLE);
        /* Finally, make sure the weather data is visible */
        mRecyclerView.setVisibility(View.VISIBLE);
>>>>>>> udacity
    }

    private void showLoading() {
        /* Then, hide the weather data */
        mRecyclerView.setVisibility(View.INVISIBLE);
        /* Finally, show the loading indicator */
        mLoadingData.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        /*
         * We inflate our menu layout to this menu and display it in the Toolbar.
         */
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
