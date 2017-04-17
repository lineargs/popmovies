package com.example.goranminov.popmovies;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
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

import com.example.goranminov.popmovies.data.PopularMoviesContract;
import com.example.goranminov.popmovies.sync.MovieSyncUtils;

/*
 * I have followed the examples from the Sunshine app that was provided during my Nanodegree course.
 * I have followed the AsyncTask class the documentation on android.developer Website
 * as well as the AsyncTask class from the Sunshine app.
 */
public class MainActivity extends AppCompatActivity implements MoviesPosterAdapter.MovieAdapterOnClickHandler,
        LoaderManager.LoaderCallbacks<Cursor> {

    private RecyclerView mRecyclerView;
    private MoviesPosterAdapter mMovieAdapter;
    private ProgressBar mLoadingData;
    private static final int MOVIE_DATABASE_LOADER_ID = 29;
    private int mPosition = RecyclerView.NO_POSITION;

    public static final String[] MAIN_PROJECTION = {
            PopularMoviesContract.MovieOverview.COLUMN_POSTER_PATH,
            PopularMoviesContract.MovieOverview.COLUMN_MOVIE_ID
    };
    public static final int INDEX_MOVIE_POSTER_PATH = 0;
    public static final int INDEX_MOVIE_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
         * The MoviesPosterAdapter is responsible to attach our data and display it.
         */
        mMovieAdapter = new MoviesPosterAdapter(this, this);
        mRecyclerView.setAdapter(mMovieAdapter);

        showLoading();

        getSupportLoaderManager().initLoader(MOVIE_DATABASE_LOADER_ID, null, this);

        MovieSyncUtils.startImmediateSync(this);
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

    /**
     * Override the onClick method so we will be able to handle the RecyclerView item clicks.
     *
     * @param id The information for the movie that was clicked.
     */
    @Override
    public void onClick(long id) {
        Intent intent = new Intent(this, DetailActivity.class);
        Uri uri = PopularMoviesContract.MovieOverview.buildMovieUriWithId(id);
        intent.setData(uri);
        startActivity(intent);
    }

    @Override
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
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mMovieAdapter.swapCursor(data);
        if (mPosition == RecyclerView.NO_POSITION) mPosition = 0;
        mRecyclerView.smoothScrollToPosition(mPosition);
        if (data.getCount() != 0) showMovieDataView();
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mMovieAdapter.swapCursor(null);
    }

    private void showMovieDataView() {
        /* First, hide the loading indicator */
        mLoadingData.setVisibility(View.INVISIBLE);
        /* Finally, make sure the weather data is visible */
        mRecyclerView.setVisibility(View.VISIBLE);
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
