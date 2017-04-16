package com.jeffrey.appsdk.okhttp.listener;

/**
 * Created by pangjiaqi on 2017/4/16.
 * 自定义监听时间
 */

public interface DisposeDataListener {

    /**
     * 请求成功回调时间
     *
     * @param responseObj
     */
    public void onSuccess(Object responseObj);

    /**
     * 请求失败回调时间
     *
     * @param responseObj
     */
    public void onFailure(Object responseObj);
}
