package com.first.LoadMoreAddRefresh;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;

/**
 * @author Admin
 * @time 2017/3/1 0001.11:31
 */
public class MyViewholder extends BaseViewHolder {
    public MyViewholder(View view) {
        super(view);
    }

    public MyViewholder setText(int viewId, CharSequence value,boolean flag) {
        TextView view = getView(viewId);
        view.setText(value);
        return this;
    }
}
