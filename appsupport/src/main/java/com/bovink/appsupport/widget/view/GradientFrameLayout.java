package com.bovink.appsupport.widget.view;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * com.bovink.appsupport.widget.view
 *
 * @author bovink
 * @since 2018/1/14
 */

public class GradientFrameLayout extends FrameLayout {
    public GradientFrameLayout(@NonNull Context context) {
        super(context);
    }

    public GradientFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public GradientFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
