package com.bovink.appsupport.utils;

/**
 * Created by Retina975 on 17/6/5.
 * <p>
 * 字符串工具类
 */

public class StringUtils {

    private StringUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static String getTime(String time) {
        String s = time.replaceAll(" ", "").trim();

        String year = s.substring(0, 4);//substring包括开始不包括结尾
        String month = s.substring(5, 7).replaceFirst("^0*", "");
        String day = s.substring(8, 10).replaceFirst("^0*", "");
        String m = s.substring(10, 15);
        return year + "年" + month + "月" + day + "日" + " " + m;
    }
}
