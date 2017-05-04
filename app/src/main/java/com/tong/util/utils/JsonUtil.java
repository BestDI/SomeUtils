package com.tong.util.utils;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * json工具类
 */
public final class JsonUtil
{

    private static final String TAG = "JsonUtil";

    private JsonUtil()
    {
    }

    /**
     * 判断JsonObject中是否有节点tag
     */
    public static String getStringValue(JSONObject jsonObject, String tag)
    {
        if (!jsonObject.isNull(tag)) {
            try {
                return jsonObject.getString(tag);
            }
            catch (JSONException e) {
                return null;
            }
        }
        return null;
    }

    /**
     * 判断JsonObject中是否有节点tag
     */
    public static int getIntValue(JSONObject jsonObject, String tag, int defaultValue)
    {
        int value = defaultValue;
        if (!jsonObject.isNull(tag)) {
            try {
                value = jsonObject.getInt(tag);
            }
            catch (JSONException e) {
                value = defaultValue;
            }
        }
        return value;
    }

    public static long getLongValue(JSONObject jsonObject, String tag, long defaultValue)
    {
        long value = defaultValue;
        if (!jsonObject.isNull(tag)) {
            try {
                value = jsonObject.getLong(tag);
            }
            catch (JSONException e) {
                value = defaultValue;
            }
        }
        return value;
    }

    public static JSONArray getJsonArray(JSONObject jsonObject, String tag)
    {
        if (null != jsonObject) {
            if (!jsonObject.isNull(tag)) {
                try {
                    return jsonObject.getJSONArray(tag);
                }
                catch (Exception e) {
                    // do nothing
                }
            }
        }
        return null;
    }

    /**
     * 判断JsonObject中是否有节点tag
     */
    public static double getDoubleValue(JSONObject jsonObject, String tag)
    {
        double value = 0;
        if (!jsonObject.isNull(tag)) {
            try {
                value = jsonObject.getDouble(tag);
            }
            catch (JSONException e) {
            }
        }
        return value;
    }

    /**
     * 获取json对象
     */
    public static JSONObject getJsonObject(JSONObject jsonObject, String tag)
    {
        JSONObject result = null;
        try {
            result = jsonObject.getJSONObject(tag);
        }
        catch (Exception e) {
        }
        return result;
    }

    /**
     * 从json对象中获取指定节点的信息字符串列表 方法表述
     */
    public static ArrayList<String> getStringArrayValue(JSONObject jsonObject, String tag)
    {
        ArrayList<String> list = null;
        try {
            JSONArray array = jsonObject.getJSONArray(tag);
            if (null != array) {
                list = new ArrayList<String>();
                String str = null;
                for (int i = 0; i < array.length(); i++) {
                    str = array.getString(i);
                    list.add(str);
                }
            }
        }
        catch (JSONException ex) {
        }

        return list;
    }
}
