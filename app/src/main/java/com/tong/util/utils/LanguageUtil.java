package com.tong.util.utils;

import java.util.Locale;

import android.content.Context;
import android.content.res.Configuration;

/***************************************
 * 文件名称 : LanguageUtil.java <br\>
 * 文件描述 : 字符串处理工具类 <br\>
 */
public final class LanguageUtil
{

    private LanguageUtil()
    {

    }

    /**
     * 判断当前是否中文
     *
     * @param context
     *
     * @return boolean
     */
    public static boolean isLanguageZhCn(Context context)
    {
        String LANGUAGE_CHINA = "zh_cn";
        if (LANGUAGE_CHINA.equals(getLanguage(context))) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * 获取语言和国家
     *
     * @param context
     */
    public static String getLanguage(Context context)
    {
        Configuration configuration = context.getResources()
                                             .getConfiguration();
        String language = configuration.locale.getLanguage();
        String country = configuration.locale.getCountry();
        return (language + '_' + country).toLowerCase(Locale.getDefault());
    }

    /**
     * 根据语言和国家获取url
     *
     * @param context
     * @param url
     *
     * @return String
     */
    public static String getUrlByLanguage(Context context, String url)
    {
        if (true == isLanguageZhCn(context)) {
            return url + "?language=zh";
        }
        else {
            return url + "?language=en";
        }
    }
}
