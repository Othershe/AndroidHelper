package com.othershe.androidhelper.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.Toast;

public class ToastUtil {
    private static Toast toast;
    private static long firstTime;
    private static String oldMessage;
    private static String looper;
    private static String lastLooper;
    private static Context mContext;

    public static void showToast(Context context, int strId) {
        mContext = context;
        String message = mContext.getString(strId);
        showToast(mContext, message);
    }

    public static void showToast(Context context, final String message) {
        if (TextUtils.isEmpty(message)) {
            return;
        }
        if (mContext == null) {
            mContext = context;
        }
        if (Looper.myLooper() == Looper.getMainLooper()) {
            looper = "main";
            resetToast();
            initToast(message);
        } else {
            looper = "child";
            resetToast();
            Looper.prepare();
            Handler handler = new Handler();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    initToast(message);
                }
            });
            Looper.loop();
        }
    }

    private static void initToast(String message) {
        if (toast == null) {
            toast = Toast.makeText(mContext, message, Toast.LENGTH_SHORT);
            toast.show();
            firstTime = System.currentTimeMillis();
        } else {
            long secondTime = System.currentTimeMillis();
            if (message.equals(oldMessage)) {
                if (secondTime - firstTime > Toast.LENGTH_SHORT) {
                    toast.show();
                }
            } else {
                toast.setText(message);
                toast.show();
            }
            firstTime = secondTime;
        }
        oldMessage = message;
    }

    private static void resetToast() {
        if (!looper.equals(lastLooper)) {
            if (toast != null) {
                toast.cancel();
                toast = null;
            }
            lastLooper = looper;
        }
    }
}
