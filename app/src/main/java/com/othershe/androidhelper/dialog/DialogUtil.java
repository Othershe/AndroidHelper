package com.othershe.androidhelper.dialog;

import android.support.v4.app.FragmentActivity;

import com.othershe.androidhelper.utils.ResourceUtil;

public class DialogUtil {

    /**
     * 显示自定义样式dialog
     *
     * @param fragmentActivity
     * @param viewId
     * @param listener
     */
    public static void showViewDialog(FragmentActivity fragmentActivity, int viewId, ViewDialogListener listener) {
        initViewDialog(fragmentActivity, viewId, listener);
    }

    /**
     * 显示系统样式dialog (two btn)
     *
     * @param fragmentActivity
     * @param title
     * @param content
     * @param rightBtnTxt
     * @param leftBtnTxt
     * @param listener
     */
    public static void showTwoBtnDialog(FragmentActivity fragmentActivity, String title, String content,
                                        String rightBtnTxt, String leftBtnTxt, TwoBtnDialogListener listener) {
        initTwoBtnDialog(fragmentActivity, title, content, rightBtnTxt, leftBtnTxt, listener);
    }

    /**
     * 显示系统样式dialog(two btn)
     *
     * @param fragmentActivity
     * @param titleId
     * @param contentId
     * @param rightBtnTxtId
     * @param leftBtnTxtId
     * @param listener
     */
    public static void showTwoBtnDialog(FragmentActivity fragmentActivity, int titleId, int contentId,
                                        int rightBtnTxtId, int leftBtnTxtId, TwoBtnDialogListener listener) {
        initTwoBtnDialog(fragmentActivity, ResourceUtil.resToString(fragmentActivity, titleId),
                ResourceUtil.resToString(fragmentActivity, contentId),
                ResourceUtil.resToString(fragmentActivity, rightBtnTxtId),
                ResourceUtil.resToString(fragmentActivity, leftBtnTxtId), listener);
    }

    /**
     * 显示系统样式dialog(one btn)
     *
     * @param fragmentActivity
     * @param title
     * @param content
     * @param okTxt
     * @param listener
     */
    public static void showOneBtnDialog(FragmentActivity fragmentActivity, String title, String content,
                                        String okTxt, OneBtnDialogListener listener) {
        initOneBtnDialog(fragmentActivity, title, content, okTxt, listener);
    }

    /**
     * 显示系统样式dialog(one btn)
     *
     * @param fragmentActivity
     * @param titleId
     * @param contentId
     * @param okTxtId
     * @param listener
     */
    public static void showOneBtnDialog(FragmentActivity fragmentActivity, int titleId, int contentId,
                                        int okTxtId, OneBtnDialogListener listener) {
        initOneBtnDialog(fragmentActivity, ResourceUtil.resToString(fragmentActivity, titleId),
                ResourceUtil.resToString(fragmentActivity, contentId),
                ResourceUtil.resToString(fragmentActivity, okTxtId), listener);
    }

    /*********************************
     * 可以继续添加show方法的重载
     ************************************/

    private static void initTwoBtnDialog(FragmentActivity fragmentActivity, String title, String content,
                                         String okTxt, String noTxt, TwoBtnDialogListener listener) {
        Builder builder = new Builder(fragmentActivity, title, content, okTxt, noTxt, listener);

        initDialog(fragmentActivity, builder);
    }

    private static void initOneBtnDialog(FragmentActivity fragmentActivity, String title, String content,
                                         String okTxt, OneBtnDialogListener listener) {
        Builder builder = new Builder(fragmentActivity, title, content, okTxt, listener);

        initDialog(fragmentActivity, builder);
    }

    private static void initViewDialog(FragmentActivity fragmentActivity, int viewId, ViewDialogListener listener) {
        CommonDialog dialog = new CommonDialog();
        Builder builder = new Builder(fragmentActivity, dialog, viewId, listener);
        dialog.setBuilder(builder);
        dialog.show(fragmentActivity.getSupportFragmentManager(), System.currentTimeMillis() + "");
    }

    private static void initDialog(FragmentActivity fragmentActivity, Builder builder) {
        CommonDialog dialog = new CommonDialog();
        dialog.setBuilder(builder);
        dialog.show(fragmentActivity.getSupportFragmentManager(), System.currentTimeMillis() + "");
    }
}
