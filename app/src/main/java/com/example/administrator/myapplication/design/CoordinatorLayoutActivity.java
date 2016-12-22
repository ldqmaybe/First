package com.example.administrator.myapplication.design;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.administrator.myapplication.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CoordinatorLayoutActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.activity_coordinator_layout)
    CoordinatorLayout activityCoordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_layout);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        toolbar.setTitle("这里是Title");
        toolbar.setSubtitle("这里是子Title");
        toolbar.setLogo(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);
    }
}
