package com.example.administrator.myapplication.mvp.login;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cn.baselib.base.BaseActivity;
import com.cn.baselib.widget.CaptchaImageView;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.bean.TimeStampResp;
import com.example.administrator.myapplication.dialog.DialogFactory;
import com.example.administrator.myapplication.dialog.ModifyPassWordDialog;

import butterknife.Bind;
import butterknife.OnClick;


public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.LoginView {
    @Bind(R.id.mEt_username)
    EditText mEt_username;
    @Bind(R.id.mEt_password)
    EditText mEt_password;
    @Bind(R.id.tvName)
    TextView tvName;
    @Bind(R.id.tvDate)
    TextView tvDate;
    @Bind(R.id.image)
    CaptchaImageView captchaImageView;
    @Bind(R.id.regen)
    ImageView regen;
    @Bind(R.id.mBt_verify)
    Button mBtRefresh;
    @Bind(R.id.header)
    ImageView header;

    @Override
    public LoginPresenter initPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_login;
    }


    @Override
    protected void initView() {
        captchaImageView.setCaptchaType(CaptchaImageView.CaptchaGenerator.BOTH);
        captchaImageView.setIsDotNeeded(true);
        captchaImageView.setCaptchaLength(6);
    }

    @Override
    public void getDate(TimeStampResp timeStampResp) {
        Toast.makeText(this, timeStampResp.getDate(), Toast.LENGTH_SHORT).show();
        tvDate.setVisibility(timeStampResp.getDate() == null ? View.GONE : View.VISIBLE);
        if (timeStampResp.getDate() != null) {
            tvDate.setText(timeStampResp.getDate());
        }
    }

    @Override
    public void onSuccess(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        tvName.setVisibility(TextUtils.isEmpty(msg) ? View.GONE : View.VISIBLE);
        if (!TextUtils.isEmpty(msg)) {
            tvName.setText(msg);
        }
    }

    @Override
    public void onFailure(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        tvName.setVisibility(TextUtils.isEmpty(msg) ? View.GONE : View.VISIBLE);
        if (!TextUtils.isEmpty(msg)) {
            tvName.setText(msg);
        }
    }

    @OnClick({R.id.mBt_login, R.id.mBt_date, R.id.mBt_loading, R.id.mBt_dialog, R.id.regen, R.id.mBt_verify})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mBt_login:
                mPresenter.login(mEt_username.getText().toString().trim(), mEt_password.getText().toString().trim());
                break;
            case R.id.mBt_date:
                mPresenter.getDate();
                break;
            case R.id.mBt_loading:
                DialogFactory.showProgressDialog(getSupportFragmentManager(), "正在加载。。。");
                break;
            case R.id.mBt_dialog:
                DialogFactory.showModifyPassWordDialog(getSupportFragmentManager(), new ModifyPassWordDialog.OnModifyPassWordListener() {
                    @Override
                    public void onModifypass(String oldPass, String newPass) {
                    }
                });
                break;
            case R.id.regen:
                captchaImageView.regenerate();
                header.setImageBitmap(captchaImageView.getCaptchaBitmap());
                break;
            case R.id.mBt_verify:
                if (mEt_username.getText().toString().trim().equalsIgnoreCase(captchaImageView.getCaptchaCode())) {
                    Toast.makeText(this, "匹配成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Not Matching", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}