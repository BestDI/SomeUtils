package com.tong.util.utils;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;

import java.util.HashMap;

public class LocationUtil
{

    private static final String DEFAULT_LONGITUDE = "0";

    private static final String DEFAULT_LATITUDE = "0";

    /**
     * 获取位置Location
     *
     * @return Location
     */
    public static Location getLastKnownLocation(Context context)
    {
        if (null == context) {
            return null;
        }
        LocationManager locationManager = (LocationManager) context.getSystemService(
                Context.LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        return location;
    }

    /**
     * 判断系统网络定位是否可用
     *
     * @return boolean
     */
    public static boolean isEnableNetLocation(Context context)
    {
        if (null == context) {
            return false;
        }
        LocationManager locationManager = (LocationManager) context.getSystemService(
                Context.LOCATION_SERVICE);
        if (locationManager != null &&
            locationManager.getProvider(LocationManager.NETWORK_PROVIDER) != null) {
            return locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        }
        return false;
    }

    /**
     * 获取位置Location
     *
     * @return Location
     */
    public static HashMap<String, String> getLocationInfo(Context context)
    {
        HashMap<String, String> locationInfo = new HashMap<String, String>();
        boolean hasPermision =
                PermissionsUtil.hasPermission(context, "android.permission.ACCESS_FINE_LOCATION") &&
                PermissionsUtil.hasPermission(context, "android.permission.ACCESS_COARSE_LOCATION");
        if (hasPermision) {
            if (LocationUtil.isEnableNetLocation(context)) {
                Location location = getLastKnownLocation(context);
                if (location != null) {
                    double lo = location.getLongitude();
                    double la = location.getLatitude();
                    locationInfo.put("longitude", String.valueOf(lo));
                    locationInfo.put("latitude", String.valueOf(la));
                }
                else {
                    locationInfo.put("longitude", DEFAULT_LONGITUDE);
                    locationInfo.put("latitude", DEFAULT_LATITUDE);
                }
            }
            else {
                locationInfo.put("longitude", DEFAULT_LONGITUDE);
                locationInfo.put("latitude", DEFAULT_LATITUDE);
            }
        }
        else {
            locationInfo.put("longitude", DEFAULT_LONGITUDE);
            locationInfo.put("latitude", DEFAULT_LATITUDE);
        }

        return locationInfo;

    }

}
