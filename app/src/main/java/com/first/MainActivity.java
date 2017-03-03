package com.first;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.cn.baselib.base.BaseActivity;
import com.cn.baselib.base.BasePresenter;
import com.cn.baselib.rxbus.RxBus;
import com.cn.baselib.rxbus.Subscribe;
import com.cn.baselib.rxbus.ThreadMode;
import com.cn.baselib.utils.LogUtils;
import com.first.LoadMoreAddRefresh.ActivityItem;
import com.first.LoadMoreAddRefresh.RecyclerViewActivity;
import com.first.design.CardViewActivity;
import com.first.design.CoordinatorLayoutActivity;
import com.first.design.NavigationDrawerActivity;
import com.first.design.TabLayoutActivity;
import com.first.design.XituActivity;
import com.first.mvp.login.LoginActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;


public class MainActivity extends BaseActivity {
    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    private BaseQuickAdapter<ActivityItem, BaseViewHolder> mAdapter;

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
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerview.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new BaseQuickAdapter<ActivityItem, BaseViewHolder>(R.layout.recyclerview_item_layout, getDatas()) {
            @Override
            protected void convert(BaseViewHolder helper, ActivityItem item) {
                helper.setText(R.id.item_content, item.getTitle());
            }
        };
        recyclerview.setAdapter(mAdapter);
        setListener();
    }

    private void setListener() {
        recyclerview.addOnItemTouchListener(new OnItemClickListener() {//2.8.0
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                toItemActivity(position);
            }
        });
    }

    private void toItemActivity(int position) {
        ActivityItem item = mAdapter.getData().get(position);
        startActivity(new Intent(this, item.getCls()));
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

    private List<ActivityItem> getDatas() {
        List<ActivityItem> mList = new ArrayList<>();
        ActivityItem coordinatorLayoutActivity = new ActivityItem(CoordinatorLayoutActivity.class, "CoordinatorLayoutActivity");
        mList.add(coordinatorLayoutActivity);
        ActivityItem tabLayoutActivity = new ActivityItem(TabLayoutActivity.class, "TabLayoutActivity");
        mList.add(tabLayoutActivity);
        ActivityItem navigationDrawerActivity = new ActivityItem(NavigationDrawerActivity.class, "NavigationDrawerActivity");
        mList.add(navigationDrawerActivity);
        ActivityItem cardViewActivity = new ActivityItem(CardViewActivity.class, "CardViewActivity");
        mList.add(cardViewActivity);
        ActivityItem xituActivity = new ActivityItem(XituActivity.class, "仿稀土");
        mList.add(xituActivity);
        ActivityItem loginActivity = new ActivityItem(LoginActivity.class, "MVP");
        mList.add(loginActivity);
        ActivityItem xRecyclerViewActivity = new ActivityItem(RecyclerViewActivity.class, "RecyclerViewActivity");
        mList.add(xRecyclerViewActivity);

        return mList;
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
                LogUtils.i("tag", str);
                Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
            }
        }, 2000);
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
