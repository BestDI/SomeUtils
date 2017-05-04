package com.tong.util.utils;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TimeUtil
{

    /**
     * 获取月份格式为"yyyyMMdd"的Calendar
     *
     * @param timeFormat
     */
    @SuppressWarnings ("deprecation")
    public static Calendar getCalendar(String timeFormat)
    {
        Calendar cal = Calendar.getInstance();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        if (TextUtils.isEmpty(timeFormat)) {
            return cal;
        }

        int month = -1;
        Date date = null;
        try {
            date = sdf.parse(timeFormat);
            month = date.getMonth();
        }
        catch (ParseException e) {
        }

        if (-1 < month && month < 13) {
            cal.setTime(date);
        }

        return cal;
    }

    // 解析时间
    public static Date parseString2Date(String dateStr, String format)
    {
        SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.getDefault());
        try {
            return formatter.parse(dateStr);
        }
        catch (ParseException e) {
            return null;
        }
    }

    // 格式化日期
    public static String formatDate2String(Date d, String format)
    {
        if (d == null) {
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.getDefault());
        return formatter.format(d);
    }

    private static long lastClickTime;

    // 判断是否快速双击
    public static boolean isFastDoubleClick()
    {
        long time = System.currentTimeMillis();
        if (time - lastClickTime < 1000) {
            lastClickTime = time;
            return true;
        }
        lastClickTime = time;
        return false;
    }
}
