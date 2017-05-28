package com.bovink.appsupport.utils;

import android.content.Context;

/**
 * dp、sp 转换为 px 的工具类
 *
 * @author bovink
 * @since 2016/6/15
 */
public class DensityUtils {
    private DensityUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static int pxToDp(Context context, float px) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    public static int dpToPx(Context context, float dp) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public static int pxToSp(Context context, float px) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (px / fontScale + 0.5f);
    }

    public static int spToPx(Context context, float sp) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (sp * fontScale + 0.5f);
    }
}