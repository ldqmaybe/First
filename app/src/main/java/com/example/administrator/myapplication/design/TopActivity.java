package com.example.administrator.myapplication.design;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.administrator.myapplication.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class TopActivity extends AppCompatActivity {

//    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_bar_layout);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        toolbar.setTitle("这里是Title");
        toolbar.setSubtitle("这里是子Title");
        toolbar.setLogo(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);
    }

    @OnClick(R.id.toolbar)
    public void onClick() {
    }
}
