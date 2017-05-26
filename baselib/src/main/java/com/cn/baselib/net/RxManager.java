package com.cn.baselib.net;


import com.cn.baselib.base.BaseHttpResult;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * 用于管理单个presenter的Rxjava相关代码的生命周期处理
 * @author Admin
 * @time 2016/7/28 0028.14:40
 */
public class RxManager {
    private CompositeSubscription mSubscription;

    /**
     *没有base bean的情况下使用
     * @param observable 事件源
     * @param subscriber 观察者
     */
    public void addSub(Observable observable, Subscriber subscriber) {
        if (mSubscription == null) {
            mSubscription = new CompositeSubscription();
        }
        mSubscription.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }
    /**
     *有base bean的情况下使用
     * @param observable 事件源
     * @param subscriber 观察者
     */
    public <T> void addSubscription(Observable<BaseHttpResult<T>> observable, Subscriber<T> subscriber) {
        if (null == mSubscription) {
            mSubscription = new CompositeSubscription();
        }

        mSubscription.add(observable.map(new HttpResultFunc<T>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }

    /**
     * 取消注册
     */
    public void unSubscribe() {
        if (null != mSubscription && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }

    /**
     * 用来统一处理Http的result,并将HttpResult的Data部分剥离出来返回给subscriber
     *
     * @param <T> Subscriber真正需要的数据类型，也就是Data部分的数据类型
     */
    private static class HttpResultFunc<T> implements Func1<BaseHttpResult<T>, T> {

        @Override
        public T call(BaseHttpResult<T> baseHttpResult) {
            if (!baseHttpResult.getResult().equals("0")) {
                //在这里处理请求返回的错误信息（）
                //将错误信息抛给Subscriber.onError()统一处理
                throw new RuntimeException(baseHttpResult.getRemark());
            }
            return baseHttpResult.getData();
        }
    }

}
