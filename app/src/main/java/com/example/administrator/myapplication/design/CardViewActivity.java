package com.example.administrator.myapplication.design;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.cn.baselib.base.BaseActivity;
import com.cn.baselib.base.BasePresenter;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.bean.CardInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class CardViewActivity extends BaseActivity {


    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.rv)
    RecyclerView rv;
    @Bind(R.id.swipe_refresh_widget)
    SwipeRefreshLayout swipeRefreshWidget;
    private PullMoreRecyclerAdapter adapter;
    private LinearLayoutManager mLayoutManager;
    List<CardInfo> list = new ArrayList<CardInfo>();

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_card_view;
    }

    @Override
    protected void initView() {
        swipeRefreshWidget.setColorSchemeResources(R.color.colorAccent, R.color.add_bg_color, R.color.colorPrimary, R.color.colorPrimaryDark, R.color.add_selected_color);
        //创建默认的线性LayoutManager
        mLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(mLayoutManager);

        adapter = new PullMoreRecyclerAdapter(getDatas());
        rv.setAdapter(adapter);

        swipeRefreshWidget.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        list.clear();
                        getDatas();
                        adapter.notifyDataSetChanged();
                        swipeRefreshWidget.setEnabled(true);
                        swipeRefreshWidget.setRefreshing(false);
                    }
                }, 3000);
            }
        });
        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int lastVisibleItem;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!swipeRefreshWidget.isRefreshing()) {
                    if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == adapter.getItemCount()) {
                        swipeRefreshWidget.setEnabled(false);
                        adapter.setMoreStatus(PullMoreRecyclerAdapter.LOADING_MORE);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                getDatas();
                                swipeRefreshWidget.setEnabled(true);
                                adapter.setMoreStatus(PullMoreRecyclerAdapter.PULLUP_LOAD_MORE);
                                adapter.notifyDataSetChanged();
                            }
                        }, 3000);
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }



    private List<CardInfo> getDatas() {
        for (int i = 0; i < 10; i++) {
            CardInfo ci = new CardInfo();
            ci.setContent("美女说：非著名程序员公众号是东半球最好的技术分享公众号");
            ci.setTitle("非著名程序员" + i);
            list.add(ci);
        }
        return list;
    }
}
