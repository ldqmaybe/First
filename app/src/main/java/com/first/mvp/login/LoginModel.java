package com.first.mvp.login;

import android.text.TextUtils;

import com.cn.baselib.net.RequestCallback;
import com.cn.baselib.net.RetrofitHttp;
import com.cn.baselib.net.RxManager;
import com.first.bean.TimeStampResp;

/**
 * @author Admin
 * @time 2016/11/24 0024.15:24
 */
public class LoginModel implements LoginContract.Model {
    @Override
    public void login(String name, String pwd,LoginPresenter.MyCallBack callBack) {
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
    }

    @Override
    public void getDates(RxManager rxManager, final LoginPresenter.MyCallBack callBack) {
        rxManager.addSubscription(RetrofitHttp.getInstance().createService(ApiService.class).getTime(), new RequestCallback<TimeStampResp>() {

            @Override
            protected void onSuccess(TimeStampResp timeStampResp) {
                callBack.onSuccess(timeStampResp);
            }

            @Override
            protected void onFailure(String error) {
                callBack.onFailure(error);
            }
        });
    }
}
