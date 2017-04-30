package com.example.goranminov.popmovies;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
<<<<<<< HEAD
import android.database.Cursor;
import android.net.Uri;
||||||| merged common ancestors
import android.databinding.DataBindingUtil;
=======
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
>>>>>>> udacity
import android.support.v4.app.LoaderManager;
<<<<<<< HEAD
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.CursorLoader;
||||||| merged common ancestors
import android.support.v4.content.AsyncTaskLoader;
=======
import android.support.v4.app.ShareCompat;
import android.support.v4.content.CursorLoader;
>>>>>>> udacity
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
<<<<<<< HEAD
||||||| merged common ancestors
import android.widget.ImageView;
import android.widget.TextView;
=======
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
>>>>>>> udacity
import android.widget.Toast;

<<<<<<< HEAD
import com.example.goranminov.popmovies.data.PopularMoviesContract;
||||||| merged common ancestors
import com.example.goranminov.popmovies.databinding.ActivityDetailBinding;
=======
import com.example.goranminov.popmovies.data.MovieContract;
import com.example.goranminov.popmovies.syncTrailerReview.TrailerSyncUtils;
>>>>>>> udacity
import com.example.goranminov.popmovies.utilities.MovieDatabaseJsonUtils;
<<<<<<< HEAD
import com.example.goranminov.popmovies.utilities.NetworkUtils;
||||||| merged common ancestors
import com.example.goranminov.popmovies.utilities.NetworkUtils;
import com.squareup.picasso.Picasso;
=======
import com.example.goranminov.popmovies.utilities.PopMoviesUtils;
import com.squareup.picasso.Picasso;
>>>>>>> udacity

<<<<<<< HEAD
import java.net.URL;
import java.util.Arrays;
||||||| merged common ancestors
import java.net.URL;
=======
import org.json.JSONObject;
>>>>>>> udacity

<<<<<<< HEAD
public class DetailActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
||||||| merged common ancestors
import butterknife.BindView;
import butterknife.ButterKnife;
=======
public class DetailActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor> {
>>>>>>> udacity

<<<<<<< HEAD
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
||||||| merged common ancestors
public class DetailActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String[]>{

    //String used to get the data from the intent.
    private String mMovieDetails;
    private String movieIdFromIntent;
    private MovieDetailsAdapter movieDetailsAdapter;
    private RecyclerView mRecyclerView;
    private static final int LOADER_ID = 101;
=======

    private static final String MOVIE_SHARE_HASHTAG = " #PopMoviesApp";

    private DetailsAdapter movieDetailsAdapter;
    private RecyclerView mOverviewRecyclerView;

    /*
     * This IDs will be used to identify the Loader responsible for loading the movie details
     * for a particular movie ID.
     */
    private static final int OVERVIEW_LOADER_ID = 101;
    private static final int TRAILER_LOADER_ID = 102;
    private static final int REVIEW_LOADER_ID = 103;

    /*
     * The columns of data that we are interested in displaying within our DetailActivity's
     * movie display.
     */
    public static final String[] DETAIL_OVERVIEW_PROJECTION = {
            MovieContract.MovieEntry.COLUMN_ORIGINAL_TITLE,
            MovieContract.MovieEntry.COLUMN_POSTER_PATH,
            MovieContract.MovieEntry.COLUMN_RELEASE_DATE,
            MovieContract.MovieEntry.COLUMN_VOTE_AVERAGE,
            MovieContract.MovieEntry.COLUMN_OVERVIEW,
    };

    /*
     * We store the indices of the values in the array of Strings above to more quickly be able
     * to access the data from our query. If the order of the Strings above changes, these
     * indices must be adjusted to match the order of the Strings.
     */
    public static final int INDEX_MOVIE_ORIGINAL_TITLE = 0;
    public static final int INDEX_MOVIE_POSTER_PATH = 1;
    public static final int INDEX_MOVIE_RELEASE_DATE = 2;
    public static final int INDEX_MOVIE_VOTE_AVERAGE = 3;
    public static final int INDEX_MOVIE_OVERVIEW = 4;

    /*
     * The columns of data that we are interested in displaying within our DetailActivity's
     * movie display.
     */
    public static final String[] DETAIL_TRAILER_PROJECTION = {
            MovieContract.MovieTrailer.COLUMN_KEY
    };

    /*
     * We store the indices of the values in the array of Strings above to more quickly be able
     * to access the data from our query. If the order of the Strings above changes, these
     * indices must be adjusted to match the order of the Strings.
     */
    public static final int INDEX_TRAILER_KEY = 0;

