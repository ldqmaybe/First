package com.example.administrator.myapplication.design;

import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.cn.baselib.base.BaseActivity;
import com.cn.baselib.base.BasePresenter;
import com.example.administrator.myapplication.R;

import butterknife.Bind;

public class CoordinatorLayoutActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.activity_coordinator_layout)
    CoordinatorLayout activityCoordinatorLayout;

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_coordinator_layout;
    }

    @Override
    protected void initView() {
        toolbar.setTitle("这里是Title");
        toolbar.setSubtitle("这里是子Title");
        toolbar.setLogo(R.mipmap.ic_launcher);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
