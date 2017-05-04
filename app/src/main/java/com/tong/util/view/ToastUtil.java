package com.tong.util.view;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil
{

    /**
     * 显示长时间的Toast
     * 常量Toast.LENGTH_LONG一般为3.5s
     *
     * @param context 上下文 类型Context
     * @param text    弹出的Toast内容的字符串 类型String
     */
    public static void showLongToast(Context context, String text)
    {
        Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
        toast.setText(text);
        toast.show();
    }

    /**
     * 显示长时间的Toast
     * 常量Toast.LENGTH_LONG一般为3.5s
     *
     * @param context 上下文 类型Context
     * @param res     弹出的Toast内容的资源ID 类型int
     */
    public static void showLongToast(Context context, int res)
    {
        Toast toast = Toast.makeText(context, res, Toast.LENGTH_LONG);
        toast.setText(res);
        toast.show();
    }

    /**
     * 显示长时间的Toast
     * 常量Toast.LENGTH_SHORT一般为2s
     *
     * @param context 上下文 类型Context
     * @param text    弹出的Toast内容的字符串 类型String
     */
    public static void showShortToast(Context context, String text)
    {
        Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        toast.setText(text);
        toast.show();
    }

    /**
     * 显示长时间的Toast
     * 常量Toast.LENGTH_SHORT一般为2s
     *
     * @param context 上下文 类型Context
     * @param res     弹出的Toast内容的资源ID 类型int
     */
    public static void showShortToast(Context context, int res)
    {
        Toast toast = Toast.makeText(context, res, Toast.LENGTH_SHORT);
        toast.setText(res);
        toast.show();
    }
}
