package com.tong.util.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class NfcUtil
{

    // 判断手机是否开启NFC
    public static boolean isEnabledNFC(Context context)
    {
        if (null == context) {
            return false;
        }

        NfcManager manager = (NfcManager) context.getSystemService(Context.NFC_SERVICE);
        if (null == manager) {
            return false;
        }

        NfcAdapter adapter = manager.getDefaultAdapter();
        if (adapter != null && adapter.isEnabled()) {
            // adapter存在，则认为是开启的
            return true;
        }

        return false;
    }


    // 启用NFC功能
    public static boolean enableNFC(Context context)
    {

        try {
            NfcAdapter adpter = NfcAdapter.getDefaultAdapter(context);

            if (null == adpter) {
                return false;
            }

            Method enable = adpter.getClass()
                                  .getDeclaredMethod("enable", (Class<?>[]) null);

            return (Boolean) enable.invoke(adpter, (Object[]) null);

        }
        catch (NoSuchMethodException e) {
        }
        catch (IllegalAccessException e) {
        }
        catch (IllegalArgumentException e) {
        }
        catch (InvocationTargetException e) {
        }

        return false;
    }

    // 判断是否支持NFC功能
    public static boolean isSupportNfc(Context context)
    {
        PackageManager pm = context.getPackageManager();

        //使用hasSystemFeature方法可以检查设备是否其他功能。如陀螺仪，NFC，蓝牙等等，
        boolean nfc = pm.hasSystemFeature(PackageManager.FEATURE_NFC);

        return nfc;
    }
}
