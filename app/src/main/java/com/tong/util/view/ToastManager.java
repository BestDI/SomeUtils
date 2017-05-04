package com.tong.util.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * ****************************************************************
 * 文件名称 : ToastManager.java
 * 作 者 :   j00193083
 * 创建时间 : 2014-11-18 上午10:21:41
 * 文件描述 : 显示toast，在EMUI3.0上和非EMUI3.0上做定制，不指定显示时长，则默认为Toast.LENGTH_SHORT
 * 版权声明 : Copyright (C) 2008-2011 华为技术有限公司(Huawei Tech.Co.,Ltd)
 * 修改历史 : 2014-11-18 1.00 初始版本
 * ****************************************************************
 */
public class ToastManager
{

    /**
     * 方法表述：显示toast，在EMUI3.0上和非EMUI3.0上做定制，默认显示时长Toast.LENGTH_SHORT
     *
     * @param mContext
     * @param resId    void
     */
    public static void show(Context mContext, int resId)
    {
        show(mContext, resId, Toast.LENGTH_SHORT);
    }

    /**
     * 方法表述：显示toast，在EMUI3.0上和非EMUI3.0上做定制，默认显示时长Toast.LENGTH_SHORT
     *
     * @param mContext
     * @param textStr  void
     */
    public static void show(Context mContext, String textStr)
    {
        show(mContext, textStr, Toast.LENGTH_SHORT);
    }

    public static void show(Context mContext, int resId, int duration)
    {
        show(mContext, mContext.getResources()
                               .getString(resId), duration);
    }

    public static void show(Context mContext, String textStr, int duration)
    {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.toast, null);
        TextView toastView = (TextView) view.findViewById(R.id.hb_toast);
        toastView.setText(textStr);

        Toast toast = new Toast(mContext);
        toast.setView(view);
        toast.setDuration(duration);
        toast.show();
    }

}
