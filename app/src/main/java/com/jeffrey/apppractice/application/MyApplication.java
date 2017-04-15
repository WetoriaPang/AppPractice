package com.jeffrey.apppractice.application;

import android.app.Application;

/**
 * Created by pangjiaqi on 2017/4/15.
 */

public class MyApplication extends Application {

    private static MyApplication mApplication = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
    }

    public static MyApplication getInstance() {
        return mApplication;
    }
}
