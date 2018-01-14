package com.bovink.appsupport.widget.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.bovink.appsupport.R;


/**
 * 使用GradientDrawable作为背景的TextView
 *
 * @author bovink
 * @since 2016/5/3
 */
public class GradientTextView extends AppCompatTextView implements View.OnTouchListener {
    /**
     * 默认触摸亮度
     */
    private final static int DEFAULT_LUM = 45;
    /**
     * 触摸控件时，控件的亮度。默认使用DEFAULT_LUM。
     */
    private int mTouchLum = DEFAULT_LUM;
    /**
     * 默认使用GradientDrawable作为背景。
     */
    private GradientDrawable mBackgroundDrawable = new GradientDrawable();
    /**
     * 控件是否可点击。默认不可点击。
     */
    private boolean mClickable = false;

    /**
     * 构造函数
     *
     * @param context 环境
     */
    public GradientTextView(Context context) {
        super(context);
    }

    /**
     * 构造函数
     *
     * @param context 环境
     * @param attrs   XML属性集合
     */
    public GradientTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * 构造函数
     *
     * @param context      环境
     * @param attrs        XML属性集合
     * @param defStyleAttr 一个决定默认值的参数
     */
    public GradientTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.GradientDrawableTextView, defStyleAttr, 0);

        setOnTouchListener(this);

        updateGradientCorners(a);

        updateGradientStroke(a);

        updateGradientSolid(a);

        updateGradient();

        a.recycle();

    }

    /**
     * 更新Gradient四角的半径
     *
     * @param a 属性集合
     */
    private void updateGradientCorners(TypedArray a) {
        // 获取一次性设置的半径
        final int radius = a.getDimensionPixelSize(
                R.styleable.GradientDrawableTextView_gdtv_cornerRadius, 0);
        mBackgroundDrawable.setCornerRadius(radius);
        mBackgroundDrawable.setOrientation(GradientDrawable.Orientation.LEFT_RIGHT);

        // 获取分别设置的半径
        final int topLeftRadius = a.getDimensionPixelSize(
                R.styleable.GradientDrawableTextView_gdtv_cornerTopLeftRadius, radius);
        final int topRightRadius = a.getDimensionPixelSize(
                R.styleable.GradientDrawableTextView_gdtv_cornerTopRightRadius, radius);
        final int bottomLeftRadius = a.getDimensionPixelSize(
                R.styleable.GradientDrawableTextView_gdtv_cornerBottomLeftRadius, radius);
        final int bottomRightRadius = a.getDimensionPixelSize(
                R.styleable.GradientDrawableTextView_gdtv_cornerBottomRightRadius, radius);

        // 当分别设置的半径与一次性设置的半径不同时，重新设置四角半径
        if (topLeftRadius != radius || topRightRadius != radius ||
                bottomLeftRadius != radius || bottomRightRadius != radius) {
            mBackgroundDrawable.setCornerRadii(new float[]{
                    topLeftRadius, topLeftRadius,
                    topRightRadius, topRightRadius,
                    bottomRightRadius, bottomRightRadius,
                    bottomLeftRadius, bottomLeftRadius});
        }
    }

    /**
     * 更新Gradient的描边
     *
     * @param a 属性集合
     */
    private void updateGradientStroke(TypedArray a) {
        // 描边宽度
        final int strokeWidth = a.getDimensionPixelSize(
                R.styleable.GradientDrawableTextView_gdtv_strokeWidth, 0);
        // 虚线宽度
        final float dashWidth = a.getDimension(
                R.styleable.GradientDrawableTextView_gdtv_strokeDashWidth, 0.0f);

        // 描边颜色
        final int strokeColor = a.getColor(
                R.styleable.GradientDrawableTextView_gdtv_strokeColor, Color.TRANSPARENT);

        if (dashWidth != 0.0f) {
            // 虚线间隙
            final float dashGap = a.getDimension(
                    R.styleable.GradientDrawableTextView_gdtv_strokeDashGap, 0.0f);
            mBackgroundDrawable.setStroke(strokeWidth, strokeColor, dashWidth, dashGap);
        } else {
            mBackgroundDrawable.setStroke(strokeWidth, strokeColor);
        }
    }

    /**
     * 更新Gradient的填充颜色
     *
     * @param a 属性集合
     */
    private void updateGradientSolid(TypedArray a) {
        // 填充颜色
        final int solidColor = a.getColor(
                R.styleable.GradientDrawableTextView_gdtv_solidColor, Color.TRANSPARENT);
        mBackgroundDrawable.setColor(solidColor);
    }

    /**
     * 更新Gradient
     */
    private void updateGradient() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            setBackground(mBackgroundDrawable);
        } else {
            setBackgroundDrawable(mBackgroundDrawable);
        }
    }

    /**
     * 设置Gradient的填充颜色
     *
     * @param color 填充颜色
     */
    public void setGradientColor(int color) {
        mBackgroundDrawable.setColor(color);
    }

    /**
     * 设置Gradient的填充颜色
     *
     * @param colors 填充颜色
     */
    public void setGradientColors(int[] colors) {
        mBackgroundDrawable.setColors(colors);
    }

    /**
     * 设置Gradient的描边
     *
     * @param strokeWidth 描边宽度
     * @param strokeColor 描边颜色
     */
    public void setGradientStroke(int strokeWidth, int strokeColor) {
        mBackgroundDrawable.setStroke(strokeWidth, strokeColor);
    }

    /**
     * 设置Gradient的描边
     *
     * @param strokeWidth 描边宽度
     * @param strokeColor 描边颜色
     * @param dashWidth   虚线宽度
     * @param dashGap     虚线间隙
     */
    public void setGradientStroke(int strokeWidth, int strokeColor,
                                  float dashWidth, float dashGap) {
        mBackgroundDrawable.setStroke(strokeWidth, strokeColor, dashWidth, dashGap);
    }

    /**
     * 设置Gradient四角的半径
     *
     * @param radius 半径
     */
    public void setGradientCornerRadius(float radius) {
        mBackgroundDrawable.setCornerRadius(radius);
    }

    /**
     * 设置Gradient四角的半径
     *
     * @param radii 半径
     */
    public void setGradientCornerRadii(float[] radii) {
        mBackgroundDrawable.setCornerRadii(radii);
    }

    /**
     * 设置触摸亮度
     *
     * @param touchLum 亮度
     */
    public void setTouchLum(int touchLum) {
        mTouchLum = touchLum;
        if (touchLum < 0 || touchLum > 100) {
            throw new IllegalArgumentException("touchLum can not be less than ZERO or greater than ONE-HUNDRED");
        }
    }

    /**
     * 获取触摸亮度
     *
     * @return 亮度
     */
    public int getTouchLum() {
        return mTouchLum;
    }

    /**
     * 根据触摸亮度设置颜色滤色片
     *
     * @param touchLum 亮度
     */
    private void setGradientColorFilter(int touchLum) {

        float lum = (touchLum - 50) * 2 * 255 * 0.01f;

        mBackgroundDrawable.setColorFilter(new ColorMatrixColorFilter(new float[]{
                1, 0, 0, 0, lum,
                0, 1, 0, 0, lum,
                0, 0, 1, 0, lum,
                0, 0, 0, 1, 0
        }));
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (!mClickable) {
            return true;
        }
        // 如父布局不为空且可点击，则父布局执行触摸事件
        if (getParent() != null) {
            View parent = (View) getParent();
            if (parent.isClickable()) {
                parent.onTouchEvent(event);
            }
        }
        // 响应触摸事件
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                setGradientColorFilter(getTouchLum());
                break;
            case MotionEvent.ACTION_UP:
                setGradientColorFilter(50);
                break;
        }
        return false;
    }

    @Override
    public void setClickable(boolean clickable) {
        super.setClickable(clickable);
        mClickable = clickable;
    }
}
