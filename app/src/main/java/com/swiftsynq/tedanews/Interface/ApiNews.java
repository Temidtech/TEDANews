package com.swiftsynq.tedanews.Interface;

import com.swiftsynq.tedanews.Model.News;
import com.swiftsynq.tedanews.Model.NewsSources;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * API for getting news from https://newsapi.org/
 */
public interface ApiNews {

    @GET("top-headlines/")
    Call<News> getTopHeadlines(@Query("country") String country,
                          @Query("apikey") String apikey);
    @GET("sources/")
    Call<NewsSources> getAllNewsSources(@Query("apikey") String apikey);
}
