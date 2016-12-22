package com.cn.baselib.base;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.cn.baselib.mvp.IView;
import com.cn.baselib.utils.HideUtil;

import butterknife.ButterKnife;

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements IView {
    public T mPresenter;
    public Context mContext;

    /**
     * 简单页面无需mvp就不用管此方法即可,完美兼容各种实际场景的变通
     */
    public abstract T initPresenter();

    /**
     * 绑定布局
     */
    protected abstract int setLayoutId();

    /**
     * 初始化页面
     */
    protected abstract void initView();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutId());
        ButterKnife.bind(this);
        HideUtil.init(this);
        mContext = this;
        this.mPresenter = initPresenter();
        this.initBindingView();
        this.initView();
    }

    /**
     * presenter与view绑定
     */
    private void initBindingView() {
        if (mPresenter != null)
            mPresenter.attachView(this);
    }

    /**
     * onDestroy中销毁presenter
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.detachView();
        ButterKnife.unbind(this);
    }
}
