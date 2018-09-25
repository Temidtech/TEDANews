package com.swiftsynq.tedanews.Infrastructure;

import android.util.Log;

import com.squareup.otto.Produce;
import com.swiftsynq.tedanews.BuildConfig;
import com.swiftsynq.tedanews.Event.HeadlinesServerEvent;
import com.swiftsynq.tedanews.Event.SourcesServerEvent;
import com.swiftsynq.tedanews.Interface.ApiNews;
import com.swiftsynq.tedanews.Model.News;
import com.swiftsynq.tedanews.Event.ErrorEvent;
import com.swiftsynq.tedanews.Event.NewsServerEvent;
import com.swiftsynq.tedanews.Model.NewsSources;
import com.swiftsynq.tedanews.Utils.Constant;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by TEMIDJOY on 8/15/2018.
 */

public class Communicator {
    private static final String TAG = "Communicator";


    public void getTopHeadlines(String country) {

        //Here a logging interceptor is created
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        //The logging interceptor will be added to the http client
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        //The Retrofit builder will have the client attached, in order to get connection logs
        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constant.BASE_URL)
                .build();
        ApiNews service = retrofit.create(ApiNews.class);

        Call<News> call = service.getTopHeadlines(country,BuildConfig.API_KEY);

        call.enqueue(new Callback<News>() {

            @Override
            public void onResponse(Call<News> call, Response<News> response) {

                BusProvider.getInstance().post(new HeadlinesServerEvent(response.body()));

                Log.e(TAG, "Success");

            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                // handle execution failures like no internet connectivity
                BusProvider.getInstance().post(produceErrorEvent(-2, "!You are not connected to the Internet."));

                Log.e(TAG, "Failure"+t.getMessage());
            }
        });
    }
    public void getEveryThingsNews(String category) {

        //Here a logging interceptor is created
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        //The logging interceptor will be added to the http client
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        //The Retrofit builder will have the client attached, in order to get connection logs
        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constant.BASE_URL)
                .build();
        ApiNews service = retrofit.create(ApiNews.class);

        Call<News> call = service.getEveryThingsNews("publishedAt",category,20,"en",BuildConfig.API_KEY);

        call.enqueue(new Callback<News>() {

            @Override
            public void onResponse(Call<News> call, Response<News> response) {

                BusProvider.getInstance().post(new NewsServerEvent(response.body()));

                Log.e(TAG, "Success");

            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                // handle execution failures like no internet connectivity
                BusProvider.getInstance().post(produceErrorEvent(-2, "!You are not connected to the Internet."));

                Log.e(TAG, "Failure"+t.getMessage());
            }
        });
    }
    public void getAllNewsSources() {

        //Here a logging interceptor is created
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        //The logging interceptor will be added to the http client
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        //The Retrofit builder will have the client attached, in order to get connection logs
        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constant.BASE_URL)
                .build();
        ApiNews service = retrofit.create(ApiNews.class);

        Call<NewsSources> call = service.getAllNewsSources(BuildConfig.API_KEY);

        call.enqueue(new Callback<NewsSources>() {

            @Override
            public void onResponse(Call<NewsSources> call, Response<NewsSources> response) {

                BusProvider.getInstance().post(new SourcesServerEvent(response.body()));

                Log.e(TAG, "Success");

            }

            @Override
            public void onFailure(Call<NewsSources> call, Throwable t) {
                // handle execution failures like no internet connectivity
                BusProvider.getInstance().post(produceErrorEvent(-4, "!You are not connected to the Internet."));

                Log.e(TAG, "Failure"+t.getMessage());
            }
        });
    }
    @Produce
    public SourcesServerEvent produceServerEvent(NewsSources serverResponse) {
        return new SourcesServerEvent(serverResponse);
    }
   @Produce
   public NewsServerEvent produceServerEvent(News serverResponse) {
       return new NewsServerEvent(serverResponse);
   }

    @Produce
    public ErrorEvent produceErrorEvent(int errorCode, String errorMsg) {
        return new ErrorEvent(errorCode, errorMsg);
    }


}
