package com.example.administrator.myapplication.retrofit;

import android.content.Context;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.BaseAdapterHelper;
import com.example.administrator.myapplication.adapter.QuickAdapter;

/**
 * @author Admin
 * @time 2016/12/8 0008.11:22
 */
public class MovieAdapter extends QuickAdapter<Movie> {
    public MovieAdapter(Context context, int layoutResId) {
        super(context, layoutResId);
    }

    @Override
    protected void convert(BaseAdapterHelper helper, Movie item) {
        helper.setText(R.id.mv_name, item.getNm())
                .setText(R.id.mv_dec, item.getScm())
                .setText(R.id.mv_date, item.getShowInfo())
                .setImageResource(R.id.image, R.mipmap.ic_launcher);
    }

}
