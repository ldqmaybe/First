package com.example.administrator.myapplication.mvp.login;

import com.cn.baselib.base.BasePresenter;
import com.example.administrator.myapplication.bean.TimeStampResp;

/**
 * @author Admin
 * @time 2016/11/23 0023.14:55
 */
public class LoginPresenter extends BasePresenter<LoginModel, LoginActivity> implements LoginContract.Presenter {

    @Override
    public LoginModel loadModel() {
        return new LoginModel();
    }

    @Override
    public void getDate() {

        mModel.getDates(mRxManage, new MyCallBack<TimeStampResp>() {
            @Override
            public void onSuccess(TimeStampResp data) {
                mView.getDate(data);
            }

            @Override
            public void onFailure(String msg) {
                mView.onFailure(msg);
            }
        });
    }

    @Override
    public void login(String name, String pwd) {
        mModel.login(name, pwd, new MyCallBack<String>() {
            @Override
            public void onSuccess(String msg) {
                mView.onSuccess(msg);
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
