package com.first.mvp.login;

import com.cn.baselib.mvp.IModel;
import com.cn.baselib.net.RxManager;
import com.first.bean.TimeStampResp;

/**
 * @author Admin
 * @time 2016/11/29 0029.15:30
 */

public class LoginContract {
    interface Model extends IModel {
        void login(String name, String pwd, LoginPresenter.MyCallBack callBack);
        void getDates(RxManager rxManager, LoginPresenter.MyCallBack callBack);
    }

    interface LoginView {
        void getDate(TimeStampResp timeStampResp);
        void onSuccess(String msg);
        void onFailure(String error);
    }
    interface Presenter{
        void getDate();
       void login(String name, String pwd);
    }
}

