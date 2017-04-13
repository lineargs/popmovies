package com.example.goranminov.popmovies;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.goranminov.popmovies.utilities.PopMoviesUtils;
import com.squareup.picasso.Picasso;

/**
 * Created by goranminov on 10/04/2017.
 */

public class MovieDetailsAdapter extends RecyclerView.Adapter<MovieDetailsAdapter.MovieAdapterViewHolder> {

    private static final int VIEW_TYPE_OVERVIEW = 0;
    private static final int VIEW_TYPE_TRAILER = 1;
    private final Context mContext;
    private String[] mMovieData;

    public MovieDetailsAdapter(@NonNull Context context) {
        mContext = context;
    }

    /*
     * Cache of the children views.
     */
    public class MovieAdapterViewHolder extends RecyclerView.ViewHolder {
        final ImageView mPosterImageView;
        final TextView mMovieTitle;
        final TextView mMovieOverview;
        final TextView mMovieVoteAverage;
        final TextView mMovieReleaseDate;

        public MovieAdapterViewHolder(View view) {
            super(view);
            mPosterImageView = (ImageView) view.findViewById(R.id.movie_poster_detail_activity);
            mMovieTitle = (TextView) view.findViewById(R.id.movie_title);
            mMovieOverview = (TextView) view.findViewById(R.id.movie_overview);
            mMovieVoteAverage = (TextView) view.findViewById(R.id.movie_vote_average);
            mMovieReleaseDate = (TextView) view.findViewById(R.id.movie_release_date);
        }
    }

    /**
     * This gets called when each new ViewHolder is created. This happens when the RecyclerView
     * is laid out. Enough ViewHolders will be created to fill the screen and allow for scrolling.
     *
     * @param parent   The ViewGroup that these ViewHolders are contained within.
     * @param viewType If the RecyclerView has more than one type of item.
     * @return A new MovieAdapterViewHolder that holds the View for each grid item
     */
    @Override
    public MovieAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutId;
        switch (viewType) {
            case VIEW_TYPE_OVERVIEW: {
                layoutId = R.layout.detail_activity_movie;
                break;
            }
            case VIEW_TYPE_TRAILER: {
                break;
            }

            default:
                throw new IllegalArgumentException("Invalid view type, value of " + viewType);
        }
        View view = LayoutInflater.from(context).inflate(R.layout.detail_activity_movie, parent, false);
        //view.setFocusable(true);
        return new MovieAdapterViewHolder(view);
    }

    /**
     * OnBindViewHolder is called by the RecyclerView to display the data at the specified
     * position.
     *
     * @param holder   The ViewHolder which should be updated to represent the
     *                 contents of the item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(MovieAdapterViewHolder holder, int position) {
        String moviePath = PopMoviesUtils.posterPath(mMovieData[position]);

        Picasso.with(holder.mPosterImageView.getContext()).load(moviePath)
                .placeholder(R.drawable.placeholder)
                .centerInside()
                .fit()
                .into(holder.mPosterImageView);
        /*int viewType = getItemViewType(position);
        String movieDetails = mMovieData[position];

        *//* We use Picasso to handle image loading, we trigger the URL asynchronously
         * into the ImageView.
         *//*


        String movieTitle = movieDetails.substring(movieDetails.indexOf("!") + 1, movieDetails.indexOf("@"));
        holder.mMovieTitle.setText(movieTitle);
        String movieOverview = movieDetails.substring(movieDetails.indexOf("@") + 1, movieDetails.indexOf("#"));
        holder.mMovieOverview.setText(movieOverview);
        String movieVoteAverage = movieDetails.substring(movieDetails.indexOf("#") + 1, movieDetails.indexOf("£"));
        holder.mMovieVoteAverage.setText(movieVoteAverage);
        String movieReleaseDate = movieDetails.substring(movieDetails.indexOf("#") + 1, movieDetails.length() - 1);
        movieReleaseDate = movieReleaseDate.substring(movieReleaseDate.indexOf("£") + 1, movieReleaseDate.indexOf("-"));
        holder.mMovieReleaseDate.setText(movieReleaseDate);*/


    }

    /**
     * This method returns the number of items to display.
     *
     * @return The number of items available.
     */
    @Override
    public int getItemCount() {
        if (mMovieData == null) {
            return 0;
        } else {
            return mMovieData.length;
        }
    }

    public void setMovieData(String[] movieData) {
        mMovieData = movieData;
        notifyDataSetChanged();
    }
}
