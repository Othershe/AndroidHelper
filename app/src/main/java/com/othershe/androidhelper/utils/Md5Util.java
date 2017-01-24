package com.othershe.androidhelper.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {

    /**
     * 将字符串进行MD5编码
     *
     * @param str
     * @return
     */
    public static String md5Encode(String str) {
        String tempStr;
        try {
            final MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(str.getBytes());
            tempStr = bytesToHexString(mDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            tempStr = String.valueOf(str.hashCode());
        }
        return tempStr;
    }

    /**
     * bytes to hex string
     *
     * @param bytes
     * @return
     */
    private static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }
}
