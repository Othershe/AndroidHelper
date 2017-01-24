package com.othershe.androidhelper.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

public class CommonDialog extends DialogFragment {
    Builder builder;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return builder.create();
    }

    public void setBuilder(Builder builder) {
        this.builder = builder;
    }
}
