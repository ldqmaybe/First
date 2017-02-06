package com.example.administrator.myapplication.LoadMoreAddRefresh;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.myapplication.R;

import java.util.List;

/**
 * @author Admin
 * @time 2017/1/17 0017.17:00
 */
public class MyAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    private Context context;
    private List<String> datas;

    public MyAdapter(Context context, int layoutResId, List<String> datas) {
        super(layoutResId, datas);
        this.context = context;
        this.datas = datas;
    }


    @Override
    protected void convert(final BaseViewHolder helper, final String item) {
        helper.setText(R.id.text, item);
        helper.setOnClickListener(R.id.btnDelete, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datas.remove(helper.getAdapterPosition());
                notifyItemRemoved(helper.getAdapterPosition());
            }
        });
        helper.setOnClickListener(R.id.content, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, item, Toast.LENGTH_SHORT).show();
//                datas.remove(helper.getAdapterPosition());
//                notifyItemRemoved(helper.getAdapterPosition());
            }
        });
    }

    public void setDatas(List<String> data) {
        if (datas != null) {
            datas.clear();
        }
        datas.addAll(data);
        notifyDataSetChanged();
    }

    private SwipeListener swipeListener;

    public interface SwipeListener {
        void onDel(int position);
    }

    public void setOnDelListener(SwipeListener swipeListener) {
        this.swipeListener = swipeListener;
    }

}
