package com.othershe.androidhelper.utils;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

public class FileUtil {
    /**
     * 判断文件是否存在
     *
     * @param filePath
     * @return
     */
    public static boolean isFileExit(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return false;
        }
        File file = new File(filePath);
        return file.isFile() && file.exists();
    }

    /**
     * 删除文件（按路径）
     *
     * @param filePath
     * @return
     */
    public static boolean deleteFile(String filePath) {
        if (!isFileExit(filePath)) {
            return false;
        }
        File file = new File(filePath);
        return file.delete();
    }

    /**
     * 删除文件
     *
     * @param file
     * @return
     */
    public static boolean deleteFile(File file) {
        return file != null && file.delete();
    }

    /**
     * 获得文件长度
     *
     * @param filePath
     * @return
     */
    public static long getFileSize(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return 0;
        }
        File file = new File(filePath);
        if (!file.isFile()) {
            return 0;
        }
        return file.length();
    }

    /**
     * 格式化文件大小
     *
     * @param fileSize
     * @return
     */
    public static String formatFileSize(long fileSize) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString;
        if (fileSize < 1024) {
            fileSizeString = df.format((double) fileSize) + "B";
        } else if (fileSize < 1048576) {
            fileSizeString = df.format((double) fileSize / 1024) + "K";
        } else if (fileSize < 1073741824) {
            fileSizeString = df.format((double) fileSize / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) fileSize / 1073741824) + "G";
        }
        return fileSizeString;
    }

    /**
     * 获取文件扩展名
     *
     * @param fileName
     * @return
     */
    public static String getFileExtensionName(String fileName) {
        if (!TextUtils.isEmpty(fileName)) {
            int dot = fileName.lastIndexOf('.');
            if ((dot > -1) && (dot < (fileName.length() - 1))) {
                return fileName.substring(dot + 1);
            }
        }
        return fileName;
    }

    /**
     * 重命名文件
     *
     * @param oldPath
     * @param newPath
     * @return
     */
    public static boolean renameFile(String oldPath, String newPath) {
        if (TextUtils.isEmpty(oldPath) || TextUtils.isEmpty(newPath)) {
            return false;
        }
        File oldFile = new File(oldPath);
        File newFile = new File(newPath);

        return oldFile.renameTo(newFile);
    }

    /**
     * 根据绝对路径获取文件名
     *
     * @param path
     * @return
     */
    public static String getFileNameByPath(String path) {
        if (TextUtils.isEmpty(path)) {
            return "";
        }

        int slot = path.lastIndexOf('/');
        if (slot <= 0) {
            return "";
        }
        return path.substring(slot + 1);
    }

    /**
     * 根据路径创建文件
     *
     * @param filePath
     * @return
     */
    public static File createFile(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return null;
        }
        File file = new File(filePath);

        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (file.exists()) {
            file.delete();
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    /**
     * 输入流转文件
     *
     * @param inputStream
     * @param savePath
     * @return
     */
    public static boolean inputStreamToFile(InputStream inputStream, String savePath) {
        if (inputStream == null || TextUtils.isEmpty(savePath)) {
            return false;
        }

        boolean result = false;

        FileOutputStream fos = null;
        try {
            File file = createFile(savePath);
            fos = new FileOutputStream(file);
            int bytesRead;
            byte[] buffer = new byte[8192];
            while ((bytesRead = inputStream.read(buffer, 0, 8192)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
                inputStream.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 字节转文件
     *
     * @param bytes
     * @param savePath
     * @return
     */
    public static boolean bytesToFile(byte[] bytes, String savePath) {
        if (bytes == null || TextUtils.isEmpty(savePath)) {
            return false;
        }

        boolean result = false;

        FileOutputStream outStream = null;
        try {
            File file = createFile(savePath);
            outStream = new FileOutputStream(file);
            outStream.write(bytes);
            outStream.flush();

            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outStream != null) {
                try {
                    outStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }

    /**
     * 文件复制
     *
     * @param srcPath
     * @param dstPath
     */
    public static boolean copyFile(String srcPath, String dstPath) {
        if (TextUtils.isEmpty(srcPath) || TextUtils.isEmpty(dstPath)) {
            return false;
        }
        File srcFile = new File(srcPath);
        if (!srcFile.exists() || !srcFile.isFile() || !srcFile.canRead()) {
            return false;
        }

        File destFile = createFile(dstPath);

        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {
            fis = new FileInputStream(srcFile);
            fos = new FileOutputStream(destFile);
            byte bt[] = new byte[1024];
            int c;
            while ((c = fis.read(bt)) > 0) {
                fos.write(bt, 0, c);
            }
        } catch (Exception e) {
            return false;
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }

                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return true;
    }

    /**
     * raw资源转文件
     *
     * @param context
     * @param resId
     * @param dstPath
     * @return
     */
    public static boolean copyRawFile(Context context, int resId, String dstPath) {
        if (context == null) {
            return false;
        }

        File destFile = new File(dstPath);
        if (!destFile.getParentFile().exists() || destFile.exists()) {
            return false;
        }
        InputStream is = null;
        FileOutputStream fos = null;
        try {
            is = context.getResources().openRawResource(resId);
            fos = new FileOutputStream(dstPath);
            byte[] buffer = new byte[8192];
            int count;
            while ((count = is.read(buffer)) > 0) {
                fos.write(buffer, 0, count);
            }
            fos.close();
            is.close();
        } catch (Exception e) {
            return false;
        } finally {
            try {
                if (is != null) {
                    is.close();
                }

                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return true;
    }

    /**
     * 获得内存绝对路径
     *
     * @return
     */
    public static String getExternalStorageAbsolutePath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    /**
     * 是否有sd卡
     *
     * @return
     */
    public static boolean isHasSdcard() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }
}
