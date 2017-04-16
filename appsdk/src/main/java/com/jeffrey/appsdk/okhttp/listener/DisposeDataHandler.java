package com.jeffrey.appsdk.okhttp.listener;

/**
 * Created by pangjiaqi on 2017/4/16.
 */

public class DisposeDataHandler {

    public DisposeDataListener mListener = null;
    public Class<?> mClass = null;

    public DisposeDataHandler(DisposeDataListener listener) {
        this.mListener = listener;
    }

    public DisposeDataHandler(DisposeDataListener listener, Class<?> aClass) {
        this.mListener = listener;
        this.mClass = aClass;
    }
}
