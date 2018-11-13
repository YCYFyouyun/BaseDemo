package com.template.base.image.constants;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


public class ImageScaleType {
    public static final int UNDEFINED = 0X00000000;
    public static final int CENTER_CROP = 0X00000001;
    public static final int FIT_CENTER = 0X00000002;
    public static final int CENTER_INSIDE = 0X00000003;

    @IntDef({UNDEFINED, CENTER_CROP, FIT_CENTER, CENTER_INSIDE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Type {
    }
}
