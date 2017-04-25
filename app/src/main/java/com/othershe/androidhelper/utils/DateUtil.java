package com.othershe.androidhelper.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static final String TYPE1 = "yyyy-MM-dd HH:mm";
    public static final String TYPE2 = "yyyy年MM月dd日 HH:mm";
    public static final String TYPE3 = "yyyy年MM月dd日 E";
    public static final String TYPE4 = "HH:mm";
    public static final String TYPE5 = "yyyy-MM-dd";

    public static String formatDate(String type) {
        SimpleDateFormat sdf = new SimpleDateFormat(type);
        Date date = new Date();
        return sdf.format(date);
    }

    public static String formatDate(long timeMills, String type) {
        SimpleDateFormat sdf = new SimpleDateFormat(type);
        Date date = new Date(timeMills);
        return sdf.format(date);
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

    public static String formatDate(long times) {
        if (times == 0) {
            return "";
        }
        String result;

        long curTimes = System.currentTimeMillis();

        long deltaTimes = curTimes - times;

        if (formatDate(times, TYPE1).equals(formatDate(TYPE5))) {//同一天
            if (deltaTimes < 60000) {//一分钟内
                result = "刚刚";
            } else if (deltaTimes < 3600000) {//一小时内
                result = deltaTimes / 60000 + "分钟前";
            } else {
                result = deltaTimes / 3600000 + "小时前";
            }
        } else {
            int day;
            if (deltaTimes / 3600000 < 24) {
                day = 1;
            } else {
                day = (int) (deltaTimes / (24 * 3600000));
            }

            if (day == 1) {
                result = "昨天";
            } else if (day < 31) {
                result = day + "天前";
            } else {
                result = formatDate(times, TYPE1);
            }
        }

        return result;
    }

    private static String getWeekStr() {
        int week = getWeek();
        String str = "";
        switch (week) {
            case 1:
                str = "一";
                break;
            case 2:
                str = "二";
                break;
            case 3:
                str = "三";
                break;
            case 4:
                str = "四";
                break;
            case 5:
                str = "五";
                break;
            case 6:
                str = "六";
                break;
            case 7:
                str = "天";
                break;
        }
        return str;
    }
}
