package com.example.administrator.myapplication;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * @author Admin
 * @time 2016/11/3 0003.15:00
 */
public class MyApp extends Application {

    private static Context context = null;
    private static Handler handler = null;
    private static long mainThreadId;

    public static Context getContext() {
        return context;
    }

    public static Handler getHandler() {
        return handler;
    }

    public static long getMainThreadId() {
        return mainThreadId;
    }
    @Override
    public void onCreate() {
        super.onCreate();
//        JPushInterface.setDebugMode(true);
//        JPushInterface.init(this);
        context = getApplicationContext();
        mainThreadId = android.os.Process.myTid();
        handler = new Handler();
    }
}
