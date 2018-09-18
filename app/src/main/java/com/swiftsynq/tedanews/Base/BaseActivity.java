package com.swiftsynq.tedanews.Base;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;

import com.swiftsynq.tedanews.Infrastructure.BusProvider;

import org.greenrobot.eventbus.EventBus;

/**
 * Abstract class of application activity based on {@link AppCompatActivity}.
 * Provides register/unregister of {@link EventBus} and show/hide of {@link ProgressDialog}
 */
public abstract class BaseActivity extends AppCompatActivity  {


    @Override
    public void onResume(){
        super.onResume();
        BusProvider.getInstance().register(this);
    }

    @Override
    public void onPause(){
        super.onPause();
        BusProvider.getInstance().unregister(this);
    }

}