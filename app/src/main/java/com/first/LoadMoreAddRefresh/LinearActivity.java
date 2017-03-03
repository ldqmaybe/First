package com.first.LoadMoreAddRefresh;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cn.baselib.base.BaseActivity;
import com.cn.baselib.base.BasePresenter;
import com.first.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

import static android.nfc.tech.MifareUltralight.PAGE_SIZE;

public class LinearActivity extends BaseActivity implements BaseQuickAdapter.RequestLoadMoreListener {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.rv)
    RecyclerView recyclerview;
    @Bind(R.id.rotate_header_list_view_frame)
    PtrClassicFrameLayout mPtrFrame;
    private MyAdapter mAdapter;
    private int mCurrentCounter = 0;
    private int TOTAL_COUNTER = 18;
    private View notLoadingView;
    private boolean mLoadMoreEndGone = false;

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_linear;
    }

    @Override
    protected void initView() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        mPtrFrame.setLastUpdateTimeRelateObject(this);
        mPtrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mAdapter.setEnableLoadMore(false);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.setDatas(getDatas());
//                        mAdapter.openLoadMore(PAGE_SIZE);
//                        mAdapter.removeAllFooterView();
                        mCurrentCounter = PAGE_SIZE;
                        mPtrFrame.refreshComplete();
                        mAdapter.setEnableLoadMore(true);
                    }
                }, 1000);
            }
        });
        mAdapter = new MyAdapter(this, R.layout.item, getDatas());
        mAdapter.addHeaderView(getHeaderView(1, null));
        mAdapter.setOnLoadMoreListener(this);
        mAdapter.openLoadAnimation();
//        mAdapter.openLoadMore(PAGE_SIZE);
        recyclerview.setAdapter(mAdapter);
        mCurrentCounter = mAdapter.getData().size();
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

    @Override
    public void onLoadMoreRequested() {
        recyclerview.post(new Runnable() {
            @Override
            public void run() {
                if (mCurrentCounter >= TOTAL_COUNTER) {
//                    mAdapter.loadComplete();
//                    if (notLoadingView == null) {
//                        notLoadingView = getLayoutInflater().inflate(R.layout.not_loading, (ViewGroup) recyclerview.getParent(), false);
//                    }
//                    mAdapter.addFooterView(notLoadingView);
                    mAdapter.loadMoreEnd(mLoadMoreEndGone);//true is gone,false is visible
                } else {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mAdapter.addData(getDatas2());
                            mCurrentCounter = mAdapter.getData().size();
                            mAdapter.loadMoreComplete();
                        }
                    }, 1000);
                }
            }
        });
    }

    private List<String> getDatas() {
        List<String> listData = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            listData.add("item  " + i);
        }
        return listData;
    }

    private List<String> getDatas2() {
        List<String> listData = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            listData.add("new item  " + i);
        }
        return listData;
    }

    private View getHeaderView(int type, View.OnClickListener listener) {
        View view = getLayoutInflater().inflate(R.layout.recyclerview_header, (ViewGroup) recyclerview.getParent(), false);
        view.setOnClickListener(listener);
        return view;
    }

}
