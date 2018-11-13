package com.template.base.utils;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.StringRes;
import android.widget.Toast;

/**
 * Toast工具类
 */
public final class ToastUtils {
    private static Handler mHandler = new Handler(Looper.getMainLooper());

    private static Toast mToast;
    private static ToastImpl mToastImpl;

    private ToastUtils() {
        throw new UnsupportedOperationException("Can't instantiate this class !");
    }

    /**
     * 显示时长较长的Toast
     */
    public static void showLong(@StringRes int resId) {
        show(Utils.getContext().getResources().getString(resId), Toast.LENGTH_LONG);
    }

    /**
     * 显示时长较长的Toast
     */
    public static void showLong(CharSequence s) {
        show(s, Toast.LENGTH_LONG);
    }

    /**
     * 显示时长较短的Toast
     */
    public static void showShort(@StringRes int resId) {
        show(Utils.getContext().getResources().getString(resId), Toast.LENGTH_SHORT);
    }

    /**
     * 显示时长较短的Toast
     */
    public static void showShort(CharSequence s) {
        show(s, Toast.LENGTH_SHORT);
    }

    //执行Toast的方法
    private static void show(final CharSequence s, final int duration) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (mToast != null) {
                    mToast.cancel();
                    mToast = null;
                }

                //为解决MIUI上显示App名字的问题，调整
                //mToast = Toast.makeText(Utils.getContext(), s, duration);
                mToast = Toast.makeText(Utils.getContext(), null, duration);
                mToast.setText(s);
                mToast.show();
            }
        });
    }

    public static void setToastImpl(ToastImpl toastImpl) {
        ToastUtils.mToastImpl = toastImpl;
    }

    public static void showNetError() {
        showShort(mToastImpl != null ? mToastImpl.getNetError() : "网络出错");
    }

}
