package com.jeffrey.appsdk.okhttp.request;

import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Request;

/**
 * Created by pangjiaqi on 2017/4/16.
 *
 * @function 接收请求参数，生成request对象
 */
public class CommonRequest {
    /**
     * post
     *
     * @param url
     * @param params
     * @return 返回创建好的Request对象
     */
    public static Request createPostRequest(String url, RequestParams params) {
        FormBody.Builder mFormBodyBuild = new FormBody.Builder();

        if (params != null) {
            for (Map.Entry<String, String> entry : params.urlParams.entrySet()) {
                //将请求参数遍历添加到请求构建类中
                mFormBodyBuild.add(entry.getKey(), entry.getValue());
            }
        }
        //通过请求构建类的build方法获取到真正的请求对象
        FormBody formBody = mFormBodyBuild.build();
        return new Request.Builder().url(url).post(formBody).build();
    }

    /**
     * get
     *
     * @param url
     * @param params
     * @return 通过传入的参数返回一个get类型的请求
     */
    public static Request createGetRequest(String url, RequestParams params) {
        StringBuilder urlBuilder = new StringBuilder(url).append("?");

        if (params != null) {
            for (Map.Entry<String, String> entry : params.urlParams.entrySet()) {
                //构建url，这里在字符串最后拼接" & "方便参数的添加
                urlBuilder.append(entry.getKey()).append("=")
                        .append(entry.getValue()).append("&");
            }
        }
        //因为拼接的时候多了一个"&"， 所以这里要去掉
        return new Request.Builder().url(urlBuilder.substring(0, urlBuilder.length() - 1))
                .get().build();
    }
}
