package com.othershe.androidhelper.utils;

public class NumberUtil {

    public static int stringToInt(String value) {
        try {
            return Integer.valueOf(value);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static float stringToFloat(String value) {
        try {
            return Float.valueOf(value);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static double stringToDouble(String value) {
        try {
            return Double.valueOf(value);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static double stringToLong(String value) {
        try {
            return Long.valueOf(value);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String integerToString(int value) {
        try {
            return String.valueOf(value);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String floatToString(float value) {
        try {
            return String.valueOf(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String doubleToString(double value) {
        try {
            return String.valueOf(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String LongToString(long value) {
        try {
            return String.valueOf(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String stringFormat(float value, int cnt) {
        try {
            return String.format("%." + cnt + "f", value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
