package com.othershe.androidhelper.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

public class ActivityUtil {
    private static List<Activity> activities = new ArrayList<>();

    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public static void finishAll() {
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

    public static void finishActivity(String name) {
        for (Activity activity : activities) {
            if (name.equals(activity.getClass().getName()) && !activity.isFinishing()) {
                activity.finish();
                break;
            }
        }
    }

    public static boolean isOpened(String fullName) {
        for (Activity activity : activities) {
            if (fullName.equals(activity.getClass().getName()) && !activity.isFinishing()) {
                return true;
            }
        }

        return false;
    }
}
