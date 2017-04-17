package com.example.goranminov.popmovies;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
    private boolean mUseOverviewLayout;
    private Cursor mCursor;

    public MovieDetailsAdapter(@NonNull Context context) {
        mContext = context;
        mUseOverviewLayout = mContext.getResources().getBoolean(R.bool.use_overview_layout);
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
        int layoutId;
        switch (viewType) {
            case VIEW_TYPE_OVERVIEW: {
                layoutId = R.layout.detail_activity_movie;
                break;
            }
            case VIEW_TYPE_TRAILER: {
                layoutId = R.layout.trailer_list_item;
                break;
            }

            default:
                throw new IllegalArgumentException("Invalid view type, value of " + viewType);
        }
        View view = LayoutInflater.from(mContext).inflate(layoutId, parent, false);
        view.setFocusable(true);
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
        mCursor.moveToPosition(position);

        int viewType = getItemViewType(position);

        switch (viewType) {
            case VIEW_TYPE_OVERVIEW:
        /* We use Picasso to handle image loading, we trigger the URL asynchronously
         * into the ImageView.
         */
                Picasso.with(mContext)
                        .load(PopMoviesUtils.getPosterPath(mCursor.getString(DetailActivity.INDEX_MOVIE_POSTER_PATH)))
                        .placeholder(R.drawable.placeholder)
                        .centerInside()
                        .fit()
                        .into(holder.mPosterImageView);
                holder.mMovieTitle.setText(mCursor.getString(DetailActivity.INDEX_MOVIE_ORIGINAL_TITLE));
                holder.mMovieOverview.setText(mCursor.getString(DetailActivity.INDEX_MOVIE_OVERVIEW));
                holder.mMovieVoteAverage.setText(mCursor.getString(DetailActivity.INDEX_MOVIE_VOTE_AVERAGE));
                holder.mMovieReleaseDate.setText(mCursor.getString(DetailActivity.INDEX_MOVIE_RELEASE_DATE));
                break;
            case VIEW_TYPE_TRAILER:
                break;

            default:
                throw new IllegalArgumentException("Invalid view type: value of " + viewType);
        }
    }

    /**
     * This method returns the number of items to display.
     *
     * @return The number of items available.
     */
    @Override
    public int getItemCount() {
        if (mCursor == null) return 0;
        return mCursor.getCount();
    }

    void swapCursor(Cursor newCursor) {
        mCursor = newCursor;
        notifyDataSetChanged();
    }
}
