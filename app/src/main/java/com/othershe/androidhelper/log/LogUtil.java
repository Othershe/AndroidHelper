package com.othershe.androidhelper.log;

/**
 * 在Application中初始化：LogUtil.init(BuildConfig.LOG_DEBUG);
 * 在gradle中添加如下配置：
 *
 *   buildTypes {
 *      debug {
 *          buildConfigField "boolean", "LOG_DEBUG", "true"
 *      }
 *
 *      release {
 *          buildConfigField "boolean", "LOG_DEBUG", "false"
 *      }
 *  }
 *
 */

public class LogUtil {
    private static boolean IS_SHOW_LOG = true;

    private static final String DEFAULT_MESSAGE = "execute";

    public static final int X = 0x0;
    public static final int V = 0x1;
    public static final int D = 0x2;
    public static final int I = 0x3;
    public static final int W = 0x4;
    public static final int E = 0x5;
    public static final int A = 0x6;
    public static final int JSON = 0x7;

    public static void init(boolean isShowLog) {
        IS_SHOW_LOG = isShowLog;
    }

    public static void v() {
        printLog(V, X, null, DEFAULT_MESSAGE);
    }

    public static void v(Object msg) {
        printLog(V, X, null, msg);
    }

    public static void v(String tag, Object msg) {
        printLog(V, X, tag, msg);
    }

    public static void d() {
        printLog(D, X, null, DEFAULT_MESSAGE);
    }

    public static void d(Object msg) {
        printLog(D, X, null, msg);
    }

    public static void d(String tag, Object msg) {
        printLog(D, X, tag, msg);
    }

    public static void i() {
        printLog(I, X, null, DEFAULT_MESSAGE);
    }

    public static void i(Object msg) {
        printLog(I, X, null, msg);
    }

    public static void i(String tag, Object msg) {
        printLog(I, X, tag, msg);
    }

    public static void w() {
        printLog(W, X, null, DEFAULT_MESSAGE);
    }

    public static void w(Object msg) {
        printLog(W, X, null, msg);
    }

    public static void w(String tag, Object msg) {
        printLog(W, X, tag, msg);
    }

    public static void e() {
        printLog(E, X, null, DEFAULT_MESSAGE);
    }

    public static void e(Object msg) {
        printLog(E, X, null, msg);
    }

    public static void e(String tag, Object msg) {
        printLog(E, X, tag, msg);
    }

    public static void a() {
        printLog(A, X, null, DEFAULT_MESSAGE);
    }

    public static void a(Object msg) {
        printLog(A, X, null, msg);
    }

    public static void a(String tag, Object msg) {
        printLog(A, X, tag, msg);
    }

    public static void json(int level, Object msg) {
        printLog(JSON, level, null, msg);
    }

    public static void json(int level, String tag, Object msg) {
        printLog(JSON, level, tag, msg);
    }

    private static void printLog(int pLevel, int cLevel, String tagStr, Object... objects) {
        if (!IS_SHOW_LOG) {
            return;
        }

        String[] contents = Parse.wrapperContent(tagStr, objects);
        String tag = contents[0];
        String msg = contents[1];
        String headString = contents[2];

        switch (pLevel) {
            case V:
            case D:
            case I:
            case W:
            case E:
            case A:
                BaseLog.printDefault(pLevel, tag, headString + msg);
                break;
            case JSON:
                BaseLog.printJson(cLevel, tag, msg, headString);
                break;
        }
    }
}
