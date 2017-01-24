package com.othershe.androidhelper.log;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BaseLog {
    /**
     * msg长度大于4k时分行处理
     *
     * @param level
     * @param tag
     * @param msg
     */
    public static void printDefault(int level, String tag, String msg) {

        int index = 0;
        int maxLength = 4000;
        int countOfSub = msg.length() / maxLength;

        if (countOfSub > 0) {
            for (int i = 0; i < countOfSub; i++) {
                String sub = msg.substring(index, index + maxLength);
                printSub(level, tag, sub);
                index += maxLength;
            }
            printSub(level, tag, msg.substring(index, msg.length()));
        } else {
            printSub(level, tag, msg);
        }
    }

    /**
     * 打印log
     *
     * @param level
     * @param tag
     * @param sub
     */
    private static void printSub(int level, String tag, String sub) {
        switch (level) {
            case LogUtil.V:
                Log.v(tag, sub);
                break;
            case LogUtil.D:
                Log.d(tag, sub);
                break;
            case LogUtil.I:
                Log.i(tag, sub);
                break;
            case LogUtil.W:
                Log.w(tag, sub);
                break;
            case LogUtil.E:
                Log.e(tag, sub);
                break;
            case LogUtil.A:
                Log.wtf(tag, sub);
                break;
        }
    }

    /**
     * 解析json，并打印
     *
     * @param level
     * @param tag
     * @param msg
     * @param headString
     */
    public static void printJson(int level, String tag, String msg, String headString) {

        String message;

        try {
            if (msg.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(msg);
                message = jsonObject.toString(Util.JSON_INDENT);
            } else if (msg.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(msg);
                message = jsonArray.toString(Util.JSON_INDENT);
            } else {
                message = msg;
            }
        } catch (JSONException e) {
            message = msg;
        }

        Util.printLine(level, tag, true);
        String LINE_SEPARATOR = System.getProperty("line.separator");
        message = headString + LINE_SEPARATOR + message;
        String[] lines = message.split(LINE_SEPARATOR);
        for (String line : lines) {
            printDefault(level, tag, "| " + line);
        }

        Util.printLine(level, tag, false);
    }
}
