package com.swiftsynq.tedanews.Base;

import android.support.v4.app.Fragment;

import com.swiftsynq.tedanews.Infrastructure.BusProvider;

public class BaseFragment extends Fragment {


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
