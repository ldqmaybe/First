package com.example.administrator.myapplication;

import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.cn.baselib.base.BaseActivity;
import com.cn.baselib.base.BasePresenter;
import com.cn.baselib.rxbus.RxBus;
import com.cn.baselib.rxbus.Subscribe;
import com.cn.baselib.rxbus.ThreadMode;
import com.example.administrator.myapplication.design.CardViewActivity;
import com.example.administrator.myapplication.design.CoordinatorLayoutActivity;
import com.example.administrator.myapplication.design.NavigationDrawerActivity;
import com.example.administrator.myapplication.design.TabLayoutActivity;
import com.example.administrator.myapplication.design.TopActivity;
import com.example.administrator.myapplication.design.XituActivity;
import com.example.administrator.myapplication.mvp.login.LoginActivity;
import com.example.administrator.myapplication.notification.Lesson_10Activity;
import com.example.administrator.myapplication.retrofit.RetrofitActivity;
import com.example.administrator.myapplication.utils.LogUtils;

import butterknife.Bind;
import butterknife.OnClick;


public class MainActivity extends BaseActivity {

    @Bind(R.id.btn_click1)
    Button btnClick1;
    @Bind(R.id.btn_click2)
    Button btnClick2;
    @Bind(R.id.btn_click3)
    Button btnClick3;
    @Bind(R.id.btn_click4)
    Button btnClick4;
    @Bind(R.id.btn_click5)
    Button btnClick5;
    @Bind(R.id.btn_click6)
    Button btnClick6;
    @Bind(R.id.btn_click7)
    Button btnClick7;
    @Bind(R.id.btn_click8)
    Button btnClick8;
    @Bind(R.id.btn_click9)
    Button btnClick9;

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void onResume() {
        super.onResume();
        String index = getIntent().getStringExtra("index");
        if (index != null) {
            Toast.makeText(this, index, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        RxBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.getDefault().unRegister(this);
    }

    @OnClick({R.id.btn_click1, R.id.btn_click2, R.id.btn_click3, R.id.btn_click4, R.id.btn_click5, R.id.btn_click6, R.id.btn_click7, R.id.btn_click8, R.id.btn_click9})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_click1:
                startActivity(new Intent(this, TopActivity.class));
                finish();
                break;
            case R.id.btn_click2:
                startActivity(new Intent(this, CoordinatorLayoutActivity.class));
                break;
            case R.id.btn_click3:
                startActivity(new Intent(this, TabLayoutActivity.class));
                break;
            case R.id.btn_click4:
                startActivity(new Intent(this, NavigationDrawerActivity.class));
                break;
            case R.id.btn_click5:
                startActivity(new Intent(this, CardViewActivity.class));
                break;
            case R.id.btn_click6:
                startActivity(new Intent(this, XituActivity.class));
                break;
            case R.id.btn_click7:
                startActivity(new Intent(this, Lesson_10Activity.class));
                break;
            case R.id.btn_click8:
                startActivity(new Intent(this, RetrofitActivity.class));
                break;
            case R.id.btn_click9:
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receive(String str) {
        LogUtils.i("tag", str);
    }

    @Subscribe(code = 0x1, threadMode = ThreadMode.MAIN)
    public void receiveCode(String str) {
        LogUtils.i("tag", str);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void receiveSticky(final String str) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                LogUtils.i("tag",str);
                Toast.makeText(MainActivity.this,str,Toast.LENGTH_SHORT).show();
            }
        }, 2000);
    }
}
