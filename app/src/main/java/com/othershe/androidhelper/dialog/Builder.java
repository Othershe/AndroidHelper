package com.othershe.androidhelper.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;

public class Builder extends AlertDialog.Builder {

    /**
     * 系统样式（one btn）dialog对应的Builder constructor
     * @param context
     * @param title
     * @param content
     * @param okBtn
     * @param listener
     */
    public Builder(final Context context, String title, String content, String okBtn,
                   final OneBtnDialogListener listener) {
        super(context);

        setTitle(title);
        setMessage(content);
        setPositiveButton(okBtn, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.onClickOk();
            }
        });
    }

    /**
     * 系统样式（two btn）dialog对应的Builder constructor
     * @param context
     * @param title
     * @param content
     * @param rightBtnTxt
     * @param leftBtnTxt
     * @param listener
     */
    public Builder(final Context context, String title, String content, String rightBtnTxt, String leftBtnTxt,
                   final TwoBtnDialogListener listener) {
        super(context);

        setTitle(title);
        setMessage(content);
        setPositiveButton(rightBtnTxt, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.onClickRight();
            }
        });

        setNegativeButton(leftBtnTxt, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.onClickLeft();
            }
        });
    }

    /**
     * 自定义样式dialog对应的Builder constructor
     * @param context
     * @param dialog
     * @param viewId
     * @param listener
     */
    public Builder(Context context,CommonDialog dialog, int viewId, ViewDialogListener listener) {
        super(context);

        View view = LayoutInflater.from(context).inflate(viewId, null);
        setView(view);
        if (listener != null) {
            listener.convertView(view, dialog);
        }
    }
}
