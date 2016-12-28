package com.example.administrator.myapplication.design;

import android.graphics.Color;
import android.support.v7.widget.Toolbar;

import com.cn.baselib.base.BaseActivity;
import com.cn.baselib.base.BasePresenter;
import com.example.administrator.myapplication.R;

import butterknife.Bind;
import butterknife.OnClick;

public class TopActivity extends BaseActivity {


    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_app_bar_layout;
    }

    @Override
    protected void initView() {
        toolbar.setTitle("这里是Title");
        toolbar.setSubtitle("这里是子Title");
        toolbar.setLogo(R.mipmap.ic_launcher);
        toolbar.setBackgroundColor(Color.parseColor("#ffffffff"));
        setSupportActionBar(toolbar);
    }
    @OnClick(R.id.toolbar)
    public void onClick() {
    }
}
