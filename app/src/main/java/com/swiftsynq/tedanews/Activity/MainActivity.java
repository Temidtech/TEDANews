package com.swiftsynq.tedanews.Activity;

import android.os.Bundle;
import android.os.RecoverySystem;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.swiftsynq.tedanews.Adapter.NewsCategoryAdapter;
import com.swiftsynq.tedanews.Event.ErrorEvent;
import com.swiftsynq.tedanews.Event.NewsServerEvent;
import com.swiftsynq.tedanews.Infrastructure.Communicator;
import com.swiftsynq.tedanews.Model.Articles;
import com.swiftsynq.tedanews.Model.News;
import com.swiftsynq.tedanews.R;
import com.swiftsynq.tedanews.Base.BaseActivity;

import com.squareup.otto.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(toolbar);

        // Find the view pager that will allow the user to swipe between fragments
        ViewPager viewPager = findViewById(R.id.viewpager);

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = findViewById(R.id.sliding_tabs);

        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this, android.R.color.white));
        tabLayout.setTabTextColors(
                ContextCompat.getColor(this, android.R.color.white),
                ContextCompat.getColor(this, R.color.colorPrimaryLight)
        );
        tabLayout.setupWithViewPager(viewPager);

        // Create an adapter that knows which fragment should be shown on each page
        NewsCategoryAdapter adapter = new NewsCategoryAdapter(this, getSupportFragmentManager());

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);

    }


}
