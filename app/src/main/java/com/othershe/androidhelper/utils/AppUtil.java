package com.othershe.androidhelper.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AppUtil {
    /**
     * 安装app
     *
     * @param context
     * @param path
     */
    public static void installApp(Context context, String path) {
        if (context == null || !FileUtil.isFileExit(path)) {
            return;
        }
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(new File(path)), "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    /**
     * 卸载app
     *
     * @param context
     * @param packageName
     */
    public static void unInstallApp(Context context, String packageName) {
        if (context == null || TextUtils.isEmpty(packageName)) {
            return;
        }
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setAction("android.intent.action.DELETE");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setData(Uri.parse("package:" + packageName));
        context.startActivity(intent);
    }

    /**
     * 启动app
     *
     * @param context
     * @param packageName
     */
    public static void launcherApp(Context context, String packageName) {
        if (context == null || TextUtils.isEmpty(packageName) || !isAppInstalled(context, packageName)) {
            return;
        }

        Intent intent = context.getPackageManager().getLaunchIntentForPackage(packageName);
        context.startActivity(intent);
    }

    /**
     * app是否已安装
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean isAppInstalled(Context context, String packageName) {
        if (context == null || TextUtils.isEmpty(packageName)) {
            return false;
        }

        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> packageInfoList = packageManager.getInstalledPackages(0);
        List<String> pName = new ArrayList<String>();
        if (packageInfoList != null) {
            for (int i = 0; i < packageInfoList.size(); i++) {
                String pn = packageInfoList.get(i).packageName;
                pName.add(pn);
            }
        }
        return pName.contains(packageName);
    }

    /**
     * 获得已安装app 名字：包名
     *
     * @param context
     * @return
     */
    public static ArrayList<String> getInstalledAppNames(Context context) {
        ArrayList<String> result = new ArrayList<String>();

        PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        if (packageInfos == null) {
            return result;
        }

        for (int i = 0; i < packageInfos.size(); i++) {
            PackageInfo packageInfo = packageInfos.get(i);
            if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                String pkgName = packageInfo.packageName;
                String appName = packageInfo.applicationInfo.loadLabel(packageManager).toString();
                result.add(appName + ":" + pkgName);
            }
        }

        return result;
    }

    /**
     * 获得已安装app信息
     *
     * @param context
     * @return
     */
    public static ArrayList<PackageInfo> getInstalledAppInfos(Context context) {
        ArrayList<PackageInfo> result = new ArrayList<PackageInfo>();

        PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        if (packageInfos == null) {
            return result;
        }

        for (int i = 0; i < packageInfos.size(); i++) {
            PackageInfo packageInfo = packageInfos.get(i);
            if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                result.add(packageInfo);
            }
        }

        return result;
    }

    /**
     * 判断app是否在前台运行
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean isAppForegroundRunning(Context context, String packageName) {
        if (context == null || TextUtils.isEmpty(packageName)) {
            return false;
        }

        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runAppProcess = am.getRunningAppProcesses();

        if (runAppProcess == null || runAppProcess.isEmpty()) {
            return false;
        }

        for (ActivityManager.RunningAppProcessInfo appProcess : runAppProcess) {
            if (appProcess.processName.equals(packageName)
                    && appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true;
            }
        }
        return false;
    }
}
