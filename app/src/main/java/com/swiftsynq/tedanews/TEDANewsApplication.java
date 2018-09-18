package com.swiftsynq.tedanews;

import android.app.Application;

import timber.log.Timber;

public class TEDANewsApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
