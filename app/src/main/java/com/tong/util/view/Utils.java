package com.tong.util.view;

import android.content.Context;
import android.graphics.Paint;

import java.lang.reflect.Field;

public class Utils
{

    private static final String TAG = Utils.class.getSimpleName();

    private static final String ArabicLanguageCode = "ar";

    private static final String HebrewLanguageCode = "iw";

    private static final String FarsiLanguageCode = "fa";

    /** 乌尔都语 */
    private static final String UrduLanguageCode = "ur";

    /**
     * 根据手机的分辨率从 px 单位转成为 dip 单位
     * px: pixels(像素)
     * dip(dp): device independent pixels(设备独立像素)
     *
     * @param context 上下文
     * @param pxValue px值
     *
     * @return dp值
     */
    public static int px2dip(Context context, float pxValue)
    {
        final float scale = context.getResources()
                                   .getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f) - 15;
    }

    /**
     * 根据手机的分辨率从 dip 单位转成为 px 单位
     * dip(dp): device independent pixels(设备独立像素)
     * px: pixels(像素)
     *
     * @param context 上下文
     * @param dpValue dp值
     *
     * @return px值
     */
    public static int dip2px(Context context, float dpValue)
    {
        final float scale = context.getResources()
                                   .getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int getStatusBarHeight(Context context)
    {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int resID = 0, statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            resID = Integer.parseInt(field.get(obj)
                                          .toString());
            statusBarHeight = context.getResources()
                                     .getDimensionPixelSize(resID);
        }
        catch (Exception e1) {
        }
        return statusBarHeight;
    }

    public static boolean isChineseOrEnglish(Context context)
    {
        return isChineseSimplifiedLocal(context) || isEnglish(context);
    }

    public static boolean isChineseSimplifiedLocal(Context context)
    {
        if (null == context) {
            return false;
        }

        if ("zh".equalsIgnoreCase(context.getResources()
                                         .getConfiguration().locale.getLanguage()) &&
            "CN".equalsIgnoreCase(context.getResources()
                                         .getConfiguration().locale.getCountry())) {
            return true;
        }
        return false;
    }

    public static boolean isEnglish(Context context)
    {
        if (null == context) {
            return false;
        }

        if ("en".equalsIgnoreCase(context.getResources()
                                         .getConfiguration().locale.getLanguage())) {
            return true;
        }
        return false;
    }

    public static boolean isRTLLanguage(Context context)
    {
        if (null == context) {
            return false;
        }

        if (ArabicLanguageCode.equalsIgnoreCase(context.getResources()
                                                       .getConfiguration().locale.getLanguage()) ||
            HebrewLanguageCode.equalsIgnoreCase(context.getResources()
                                                       .getConfiguration().locale.getLanguage()) ||
            FarsiLanguageCode.equalsIgnoreCase(context.getResources()
                                                      .getConfiguration().locale.getLanguage()) ||
            UrduLanguageCode.equalsIgnoreCase(context.getResources()
                                                     .getConfiguration().locale.getLanguage())) {

            return true;
        }
        return false;
    }


    //获取文字宽度
    public static float getFontlength(Paint paint, String str)
    {
        return paint.measureText(str);
    }

    //获取文字高度
    public static float getFontHeight(Paint paint)
    {
        Paint.FontMetrics fm = paint.getFontMetrics();
        return fm.descent - fm.ascent;
    }
}
