package com.bovink.appsupportforandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bovink.appsupport.widget.view.GradientDrawableTextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GradientDrawableTextView testTextView = (GradientDrawableTextView) findViewById(R.id.tv_test);
        testTextView.setClickable(true);
    }
}
