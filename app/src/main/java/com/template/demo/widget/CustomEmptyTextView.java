package com.template.demo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.template.demo.R;
import com.template.demo.utils.core.LanguageUtils;


public class CustomEmptyTextView extends android.support.v7.widget.AppCompatTextView {

    public CustomEmptyTextView(Context context) {
        super(context);
    }

    public CustomEmptyTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomEmptyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setText(LanguageUtils.get().getString("empty_data", R.string.empty_data));
    }

}
