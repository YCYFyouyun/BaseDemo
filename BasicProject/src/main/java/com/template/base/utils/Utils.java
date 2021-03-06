package com.template.base.utils;

import android.content.Context;

public final class Utils {
    private static Context mContext;

    private Utils() {
        throw new UnsupportedOperationException("Can't instantiate this class !");
    }

    public static void init(Context context) {
        mContext = context.getApplicationContext();
    }

    public static Context getContext() {
        if (mContext != null)
            return mContext;
        else
            throw new UnsupportedOperationException("You should invoke Utils.init(Context context) in Application !");
    }
}
