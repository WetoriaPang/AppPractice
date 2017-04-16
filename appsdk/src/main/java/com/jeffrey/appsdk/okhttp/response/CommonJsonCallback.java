package com.jeffrey.appsdk.okhttp.response;

import android.os.Handler;
import android.os.Looper;

import com.jeffrey.appsdk.adutil.ResponseEntityToModule;
import com.jeffrey.appsdk.okhttp.exception.OkHttpException;
import com.jeffrey.appsdk.okhttp.listener.DisposeDataHandler;
import com.jeffrey.appsdk.okhttp.listener.DisposeDataListener;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by pangjiaqi on 2017/4/16.
 * 专门处理json的回调响应
 */

public class CommonJsonCallback implements Callback {
    //与服务器返回字段的一个对应关系
    protected final String RESULT_CODE = "ecode";// 有返回则对于http请求来说是成功的，但还有可能是业务逻辑上的错误
    protected final int RESULT_CODE_VALUE = 0;
    protected final String ERROR_MSG = "emsg";
    protected final String EMPTY_MSG = "";

    /**
     * 自定义异常
     */
    protected final int NETWORK_ERROR = -1;
    protected final int JSON_ERROR = -2;
    protected final int OTHER_ERROR = -3;

    private Handler mDeliveryHandler;//消息的转发
    private DisposeDataListener mListener;
    private Class<?> mClass;
    private Object mResponseObj;

    public CommonJsonCallback(DisposeDataHandler handler) {
        this.mListener = handler.mListener;
        this.mClass = handler.mClass;
        this.mDeliveryHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void onFailure(Call call, final IOException e) {
        mDeliveryHandler.post(new Runnable() {
            @Override
            public void run() {
                mListener.onFailure(new OkHttpException(NETWORK_ERROR, e));
            }
        });
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        final String result = response.body().string();
        mDeliveryHandler.post(new Runnable() {
            @Override
            public void run() {

                handleResponse(result);
            }
        });
    }

    /**
     * 处理服务器返回的响应数据
     *
     * @param responseObj
     */
    private void handleResponse(Object responseObj) {
        //保证代码的健壮性
        if (responseObj == null && responseObj.toString().trim().equals(" ")) {

            mListener.onFailure(new OkHttpException(NETWORK_ERROR, EMPTY_MSG));
            return;
        }

        try {
            JSONObject result = new JSONObject(responseObj.toString());
            //开始解析json
            if (result.has(RESULT_CODE)) {
                //从json对象中取出我们的响应码，若为0，则是正确的响应。（根据服务器返回的果，如果是其他，修改即可）
                if (result.getInt(RESULT_CODE) == RESULT_CODE_VALUE) {
                    //不需要解析，直接返回数据到应用层
                    if (mClass == null) {
                        mListener.onSuccess(mResponseObj);
                    } else {
                        //需要我们将json对象转换为实体对象(这里可使用第三方的，如gson、fastjson)
                        Object obj = ResponseEntityToModule.parseJsonObjectToModule(result, mClass);
                        //表明正确的转化为了实体对象
                        if (obj != null) {
                            mListener.onSuccess(obj);
                        } else {
                            //返回的是不合法的
                            mListener.onFailure(new OkHttpException(JSON_ERROR, EMPTY_MSG));
                        }
                    }
                } else {//服务器返回的是不正常的
                    //将服务器返回给我们的异常回调到应用层去处理
                    mListener.onFailure(new OkHttpException(OTHER_ERROR, result.get(RESULT_CODE)));
                }
            }
        } catch (Exception e) {

            mListener.onFailure(new OkHttpException(OTHER_ERROR, e.getMessage()));
        }
    }
}
