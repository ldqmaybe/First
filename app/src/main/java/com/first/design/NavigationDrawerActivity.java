package com.first.design;

import android.graphics.Color;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;

import com.first.base.BaseActivity;
import com.cn.baselib.statusbar.StatusBarUtil;
import com.first.R;

import butterknife.Bind;

public class NavigationDrawerActivity extends BaseActivity  {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
//    @Bind(R.id.nav_view)
//    NavigationView navigationView;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawer;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_navigation_drawer;
    }

    @Override
    protected void setStatusBar() {
        super.setStatusBar();
        StatusBarUtil.setColor(this,getResources().getColor(R.color.colorPrimary));
    }

    @Override
    protected void initView() {
        // 设置Toolbar
        toolbar.setTitle("Demo");
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        // 设置toolbar支持actionbar
        setSupportActionBar(toolbar);
        // 使用ActionBarDrawerToggle，配合DrawerLayout和ActionBar,以实现推荐的抽屉功能。
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
//        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
//        navigationView.setNavigationItemSelectedListener(this);

        //添加头部
//        View navHeaderView = navigationView.inflateHeaderView(R.layout.header_layout);
//        ImageView headIv = (ImageView) navHeaderView.findViewById(R.id.head_iv);
//        headIv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(NavigationDrawerActivity.this, "点击我的头像", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if (id == R.id.nav_home) {
//            Toast.makeText(this, "home", Toast.LENGTH_SHORT).show();
//        } else if (id == R.id.nav_favorite) {
//            Toast.makeText(this, "收藏", Toast.LENGTH_SHORT).show();
//        } else if (id == R.id.nav_followers) {
//            Toast.makeText(this, "群组", Toast.LENGTH_SHORT).show();
//        } else if (id == R.id.nav_settings) {
//            Toast.makeText(this, "设置", Toast.LENGTH_SHORT).show();
//        } else if (id == R.id.nav_share) {
//            Toast.makeText(this, "分享", Toast.LENGTH_SHORT).show();
//        } else if (id == R.id.nav_feedback) {
//            Toast.makeText(this, "意见反馈", Toast.LENGTH_SHORT).show();
//        }
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }
//    @Override
//    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }
}
