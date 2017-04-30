package com.example.goranminov.popmovies;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by goranminov on 10/04/2017.
 */

public class DetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_TYPE_OVERVIEW = 0;
    private final int VIEW_TYPE_TRAILERS = 1;
    private final int VIEW_TYPE_REVIEWS = 2;

    private final Context mContext;
    private Cursor overviewCursor;
    private Cursor trailerCursor;
    private Cursor reviewCursor;

    public DetailsAdapter(@NonNull Context context) {
        mContext = context;
    }

    /*
     * Cache of the children views.
     */


    /**
     * This gets called when each new ViewHolder is created. This happens when the RecyclerView
     * is laid out. Enough ViewHolders will be created to fill the screen and allow for scrolling.
     *
     * @param parent   The ViewGroup that these ViewHolders are contained within.
     * @param viewType If the RecyclerView has more than one type of item.
     * @return A new MovieOverviewViewHolder that holds the View for each grid item
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_OVERVIEW:
                View view = LayoutInflater.from(mContext).inflate(R.layout.detail_activity_movie, parent, false);
                return new MovieAdapterViewHolder(view);
            case VIEW_TYPE_TRAILERS:
                View trailerView = LayoutInflater.from(mContext).inflate(R.layout.trailer_list_item, parent, false);
                return new MovieTrailerViewHolder(trailerView);
            case VIEW_TYPE_REVIEWS:
                View reviewView = LayoutInflater.from(mContext).inflate(R.layout.review_list_item, parent, false);
                return new MovieReviewsViewHolder(reviewView);
            default:
                throw new IllegalArgumentException("Invalid view type, value of " + viewType);
        }
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
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        switch (getItemViewType(position)) {
            case VIEW_TYPE_OVERVIEW:
                MovieAdapterViewHolder movieAdapterViewHolder = (MovieAdapterViewHolder) holder;
                movieAdapterViewHolder.bindViews(mContext);
                break;
            case VIEW_TYPE_TRAILERS:
                MovieTrailerViewHolder movieTrailerViewHolder = (MovieTrailerViewHolder) holder;
                movieTrailerViewHolder.bindViews(mContext, position - 1);
                movieTrailerViewHolder.mTrailerIcon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        trailerCursor.moveToPosition(position - 1);
                        String trailerKey = trailerCursor.getString(DetailActivity.INDEX_TRAILER_KEY);
                        Intent intent = new Intent(Intent.ACTION_VIEW,
                                Uri.parse("http://www.youtube.com/watch?v=" + trailerKey));
                        if (mContext.getPackageManager() != null) {
                            mContext.startActivity(intent);
                        } else {
                            Toast.makeText(mContext, "Couldn't start intent, no applications installed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
            case VIEW_TYPE_REVIEWS:
                MovieReviewsViewHolder movieReviewsViewHolder = (MovieReviewsViewHolder) holder;
                movieReviewsViewHolder.bindViews(mContext, position - 1 - trailerCursor.getCount());
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return VIEW_TYPE_OVERVIEW;
        } else if (position > 0 && position <= trailerCursor.getCount()) {
            return VIEW_TYPE_TRAILERS;
        } else {
            return VIEW_TYPE_REVIEWS;
        }
    }

    /**
     * This method returns the number of items to display.
     *
     * @return The number of items available.
     */
    @Override
    public int getItemCount() {
        if (overviewCursor == null || trailerCursor == null || reviewCursor == null) return 0;
        return 1 + trailerCursor.getCount() + reviewCursor.getCount();
    }

    class MovieAdapterViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.movie_poster_detail_activity)
        ImageView mPosterImageView;
        @BindView(R.id.movie_title)
        TextView mMovieTitle;
        @BindView(R.id.movie_overview)
        TextView mMovieOverview;
        @BindView(R.id.movie_vote_average)
        TextView mMovieVoteAverage;
        @BindView(R.id.movie_release_date)
        TextView mMovieReleaseDate;

        MovieAdapterViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void bindViews(Context context) {
            /* We use Picasso to handle image loading, we trigger the URL asynchronously
             * into the ImageView.
             */
            overviewCursor.moveToPosition(getAdapterPosition());
            Picasso.with(context)
                    .load(overviewCursor.getString(DetailActivity.INDEX_MOVIE_POSTER_PATH))
                    .placeholder(R.drawable.placeholder)
                    .centerInside()
                    .fit()
                    .into(mPosterImageView);
            mMovieTitle.setText(overviewCursor.getString(DetailActivity.INDEX_MOVIE_ORIGINAL_TITLE));
            mMovieOverview.setText(overviewCursor.getString(DetailActivity.INDEX_MOVIE_OVERVIEW));
            mMovieVoteAverage.setText(overviewCursor.getString(DetailActivity.INDEX_MOVIE_VOTE_AVERAGE));
            mMovieReleaseDate.setText(overviewCursor.getString(DetailActivity.INDEX_MOVIE_RELEASE_DATE));
        }
    }

    class MovieTrailerViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.trailer_icon) ImageView mTrailerIcon;
        @BindView(R.id.trailer_text_view) TextView mTrailerText;

        MovieTrailerViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void bindViews(Context context, int position) {
            trailerCursor.moveToPosition(position);
            mTrailerIcon.setImageResource(R.drawable.ic_play_arrow_black_36px);
            int trailerNumber = position + 1;
            mTrailerText.setText("Trailer " + trailerNumber);
        }
    }

    class MovieReviewsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.review_author_text_view) TextView mReviewAuthor;
        @BindView(R.id.review_content_text_view) TextView mReviewContent;

        MovieReviewsViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void bindViews (Context context, int position) {
            reviewCursor.moveToPosition(position);
            mReviewAuthor.setText(reviewCursor.getString(DetailActivity.INDEX_REVIEW_AUTHOR));
            mReviewContent.setText(reviewCursor.getString(DetailActivity.INDEX_REVIEW_CONTENT));
        }
    }

    public void swapOverviewCursor(Cursor newCursor) {
        overviewCursor = newCursor;
        notifyDataSetChanged();
    }

    public void swapTrailerCursor(Cursor newCursor) {
        trailerCursor = newCursor;
        notifyDataSetChanged();
    }

    public void swapReviewCursor(Cursor newCursor) {
        reviewCursor = newCursor;
        notifyDataSetChanged();
    }
}
