package com.example.waterfalls;

import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by Administrator on 2015/10/28 0028.
 */
public class MyApplication extends Application {

    private static MyApplication application;

    public static boolean loading;

    public static boolean isLoading() {
        return loading;
    }

    public static void setLoading(boolean loading) {
        MyApplication.loading = loading;
    }

    /**
     * DisplayMetrics是系统提供的显示实际屏幕的相关信息。可以获取当前手机屏幕的分辨率，大小等等
     */

    private static DisplayMetrics dm = new DisplayMetrics();

    public static DisplayMetrics getDisplayMetrics() {
        return dm;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
       
    }

    public static Context getApplication() {
        return application;
    }
}
