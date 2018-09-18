package com.swiftsynq.tedanews.Activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.swiftsynq.tedanews.Adapter.NewsCategoryAdapter;
import com.swiftsynq.tedanews.Event.ErrorEvent;
import com.swiftsynq.tedanews.Event.ServerEvent;
import com.swiftsynq.tedanews.Infrastructure.Communicator;
import com.swiftsynq.tedanews.Model.Articles;
import com.swiftsynq.tedanews.Model.News;
import com.swiftsynq.tedanews.R;
import com.swiftsynq.tedanews.Base.BaseActivity;

import com.squareup.otto.Subscribe;

import java.util.List;

import timber.log.Timber;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        new Communicator().getTopHeadlines("us");
        Timber.d("Start getting news data");

    }

    @Subscribe
    public void onServerEvent(ServerEvent event) {


        if (event.getServerResponse().getArticlesResponse()!=null) {
            News news=event.getServerResponse();
            if(news.getStatusResponse().equals("ok")) {
                List<Articles> result=news.getArticlesResponse();
                Toast.makeText(this,String.valueOf(result.size()),Toast.LENGTH_LONG).show();
            }

            Timber.d("Received News data.");

        } else {
            Timber.d("Canceled getting News data.");
        }
    }
    @Subscribe
    public void onErrorEvent(ErrorEvent errorEvent){

        Toast.makeText(this,errorEvent.getErrorMsg(),Toast.LENGTH_LONG).show();

    }
}
