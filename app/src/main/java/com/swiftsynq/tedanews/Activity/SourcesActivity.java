package com.swiftsynq.tedanews.Activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.squareup.otto.Subscribe;
import com.swiftsynq.tedanews.Adapter.NewsCategoryAdapter;
import com.swiftsynq.tedanews.Adapter.NewsSourcesAdapter;
import com.swiftsynq.tedanews.Base.BaseActivity;
import com.swiftsynq.tedanews.Event.ErrorEvent;
import com.swiftsynq.tedanews.Event.NewsServerEvent;
import com.swiftsynq.tedanews.Event.SourcesServerEvent;
import com.swiftsynq.tedanews.Infrastructure.Communicator;
import com.swiftsynq.tedanews.Model.Articles;
import com.swiftsynq.tedanews.Model.News;
import com.swiftsynq.tedanews.Model.NewsSources;
import com.swiftsynq.tedanews.Model.Sources;
import com.swiftsynq.tedanews.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

import static com.swiftsynq.tedanews.Adapter.NewsSourcesAdapter.SPAN_COUNT_ONE;

public class SourcesActivity extends BaseActivity {
    @BindView(R.id.sources_recycler)
    RecyclerView newSourcesView;
    private GridLayoutManager gridLayoutManager;
    NewsSourcesAdapter newsSourcesAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sources);
        ButterKnife.bind(this);
        gridLayoutManager = new GridLayoutManager(this, SPAN_COUNT_ONE);
        new Communicator().getAllNewsSources();
        Timber.d("Start getting news data");


    }
    @Subscribe
    public void onSourcesServerEvent(SourcesServerEvent event) {


        if (event.getServerResponse().getArticles()!=null) {
            NewsSources news=event.getServerResponse();
            if(news.getStatus().equals("ok")) {
                List<Sources> result=news.getArticles();
                Toast.makeText(this,String.valueOf(result.size()),Toast.LENGTH_LONG).show();
                newsSourcesAdapter = new NewsSourcesAdapter(this,result, gridLayoutManager);
                gridLayoutManager.setSpanCount(SPAN_COUNT_ONE);
                newSourcesView.setAdapter(newsSourcesAdapter);
                newSourcesView.setLayoutManager(gridLayoutManager);
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
