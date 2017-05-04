package com.tong.util.utils;

import android.content.Context;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class NfcUtil
{

    /**
     * /**
     * 判断当前是否手机是否已开启nfc服务 方法表述
     *
     * @param context
     *
     * @return boolean
     */
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
}
