package com.first.dialog;

import android.os.Bundle;
import android.support.v4.app.BaseDialogFragment;
import android.view.View;
import android.widget.TextView;

import com.first.R;


/**
 * 进度对话框
 */
public class ProgressDialogFragment extends BaseDialogFragment {

    public static ProgressDialogFragment newInstance(String title){
        ProgressDialogFragment fragment = new ProgressDialogFragment();
        Bundle args = new Bundle();
        args.putString("Title",title);
        fragment.setArguments(args);
        return  fragment;
    }

    @Override
    protected int getDialogTheme() {
        return R.style.dialog_loading_style;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.dialog_loading;
    }

    @Override
    protected void initView(View layout) {
        TextView tvMsg = (TextView) layout.findViewById(R.id.dlg_loading_tvMsg);
        tvMsg.setText(getArguments().getString("Title"));

    }
}
