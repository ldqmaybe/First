package com.example.administrator.myapplication.retrofit;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cn.baselib.base.BaseActivity;
import com.cn.baselib.rxbus.RxBus;
import com.example.administrator.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RetrofitActivity extends BaseActivity<MoviePresenter> implements MovieContact.View {

    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    @Bind(R.id.text)
    TextView tvHeader;
    private MovieAdapter adapter;
    private ArrayList<NameBean> dataList;

    @Override
    public MoviePresenter initPresenter() {
        return new MoviePresenter();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_retrofit;
    }

    @Override
    protected void initView() {
        recyclerview.setLayoutManager(new GridLayoutManager(this, 1));
//        recyclerview.addItemDecoration(new CustomDecoration(this,R.color.colorAccent,R.dimen.divider_bottom));
        adapter = new MovieAdapter(this, R.layout.movie_item);
        mPresenter.getMovie();
    }

    @Override
    public void onSuccess(List<Movie> movies) {
        adapter.addAll(movies);
        recyclerview.setAdapter(adapter);
    }

    @Override
    public void onFailure(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.text)
    public void onClick(View view) {
        RxBus.getDefault().post("test");
//        RxBus.getDefault().post(0x1, "toast");
//        RxBus.getDefault().post( "sticky");
    }
}
