package com.first.LoadMoreAddRefresh;

/**
 * @author Admin
 * @time 2017/1/17 0017.15:34
 */
public class ActivityItem {
    private String title;
    private  Class cls;

    public ActivityItem(Class cls) {
        this.cls = cls;
    }
    public ActivityItem(Class cls,String title) {
        this.cls = cls;
        this.title = title;
    }

    public Class getCls() {
        return cls;
    }

    public void setCls(Class cls) {
        this.cls = cls;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
