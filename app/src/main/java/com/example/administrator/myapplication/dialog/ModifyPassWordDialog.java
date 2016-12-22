package com.example.administrator.myapplication.dialog;

import android.support.v4.app.BaseDialogFragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplication.R;


/**
 * @author Admin
 * @time 2016/8/5 0005.16:35
 */
public class ModifyPassWordDialog extends BaseDialogFragment {
    private EditText etOldPass;
    private EditText etNewPass;
    private EditText etConfirmPass;
    private OnModifyPassWordListener mListener;

    @Override
    protected int getDialogTheme() {
        return R.style.BottomDialog;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.dlg_modify_password;
    }

    @Override
    public void onStart() {
        super.onStart();
//         设置宽度为屏宽、靠近屏幕底部。
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        wlp.y = 100;
//        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(wlp);
    }

    @Override
    protected void initView(View layout) {
        TextView tvTest = (TextView) layout.findViewById(R.id.tv_text);
        tvTest.setText("注意，这是测试信息");

        etOldPass = (EditText) layout.findViewById(R.id.modify_old_password);
        etNewPass = (EditText) layout.findViewById(R.id.modify_new_password);
        etConfirmPass = (EditText) layout.findViewById(R.id.modify_confirm_password);

        ClickListener listener = new ClickListener();
        layout.findViewById(R.id.dlg_com_tvOk).setOnClickListener(listener);
        layout.findViewById(R.id.dlg_com_tvCancle).setOnClickListener(listener);
    }

    public void show(FragmentManager manager, String tag, OnModifyPassWordListener listener) {
        mListener = listener;
        super.show(manager, tag);
    }

    private void checkInput() {
        String oldPass = etOldPass.getText().toString().trim();
        String newPass = etNewPass.getText().toString().trim();
        String confirmPass = etConfirmPass.getText().toString().trim();
        String msg = TextUtils.isEmpty(oldPass) ? "请输入旧密码！" : TextUtils.isEmpty(newPass) ? "请输入新密码！" : TextUtils.isEmpty(confirmPass) ? "请再次输入新密码" : !confirmPass.equals(newPass) ? "密码不一致！" : null;
        if (TextUtils.isEmpty(msg)) {
            if (mListener != null) {
                mListener.onModifypass(oldPass, newPass);
            }
            dismissAllowingStateLoss();
        } else {
            Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
        }
    }

    private class ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.dlg_com_tvOk) {
                checkInput();
            } else {
                dismissAllowingStateLoss();
            }

        }
    }

    public interface OnModifyPassWordListener {
        void onModifypass(String oldPass, String newPass);
    }
}
