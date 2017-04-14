package com.othershe.androidhelper.utils;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextUtil {

    /**
     * 如果EditText输入的内容不是 数字 或 字母 则清除相应输入
     *
     * @param context
     * @param editText
     */
    public static void inputCheck(Context context, final EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (TextUtils.isEmpty(s)) {
                    return;
                }

                boolean isLegalChar = true;
                String str = s.toString();

                if (count == 1) {
                    if (!isNumOrLetter(s.toString().charAt(s.toString().length() - 1))) {
                        str = str.substring(0, str.length() - 1);
                        isLegalChar = false;
                    }
                } else if (before == 0 && count > 1) {
                    str = str.substring(0, str.length() - count);
                    isLegalChar = false;
                }

                if (!isLegalChar) {
                    //show tip
                    editText.setText(str);
                    editText.setSelection(str.length());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    /**
     * 检测数字或字母
     *
     * @param s
     * @return
     */
    public static boolean isNumOrLetter(char s) {
        String regEx = "[a-zA-Z0-9]";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(s + "");
        return matcher.matches();
    }
}
