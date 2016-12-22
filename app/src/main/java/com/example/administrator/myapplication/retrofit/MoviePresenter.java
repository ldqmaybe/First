package com.example.administrator.myapplication.retrofit;

import com.cn.baselib.base.BasePresenter;

import java.util.List;

/**
 * @author Admin
 * @time 2016/11/23 0023.14:55
 */
public class MoviePresenter extends BasePresenter<MovieContact.Model, RetrofitActivity> implements MovieContact.Presenter {


    @Override
    public MovieContact.Model loadModel() {
        return new MovieModle();
    }

    @Override
    public void getMovie() {
        mModel.getMovie(new MyCallBack<List<Movie>>() {
            @Override
            public void onSuccess(List<Movie> movies) {
                mView.onSuccess(movies);
            }

            @Override
            public void onFailure(String msg) {
                mView.onFailure(msg);
            }
        });
    }

    interface MyCallBack<T> {
        void onSuccess(T data);

        void onFailure(String msg);
    }
}
