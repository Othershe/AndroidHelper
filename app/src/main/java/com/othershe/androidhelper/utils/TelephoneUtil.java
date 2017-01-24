package com.othershe.androidhelper.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

public class TelephoneUtil {
    public static String NETWORK_TYPE_2G = "2G";
    public static String NETWORK_TYPE_3G = "3G";
    public static String NETWORK_TYPE_4G = "4G";
    public static String NETWORK_TYPE_WIFI = "WIFI";

    public static String OPERATOR_TYPE_MOBILE = "中国移动";
    public static String OPERATOR_TYPE_UNICOM = "中国联通";
    public static String OPERATOR_TYPE_TELECOM = "中国电信";

    /**
     * 获取IMEI码
     *
     * @param context
     * @return
     */
    public static String getIMEI(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String imei = tm.getDeviceId();
        return imei != null ? imei : "";
    }

    /**
     * 获取运营商
     *
     * @param context
     * @return
     */
    public static String getOperatorType(Context context) {
        String operatorName = "";
        TelephonyManager tm = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE));
        String IMSI = tm.getSubscriberId();
        if (IMSI != null) {
            if (IMSI.startsWith("46000") || IMSI.startsWith("46002")) {
                operatorName = OPERATOR_TYPE_MOBILE;
            } else if (IMSI.startsWith("46001")) {
                operatorName = OPERATOR_TYPE_UNICOM;
            } else if (IMSI.startsWith("46003")) {
                operatorName = OPERATOR_TYPE_TELECOM;
            }
        }
        return operatorName;
    }

    /**
     * 获取网络类型
     *
     * @param context
     * @return
     */
    public static String getNetworkType(Context context) {
        String networkType = null;

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                networkType = NETWORK_TYPE_WIFI;
            } else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                String _strSubTypeName = networkInfo.getSubtypeName();

                int type = networkInfo.getSubtype();
                switch (type) {
                    case TelephonyManager.NETWORK_TYPE_GPRS:
                    case TelephonyManager.NETWORK_TYPE_EDGE:
                    case TelephonyManager.NETWORK_TYPE_CDMA:
                    case TelephonyManager.NETWORK_TYPE_1xRTT:
                    case TelephonyManager.NETWORK_TYPE_IDEN:
                        networkType = NETWORK_TYPE_2G;
                        break;
                    case TelephonyManager.NETWORK_TYPE_UMTS:
                    case TelephonyManager.NETWORK_TYPE_EVDO_0:
                    case TelephonyManager.NETWORK_TYPE_EVDO_A:
                    case TelephonyManager.NETWORK_TYPE_HSDPA:
                    case TelephonyManager.NETWORK_TYPE_HSUPA:
                    case TelephonyManager.NETWORK_TYPE_HSPA:
                    case TelephonyManager.NETWORK_TYPE_EVDO_B:
                    case TelephonyManager.NETWORK_TYPE_EHRPD:
                    case TelephonyManager.NETWORK_TYPE_HSPAP:
                        networkType = NETWORK_TYPE_3G;
                        break;
                    case TelephonyManager.NETWORK_TYPE_LTE:
                        networkType = NETWORK_TYPE_4G;
                        break;
                    default:
                        if (_strSubTypeName.equalsIgnoreCase("TD-SCDMA")
                                || _strSubTypeName.equalsIgnoreCase("WCDMA")
                                || _strSubTypeName.equalsIgnoreCase("CDMA2000")) {
                            networkType = NETWORK_TYPE_3G;
                        } else {
                            networkType = _strSubTypeName;
                        }
                        break;
                }
            }
        }
        return networkType;
    }

    /**
     * 获取电话号码
     *
     * @param context
     * @return
     */
    public static String getPhoneNum(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String phoneNum = tm.getLine1Number();
        return phoneNum != null ? phoneNum : "";
    }
}
