package com.example.administrator.myapplication.design;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.myapplication.R;

import java.util.ArrayList;
import java.util.List;


public class PageFragment extends BaseFragment {
    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;
    private RecyclerView lv;

    public static PageFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        PageFragment pageFragment = new PageFragment();
        pageFragment.setArguments(args);
        return pageFragment;
    }

    @Override
    protected LoadMode getMode() {
        return LoadMode.UNLAZY_LOAD_DATA;
    }

    @Override
    protected View getView(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.fragment_page, null);
        lv = (RecyclerView) view.findViewById(R.id.lv);
        Toast.makeText(getActivity(), "position:" + mPage, Toast.LENGTH_SHORT).show();
        // 创建一个线性布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        // 设置布局管理器
        lv.setLayoutManager(layoutManager);
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 100; i++) {
            list.add(i + "");
        }
        lv.setAdapter(new XituRecyclerViewAdapter(list));
        return view;
    }

    @Override
    protected void loadData() {}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

}
