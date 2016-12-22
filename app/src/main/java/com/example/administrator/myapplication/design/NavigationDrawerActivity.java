package com.example.administrator.myapplication.design;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.myapplication.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class NavigationDrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

//    @BindView(R.id.toolbar)
    Toolbar toolbar;
//    @BindView(R.id.fab)
    FloatingActionButton fab;
//    @BindView(R.id.nav_view)
    NavigationView navigationView;
//    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        //添加头部
        View navHeaderView = navigationView.inflateHeaderView(R.layout.header_layout);
        ImageView headIv = (ImageView) navHeaderView.findViewById(R.id.head_iv);
        headIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(NavigationDrawerActivity.this, "点击我的头像", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            Toast.makeText(this, "home", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_favorite) {
            Toast.makeText(this, "收藏", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_followers) {
            Toast.makeText(this, "群组", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_settings) {
            Toast.makeText(this, "设置", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_share) {
            Toast.makeText(this, "分享", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_feedback) {
            Toast.makeText(this, "意见反馈", Toast.LENGTH_SHORT).show();
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @OnClick({R.id.fab})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                Snackbar.make(view, "点击了邮件", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
