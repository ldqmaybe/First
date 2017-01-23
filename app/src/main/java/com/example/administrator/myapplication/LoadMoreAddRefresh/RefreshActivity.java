package com.example.administrator.myapplication.LoadMoreAddRefresh;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.cn.baselib.base.BaseActivity;
import com.cn.baselib.base.BasePresenter;
import com.example.administrator.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

public class RefreshActivity extends BaseActivity {
    private RecyclerView mRv;
    private List<SwipeBean> mDatas;
    private FullDelDemoAdapter mAdapter;
    private PtrClassicFrameLayout mPtrFrame;

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_load_more;
    }

    @Override
    protected void initView() {
        mRv = (RecyclerView) findViewById(R.id.rv);
        mRv.setLayoutManager(new LinearLayoutManager(this));
        initDatas();
        mAdapter = new FullDelDemoAdapter(this, mDatas);
        mRv.setAdapter(mAdapter);

        mPtrFrame = (PtrClassicFrameLayout) findViewById(R.id.rotate_header_list_view_frame);
        mPtrFrame.setLastUpdateTimeRelateObject(this);
        mPtrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                Toast.makeText(RefreshActivity.this,"onRefresh",Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void initDatas() {
        mDatas = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            mDatas.add(new SwipeBean("" + i));
        }
    }
}
