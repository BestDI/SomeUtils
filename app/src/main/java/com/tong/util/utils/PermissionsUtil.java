/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.tong.util.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

/**
 * ****************************************************************
 * 文件名称 : PermissionsUtil.java
 * 作 者 :   x00355498
 * 创建时间 : 2015年10月8日 下午9:33:08
 * 文件描述 : 权限申请的辅助工具类
 * 版权声明 : Copyright (C) 2008-2011 华为技术有限公司(Huawei Tech.Co.,Ltd)
 * 修改历史 : 2015年10月8日 1.00 初始版本
 * ****************************************************************
 */
public class PermissionsUtil
{

    /** M版本对应Android版本号 */
    private static final int ANDROID_M_CODE = 23;

    /**
     * 请求权限的map表key转成数组
     *
     * @param map
     */
    public static String[] arrayPermissions(Map<String, Integer> map)
    {
        Set<String> set = map.keySet();
        String[] keys = set.toArray(new String[set.size()]);
        return keys;
    }

    /**
     * 将请求权限的map表状态转成数组
     *
     * @param map
     */
    public static int[] arrayStatusPermissions(Map<String, Integer> map)
    {
        int[] result = new int[map.size()];
        int i = 0;
        Iterator<Entry<String, Integer>> it = map.entrySet()
                                                 .iterator();
        while (it.hasNext()) {
            result[i++] = it.next()
                            .getValue();
        }
        return result;
    }

    /**
     * 将请求权限的map未授权的请求筛选出来
     *
     * @param map
     */
    public static String[] arrayDeniedPermissions(Map<String, Integer> map)
    {
        List<String> list = new ArrayList<String>();
        Iterator<Entry<String, Integer>> it = map.entrySet()
                                                 .iterator();
        while (it.hasNext()) {
            Entry<String, Integer> entry = it.next();
            if (entry.getValue() == PackageManager.PERMISSION_DENIED) {
                list.add(entry.getKey());
            }
        }
        return list.toArray(new String[list.size()]);
    }

    /**
     * 新的返回请求权限的状态，返回的LinkedHashMap中保存着对应将要申请的权限的状态
     *
     * @param context
     * @param permissions
     */
    public static Map<String, Integer> getStatusPermissions(Context context, String... permissions)
    {
        final Map<String, Integer> map = new LinkedHashMap<String, Integer>();
        for (String permission : permissions) {
            if (hasPermission(context, permission)) {
                map.put(permission, PackageManager.PERMISSION_GRANTED);
            }
            else {
                map.put(permission, PackageManager.PERMISSION_DENIED);
            }
        }
        return map;
    }

    /**
     * 刷新检测Map中的权限状态
     *
     * @param context
     * @param map
     *
     * @return Map<String,Integer>
     */
    public static Map<String, Integer> getStatusPermissions(Context context, Map<String, Integer> map)
    {
        for (String permission : map.keySet()) {
            if (hasPermission(context, permission)) {
                map.put(permission, PackageManager.PERMISSION_GRANTED);
            }
            else {
                map.put(permission, PackageManager.PERMISSION_DENIED);
            }
        }

        return map;
    }

    /**
     * 刷选出没有权限的集合
     *
     * @param activity
     * @param permissions
     *
     * @return List<String>
     */
    public static List<String> getDeniedPermissions(Activity activity, String... permissions)
    {
        final List<String> deniedPermissions = new ArrayList<String>();
        for (String permission : permissions) {
            if (activity.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                deniedPermissions.add(permission);
            }
        }
        return deniedPermissions;
    }

    /**
     * 是M版本一下 方法表述 版本值小于Android L 默认授予了权限
     *
     * @param context
     * @param permission
     *
     * @return boolean
     */
    public static boolean hasPermission(Context context, String permission)
    {
        if (Build.VERSION.SDK_INT < ANDROID_M_CODE) {
            return true;
        }
        return context.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * 检测本次权限申请是否有用户之前拒绝并已勾选不再提醒权限
     *
     * @param activity
     * @param permissionsArray
     *
     * @return boolean
     */
    public static boolean isShouldShowRequestPermissionRationale(Activity activity, String[] permissionsArray)
    {
        boolean result = true;
        if (null != permissionsArray) {
            for (String string : permissionsArray) {
                if (!activity.shouldShowRequestPermissionRationale(string)) {
                    result = false;
                }
            }
        }
        return result;
    }

    /**
     * 申请权限
     *
     * @param activity
     * @param requestCode
     * @param permissions
     */
    public static void requestPermissions(Activity activity, int requestCode, String[] permissions)
    {
        activity.requestPermissions(permissions, requestCode);
    }

    /**
     * 检查所有权限申请结果是否都已授予
     *
     * @param grantResults
     *
     * @return boolean
     */
    public static boolean allGranted(int[] grantResults)
    {
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 检查权限组是否都已授予
     *
     * @param activity
     * @param permissions
     *
     * @return boolean
     */
    public static boolean checkAllPermissionsGranted(Activity activity, String... permissions)
    {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP_MR1) {
            // For all pre-M devices, we should have all the premissions granted
            // on install.
            return true;
        }

        for (String permission : permissions) {
            if (activity.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断有没有对给定的权限授权
     *
     * @param permissionDesc 权限描述
     *
     * @return 授权返回true 未授权返回false
     */
    public static boolean isGrantedPermission(Context mContext, String permissionDesc)
    {
        boolean isGranted = true;
        if (Build.VERSION.SDK_INT >= ANDROID_M_CODE) {
            if ((mContext.checkSelfPermission(permissionDesc)) !=
                PackageManager.PERMISSION_GRANTED) {
                isGranted = false;
            }
            else {
                isGranted = true;
            }
        }
        return isGranted;
    }
}