    /*
     * The columns of data that we are interested in displaying within our DetailActivity's
     * movie display.
     */
    public static final String[] DETAIL_REVIEW_PROJECTION = {
            MovieContract.MovieReview.COLUMN_AUTHOR,
            MovieContract.MovieReview.COLUMN_CONTENT
    };

    /*
     * We store the indices of the values in the array of Strings above to more quickly be able
     * to access the data from our query. If the order of the Strings above changes, these
     * indices must be adjusted to match the order of the Strings.
     */
    public static final int INDEX_REVIEW_AUTHOR = 0;
    public static final int INDEX_REVIEW_CONTENT = 1;

    /* The URI that is used to access the chosen movie ID details */
    private Uri mMovieUri;

>>>>>>> udacity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
<<<<<<< HEAD
        setContentView(R.layout.activity_movie);
        mUri = getIntent().getData();

        if (mUri == null) {
            throw new NullPointerException("URI cannot be null.");
||||||| merged common ancestors
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
            getSupportLoaderManager().initLoader(LOADER_ID, bundle, callbacks);
=======
        setContentView(R.layout.activity_detail);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mMovieUri = getIntent().getData();


        if (mMovieUri == null) {
            throw new NullPointerException("URI cannot be null");
        }

        /*
         * Using findViewById, we get a reference to our RecyclerView from xml. This allows us to
         * do things like set the adapter of the RecyclerView and toggle the visibility.
         */
        mOverviewRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_movie_details);

        /*
         * A LinearLayoutManager is responsible for measuring and positioning item views within a
         * RecyclerView into a linear list. This means that it can produce either a horizontal or
         * vertical list depending on which parameter you pass in to the LinearLayoutManager
         * constructor. In our case, we want a vertical list, so we pass in the constant from the
         * LinearLayoutManager class for vertical lists, LinearLayoutManager.VERTICAL.
         *
         * There are other LayoutManagers available to display your data in uniform grids,
         * staggered grids, and more! See the developer documentation for more details.
         *
         * The third parameter (shouldReverseLayout) should be true if you want to reverse your
         * layout. Generally, this is only true with horizontal lists that need to support a
         * right-to-left layout.
         */
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        layoutManager.setAutoMeasureEnabled(true);

        /* setLayoutManager associates the LayoutManager we created above with our RecyclerView */
        mOverviewRecyclerView.setLayoutManager(layoutManager);

        /*
         * Use this setting to improve performance if you know that changes in content do not
         * change the child layout size in the RecyclerView
         */
        mOverviewRecyclerView.setHasFixedSize(true);

        /*
         * The DetailsAdapter is responsible for linking our movie data with the Views that
         * will end up displaying our movie data.
         *
         */
        movieDetailsAdapter = new DetailsAdapter(this);

        /* Setting the adapter attaches it to the RecyclerView in our layout. */
        mOverviewRecyclerView.setAdapter(movieDetailsAdapter);

        /*
         * Ensures a loader is initialized and active. If the loader doesn't already exist, one is
         * created and (if the activity/fragment is currently started) starts the loader. Otherwise
         * the last created loader is re-used.
         */
        getSupportLoaderManager().initLoader(OVERVIEW_LOADER_ID, null, this);
        getSupportLoaderManager().initLoader(TRAILER_LOADER_ID, null, this);
        getSupportLoaderManager().initLoader(REVIEW_LOADER_ID, null, this);

        TrailerSyncUtils.startImmediateSync(this, mMovieUri);
    }

