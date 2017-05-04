package com.tong.util.utils;

import android.app.Service;
import android.content.Context;
import android.os.Vibrator;

public final class VibratorUtil
{

    private VibratorUtil()
    {

    }

    /**
     * 手机震动方法
     * 方法表述
     *
     * @param Context
     * @param milliseconds void
     */
    public static void vibrate(final Context Context, long milliseconds)
    {
        Vibrator vib = (Vibrator) Context.getSystemService(Service.VIBRATOR_SERVICE);
        if (null == vib) {
        }
        else {
            vib.vibrate(milliseconds);
        }
    }
}
