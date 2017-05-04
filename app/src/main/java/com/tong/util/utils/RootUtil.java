package com.tong.util.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class RootUtil
{

    /**
     * 判断手机是否root
     */
    public static boolean isRoot()
    {
        if (new File(getBinPath()).exists() && isExecutable(getBinPath())) {
            return true;
        }

        if (new File(getXbinPath()).exists() && isExecutable(getXbinPath())) {
            return true;
        }

        return false;
    }

    /**
     * bin路径
     */
    private static String getBinPath()
    {
        return "/system/bin/su";
    }

    /**
     * xbin路径
     */
    private static String getXbinPath()
    {
        return "/system/xbin/su";
    }

    /**
     * 是否可执行
     * 方法表述
     *
     * @param filePath
     *
     * @return boolean
     */
    private static boolean isExecutable(String filePath)
    {
        Process p = null;
        InputStream inputStream = null;
        InputStreamReader reader = null;
        BufferedReader in = null;
        char[] buff = new char[10];
        try {
            p = Runtime.getRuntime()
                       .exec("ls -l " + filePath);
            // 获取返回内容
            inputStream = p.getInputStream();
            reader = new InputStreamReader(inputStream, "UTF-8");
            in = new BufferedReader(reader);

            //修改codedex， readline有可能读取数据太多，导致应用程序过载，修改成读取指定字符
            int len = in.read(buff, 0, 5);
            if (len >= 4) {
                char flag = buff[3];
                if (flag == 's' || flag == 'x') {
                    return true;
                }
            }
        }
        catch (IOException e) {

        }
        finally {
            buff = null;

            closeStream(inputStream);
            closeReader(reader);
            closeReader(in);

            if (p != null) {
                p.destroy();
            }
        }
        return false;
    }

    /**
     * 统一的InputStream流关闭函数
     */
    private static void closeStream(InputStream inSteam)
    {
        if (null != inSteam) {
            try {
                inSteam.close();
            }
            catch (IOException e) {
            }
        }
    }

    /**
     * 统一的Reader流关闭函数
     */
    private static void closeReader(Reader reader)
    {
        if (null != reader) {
            try {
                reader.close();
            }
            catch (IOException e) {
            }
        }
    }
}
