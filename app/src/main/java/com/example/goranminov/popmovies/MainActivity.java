package com.example.goranminov.popmovies;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.goranminov.popmovies.utilities.MovieDatabaseJsonUtils;
import com.example.goranminov.popmovies.utilities.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/*
 * I have followed the examples from the Sunshine app that was provided during my Nanodegree course.
 * I have followed the AsyncTask class the documentation on android.developer Website
 * as well as the AsyncTask class from the Sunshine app.
 */
public class MainActivity extends AppCompatActivity implements MovieAdapter.MovieAdapterOnClickHandler {

    private RecyclerView mRecyclerView;
    private MovieAdapter mMovieAdapter;
    private TextView mErrorMessage;
    private ProgressBar mLoadingData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
         * We get the reference to our RecyclerView so we can later attach the adapter.
         */
        mRecyclerView = (RecyclerView) findViewById(R.id.movies_data_recycler_view);

        /*
         * We get the reference to our Error Message TextView so we can display the
         * error message in case there is a problem to retrieve the data from
         * the Movie Database website
         */
        mErrorMessage = (TextView) findViewById(R.id.error_message);

        /*
         * We get the reference to our ProgressBar so we will indicate to the user that we are
          * loading the data.
         */
        mLoadingData = (ProgressBar) findViewById(R.id.loading_data_progress_bar);

        /*
         * We attach GridLayoutManager to our RecyclerView as we need to display our results
         * in Grid style.
         */
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        /*
         * The MovieAdapter is responsible to attach our data and display it.
         */
        mMovieAdapter = new MovieAdapter(this);
        mRecyclerView.setAdapter(mMovieAdapter);

        /*
         * Call our loadPopularMovies method.
         */
        loadPopularMovies();

    }

    /*
     * Method used to get the movies data. The preffered sorting is set to be popular.
     */
    private void loadPopularMovies() {
        showMovieData();
        new GetMovieData().execute("popular");
    }

    /*
     * Method used to get the movies data with the top_rated sorting.
     */
    private void loadTopRatedMovies() {
        showMovieData();
        new GetMovieData().execute("top_rated");
    }

    /*
     * Method used to set the error message as invisible and the RecyclerView
     * as visible.
     */
    private void showMovieData() {
        mErrorMessage.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    /*
     * Method used to set the error message as visible and the RecyclerView
     * as invisible.
     */
    private void showErrorData() {
        mErrorMessage.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.INVISIBLE);
    }

    /**
     * Override the onClick method so we will be able to handle the RecyclerView item clicks.
     *
     * @param selectedMovie The information for the movie that was clicked.
     */
    @Override
    public void onClick(String selectedMovie) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(Intent.EXTRA_TEXT, selectedMovie);
        startActivity(intent);
    }

    /*
     * Class used to perform network requests, extends AsyncTask
     */
    public class GetMovieData extends AsyncTask<String, Void, String[]> {


        /*
         * Set the ProgressBar to be visible to indicate the user that we are
         * loading the data.
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingData.setVisibility(View.VISIBLE);
        }

        @Override
        protected String[] doInBackground(String... params) {


            // If there is no params there is nothing to look up.
            if (params.length == 0) {
                return null;
            }
                /*
                 * Construct the URL for the TheMovieDB query.
                 * Possible parameters are available at TMDB's API page.
                 */
            URL url = NetworkUtils.buildUrl(params[0]);

            try {
                String moviesJsonString = NetworkUtils.getResponseFromHttpUrl(url);
                String[] movieData = MovieDatabaseJsonUtils.getMovieDataFromJson(MainActivity.this,
                        moviesJsonString);
                return movieData;
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
            return null;
        }

        @Override
        protected void onPostExecute(String[] strings) {
            /*
             * Set the ProgressBar to invisible and pass the data to the Adapter.
             */
            mLoadingData.setVisibility(View.INVISIBLE);
            if (strings != null) {
                showMovieData();
                mMovieAdapter.setMovieData(strings);
            } else {
                showErrorData();
            }
        }
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
        /*
         * Clear the data in the Adapter and load it again.
         */
        if (id == R.id.action_popular) {
            mMovieAdapter.setMovieData(null);
            loadPopularMovies();
            return true;
        }

        /*
         * Clear the data in the Adapter and load it again.
         */
        if (id == R.id.action_top_rated) {
            mMovieAdapter.setMovieData(null);
            loadTopRatedMovies();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
