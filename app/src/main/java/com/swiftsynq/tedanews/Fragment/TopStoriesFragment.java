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


import com.swiftsynq.tedanews.R;

import java.util.ArrayList;
import java.util.List;

public class TopStoriesFragment
		extends Fragment
		{

	/** Adapter that holds the list of top stories */


	public TopStoriesFragment() {
		// Required empty public constructor
	}

	/** Factory method to pass arguments when recreating fragments */
	public static TopStoriesFragment newInstance() {
		// Base fragment to reuse
		TopStoriesFragment fragment = new TopStoriesFragment();


		// Create and return {@link TopStoriesFragment} with the passed-in string parameter
		return fragment;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the passed-in argument from the fragment

	}

	/** Create the fragment UI view */
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
		// Inflate UI view for the fragment
		final View rootView = inflater.inflate(R.layout.recycler, container, false); // think about layout



		// Return the populated UI view
		return rootView;
	}


}
