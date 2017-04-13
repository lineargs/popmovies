package com.example.goranminov.popmovies;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.goranminov.popmovies.utilities.PopMoviesUtils;
import com.squareup.picasso.Picasso;

    /*
     * I have followed the examples from the Sunshine app that was provided during my Nanodegree course.
     * I have followed the RecyclerView class the documentation on android.developer Website
     * as well as the ForecastAdapter class from the Sunshine app.
     */
/**
 * Created by goranminov on 26/03/2017.
 */

public class MoviesPosterAdapter extends RecyclerView.Adapter<MoviesPosterAdapter.MovieAdapterViewHolder>{

    private String[] mMoviesData;

    /* And onClick handler to make it easy for an Activity to interface
     * with our RecyclerView.
     */
    private final MovieAdapterOnClickHandler movieAdapterOnClickHandler;
    private final Context mContext;

    //The interface that receives onClick messages.
    public interface MovieAdapterOnClickHandler {
        void onClick(String selectedMovie);
    }

    /**
     * Creates a MoviesPosterAdapter.
     *
     * @param movieAdapterOnClickHandler The onClick handler for this adapter.
     */
    public MoviesPosterAdapter(@NonNull Context context, MovieAdapterOnClickHandler movieAdapterOnClickHandler) {
        mContext = context;
        this.movieAdapterOnClickHandler = movieAdapterOnClickHandler;
    }

    /*
     * Cache of the children views.
     */
    public class MovieAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final ImageView mPosterImageView;

        public MovieAdapterViewHolder(View view) {
            super(view);
            mPosterImageView = (ImageView) view.findViewById(R.id.picasso_image_view);
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
        }
    }

    /**
     * This gets called when each new ViewHolder is created. This happens when the RecyclerView
     * is laid out. Enough ViewHolders will be created to fill the screen and allow for scrolling.
     *
     * @param parent The ViewGroup that these ViewHolders are contained within.
     * @param viewType  If the RecyclerView has more than one type of item.
     * @return A new MovieAdapterViewHolder that holds the View for each grid item
     */
    @Override
    public MovieAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater
                .from(mContext)
                .inflate(R.layout.movies_list_item, parent, false);
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
    public void onBindViewHolder(MovieAdapterViewHolder holder, int position) {
        String selectedMoviePosterPath = PopMoviesUtils.posterPath(mMoviesData[position]);
        /* We use Picasso to handle image loading, we trigger the URL asynchronously
         * into the ImageView.
         */
        Picasso.with(holder.mPosterImageView.getContext()).load(selectedMoviePosterPath)
                .placeholder(R.drawable.placeholder)
                .centerInside()
                .fit()
                .into(holder.mPosterImageView);

    }

    /**
     * This method returns the number of items to display.
     *
     * @return The number of items available.
     */
    @Override
    public int getItemCount() {
        if (mMoviesData == null) {
            return 0;
        }
        return mMoviesData.length;
    }

    /**
     * This method is used to set the movie data on a MoviesPosterAdapter if we've already
     * created one.
     *
     * @param moviesData The new movie data to be displayed.
     */
    public void setMoviesData(String[] moviesData) {
        mMoviesData = moviesData;
        notifyDataSetChanged();
    }
}
