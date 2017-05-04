package com.tong.util.utils;

import android.content.Context;
import android.text.TextUtils;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;

import org.apache.http.conn.util.InetAddressUtils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class SystemUtil
{

    /**
     * 方法表述 clear all the related cookies
     *
     * @param context void
     */
    public static void clearCookie(Context context)
    {
        CookieSyncManager.createInstance(context);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookie();
        CookieSyncManager.getInstance()
                         .sync();

    }

    /**
     * 取deviceType
     *
     * @return String DeviceType
     */
    public static String getDeviceType()
    {
        String deviceType = android.os.Build.MODEL;

        if (TextUtils.isEmpty(deviceType)) {

            deviceType = android.os.Build.DEVICE;
        }

        if (TextUtils.isEmpty(deviceType)) {

            deviceType = android.os.Build.PRODUCT;
        }

        if (TextUtils.isEmpty(deviceType)) {

            deviceType = "0";
        }
        return deviceType;
    }

    /**
     * 获取手机ipv4网络地址
     */
    public static String getHostIp()
    {
        try {

            Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
            if (en == null) {
                return null;
            }

            for (; en.hasMoreElements(); ) {

                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> ipAddr
                     = intf.getInetAddresses(); ipAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = ipAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() &&
                        InetAddressUtils.isIPv4Address(inetAddress.getHostAddress())) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        }
        catch (SocketException ex) {
        }
        catch (NullPointerException e) {
        }
        return null;
    }
}
