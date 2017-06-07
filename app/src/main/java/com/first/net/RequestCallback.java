package com.first.net;

import com.first.base.BaseActivity;

import java.net.ConnectException;
import java.net.SocketException;
import java.net.UnknownHostException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * @author Admin
 * @time 2016/7/28 0028.17:18
 */
public abstract class RequestCallback<T> extends Subscriber<T> {

    protected abstract void onSuccess(T t);

    protected abstract void onFailure(String error);

    @Override
    public void onCompleted() {
        //完成后做的一些操作
    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        String msg;
        if (e instanceof UnknownHostException) {
            msg = "无网络，请设置网络";
        } else if (e instanceof SocketException) {
            msg = "系统繁忙，请稍后再试";
        } else if (e instanceof ConnectException) {
            msg = "无法连接服务器，请检查您的网络状态";
        } else if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            if (httpException.code() == 504) {
                msg = "网络不给力";
            } else {
                msg = httpException.getMessage();
            }
        } else {
            msg = e.getMessage();
        }
        onFailure(msg);
    }
}
