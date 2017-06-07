package com.first.login;

/**
 * Created by admin on 2017/6/1.
 */

public  interface MyCallBack<T> {
    void onSuccess(T data);

    void onFailure(String msg);

    void onFinish();
}
