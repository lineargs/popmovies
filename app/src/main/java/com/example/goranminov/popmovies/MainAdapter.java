package com.example.goranminov.popmovies;

import android.content.Context;
<<<<<<< HEAD:app/src/main/java/com/example/goranminov/popmovies/MoviesPosterAdapter.java
import android.database.Cursor;
||||||| merged common ancestors
=======
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
>>>>>>> udacity:app/src/main/java/com/example/goranminov/popmovies/MainAdapter.java
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.goranminov.popmovies.data.MovieContract;
import com.squareup.picasso.Picasso;

    /*git add .
     * I have followed the examples from the Sunshine app that was provided during my Nanodegree course.
     * I have followed the RecyclerView class the documentation on android.developer Website
     * as well as the ForecastAdapter class from the Sunshine app.
     */
/**
 * Created by goranminov on 26/03/2017.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MovieAdapterViewHolder>{

<<<<<<< HEAD:app/src/main/java/com/example/goranminov/popmovies/MoviesPosterAdapter.java
    /* And onClick handler to make it easy for an Activity to interface
     * with our RecyclerView.
     */
    private final MovieAdapterOnClickHandler movieAdapterOnClickHandler;
||||||| merged common ancestors
    private String[] mMoviesData;

    /* And onClick handler to make it easy for an Activity to interface
     * with our RecyclerView.
     */
    private final MovieAdapterOnClickHandler movieAdapterOnClickHandler;
=======

>>>>>>> udacity:app/src/main/java/com/example/goranminov/popmovies/MainAdapter.java
    private final Context mContext;
<<<<<<< HEAD:app/src/main/java/com/example/goranminov/popmovies/MoviesPosterAdapter.java
    private String movieId;

    //The interface that receives onClick messages.
    public interface MovieAdapterOnClickHandler {
        void onClick(long id);
    }
||||||| merged common ancestors

    //The interface that receives onClick messages.
    public interface MovieAdapterOnClickHandler {
        void onClick(String selectedMovie);
    }
=======
    private Cursor mCursor;
>>>>>>> udacity:app/src/main/java/com/example/goranminov/popmovies/MainAdapter.java

    private Cursor mCursor;

    /**
     * Creates a MainAdapter.
     */
    public MainAdapter(@NonNull Context context) {
        mContext = context;
    }

    /*
     * Cache of the children views.
     */
    public class MovieAdapterViewHolder extends RecyclerView.ViewHolder{
        public final ImageView mPosterImageView;

        public MovieAdapterViewHolder(View view) {
            super(view);
            mPosterImageView = (ImageView) view.findViewById(R.id.picasso_image_view);
<<<<<<< HEAD:app/src/main/java/com/example/goranminov/popmovies/MoviesPosterAdapter.java
            view.setOnClickListener(this);
        }

        /**
         * This gets called by the child ciews during a click.
         *
         * @param v The View that is clicked.
         */
        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            mCursor.moveToPosition(adapterPosition);
            long id = mCursor.getLong(MainActivity.INDEX_MOVIE_ID);
            movieAdapterOnClickHandler.onClick(id);
||||||| merged common ancestors
            view.setOnClickListener(this);
        }

        /**
         * This gets called by the child ciews during a click.
         *
         * @param v The View that is clicked.
         */
        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            String selectedMovie = mMoviesData[adapterPosition];
            movieAdapterOnClickHandler.onClick(selectedMovie);
=======
>>>>>>> udacity:app/src/main/java/com/example/goranminov/popmovies/MainAdapter.java
        }
    }

    /**
     * This gets called when each new ViewHolder is created. This happens when the RecyclerView
     * is laid out. Enough ViewHolders will be created to fill the screen and allow for scrolling.
     *
     * @param parent The ViewGroup that these ViewHolders are contained within.
     * @param viewType  If the RecyclerView has more than one type of item.
     * @return A new MovieOverviewViewHolder that holds the View for each grid item
     */
    @Override
    public MovieAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater
                .from(mContext)
                .inflate(R.layout.movies_list_item, parent, false);
        view.setFocusable(true);
        return new MovieAdapterViewHolder(view);
    }

    /**
     * OnBindViewHolder is called by the RecyclerView to display the data at the specified
     * position.
     *
     * @param holder The ViewHolder which should be updated to represent the
     * contents of the item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
<<<<<<< HEAD:app/src/main/java/com/example/goranminov/popmovies/MoviesPosterAdapter.java
    public void onBindViewHolder(MovieAdapterViewHolder holder, int position) {
        mCursor.moveToPosition(position);

||||||| merged common ancestors
    public void onBindViewHolder(MovieAdapterViewHolder holder, int position) {
        String selectedMoviePosterPath = PopMoviesUtils.posterPath(mMoviesData[position]);
=======
    public void onBindViewHolder(MovieAdapterViewHolder holder, final int position) {
        mCursor.moveToPosition(position);
        String selectedMoviePosterPath = mCursor.getString(MainActivity.INDEX_MOVIE_POSTER_PATH);
>>>>>>> udacity:app/src/main/java/com/example/goranminov/popmovies/MainAdapter.java
        /* We use Picasso to handle image loading, we trigger the URL asynchronously
         * into the ImageView.
         */
        Picasso.with(holder.mPosterImageView.getContext()).load(mCursor.getString(MainActivity.INDEX_MOVIE_POSTER_PATH))
                .placeholder(R.drawable.placeholder)
                .centerInside()
                .fit()
                .into(holder.mPosterImageView);
        holder.mPosterImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCursor.moveToPosition(position);
                Intent intent = new Intent(mContext, DetailActivity.class);
                Uri uri = MovieContract.MovieEntry.buildMovieUriWithId(mCursor.getLong(MainActivity.INDEX_MOVIE_ID));
                intent.setData(uri);
                mContext.startActivity(intent);
            }
        });

    }

    /**
     * This method returns the number of items to display.
     *
     * @return The number of items available.
     */
    @Override
    public int getItemCount() {
        if (mCursor == null) {
            return 0;
        }
        return mCursor.getCount();
    }

    /**
     * This method is used to set the movie data on a MainAdapter if we've already
     * created one.
     *
     * @param newCursor The new movie data to be displayed.
     */
    public void swapCursor(Cursor newCursor) {
        mCursor = newCursor;
        notifyDataSetChanged();
    }
}
