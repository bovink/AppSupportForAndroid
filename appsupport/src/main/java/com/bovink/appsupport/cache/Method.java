package com.bovink.appsupport.cache;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.ColorMatrixColorFilter;
import android.os.Environment;
import android.view.MotionEvent;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 保存一些有用的方法
 *
 * @author bovink
 * @since 2016/9/11
 */
public class Method {

    /**
     * 添加触摸变色效果
     *
     * @param view 变色的视图
     */
    private void setClickFade(View view, final int touchLum) {
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // 如果父布局不为空且可点击，则执行触摸事件
                if (v.getParent() != null) {
                    View parent = (View) v.getParent();
                    if (parent.isClickable()) {
                        parent.onTouchEvent(event);
                    }
                }
                if (v.isClickable()) {
                    // 获取触摸时变化的亮度，其他值为公式
                    float lum = (touchLum - 50) * 2 * 255 * 0.01f;
                    // 响应触摸事件
                    switch (event.getActionMasked()) {
                        case MotionEvent.ACTION_DOWN:
                            v.getBackground().setColorFilter(new ColorMatrixColorFilter(new float[]{
                                    1, 0, 0, 0, lum,
                                    0, 1, 0, 0, lum,
                                    0, 0, 1, 0, lum,
                                    0, 0, 0, 1, 0
                            }));
                            break;
                        // 故意这样写，让ACTION_CANCEL执行和ACTION_UP一样的代码
                        case MotionEvent.ACTION_CANCEL:
                        case MotionEvent.ACTION_UP:
                            v.getBackground().setColorFilter(new ColorMatrixColorFilter(new float[]{
                                    1, 0, 0, 0, 0,
                                    0, 1, 0, 0, 0,
                                    0, 0, 1, 0, 0,
                                    0, 0, 0, 1, 0
                            }));
                            break;
                    }
                }
                return true;
            }
        });
    }

    /**
     * @param context     环境
     * @param packageName 包名
     * @return 是否存在改安装包
     */
    public static boolean isPackageInstalled(Context context, String packageName) {
        PackageManager pm = context.getPackageManager();
        boolean installed;
        try {
            pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            installed = false;
        }
        return installed;
    }

    /**
     * 截屏
     * 需要写入sdcard权限
     *
     * @param activity 活动
     */
    public static void screenshot(Activity activity) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-hh:mm:ss", Locale.getDefault());
        String now = sdf.format(date);

        try {
            String path = Environment.getExternalStorageDirectory().toString() + "/" + now + ".jpg";

            View view = activity.getWindow().getDecorView().getRootView();
            view.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
            view.setDrawingCacheEnabled(false);

            File imageFile = new File(path);

            FileOutputStream outputStream = new FileOutputStream(imageFile);
            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            outputStream.flush();
            outputStream.close();

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
