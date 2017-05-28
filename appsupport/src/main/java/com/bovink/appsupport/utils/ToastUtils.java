package com.bovink.appsupport.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Toast工具类
 *
 * @author bovink
 * @since 2016/6/16
 */
public class ToastUtils {
    /**
     * Toast显示开关
     */
    public static boolean isShow = true;

    private ToastUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 短时间显示Toast，duration为Toast.LENGTH_SHORT
     *
     * @param context context
     * @param text    text
     */
    public static void showShort(Context context, CharSequence text) {
        if (isShow) {
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 短时间显示Toast，duration为Toast.LENGTH_SHORT
     *
     * @param context context
     * @param resId   字符串资源Id
     */
    public static void showShort(Context context, int resId) {
        if (isShow) {
            Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 长时间显示Toast，duration为Toast.LENGTH_LONG
     *
     * @param context context
     * @param text    text
     */
    public static void showLong(Context context, CharSequence text) {
        if (isShow) {
            Toast.makeText(context, text, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 长时间显示Toast，duration为Toast.LENGTH_LONG
     *
     * @param context context
     * @param resId   字符串资源Id
     */
    public static void showLong(Context context, int resId) {
        if (isShow) {
            Toast.makeText(context, resId, Toast.LENGTH_LONG).show();
        }
    }
}
