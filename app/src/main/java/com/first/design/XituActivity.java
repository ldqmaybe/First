package com.first.design;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.cn.baselib.base.BaseActivity;
import com.cn.baselib.base.BasePresenter;
import com.first.R;

import butterknife.Bind;

public class XituActivity extends BaseActivity {
    @Bind(R.id.head_iv)
    ImageView headIv;
    @Bind(R.id.one)
    LinearLayout one;
    @Bind(R.id.two)
    LinearLayout two;
    @Bind(R.id.head_layout)
    LinearLayout head_layout;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @Bind(R.id.toolbar_tab)
    TabLayout toolbar_tab;
    @Bind(R.id.app_bar_layout)
    AppBarLayout app_bar_layout;
    @Bind(R.id.main_vp_container)
    ViewPager main_vp_container;
    @Bind(R.id.nsv)
    NestedScrollView nsv;
    @Bind(R.id.root_layout)
    CoordinatorLayout rootLayout;

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_xitu;
    }

    @Override
    protected void initView() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        app_bar_layout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset <= -head_layout.getHeight() / 2) {
                    mCollapsingToolbarLayout.setTitle("悦餐");
                } else {
                    mCollapsingToolbarLayout.setTitle(" ");
                }
            }
        });
        ViewPagerAdapter vpAdapter = new ViewPagerAdapter(getSupportFragmentManager(), this);
        main_vp_container.setAdapter(vpAdapter);
        main_vp_container.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(toolbar_tab));
        toolbar_tab.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(main_vp_container));
        toolbar_tab.setTabMode(TabLayout.MODE_SCROLLABLE);
        //tablayout和viewpager建立联系为什么不用下面这个方法呢？自己去研究一下，可能收获更多
        //toolbar_tab.setupWithViewPager(main_vp_container);
    }
}
