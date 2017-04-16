package com.jeffrey.appsdk.okhttp;

import com.jeffrey.appsdk.okhttp.https.HttpsUtils;
import com.jeffrey.appsdk.okhttp.response.CommonJsonCallback;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by pangjiaqi on 2017/4/16.
 *
 * @function 请求参数的发送， 请求参数的配置， https的支持；
 */

public class CommonOkHttpClient {
    private static final int TIMM_OUT = 30; //超时参数
    private static OkHttpClient mOkHttpClient;

    //在client配置参数
    static {
        //创建client对象构建者
        OkHttpClient.Builder okhttpBulider = new OkHttpClient.Builder();
        //为构建者填充超时时间
        okhttpBulider.connectTimeout(TIMM_OUT, TimeUnit.SECONDS);
        okhttpBulider.readTimeout(TIMM_OUT, TimeUnit.SECONDS);
        okhttpBulider.writeTimeout(TIMM_OUT, TimeUnit.SECONDS);

        //重定向
        okhttpBulider.followRedirects(true);

        //https支持
        okhttpBulider.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String s, SSLSession sslSession) {
                return true;
            }
        });
        okhttpBulider.sslSocketFactory(HttpsUtils.initSSLSocketFactory(), HttpsUtils.initTrustManager());

        //生成client对象
        mOkHttpClient = okhttpBulider.build();
    }

    /**
     * 发送具体的http/https请求
     *
     * @param request
     * @param callback
     * @return call
     */
    public static Call sendRequest(Request request, CommonJsonCallback callback) {
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(callback);
        return call;
    }


}
