package com.example.administrator.myapplication.LoadMoreAddRefresh;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * @author Admin
 * @time 2017/2/9 0009.18:10
 */
public class TypeTest implements MultiItemEntity {
    String str;
    int type;

    public TypeTest(String str, int type) {
        this.str = str;
        this.type = type;
    }

    public TypeTest() {
    }
    @Override
    public int getItemType() {
        return type;
    }
    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
