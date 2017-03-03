package com.example.administrator.myapplication.dagger;

import com.cn.baselib.base.BaseActivity;
import com.cn.baselib.base.BasePresenter;
import com.example.administrator.myapplication.R;

import javax.inject.Inject;

public class Dagger2Activity extends BaseActivity {

    @Inject
    User user;
    UserComponent userComponent;
    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_dagger2;
    }

    @Override
    protected void initView() {
//        userComponent = da
    }
}
