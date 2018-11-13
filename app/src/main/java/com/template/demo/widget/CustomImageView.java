package com.template.demo.widget;


import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.template.demo.R;


public class CustomImageView extends android.support.v7.widget.AppCompatImageView {

    private int width_weight = 1; //宽度占比
    private int height_weight = 1; //高度占比

    public CustomImageView(Context context, int width_weight, int height_weight) {
        super(context);
        this.width_weight = width_weight;
        this.height_weight = height_weight;
    }

    public CustomImageView(Context context) {
        this(context, null);
    }

    public CustomImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.CustomCardView, defStyleAttr, 0);
        for (int i = 0; i < typedArray.getIndexCount(); i++) {
            int attr = typedArray.getIndex(i);
            switch (attr) {
                case R.styleable.CustomCardView_width_weight:
                    width_weight = typedArray.getInt(attr, 1);
                    break;
                case R.styleable.CustomCardView_height_weight:
                    height_weight = typedArray.getInt(attr, 1);
                    break;
            }
        }
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获取父容器允许的宽高
        int maxWidth = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int maxHeight = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        //获取测量的宽高
        int mWidth = getMeasuredWidth();
        int mHeight = getMeasuredHeight();
        //最终设定的宽高
        int width = 0;
        int height = 0;
        //根据MeasureSpec模式的不同，宽高取值不同
        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            //当都为wrap时，宽高取最大值
            if (mWidth * height_weight >= mHeight * width_weight) {
                width = mWidth;
                height = width * height_weight / width_weight;
            } else {
                height = mHeight;
                width = height * width_weight / height_weight;
            }
        } else if (widthMode == MeasureSpec.EXACTLY && heightMode == MeasureSpec.EXACTLY) {
            //当都为固定值或match时，宽高取最小值，以保证不会越过设定的最大范围
            if (mWidth * height_weight <= mHeight * width_weight) {
                width = mWidth;
                height = width * height_weight / width_weight;
            } else {
                height = mHeight;
                width = height * width_weight / height_weight;
            }
        } else if (widthMode == MeasureSpec.EXACTLY) {
            //当一项设为固定值或match时，以这条为标准
            width = mWidth;
            height = width * height_weight / width_weight;
        } else if (heightMode == MeasureSpec.EXACTLY) {
            //当一项设为固定值或match时，以这条为标准
            height = mHeight;
            width = height * width_weight / height_weight;
        }
        //最终和父容器允许的宽高比较，最大不超过父容器的约束
        if (maxWidth < width) {
            width = maxWidth;
            height = width * height_weight / width_weight;
        }
        if (maxHeight < height) {
            height = maxHeight;
            width = height * width_weight / height_weight;
        }
        //将最终的宽高设定为容器的宽高
        setMeasuredDimension(width, height);
    }

}
