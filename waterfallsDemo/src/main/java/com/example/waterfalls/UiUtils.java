package com.example.waterfalls;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.ArrayRes;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

public class UiUtils {
    /**
     * 获取到字符数组
     *
     * @param tabNames 字符数组的id
     */
    public static String[] getStringArray(@ArrayRes int tabNames) {
        return getResource().getStringArray(tabNames);
    }

    //这里采用资源注解，如传递参数与资源注解不符合则会报错
    @ColorInt //代表返回一个颜色类型的id
    public static int getColor(@ColorRes int color) {
        return getResource().getColor(color);
    }

    @NonNull //当返回值为null时会出现警告
    public static String setText(@StringRes int str) {
        return getResource().getString(str);
    }

    //
    public static Resources getResource() {
        return MyApplication.getApplication().getResources();
    }

    /**
     * dip转换px
     */
    public static int dip2px(int dip) {
        final float scale = getResource().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }

    /**
     * px转换dip
     */

    public static int px2dip(int px) {
        final float scale = getResource().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }
}
