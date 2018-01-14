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
 * 一个自带GradientDrawable的TextView
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
     * 触摸时变化的亮度
     */
    private int touchLum = DEFAULT_LUM;
    /**
     * getBackground为null时，gradient可以作为有效背景
     */
    private GradientDrawable gradient = new GradientDrawable();
    /**
     * 是否能点击
     */
    private boolean clickable = false;


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
        gradient.setCornerRadius(radius);
        gradient.setOrientation(GradientDrawable.Orientation.LEFT_RIGHT);

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
            gradient.setCornerRadii(new float[]{
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
            gradient.setStroke(strokeWidth, strokeColor, dashWidth, dashGap);
        } else {
            gradient.setStroke(strokeWidth, strokeColor);
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
        gradient.setColor(solidColor);
    }

    /**
     * 更新Gradient
     */
    private void updateGradient() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            setBackground(gradient);
        } else {
            setBackgroundDrawable(gradient);
        }
    }

    /**
     * 设置Gradient的填充颜色
     *
     * @param solidColor 填充颜色
     */
    public void setGradientSolidColor(String solidColor) {
        gradient.setColor(Color.parseColor(solidColor));
    }

    public void setGradientSolidColors(int[] colors) {
        gradient.setColors(colors);
    }

    /**
     * 设置Gradient的描边
     *
     * @param strokeWidth 描边宽度
     * @param strokeColor 描边颜色
     */
    public void setGradientStroke(int strokeWidth, String strokeColor) {
        gradient.setStroke(strokeWidth, Color.parseColor(strokeColor));
    }

    /**
     * 设置Gradient的描边
     *
     * @param strokeWidth     描边宽度
     * @param strokeColor     描边颜色
     * @param strokeDashWidth 虚线宽度
     * @param strokeDashGap   虚线间隙
     */
    public void setGradientStroke(int strokeWidth, String strokeColor,
                                  float strokeDashWidth, float strokeDashGap) {
        gradient.setStroke(strokeWidth, Color.parseColor(strokeColor), strokeDashWidth, strokeDashGap);
    }

    /**
     * 设置Gradient四角的半径
     *
     * @param radius 半径
     */
    public void setGradientCornerRadius(float radius) {
        gradient.setCornerRadius(radius);
    }

    /**
     * 设置Gradient四角的半径
     *
     * @param radii 半径数组
     */
    public void setGradientCornerRadii(float[] radii) {
        gradient.setCornerRadii(radii);
    }

    /**
     * 设置触摸时的亮度
     *
     * @param lum 亮度
     */
    public void setTouchLum(int lum) {
        touchLum = lum;
        if (lum < 0 || lum > 100) {
            throw new IllegalArgumentException("lum can not be < 0 or > 100");
        }
    }

    /**
     * 获取触摸时的亮度
     *
     * @return 亮度
     */
    public int getTouchLum() {
        return touchLum;
    }

    private void setGradientColorFilter(int touchLum) {

        // 获取触摸时变化的亮度，其他值为公式
        float lum = (touchLum - 50) * 2 * 255 * 0.01f;

        gradient.setColorFilter(new ColorMatrixColorFilter(new float[]{
                1, 0, 0, 0, lum,
                0, 1, 0, 0, lum,
                0, 0, 1, 0, lum,
                0, 0, 0, 1, 0
        }));
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (!clickable) {
            return true;
        }
        // 如果父布局不为空且可点击，则执行触摸事件
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
        this.clickable = clickable;
    }
}
