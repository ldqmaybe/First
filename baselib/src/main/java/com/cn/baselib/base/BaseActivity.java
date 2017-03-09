package com.cn.baselib.base;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.cn.baselib.R;
import com.cn.baselib.mvp.IView;
import com.cn.baselib.statusbar.StatusBarUtil;
import com.cn.baselib.utils.HideUtil;

import butterknife.ButterKnife;


public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements IView {
    public T mPresenter;
    public Context mContext;

    /**
     * 简单页面无需mvp就不用管此方法即可,完美兼容各种实际场景的变通
     */
    protected T initPresenter() {
        return mPresenter;
    }
//    public abstract T initPresenter();

    /**
     * 绑定布局
     */
    protected abstract int setLayoutId();

    /**
     * 初始化页面
     */
    protected abstract void initView();

    /**
     * 封装的findViewByID方法
     */
    @SuppressWarnings("unchecked")
    protected <T extends View> T $(@IdRes int id) {
        return (T) super.findViewById(id);
    }

    /**
     * 处理Intent，防止开发人员没做Intent判空
     */
    protected void handleIntent(Intent intent) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutId());
        BaseApplication.getIns().addActivity(this);
        setStatusBar();
        mContext = this;
        ButterKnife.bind(this);
        HideUtil.init(this);
        this.mPresenter = initPresenter();
        this.initBindingView();
        this.initView();
        //强制在基类Intent判空
        if (null != getIntent()) {
            handleIntent(getIntent());
        }
    }


    protected void setStatusBar() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary));
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
        BaseApplication.getIns().finishActivity(this);
    }
}
