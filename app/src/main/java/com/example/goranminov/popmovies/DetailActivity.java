package com.example.goranminov.popmovies;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.goranminov.popmovies.data.PopularMoviesContract;
import com.example.goranminov.popmovies.utilities.MovieDatabaseJsonUtils;
import com.example.goranminov.popmovies.utilities.NetworkUtils;

import java.net.URL;
import java.util.Arrays;

public class DetailActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private MovieDetailsAdapter movieDetailsAdapter;
    private RecyclerView mRecyclerView;
    private static final int MOVIE_INFO_LOADER_ID = 101;
    private static final int MOVIE_TRAILERS_LOADER_ID = 102;
    private int mPosition = RecyclerView.NO_POSITION;

    public static final String[] MOVIE_DETAIL_PROJECTION = {
            PopularMoviesContract.MovieOverview.COLUMN_ORIGINAL_TITLE,
            PopularMoviesContract.MovieOverview.COLUMN_POSTER_PATH,
            PopularMoviesContract.MovieOverview.COLUMN_OVERVIEW,
            PopularMoviesContract.MovieOverview.COLUMN_RELEASE_DATE,
            PopularMoviesContract.MovieOverview.COLUMN_VOTE_AVERAGE
    };

    public static final int INDEX_MOVIE_ORIGINAL_TITLE = 0;
    public static final int INDEX_MOVIE_POSTER_PATH = 1;
    public static final int INDEX_MOVIE_OVERVIEW = 2;
    public static final int INDEX_MOVIE_RELEASE_DATE = 3;
    public static final int INDEX_MOVIE_VOTE_AVERAGE = 4;

    private Uri mUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        mUri = getIntent().getData();

        if (mUri == null) {
            throw new NullPointerException("URI cannot be null.");
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_movie_details);
        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        movieDetailsAdapter = new MovieDetailsAdapter(this);
        mRecyclerView.setAdapter(movieDetailsAdapter);
        getSupportLoaderManager().initLoader(MOVIE_INFO_LOADER_ID, null, this);
//        getSupportLoaderManager().initLoader(MOVIE_TRAILERS_LOADER_ID, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch (id) {
            case MOVIE_INFO_LOADER_ID:
                return new CursorLoader(this,
                        mUri,
                        MOVIE_DETAIL_PROJECTION,
                        null,
                        null,
                        null);

            default:
                throw new RuntimeException("Loader Not Implemented: " + id);
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        movieDetailsAdapter.swapCursor(data);
        if (mPosition == RecyclerView.NO_POSITION) mPosition = 0;
        mRecyclerView.smoothScrollToPosition(mPosition);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
