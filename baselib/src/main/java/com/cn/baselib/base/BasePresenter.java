package com.cn.baselib.base;

import com.cn.baselib.mvp.IModel;
import com.cn.baselib.mvp.IPresenter;
import com.cn.baselib.mvp.IView;
import com.cn.baselib.net.RxManager;

/**
 * @author Admin
 * @time 2016/11/25 0025.10:31
 */
public abstract class BasePresenter<M extends IModel, V extends IView> implements IPresenter {
    public V mView;
    protected M mModel;
    protected RxManager mRxManage;

    public BasePresenter() {
        this.mModel = loadModel(); //使用前先进行初始化
        this.mRxManage = new RxManager();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void attachView(IView view) {
        this.mView = (V) view;
    }

    @Override
    public void detachView() {
        if (mView != null) {
            mView = null;
        }
        mRxManage.clear();
    }

    public abstract M loadModel();
}
