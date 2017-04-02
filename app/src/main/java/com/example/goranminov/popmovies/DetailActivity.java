package com.example.goranminov.popmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {
    /*
     * Butterknife is a popular View "injection" library for Android.
     * Automatically finds each field by the specified ID.
     */
    @BindView(R.id.movie_title) TextView mMovieTitle;
    @BindView(R.id.movie_overview) TextView mMovieOverview;
    @BindView(R.id.movie_vote_average) TextView mMovieVoteAverage;
    @BindView(R.id.movie_release_date) TextView mMovieReleaseDate;
    @BindView(R.id.movie_poster_detail_activity) ImageView mMoviePoster;

    //String used to get the data from the intent.
    private String mMovieDetails;
    private static final String MDB_BASE = "http://image.tmdb.org/t/p/w185/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        ButterKnife.bind(this);
        Intent intent = getIntent();
        //If there is intent
        if (intent != null) {

            //And we passed the data
            if (intent.hasExtra(Intent.EXTRA_TEXT)) {

                //We put the passed data into the created String
                mMovieDetails = intent.getStringExtra(Intent.EXTRA_TEXT);

                // Used to call parsePassedData method.
                parsePassedData();
            }
        }
    }

    private void parsePassedData() {

        /*
         * Here came handy why we have used different symbols while we were creating the String Array
         * from the JSONObject.
         *
         * We extract the substrings and we passed the information to their TextView and ImageView.
         */
        String moviePath = MDB_BASE + mMovieDetails.substring(0, mMovieDetails.indexOf("!"));

        /* We use Picasso to handle image loading, we trigger the URL asynchronously
         * into the ImageView.
         */
        Picasso.with(getApplicationContext()).load(moviePath)
                .placeholder(R.drawable.placeholder)
                .centerInside()
                .fit()
                .into(mMoviePoster);
        String movieTitle = mMovieDetails.substring(mMovieDetails.indexOf("!") + 1, mMovieDetails.indexOf("@"));
        mMovieTitle.setText(movieTitle);
        String movieOverview = mMovieDetails.substring(mMovieDetails.indexOf("@") + 1, mMovieDetails.indexOf("#"));
        mMovieOverview.setText(movieOverview);
        String movieVoteAverage = mMovieDetails.substring(mMovieDetails.indexOf("#") + 1, mMovieDetails.indexOf("£"));
        mMovieVoteAverage.setText(movieVoteAverage + "/10");
        String movieReleaseDate = mMovieDetails.substring(mMovieDetails.indexOf("#") + 1, mMovieDetails.length() - 1);
        movieReleaseDate = movieReleaseDate.substring(movieReleaseDate.indexOf("£") + 1, movieReleaseDate.indexOf("-"));
        mMovieReleaseDate.setText(movieReleaseDate);

    }
}
