package com.template.demo.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.template.demo.R;


public class CustomViewPager extends ViewPager {

    private boolean mNoScroll = false;

    public CustomViewPager(@NonNull Context context) {
        this(context, null);
    }

    public CustomViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.CustomViewPager, 0, 0);
        mNoScroll = typedArray.getBoolean(R.styleable.CustomViewPager_no_scroll, false);
        typedArray.recycle();
    }

    public void setNoScroll(boolean noScroll) {
        this.mNoScroll = noScroll;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (mNoScroll) return false;
        return super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (mNoScroll) return false;
        return super.onInterceptTouchEvent(ev);
    }

}
