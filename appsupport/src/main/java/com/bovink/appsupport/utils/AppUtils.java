package com.bovink.appsupport.utils;

import com.bovink.appsupport.BuildConfig;

/**
 * Created by Retina975 on 17/6/2.
 * <p>
 * App工具类
 * <p>
 * 获取App版本等信息
 * 这个类获取的是appsupport module的版本,因此appsupport的版本须与app版本一致
 */

public class AppUtils {

    private AppUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 获取版本名
     *
     * @return
     */
    public static String getVersionName() {
        return BuildConfig.VERSION_NAME;
    }

    /**
     * 获取版本号
     *
     * @return
     */
    public static int getVersionCode() {
        return BuildConfig.VERSION_CODE;
    }
}
