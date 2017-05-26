package com.first.mvp.login;

import android.os.Handler;
import android.os.health.TimerStat;
import android.text.TextUtils;

import com.cn.baselib.base.BasePresenter;
import com.cn.baselib.net.RequestCallback;
import com.cn.baselib.net.RetrofitHttp;
import com.first.bean.TimeStampResp;


/**
 * @author Admin
 * @time 2016/11/23 0023.14:55
 */
public class LoginPresenter extends BasePresenter<LoginActivity> implements LoginContract.Presenter {


    @Override
    public void getDate() {
        mRxManage.addSubscription(RetrofitHttp.getInstance().createService(ApiService.class).getTime(), new RequestCallback<TimeStampResp>() {
            @Override
            public void onStart() {
                super.onStart();
                mView.showDialog();
            }

            @Override
            protected void onSuccess(TimeStampResp timeStampResp) {
                mView.getDate(timeStampResp);
            }

            @Override
            protected void onFailure(String error) {
                mView.onFailure(error);
            }

            @Override
            public void onCompleted() {
                super.onCompleted();
                mView.dismissDialog();
            }
        });
    }


    @Override
    public void login(String name, String pwd) {
        mView.showDialog();
        login(name, pwd, new MyCallBack<String>() {
            @Override
            public void onSuccess(String msg) {
                mView.onSuccess(msg);
            }

            @Override
            public void onFailure(String msg) {
                mView.onFailure(msg);
            }

            @Override
            public void onFinish() {
                mView.dismissDialog();
            }
        });
    }

    public void login(String name, String pwd, final LoginPresenter.MyCallBack callBack) {
        //用户名和密码不能为空
        if ("admin".equals(name) && "admin".equals(pwd)) {
            callBack.onSuccess(name + " --- " + pwd);
        } else if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd)) {
            callBack.onFailure("用户名或密码不能为空！");
        } else if (!"admin".equals(name)) {
            callBack.onFailure("登录失败,帐户不存在");
        } else if ("admin".equals(name) || !"admin".equals(pwd)) {
            callBack.onFailure("登录失败,密码错误");
        } else {
            callBack.onFailure("登录失败,未知错误");
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                callBack.onFinish();
            }
        }, 3000);
    }

    interface MyCallBack<T> {
        void onSuccess(T data);

        void onFailure(String msg);

        void onFinish();
    }
}
