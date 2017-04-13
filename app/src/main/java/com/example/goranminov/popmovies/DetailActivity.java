package com.example.goranminov.popmovies;

import android.content.Intent;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.goranminov.popmovies.utilities.MovieDatabaseJsonUtils;
import com.example.goranminov.popmovies.utilities.NetworkUtils;

import java.net.URL;

public class DetailActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String[]>{

    //String used to get the data from the intent.
    private String mMovieDetails;
    private String movieIdFromIntent;
    private MovieDetailsAdapter movieDetailsAdapter;
    private RecyclerView mRecyclerView;
    private static final int MOVIE_INFO_LOADER_ID = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        Intent intent = getIntent();
        //If there is intent
        if (intent != null) {

            //And we passed the data
            if (intent.hasExtra(Intent.EXTRA_TEXT)) {

                //We put the passed data into the created String
                mMovieDetails = intent.getStringExtra(Intent.EXTRA_TEXT);
                movieIdFromIntent = mMovieDetails.substring(mMovieDetails.indexOf("£") + 1,
                        mMovieDetails.length());
            }

            mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_movie_details);
            LinearLayoutManager linearLayoutManager =
                    new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            mRecyclerView.setLayoutManager(linearLayoutManager);
            mRecyclerView.setHasFixedSize(true);
            movieDetailsAdapter = new MovieDetailsAdapter(this);
            mRecyclerView.setAdapter(movieDetailsAdapter);
            LoaderManager.LoaderCallbacks<String[]> callbacks = DetailActivity.this;
            Bundle bundle = null;
            getSupportLoaderManager().initLoader(MOVIE_INFO_LOADER_ID, bundle, callbacks);
        }
    }

    @Override
    public Loader<String[]> onCreateLoader(int id, final Bundle args) {
        if (id == MOVIE_INFO_LOADER_ID) {
            return new AsyncTaskLoader<String[]>(this) {

                String[] mMovieData = null;

                @Override
                protected void onStartLoading() {
                    if (mMovieData != null) {
                        deliverResult(mMovieData);
                    } else {
                        forceLoad();
                    }
                }

                @Override
                public String[] loadInBackground() {
                    String movieId = movieIdFromIntent;
                    URL url = NetworkUtils.buildDetailUrl(movieId);
                    try {
                        String movieString = NetworkUtils.getMainResponseFromHttpUrl(url);
                        String[] movieData = MovieDatabaseJsonUtils.getMovieDetailFromJson(DetailActivity.this,
                                movieString);
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
        } else {
            return null;
        }
    }

    @Override
    public void onLoadFinished(Loader<String[]> loader, String[] data) {
        if (loader.getId() == MOVIE_INFO_LOADER_ID) {
            movieDetailsAdapter.setMovieData(data);
        }else if (data == null && loader.getId() == MOVIE_INFO_LOADER_ID) {
            Toast.makeText(DetailActivity.this, R.string.error_message_display, Toast.LENGTH_LONG).show();
            movieDetailsAdapter.setMovieData(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<String[]> loader) {

    }
}
