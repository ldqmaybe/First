package com.example.administrator.myapplication.retrofit;

import com.cn.baselib.mvp.IModel;

import java.util.List;

/**
 * @author Admin
 * @time 2016/12/8 0008.10:59
 */
public class MovieContact {
    interface Model extends IModel {
       void getMovie(MoviePresenter.MyCallBack callBack);
    }
    interface View {
        void onSuccess(List<Movie> movies);
        void onFailure(String error);
    }
    interface Presenter{
        void getMovie();
    }
}
