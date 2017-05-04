package com.tong.util.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.Switch;

public class CustomSwitchButton extends Switch
{

    private static final String TAG = "CustomSwitchButton";

    private Context mContext;

    public CustomSwitchButton(Context context)
    {
        super(context);
        mContext = context;
        initView();
    }

    public CustomSwitchButton(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        mContext = context;
        // setTrackResource();
        // setThumbDrawable();
        // setw

        // TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.custom_title_bar);
        // mTypeIndex = a.getInteger(R.styleable.custom_title_bar_custom_type, CUSTOM_TITLEBAR_TYPE_SAVE);
        // mStrTitle = a.getString(R.styleable.custom_title_bar_titleBarText);
        // mBackBackground = a.getDrawable(R.styleable.custom_title_bar_titleBarBg);
        // mLeftSoftkeyVisible = a.getInteger(R.styleable.custom_title_bar_leftSoftkey_visibility, View.VISIBLE);
        // mRightSoftkeyVisible = a.getInteger(R.styleable.custom_title_bar_rightSoftkey_visibility, View.VISIBLE);
        // mLeftIconDrawable = a.getDrawable(R.styleable.custom_title_bar_leftIcon);
        // mRightIconDrawable = a.getDrawable(R.styleable.custom_title_bar_rightIcon);
        // mTitleTextColor = a.getInteger(R.styleable.custom_title_bar_titleBarTextColor, R.color.common_color_black);
        // a.recycle();

        initView();
    }

    private void initView()
    {
        // 设置背景
        // 先加载主题中设置的背景 再加载xml属性(custom_title_bar_titleBarBg)中设置的背景 如果设置了则覆盖
        Resources.Theme t = mContext.getTheme();
        if (t == null) {
            return;
        }

        TypedArray arraySwitchTrack = t.obtainStyledAttributes(new int[]{R.attr.swithTrack});
        if (arraySwitchTrack != null) {
            Drawable drawableSwitchTrack = arraySwitchTrack.getDrawable(0);
            if (drawableSwitchTrack != null) {
                setTrackDrawable(drawableSwitchTrack);
            }

            arraySwitchTrack.recycle();
        }

        TypedArray arraySwitchThumb = t.obtainStyledAttributes(new int[]{R.attr.swithInner});
        if (arraySwitchThumb != null) {
            Drawable drawableSwitchThumb = arraySwitchTrack.getDrawable(0);
            if (drawableSwitchThumb != null) {
                setThumbDrawable(drawableSwitchThumb);
            }

            arraySwitchThumb.recycle();
        }
    }
}
