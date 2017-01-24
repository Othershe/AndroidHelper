package com.othershe.androidhelper.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import java.util.HashMap;
import java.util.Map;

public class NotificationUtil {

    private static Map<Integer, NotificationCompat.Builder> notificationMap = new HashMap<>();

    private static NotificationManager notificationManager;


    private static NotificationManager initNotificationManager(Context context) {
        if (notificationManager == null) {
            notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        }

        return notificationManager;
    }

    /**
     * 创建普通通知栏
     *
     * @param context
     * @param title
     * @param content
     * @param smallIcon
     * @param largeIcon
     * @param listener
     */
    public static void createNormalNotification(Context context, String title, String content,
                                                int smallIcon, int largeIcon, ClickNotificationListener listener) {
        initNotificationManager(context);

        NotificationCompat.Builder builder = initBaseBuilder(context, title, content, smallIcon, largeIcon);

        if (listener == null && AndroidUtil.getApiLevel() < Build.VERSION_CODES.HONEYCOMB) {
            Intent intent = new Intent();
            PendingIntent contentIntent = PendingIntent.getActivity(context, 0, intent, 0);
            builder.setContentIntent(contentIntent);
        } else {
            if (listener != null) {
                PendingIntent contentIntent = PendingIntent.getActivity(context, 0, listener.onClickNotification(), 0);
                builder.setContentIntent(contentIntent);
            }
        }

        notificationManager.notify((int) System.currentTimeMillis(), builder.build());
    }

    /**
     * 重载
     *
     * @param context
     * @param titleId
     * @param contentId
     * @param smallIcon
     * @param largeIcon
     * @param listener
     */
    public static void createNormalNotification(Context context, int titleId, int contentId,
                                                int smallIcon, int largeIcon, ClickNotificationListener listener) {
        createNormalNotification(context, ResourceUtil.resToString(context, titleId),
                ResourceUtil.resToString(context, contentId), smallIcon, largeIcon, listener);
    }

    /**
     * 创建进度通知栏
     *
     * @param context
     * @param title
     * @param content
     * @param smallIcon
     * @param largeIcon
     */
    public static int createProgressNotification(Context context, String title, String content,
                                                 int smallIcon, int largeIcon) {
        initNotificationManager(context);

        NotificationCompat.Builder builder = initBaseBuilder(context, title, content, smallIcon, largeIcon);
        builder.setOngoing(true);
        if (AndroidUtil.getApiLevel() < Build.VERSION_CODES.HONEYCOMB) {
            Intent intent = new Intent();
            PendingIntent contentIntent = PendingIntent.getActivity(context, 0, intent, 0);
            builder.setContentIntent(contentIntent);
        }

        int notifyId = (int) System.currentTimeMillis();

        notificationManager.notify(notifyId, builder.build());

        notificationMap.put(notifyId, builder);

        return notifyId;
    }

    /**
     * 重载
     *
     * @param context
     * @param titleId
     * @param contentId
     * @param smallIcon
     * @param largeIcon
     * @return
     */
    public static int createProgressNotification(Context context, int titleId, int contentId,
                                                 int smallIcon, int largeIcon) {
        return createProgressNotification(context, ResourceUtil.resToString(context, titleId),
                ResourceUtil.resToString(context, contentId), smallIcon, largeIcon);
    }

    /**
     * 初始化Builder
     *
     * @param context
     * @param title
     * @param content
     * @param smallIcon
     * @param largeIcon
     * @return
     */
    private static NotificationCompat.Builder initBaseBuilder(Context context, String title, String content,
                                                              int smallIcon, int largeIcon) {

        return new NotificationCompat.Builder(context)
                .setContentTitle(title)
                .setContentText(content)
                .setSmallIcon(smallIcon)
                .setLargeIcon(BitmapUtil.resourceToBitmap(context.getResources(), largeIcon, 0, 0))
                .setDefaults(Notification.DEFAULT_ALL)
                .setOnlyAlertOnce(true)
                .setAutoCancel(true)
                .setWhen(System.currentTimeMillis());
    }

    /**
     * 取消进度通知栏
     *
     * @param notifyId
     */
    public static void cancelNotification(int notifyId) {
        notificationManager.cancel(notifyId);
    }

    /**
     * 更新通知栏进度
     *
     * @param notifyId
     * @param progress
     */
    public static void updateNotification(int notifyId, int progress) {
        NotificationCompat.Builder builder = notificationMap.get(notifyId);
        builder.setProgress(100, progress, false);
        builder.setContentText(progress + "%");
        notificationManager.notify(notifyId, builder.build());
    }

    /**
     * NormalNotification interface
     */
    public interface ClickNotificationListener {
        Intent onClickNotification();
    }
}
