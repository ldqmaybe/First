package com.example.administrator.myapplication.LoadMoreAddRefresh;

import android.content.Context;
import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.myapplication.R;

import java.util.List;

/**
 * @author Admin
 * @time 2017/2/10 0010.9:41
 */
public class MultipleAdapter extends BaseMultiItemQuickAdapter<TypeTest,BaseViewHolder> {
    private List<TypeTest> datas;
    private Context context;
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public MultipleAdapter(Context context,List<TypeTest> data) {
        super(data);
        this.context = context;
        this.datas = data;
        addItemType(1, R.layout.item);
        addItemType(2, R.layout.item2);
    }

    @Override
    protected void convert(BaseViewHolder helper, final TypeTest item) {

        switch (helper.getItemViewType()) {
            case 1:
                final int pos = helper.getAdapterPosition();//有几个header就减去多少
                helper.setText(R.id.text, item.getStr());
                helper.setOnClickListener(R.id.content, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Toast.makeText(context, item.getStr()+"====position="+pos, Toast.LENGTH_SHORT).show();
                        datas.remove(pos);
//                notifyItemRemoved(pos);
                        notifyDataSetChanged();
                    }
                });
                break;
            case 2:
                final int pos2 = helper.getAdapterPosition();
                helper.setText(R.id.text, item.getStr());
                helper.setOnClickListener(R.id.content, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Toast.makeText(context, item.getStr()+"====position="+pos2, Toast.LENGTH_SHORT).show();
                        datas.remove(pos2);
//                notifyItemRemoved(pos);
                        notifyDataSetChanged();
                    }
              });
        }
    }

    public void setDatas(List<TypeTest> data) {
        if (this.datas != null) {
            this.datas.clear();
        }
        this.datas.addAll(data);
        notifyDataSetChanged();
    }
}
