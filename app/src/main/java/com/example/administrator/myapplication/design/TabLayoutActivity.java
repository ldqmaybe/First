package com.example.administrator.myapplication.design;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.cn.baselib.base.BaseActivity;
import com.cn.baselib.base.BasePresenter;
import com.example.administrator.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class TabLayoutActivity extends BaseActivity {
    @Bind(R.id.tab_FindFragment_title)
    TabLayout tab_FindFragment_title;
    @Bind(R.id.vp_FindFragment_pager)
    ViewPager vp_FindFragment_pager;
    private FragmentPagerAdapter fAdapter;                               //定义adapter

    private List<Fragment> list_fragment;                                //定义要装fragment的列表
    private List<String> list_title;                                     //tab名称列表

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_tab_layout;
    }

    @Override
    protected void initView() {
        //初始化各fragment
        Find_hotToday hotToday0 = new Find_hotToday();
        Find_hotToday hotToday1 = new Find_hotToday();
        Find_hotToday hotToday2 = new Find_hotToday();
        Find_hotToday hotToday3 = new Find_hotToday();
        Find_hotToday hotToday4 = new Find_hotToday();
        Find_hotToday hotToday5 = new Find_hotToday();
        Find_hotToday hotToday6 = new Find_hotToday();
        Find_hotToday hotToday7 = new Find_hotToday();
        Find_hotToday hotToday8 = new Find_hotToday();
        Find_hotToday hotToday9 = new Find_hotToday();

        //将fragment装进列表中
        list_fragment = new ArrayList<>();
        list_fragment.add(hotToday0);
        list_fragment.add(hotToday1);
        list_fragment.add(hotToday2);
        list_fragment.add(hotToday3);
        list_fragment.add(hotToday4);
        list_fragment.add(hotToday5);
        list_fragment.add(hotToday6);
        list_fragment.add(hotToday7);
        list_fragment.add(hotToday8);
        list_fragment.add(hotToday9);

        //将名称加载tab名字列表，正常情况下，我们应该在values/arrays.xml中进行定义然后调用
        list_title = new ArrayList<>();
        list_title.add("热门推荐");
        list_title.add("热门收藏");
        list_title.add("本月热榜");
        list_title.add("今日热榜");
        list_title.add("今日热榜");
        list_title.add("今日热榜");
        list_title.add("今日热榜");
        list_title.add("今日热榜");
        list_title.add("今日热榜");
        list_title.add("今日热榜");

        //设置TabLayout的模式
//        tab_FindFragment_title.setTabMode(TabLayout.LAYOUT_MODE_OPTICAL_BOUNDS);
//        tab_FindFragment_title.setTabMode(TabLayout.LAYOUT_MODE_CLIP_BOUNDS);
//        tab_FindFragment_title.setTabMode(TabLayout.FOCUSABLES_TOUCH_MODE);
        tab_FindFragment_title.setTabMode(TabLayout.MODE_SCROLLABLE);
//        tab_FindFragment_title.setTabMode(TabLayout.MODE_FIXED);
        //为TabLayout添加tab名称
        tab_FindFragment_title.addTab(tab_FindFragment_title.newTab().setText(list_title.get(0)));
        tab_FindFragment_title.addTab(tab_FindFragment_title.newTab().setText(list_title.get(1)));
        tab_FindFragment_title.addTab(tab_FindFragment_title.newTab().setText(list_title.get(2)));
        tab_FindFragment_title.addTab(tab_FindFragment_title.newTab().setText(list_title.get(3)));
        tab_FindFragment_title.addTab(tab_FindFragment_title.newTab().setText(list_title.get(4)));
        tab_FindFragment_title.addTab(tab_FindFragment_title.newTab().setText(list_title.get(5)));
        tab_FindFragment_title.addTab(tab_FindFragment_title.newTab().setText(list_title.get(6)));
        tab_FindFragment_title.addTab(tab_FindFragment_title.newTab().setText(list_title.get(7)));
        tab_FindFragment_title.addTab(tab_FindFragment_title.newTab().setText(list_title.get(8)));
        tab_FindFragment_title.addTab(tab_FindFragment_title.newTab().setText(list_title.get(9)));

        fAdapter = new Find_tab_Adapter(getSupportFragmentManager(), list_fragment, list_title);

        //viewpager加载adapter
        vp_FindFragment_pager.setAdapter(fAdapter);
        //TabLayout加载viewpager
        vp_FindFragment_pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab_FindFragment_title));
        tab_FindFragment_title.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(vp_FindFragment_pager));
//        tab_FindFragment_title.setupWithViewPager(vp_FindFragment_pager);
        //tab_FindFragment_title.set
    }
}
