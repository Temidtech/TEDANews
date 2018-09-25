package com.swiftsynq.tedanews.Fragment;


import android.content.Context;
import android.graphics.PorterDuff;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.otto.Subscribe;
import com.swiftsynq.tedanews.Adapter.NewsAdapter;
import com.swiftsynq.tedanews.Adapter.NewsSourcesAdapter;
import com.swiftsynq.tedanews.Base.BaseFragment;
import com.swiftsynq.tedanews.Event.ErrorEvent;
import com.swiftsynq.tedanews.Event.NewsServerEvent;
import com.swiftsynq.tedanews.Event.SourcesServerEvent;
import com.swiftsynq.tedanews.Infrastructure.Communicator;
import com.swiftsynq.tedanews.Model.Articles;
import com.swiftsynq.tedanews.Model.News;
import com.swiftsynq.tedanews.Model.NewsSources;
import com.swiftsynq.tedanews.Model.Sources;
import com.swiftsynq.tedanews.R;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

import static com.swiftsynq.tedanews.Adapter.NewsSourcesAdapter.SPAN_COUNT_ONE;

public class TopStoriesFragment
		extends BaseFragment {

	/** Adapter that holds the list of top stories */
	private NewsAdapter adapter;

	/** Loader ID */
	private static final int LOADER_ID = 1;

	/** News category (fragment initiating parameter) */
	private String newsCategory;

    RecyclerView recyclerView;

	/** Indeterminate progress bar */
	private ProgressBar progressSpinner;

	/** String key for news category */
	private static final String NEWS_CATEGORY = "CATEGORY";

	public TopStoriesFragment() {
		// Required empty public constructor
	}

	/** Factory method to pass arguments when recreating fragments */
	public static TopStoriesFragment newInstance(String newsCategory) {
		// Base fragment to reuse
		TopStoriesFragment fragment = new TopStoriesFragment();

		// Initialize bundle to store arguments
		Bundle bundle = new Bundle(1);

		// String url parameter to pass arguments when recreating {@link TopStoriesFragment}
		bundle.putString(NEWS_CATEGORY, newsCategory);

		// Save arguments to the fragment instance to be called upon later
		fragment.setArguments(bundle);
		// Create and return {@link TopStoriesFragment} with the passed-in string parameter
		return fragment;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the passed-in argument from the fragment
		if (getArguments() != null) {
			newsCategory = getArguments().getString(NEWS_CATEGORY);

		}
	}

	/** Create the fragment UI view */
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
		// Inflate UI view for the fragment
        View rootView = inflater.inflate(R.layout.activity_news, container, false); // think about layout
		Timber.d("Start getting news data");
		// Get a reference to the recycler view
        recyclerView = rootView.findViewById(R.id.recycler_view);
		// Set layout for recycler view
		recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		recyclerView.setHasFixedSize(true);



		// Get reference to the Progress bar
		progressSpinner = rootView.findViewById(R.id.progress_spinner);
		// Indeterminate progress bar type
		progressSpinner.setIndeterminate(true);
		// Set progress bar color
		progressSpinner.getIndeterminateDrawable()
				.setColorFilter(
						ContextCompat.getColor(getContext(), R.color.colorPrimary),
						PorterDuff.Mode.SRC_IN
				);
        progressSpinner.setVisibility(View.VISIBLE);
		// Set empty view when there is no data on the recycler view
		TextView mEmptyStateView = rootView.findViewById(R.id.empty_text_view);

		// Check internet connectivity
		if (getActivity() != null) {
			ConnectivityManager connMgr = (ConnectivityManager) getActivity()
					.getSystemService(Context.CONNECTIVITY_SERVICE);

			// Get details on the currently active default data network
			if (connMgr != null) {
				NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

				// If there is a network connection, fetch data
				if (networkInfo != null && networkInfo.isConnected()) {
                    Toast.makeText(getActivity(),newsCategory.toString(),Toast.LENGTH_LONG).show();
                    new Communicator().getEveryThingsNews(newsCategory);
				} else {
					// Otherwise, display error
					// First, hide loading indicator so error message will be visible
					progressSpinner.setVisibility(View.GONE);

					// Update empty state with no connection error message
					mEmptyStateView.setText(R.string.no_internet_connection);
				}
			}
		}

		// Return the populated UI view
		return rootView;
	}
	@Subscribe
	public void onNewsServerEvent(NewsServerEvent event) {


		if (event.getServerResponse().getArticlesResponse()!=null) {
			News news=event.getServerResponse();
			if(news.getStatusResponse().equals("ok")) {
				List<Articles> result=news.getArticlesResponse();
				//Toast.makeText(getActivity(),String.valueOf(result.size()),Toast.LENGTH_LONG).show();
				// Create adapter
				adapter = new NewsAdapter(getContext(), result);
                // Set adapter for recycler view
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                progressSpinner.setVisibility(View.GONE);

			}

			Timber.d("Received News data.");

		} else {
			Timber.d("Canceled getting News data.");
		}
	}
	@Subscribe
	public void onErrorEvent(ErrorEvent errorEvent){

		Toast.makeText(getActivity(),errorEvent.getErrorMsg(),Toast.LENGTH_LONG).show();

	}

}
