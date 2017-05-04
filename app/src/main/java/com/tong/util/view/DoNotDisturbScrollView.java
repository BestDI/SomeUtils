package com.tong.util.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * @Description: ScrollView 用户不拦截内里控件的Touch事件
 */
public class DoNotDisturbScrollView extends ScrollView
{

    public DoNotDisturbScrollView(Context context)
    {
        super(context);
    }

    public DoNotDisturbScrollView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public DoNotDisturbScrollView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev)
    {
        return false;
    }
}
