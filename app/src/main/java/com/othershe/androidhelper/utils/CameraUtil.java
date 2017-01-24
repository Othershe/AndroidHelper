package com.othershe.androidhelper.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CameraUtil {
    private static Uri imageUri;

    /**
     * 启动相机
     *
     * @param context
     * @param imagePath
     * @param requestCode 拍照的请求码
     */
    public static void captureImage(Activity context, String imagePath, int requestCode) {
        if (!hasCamera(context)) {
            return;
        }

        if (TextUtils.isEmpty(imagePath)) {
            return;
        }

        File outputImage = FileUtil.createFile(imagePath);
        imageUri = Uri.fromFile(outputImage);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        context.startActivityForResult(intent, requestCode);
    }

    /**
     * call at onActivityResult
     * 获得正常拍照得到的bitmap
     *
     * @param imagePath
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static Bitmap handleCapturedImage(String imagePath, int reqWidth, int reqHeight) {
        Bitmap bitmap = BitmapUtil.fileToBitmap(imagePath, reqWidth, reqHeight);

        int degree;
        if (bitmap != null && (degree = BitmapUtil.getBitmapDegree(imagePath)) != 0) {
            bitmap = BitmapUtil.rotateBitmapByDegree(bitmap, degree);
        }
        return bitmap;
    }

    /**
     * 启动裁剪意图
     *
     * @param context
     * @param cropRequestCode 裁剪照片的请求码
     */
    public static void startCropImage(Activity context, int cropRequestCode) {
        cropImage(context, imageUri, cropRequestCode);
    }

    /**
     * call at onActivityResult
     * 获得裁剪拍照得到的bitmap
     *
     * @param context
     * @return
     */
    public static Bitmap handleCropCaptureImage(Context context) {
        try {
            return MediaStore.Images.Media.getBitmap(context.getContentResolver(), imageUri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 裁剪意图
     *
     * @param context
     * @param imageUri
     * @param cropRequestCode
     */
    private static void cropImage(Activity context, Uri imageUri, int cropRequestCode) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(imageUri, "image/*");
        intent.putExtra("crop", "true");//设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("aspectX", 1);//宽高比例
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 250);//初始的裁剪框宽高
        intent.putExtra("outputY", 250);
        intent.putExtra("scale", true);//去黑边
        intent.putExtra("scaleUpIfNeeded", true);//去黑边
        intent.putExtra("outputFormat", "JPG");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        context.startActivityForResult(intent, cropRequestCode);
    }

    /**
     * 是否有可拍照的app
     *
     * @param context
     * @return
     */
    public static boolean hasCamera(Context context) {
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }
}
