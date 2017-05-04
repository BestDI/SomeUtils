package com.tong.util.utils;


public final class StringUtil
{

    private StringUtil()
    {

    }

    /**
     * 方法表述 判断字符串是否为空
     *
     * @param str      被判断的字符串
     * @param isToTrim 字符串是否需要前后去除空格
     *
     * @return boolean true 字符串为空； false 字符串不为空
     */
    public static boolean isEmpty(String str, boolean isToTrim)
    {
        if (null == str) {
            return true;
        }
        if (isToTrim) {
            if (0 >= str.trim()
                        .length()) {
                return true;
            }
        }
        else {
            if (0 >= str.length()) {
                return true;
            }
        }
        return false;
    }
}
