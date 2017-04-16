package com.jeffrey.appsdk.okhttp.exception;

/**
 * Created by pangjiaqi on 2017/4/16.
 * 自定义异常类,返回ecode,emsg到业务层
 * 如果有其他异常情况在这里添加
 */

public class OkHttpException extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     * the server return code
     */
    private int ecode;

    /**
     * the server return error message
     */
    private Object emsg;

    public OkHttpException(int ecode, Object emsg) {
        this.ecode = ecode;
        this.emsg = emsg;
    }

    public int getEcode() {
        return ecode;
    }

    public Object getEmsg() {
        return emsg;
    }
}
