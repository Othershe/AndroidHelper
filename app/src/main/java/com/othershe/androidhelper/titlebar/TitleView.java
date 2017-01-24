package com.othershe.androidhelper.titlebar;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.othershe.androidhelper.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 自定义titleBar
 * <p>
 * xmlns:titleview="http://schemas.android.com/apk/res-auto"
 */

public class TitleView extends RelativeLayout {
    @BindView(R.id.title_back_layout)
    LinearLayout mBackLayout;

    @BindView(R.id.title_back_iv)
    ImageView mBackImg;

    @BindView(R.id.title_back_tv)
    TextView mBackTv;

    @BindView(R.id.title_close_tv)
    TextView mCloseTv;

    @BindView(R.id.title_title_tv)
    TextView mTitle;

    @BindView(R.id.title_right_tv)
    TextView mRightTv;

    @BindView(R.id.title_layout)
    RelativeLayout mTitleLayout;

    @BindView(R.id.title_right_iv)
    ImageView mRightImg;

    //默认执行关闭操作
    @OnClick(R.id.title_back_layout)
    void onClickBack() {
        ((Activity) getContext()).finish();
    }

    //默认执行关闭操作
    @OnClick(R.id.title_close_tv)
    void onClickClose() {
        ((Activity) getContext()).finish();
    }

    public TitleView(Context context) {
        this(context, null);
    }

    public TitleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.title_layout, this);

        ButterKnife.bind(this, view);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TitleView, defStyleAttr, 0);
        for (int i = 0; i < ta.getIndexCount(); i++) {
            int attr = ta.getIndex(i);
            switch (attr) {
                case R.styleable.TitleView_title_content://标题
                    String title = ta.getString(R.styleable.TitleView_title_content);
                    setTitle(title);
                    break;
                case R.styleable.TitleView_title_color://title背景色
                    int color = ta.getColor(R.styleable.TitleView_title_color, Color.parseColor("#ed994c"));
                    mTitleLayout.setBackgroundColor(color);
                    break;
                case R.styleable.TitleView_title_close_text://关闭按钮文字
                    String closetText = ta.getString(R.styleable.TitleView_title_close_text);
                    setCloseText(closetText);
                    break;
                case R.styleable.TitleView_title_close_text_show://是否显示关闭按钮
                    boolean showClose = ta.getBoolean(R.styleable.TitleView_title_close_text_show, false);
                    showCloseText(showClose);
                    break;
                case R.styleable.TitleView_title_back_text://返回按钮文字
                    String backText = ta.getString(R.styleable.TitleView_title_back_text);
                    setBackText(backText);
                    break;
                case R.styleable.TitleView_title_back_text_show://是否显示返回按钮文字
                    boolean showBackText = ta.getBoolean(R.styleable.TitleView_title_back_text_show, false);
                    showBackText(showBackText);
                    break;
                case R.styleable.TitleView_title_back_image://返回图标
                    int backImage = ta.getResourceId(R.styleable.TitleView_title_back_image, -1);
                    setBackImage(backImage);
                    break;
                case R.styleable.TitleView_title_back_image_show://是否显示返回图标
                    boolean showBackImage = ta.getBoolean(R.styleable.TitleView_title_back_image_show, false);
                    showBackImage(showBackImage);
                    break;
                case R.styleable.TitleView_title_right_text://右边文本
                    String rightText = ta.getString(R.styleable.TitleView_title_right_text);
                    setRightText(rightText);
                    break;
                case R.styleable.TitleView_title_right_text_show://是否显示右边文本
                    boolean showRightText = ta.getBoolean(R.styleable.TitleView_title_right_text_show, false);
                    showRightText(showRightText);
                    break;
                case R.styleable.TitleView_title_right_image://右边图标
                    int rightImage = ta.getResourceId(R.styleable.TitleView_title_right_image, -1);
                    setRightImage(rightImage);
                    break;
                case R.styleable.TitleView_title_right_image_show://是否显示右边图标
                    boolean showRightImage = ta.getBoolean(R.styleable.TitleView_title_right_image_show, false);
                    showRightImage(showRightImage);
                    break;
            }
        }

        ta.recycle();
    }

    public void setTitle(String str) {
        mTitle.setText(str);
    }

    public void setTitle(int id) {
        mTitle.setText(id);
    }

    /**
     * 设置右边按钮文本
     *
     * @param str
     */
    public void setRightText(String str) {
        mRightTv.setText(str);
    }

    public void setRightText(int id) {
        mRightTv.setText(id);
    }

    /**
     * 右按钮点击
     *
     * @param listener
     */
    public void setOnRightTextClick(OnClickListener listener) {
        mRightTv.setOnClickListener(listener);
    }

    public void setOnRightImageClick(OnClickListener listener) {
        mRightImg.setOnClickListener(listener);
    }

    /**
     * 返回按钮点击
     *
     * @param listener
     */
    public void setOnBackClick(OnClickListener listener) {
        mBackLayout.setOnClickListener(listener);
    }

    /**
     * 关闭按钮点击
     *
     * @param listener
     */
    public void setOnCloseTextClick(OnClickListener listener) {
        mCloseTv.setOnClickListener(listener);
    }

    /**
     * 隐藏关闭按钮
     */
    public void showCloseText(boolean isShow) {
        if (isShow) {
            mCloseTv.setVisibility(VISIBLE);
        } else {
            mCloseTv.setVisibility(GONE);
        }
    }

    /**
     * 设置关闭按钮文字
     *
     * @param txt
     */
    public void setCloseText(String txt) {
        mCloseTv.setText(txt);
    }

    public void setCloseText(int txtId) {
        mCloseTv.setText(txtId);
    }

    /**
     * 隐藏返回按钮文字
     */
    public void showBackText(boolean isShow) {
        if (isShow) {
            mBackTv.setVisibility(VISIBLE);
        } else {
            mBackTv.setVisibility(GONE);
        }
    }

    /**
     * 隐藏右边文字
     */
    public void showRightText(boolean isShow) {
        if (isShow) {
            mRightTv.setVisibility(VISIBLE);
        } else {
            mRightTv.setVisibility(GONE);
        }
    }

    /**
     * 设置返回按钮文字
     *
     * @param txt
     */
    public void setBackText(String txt) {
        mBackTv.setText(txt);
    }

    public void setBackText(int txtId) {
        mBackTv.setText(txtId);
    }

    /**
     * 设置返回按钮图标
     *
     * @param imageId
     */
    public void setBackImage(int imageId) {
        mBackImg.setImageResource(imageId);
    }

    /**
     * 隐藏返回按钮图标
     *
     * @param isShow
     */
    public void showBackImage(boolean isShow) {
        if (isShow) {
            mBackImg.setVisibility(VISIBLE);
        } else {
            mBackImg.setVisibility(GONE);
        }
    }

    /**
     * 设置背景色
     *
     * @param id
     */
    public void setTitleBgColor(int id) {
        mTitleLayout.setBackgroundColor(getResources().getColor(id));
    }

    /**
     * 设置背景色
     *
     * @param str （#xxxxxx）
     */
    public void setTitleBgColor(String str) {
        mTitleLayout.setBackgroundColor(Color.parseColor(str));
    }

    /**
     * 设置右边图标
     *
     * @param imageId
     */
    public void setRightImage(int imageId) {
        mRightImg.setImageResource(imageId);
    }

    /**
     * 隐藏右边图标
     *
     * @param isShow
     */
    public void showRightImage(boolean isShow) {
        if (isShow) {
            mRightImg.setVisibility(VISIBLE);
        } else {
            mRightImg.setVisibility(GONE);
        }
    }
}
