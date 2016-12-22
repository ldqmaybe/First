package com.cn.baselib.mvp;

/**
 * Created by GaoSheng on 2016/11/26.
 * 17:20
 *
 * @VERSION V1.4
 * com.example.gs.mvpdemo.base
 * mvp之P
 */

public interface IPresenter<V extends IView> {

    /**
     * @param view 绑定view
     */
    void attachView(V view);


    /**
     * 防止内存的泄漏,清楚presenter与activity之间的绑定
     */
    void detachView();

}
