package com.first.LoadMoreAddRefresh;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.cn.baselib.base.BaseActivity;
import com.first.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;


public class HorizonRecycleviewActivity extends BaseActivity {

    @Bind(R.id.rv)
    RecyclerView rv;
    @Bind(R.id.rv2)
    RecyclerView rv2;
    BaseQuickAdapter<String, BaseViewHolder> adapter1;
    BaseQuickAdapter<String, BaseViewHolder> adapter2;
    private View emptyView;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_horizon_recycleview;
    }

    @Override
    protected void initView() {
        emptyView = getLayoutInflater().inflate(R.layout.empty_view, null);
        initRV1();
        initRV2();
        initListener();
    }

    private void initRV1() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv.setLayoutManager(linearLayoutManager);
        adapter1 = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.horizon_item) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                if (helper.getAdapterPosition() % 2 == 0) {
                    helper.setBackgroundColor(R.id.tv, R.color.colorAccent);
                }
                helper.setText(R.id.tv, item);
            }
        };
        adapter1.setNewData(getDatas(15));
        rv.setAdapter(adapter1);
    }

    private void initRV2() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv2.setLayoutManager(linearLayoutManager);
        adapter2 = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.item) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                helper.setText(R.id.text, item);
            }
        };
        adapter2.setNewData(getDatas(15));
        rv2.setAdapter(adapter2);
    }

    private void initListener() {
        rv.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (position==0) {
                    adapter2.setEmptyView(emptyView);
                }
                adapter2.setNewData(getDatas(position));
            }
        });
        rv2.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(HorizonRecycleviewActivity.this,"positioin="+position , Toast.LENGTH_SHORT).show();
            }
        });
    }

    private List<String> getDatas(int position) {
        List<String> listData = new ArrayList<>();
        for (int i = 0; i < position; i++) {
            listData.add("item  " + i);
        }
        return listData;
    }
}
