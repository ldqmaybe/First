package com.cn.baselib.base;

import com.cn.baselib.net.RetrofitHttp;
import com.cn.baselib.net.RxManager;

/**
 * @author Admin
 * @time 2016/11/25 0025.10:31
 */
public abstract class BasePresenter<V >  {
    public V mView;
    public RxManager mRxManage;
    public BasePresenter() {
        this.mRxManage = new RxManager();
    }
    @SuppressWarnings("unchecked")
    public void attachView(V view) {
        this.mView = (V) view;
    }

    public void detachView() {
        if (mView != null) {
            mView = null;
        }
        mRxManage.unSubscribe();
    }
}
