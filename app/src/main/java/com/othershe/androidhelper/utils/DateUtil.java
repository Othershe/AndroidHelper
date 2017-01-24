package com.othershe.androidhelper.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static final String TYPE1 = "yyyy-MM-dd HH:mm";
    public static final String TYPE2 = "yyyy年MM月dd日 HH:mm";
    public static final String TYPE3 = "yyyy年MM月dd日 E";
    public static final String TYPE4 = "HH:mm";

    public static String formatDate(String type) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(type);
        Date date = new Date();
        return simpleDateFormat.format(date);
    }

    public static String formatDate(long timeMills, String type) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(type);
        Date date = new Date(timeMills);
        return simpleDateFormat.format(date);
    }

    public static String getApm() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.AM_PM) == 0 ? "上午" : "下午";
    }

    public static int getYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

    public static int getMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH) + 1;
    }

    public static int getDay() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static int getWeek() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.WEEK_OF_MONTH);
    }

    public static int get12Hour() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.HOUR);
    }

    public static int get24Hour() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    public static int getMinute() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MINUTE);
    }

    public static int getSecond() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.SECOND);
    }

    public static String formatBeforeDate(long oldTimeMills) {

        long currentTimeMillis = System.currentTimeMillis();
        long tm = get24Hour() * 60 * 60 + getMinute() * 60 + getSecond();
        if (oldTimeMills < currentTimeMillis) {
            long d = (currentTimeMillis - oldTimeMills) / 1000;
            if (d <= 60) {
                return "1分钟前";
            } else if (d <= 60 * 60) {
                long m = d / 60;
                if (m <= 0) {
                    m = 1;
                }
                return m + "分钟前";
            } else if (d > 60 * 60 && d <= tm) {
                return formatDate(oldTimeMills, "今天 HH:mm");
            } else if (d > tm && d < tm + 24 * 60 * 60) {
                return formatDate(oldTimeMills, "昨天 HH:mm");
            }
        }
        return formatDate(TYPE1);
    }
}
