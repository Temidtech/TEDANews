package com.swiftsynq.tedanews.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.swiftsynq.tedanews.Model.Articles;
import com.swiftsynq.tedanews.Model.News;
import com.swiftsynq.tedanews.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.CardViewHolder> {

	/** Adapter's data set */
	private List<Articles> mNewsList;

	/** Activity's context */
	private Context mContext;

	/**
	 * Create new {@link NewsAdapter} for the {@link RecyclerView} displaying
	 * list of news stories
	 */
	public NewsAdapter(Context context, List<Articles> news) {
		this.mNewsList = news;
		this.mContext = context;
	}

	/** Create new {@link ViewGroup} for the {@link NewsAdapter} to populate with data
	 * This method gets called when there are no {@link ViewGroup}s to recycle
	 */
	@Override
	public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		// Use layout inflater and inflate the necessary view
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View view = inflater.inflate(R.layout.news_card, parent, false);

		// Return inflated view
		return new CardViewHolder(view);
	}

	@Override
	public void onBindViewHolder(CardViewHolder holder, int position) {
		// Get the current news item at the position
		final Articles currentItem = mNewsList.get(position);

		// Get the news title information from the current news item and
		// set text on the news title {@link TextView}
		holder.newsTitleTextView.setText(currentItem.getTitle());

		// Get the news section information from the current news item
		// and set text on the section {@link TextView}
		holder.sectionNameTextView.setText(currentItem.getDescription());

		// Get the published date of the current news item information from the current news item
		// and set the same as text on the date published {@link TextView}
		holder.datePublishedTextView.setText(currentItem.getPublishedAt());

        // Get the source of the current news item information from the current news item
        // and set the same as text on the date published {@link TextView}
        holder.sourceTextView.setText(currentItem.getSource().getName());
        if (currentItem.getAuthor()==null)
            holder.authorTextView.setText("No Author");
        else
            // Get the author of the current news item information from the current news item
            // and set the same as text on the date published {@link TextView}
            holder.authorTextView.setText(currentItem.getAuthor());

		// Register and setup listener to open up news story in web browser
		holder.storyCard.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// Parse string as url object
				Uri webpage = Uri.parse(currentItem.getUrl());

				// Create web browser intent
				Intent storyOnWebIntent = new Intent(Intent.ACTION_VIEW, webpage);

				// Check web activity can be handled by the device and start activity
				if (storyOnWebIntent.resolveActivity(mContext.getPackageManager()) != null) {
					mContext.startActivity(storyOnWebIntent);
				}

			}
		});

		// Check whether or not the current news item has a thumbnail or not
		if (currentItem.getUrlToImage() == null) {
			// The current news item does not have thumbnail information
			// Set scale type for the default image
			holder.newsThumbnail.setScaleType(ImageView.ScaleType.CENTER);

			// Set the default image on the {@link ImageView} for the thumbnail
			holder.newsThumbnail.setImageResource(R.drawable.business);
		} else {
			// The current news item has thumbnail information
			holder.newsThumbnail.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Glide.with(mContext)
                    .load(Uri.parse(currentItem.getUrlToImage())) // add your image url
                    .placeholder(R.drawable.link_icon)
                    .into(((CardViewHolder) holder).newsThumbnail);

		}
	}

	/** Informs the {@link RecyclerView} of the total number of items in the data set */
	@Override
	public int getItemCount() {
		// Return the number of news story in the list
		return mNewsList.size();
	}

	/**
	 * Clear the adapter's data set
	 */
	void clear() {
		// Initialize new empty list, clearing out old data
		mNewsList = new ArrayList<>();
	}

	/** Add all the data items from another list to the adapter's data set */
	void addAll(List<Articles> data) {
		// Traverse the data list to add news item to the adapter's data set
		for (int i = 0; i < data.size(); i++) {
			// Get the book at current index
			Articles newsStory = data.get(i);
			// Add the book to the data set
			mNewsList.add(newsStory);

			// Notify the adapter of the change in the data set
			// so that it repopulates the view with the updated data set
			notifyDataSetChanged();
		}
	}

	/**
	 * Inner class to cache the the expensive findViewById() results to be used by the adapter's
	 * bindView callback
	 */
	static class CardViewHolder extends RecyclerView.ViewHolder {

		/** {@link ImageView} for the news story's thumbnail */
		ImageView newsThumbnail;

		/** {@link TextView} for the title of the news story */
		TextView newsTitleTextView;

		/** {@link TextView} for the section of the news story */
		TextView sectionNameTextView;

		/** {@link TextView} for the published date of the news story */
		TextView datePublishedTextView;

        /** {@link TextView} for the source of the news story */
        TextView sourceTextView;
        /** {@link TextView} for the author of the news story */
        TextView authorTextView;


        /** {@link CardView} for every news story */
		CardView storyCard;

		/** ItemView to cache reference hooks to the view elements of the recycler view */
		CardViewHolder(View itemView) {
			super(itemView);

			// Find the {@ink ImageView} for the thumbnail
			newsThumbnail = itemView.findViewById(R.id.story_image);

			// Find the {@link TextView} for the news title
			newsTitleTextView = itemView.findViewById(R.id.tvTitle);

			// Find the {@link TextView} for the section of the news story
			sectionNameTextView = itemView.findViewById(R.id.tvDescription);

            // Find the {@link TextView} for the section of the news story
            sourceTextView = itemView.findViewById(R.id.tvSource);

            // Find the {@link TextView} for the section of the news story
            authorTextView = itemView.findViewById(R.id.tvAuthor);

			// Find the {@link TextView} for the published date
			datePublishedTextView = itemView
					.findViewById(R.id.tvDate);

			// Find the {@link CardView} for each news story
			storyCard = itemView.findViewById(R.id.story_card);
		}
	}
}
