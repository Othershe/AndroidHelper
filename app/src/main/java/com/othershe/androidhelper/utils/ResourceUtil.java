package com.othershe.androidhelper.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ResourceUtil {

    public static String resToString(Context context, int strId) {
        return context.getString(strId);
    }

    public static int resToColor(Context context, int colorId) {
        return context.getResources().getColor(colorId);
    }

    public static View resToView(Context context, ViewGroup parent, int viewId) {
        return LayoutInflater.from(context).inflate(viewId, parent, false);
    }


    /**
     * 解析String 类型的array.xml元素
     *
     * @param context
     * @param arrayId
     * @return
     */
    public static String[] stringResToArray(Context context, int arrayId) {
        return context.getResources().getStringArray(arrayId);
    }

    /**
     * 解析Integer 类型的array.xml元素
     *
     * @param context
     * @param arrayId
     * @return
     */
    public static int[] intResToArray(Context context, int arrayId) {
        return context.getResources().getIntArray(arrayId);
    }
}
