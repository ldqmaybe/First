package com.example.administrator.myapplication.LoadMoreAddRefresh;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.cn.baselib.base.BaseActivity;
import com.cn.baselib.base.BasePresenter;
import com.example.administrator.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class RecyclerViewActivity extends BaseActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.id_item_remove_recyclerview)
    RecyclerView recyclerView;
    private BaseQuickAdapter<ActivityItem, BaseViewHolder> mAdapter;

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_swipe;
    }

    @Override
    protected void initView() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new BaseQuickAdapter<ActivityItem, BaseViewHolder>(R.layout.recyclerview_item_layout, getDatas()) {
            @Override
            protected void convert(BaseViewHolder helper, ActivityItem item) {
                helper.setText(R.id.item_content, item.getTitle());
            }
        };

        recyclerView.setAdapter(mAdapter);
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {//2.8.0
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

    private List<ActivityItem> getDatas() {
        List<ActivityItem> mList = new ArrayList<>();

        ActivityItem linearActivity = new ActivityItem(LinearActivity.class, "LinearActivity");
        mList.add(linearActivity);
        ActivityItem multipleActivity = new ActivityItem(MultipleActivity.class, "MultipleActivity");
        mList.add(multipleActivity);

        ActivityItem fullDelDemoActivity = new ActivityItem(FullDelDemoActivity.class, "FullDelDemoActivity");
        mList.add(fullDelDemoActivity);
        ActivityItem loadMoreActivity = new ActivityItem(RefreshActivity.class, "RefreshActivity");
        mList.add(loadMoreActivity);

        return mList;
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
