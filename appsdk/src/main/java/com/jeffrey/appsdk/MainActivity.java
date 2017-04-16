package com.jeffrey.appsdk;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jeffrey.appsdk.okhttp.CommonOkHttpClient;
import com.jeffrey.appsdk.okhttp.listener.DisposeDataHandler;
import com.jeffrey.appsdk.okhttp.listener.DisposeDataListener;
import com.jeffrey.appsdk.okhttp.request.CommonRequest;
import com.jeffrey.appsdk.okhttp.response.CommonJsonCallback;

/**
 * 测试
 */
public class MainActivity extends AppCompatActivity {
    CommonOkHttpClient mCommonOkHttpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCommonOkHttpClient.sendRequest(CommonRequest.createGetRequest("http://www.baidu.com", null),
                new CommonJsonCallback(new DisposeDataHandler(new DisposeDataListener() {
                    @Override
                    public void onSuccess(Object responseObj) {

                    }

                    @Override
                    public void onFailure(Object responseObj) {

                    }
                })));
    }
}