    /**
     * Called by the {@link android.support.v4.app.LoaderManagerImpl} when a new Loader needs to be
     * created.
     *
     * @param id The loader ID for which we need to create a loader
     * @param args   Any arguments supplied by the caller
     * @return A new Loader instance that is ready to start loading.
     */
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch (id) {
            case OVERVIEW_LOADER_ID:
                return new CursorLoader(this,

                        /* URI for all rows of movie data in our movie table */
                        mMovieUri,
                        DETAIL_OVERVIEW_PROJECTION,
                        null,
                        null,
                        null);
            case TRAILER_LOADER_ID:

                /* URI for all rows of trailer data in our trailer table */
                Uri trailerUri = MovieContract.MovieTrailer.buildTrailerUriWithId(Long.parseLong(mMovieUri.getLastPathSegment()));
                return new CursorLoader(this,
                        trailerUri,
                        DETAIL_TRAILER_PROJECTION,
                        null,
                        null,
                        null);
            case REVIEW_LOADER_ID:

                /* URI for all rows of review data in our review table */
                Uri reviewUri = MovieContract.MovieReview.buildReviewUriWithId(Long.parseLong(mMovieUri.getLastPathSegment()));
                return new CursorLoader(this,
                        reviewUri,
                        DETAIL_REVIEW_PROJECTION,
                        null,
                        null,
                        null);
            default:
                throw new RuntimeException("Loader Not Implemented: " + id);

>>>>>>> udacity
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

    /**
     * Called when a Loader has finished loading its data.
     *
     * @param loader The Loader that has finished.
     * @param data   The data generated by the Loader.
     */
    @Override
<<<<<<< HEAD
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
||||||| merged common ancestors
    public Loader<String[]> onCreateLoader(int id, final Bundle args) {
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
=======
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        int idLoader = loader.getId();
        switch (idLoader) {
            case OVERVIEW_LOADER_ID:
                movieDetailsAdapter.swapOverviewCursor(data);
                break;
            case TRAILER_LOADER_ID:
                movieDetailsAdapter.swapTrailerCursor(data);
                break;
            case REVIEW_LOADER_ID:
                movieDetailsAdapter.swapReviewCursor(data);
                break;
            default:
                throw new RuntimeException("Loader not implemented: " + idLoader);

        }
>>>>>>> udacity
    }

    /**
     * Called when a previously created loader is being reset, and thus making its data unavailable.
     * The application should at this point remove any references it has to the Loader's data.
     *
     * @param loader The Loader that is being reset.
     */
    @Override
<<<<<<< HEAD
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        movieDetailsAdapter.swapCursor(data);
        if (mPosition == RecyclerView.NO_POSITION) mPosition = 0;
        mRecyclerView.smoothScrollToPosition(mPosition);
||||||| merged common ancestors
    public void onLoadFinished(Loader<String[]> loader, String[] data) {
        movieDetailsAdapter.setMovieData(data);
        if (data == null) {
            Toast.makeText(DetailActivity.this, R.string.error_message_display, Toast.LENGTH_LONG).show();
            movieDetailsAdapter.setMovieData(data);
        }
=======
    public void onLoaderReset(Loader<Cursor> loader) {
        int id = loader.getId();
        switch (id) {
            case OVERVIEW_LOADER_ID:
                movieDetailsAdapter.swapOverviewCursor(null);
                break;
            case TRAILER_LOADER_ID:
                movieDetailsAdapter.swapTrailerCursor(null);
                break;
            case REVIEW_LOADER_ID:
                movieDetailsAdapter.swapReviewCursor(null);
                break;
            default:
                throw new RuntimeException("Loader not implemented: " + id);
        }
>>>>>>> udacity
    }

    /**
     * This method checks if the movie is added to the favorite table. If it is
     * it removes it if not adds it.
     * @param view The view from our recycler view adapter
     */
    public void favoriteOnClick(View view) {
        Uri uri = MovieContract.MovieFavorite.buildFavoriteUriWithId(Long.parseLong(mMovieUri.getLastPathSegment()));
        Cursor cursor = getApplicationContext().getContentResolver().query(uri,
                null,
                null,
                null,
                null);

        if (cursor != null && cursor.getCount() != 0) {
            MovieDatabaseJsonUtils.removeFromFavorites(this, mMovieUri);
            Toast.makeText(this, "Movie removed from favorites", Toast.LENGTH_SHORT).show();
            cursor.close();
        } else {
            MovieDatabaseJsonUtils.addToFavorites(this, mMovieUri);
            Toast.makeText(this, "Movie added to favorites", Toast.LENGTH_SHORT).show();
            cursor.close();
        }
    }
    private Intent createShareTrailerIntent() {
        String youTubeTrailer = MovieDatabaseJsonUtils.getTrailerForShare(this, mMovieUri);
        Intent shareIntent = ShareCompat.IntentBuilder.from(this)
                .setType("text/plain")
                .setText(youTubeTrailer + MOVIE_SHARE_HASHTAG)
                .getIntent();
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
        return shareIntent;
    }
    @Override
<<<<<<< HEAD
    public void onLoaderReset(Loader<Cursor> loader) {
||||||| merged common ancestors
    public void onLoaderReset(Loader<String[]> loader) {
=======
    public boolean onCreateOptionsMenu(Menu menu) {

        /*
         * We inflate our menu layout to this menu and display it in the Toolbar.
         */
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_detail, menu);
        return true;
    }
>>>>>>> udacity

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_share) {
            Intent shareIntent = createShareTrailerIntent();
            startActivity(shareIntent);
        } else if (id == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
