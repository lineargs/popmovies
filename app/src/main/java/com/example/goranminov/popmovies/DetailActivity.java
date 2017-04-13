package com.example.goranminov.popmovies;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.goranminov.popmovies.databinding.ActivityDetailBinding;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    private ActivityDetailBinding mDetailBinding;

    //String used to get the data from the intent.
    private String mMovieDetails;
    private String[] data;
    private static final String MDB_BASE = "http://image.tmdb.org/t/p/w185/";
    private MovieDetailsAdapter movieDetailsAdapter;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie);
        Intent intent = getIntent();
        //If there is intent
        if (intent != null) {

            //And we passed the data
            if (intent.hasExtra(Intent.EXTRA_TEXT)) {

                //We put the passed data into the created String
                mMovieDetails = intent.getStringExtra(Intent.EXTRA_TEXT);
                data = new String[] {mMovieDetails};
            }

            mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_movie_details);
            LinearLayoutManager linearLayoutManager =
                    new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            mRecyclerView.setLayoutManager(linearLayoutManager);
            mRecyclerView.setHasFixedSize(true);
            movieDetailsAdapter = new MovieDetailsAdapter(data);
            mRecyclerView.setAdapter(movieDetailsAdapter);
        }
    }
}
