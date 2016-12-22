package com.example.administrator.myapplication.design;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/2/4.
 */
public abstract class BaseFragment extends Fragment {
    private View baseView;

    /**
     * 标记是否记载过
     */
    private boolean isLoaded;
    /**
     * 标记界面是否绘制完毕
     */
    private boolean isPrepared;
    /**
     * 判断用户时候看到界面
     */
    private boolean isVisible = false;


    private LoadMode mode = LoadMode.UNLAZY_LOAD_DATA;

    public enum LoadMode {
        LAZY_LOAD_MORE(0), LAZY_LOAD_ONE(1), UNLAZY_LOAD_DATA(2);
        int state;

        private LoadMode(int state) {
            this.state = state;
        }

    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInVisible();
        }

    }
//
//    public void showDialog(String text) {
//        try {
//            Activity activity = getActivity();
//            if (activity != null) {
//                ((BaseActivity) activity).showDialog(text);
//            }
//        } catch (Exception i) {
//            i.printStackTrace();
//        }
//    }
//
//    public void dismissDialog() {
//        try {
//            Activity activity = getActivity();
//            if (activity != null) {
//                ((BaseActivity) activity).dismissDialog();
//            }
//        } catch (Exception i) {
//            i.printStackTrace();
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (baseView == null) {
            baseView = getView(inflater);
            isPrepared = true;
            onVisible();
        }

        ViewGroup viewGroup = (ViewGroup) baseView.getParent();
        if (viewGroup != null) {
            viewGroup.removeView(baseView);
        }

        return baseView;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        unLazyLoadData();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    protected void onVisible() {

        //取得加载模式

        mode = getMode();

        //如果用户没有看到界面就不加载
        if (!isVisible) {
            return;
        }

        //如果界面没有加载完成，就不继续下去
        if (!isPrepared) {
            return;
        }

        lazyLoadMore();

        if (isLoaded) {
            return;
        }

        lazyLoadOne();
        isLoaded = true;
    }

    protected void onInVisible() {

    }

    /***
     * 懒加载，并加载多次
     */
    protected void lazyLoadMore() {
        if (mode != LoadMode.LAZY_LOAD_MORE) {
            return;
        }

        loadData();
    }

    /***
     * 懒加载，并只加载一次
     */
    protected void lazyLoadOne() {
        if (mode != LoadMode.LAZY_LOAD_ONE) {
            return;
        }

        try {
            loadData();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /***
     * 非懒加载，跟随onActivityCreated生命周期加载
     */
    protected void unLazyLoadData() {
        if (mode != LoadMode.UNLAZY_LOAD_DATA) {
            return;
        }

        loadData();
    }


    protected abstract LoadMode getMode();

    protected abstract View getView(LayoutInflater inflater);

    protected abstract void loadData();


}
