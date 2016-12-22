package com.example.administrator.myapplication.retrofit;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.example.administrator.myapplication.MyApp;

/**
 * @author 172042886@qq.com
 * @time 2016/3/28.16:44
 */
public class UIUtils {
    public static Context getContext() {
        return MyApp.getContext();
    }

    public static Resources getResource() {
        return getContext().getResources();
    }

    public static long getMainThreadId() {
        return MyApp.getMainThreadId();
    }

    public static Handler getMainThreadHandler() {
        return MyApp.getHandler();
    }


    public static void showShortToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public static void showShortToast(int resid) {
        showShortToast(getContext().getResources().getString(resid));
    }

    public static void showLongToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
    }

    public static void showLongToast(int resid) {
        showLongToast(getContext().getResources().getString(resid));
    }

    /**
     * 安全的执行一个任务
     *
     * @param task
     */
    public static void postTaskSafely(Runnable task) {
        int curThread = android.os.Process.myTid();
        if (curThread == getMainThreadId()) {
            task.run();
        } else {
            getMainThreadHandler().post(task);
        }
    }


    /**
     * 延迟执行任务
     */
    public static void postTaskDelay(Runnable task, int delayMillis) {
        getMainThreadHandler().postDelayed(task, delayMillis);
    }

    public static void sendMsg(Handler handler, int code, Object obj) {
        Message message = Message.obtain();
        message.obj = obj;
        message.what = code;
        handler.sendMessage(message);
    }


    /**
     * px-->dip
     */
    public static int px2dp(int px) {
        // px/dip = density;
        float density = getResource().getDisplayMetrics().density;
        int dip = (int) (px / density + .5f);
        return dip;
    }

}
