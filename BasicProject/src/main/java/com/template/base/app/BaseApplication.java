package com.template.base.app;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.multidex.MultiDexApplication;

import com.socks.library.KLog;
import com.template.base.BuildConfig;
import com.template.base.R;
import com.template.base.net.OkUtils;
import com.template.base.utils.CrashUtils;
import com.template.base.utils.Utils;

public class BaseApplication extends MultiDexApplication implements Application.ActivityLifecycleCallbacks {
    @Override
    public void onCreate() {
        super.onCreate();
        initKLog();
        initCommonUtils();
        initCrashUtils();
        initNetUtils();
        //注册Activity生命周期监听
        registerActivityLifecycleCallbacks(this);
    }

    /**
     * 初始化日志，如需关闭日志，建议在主Moudle的build.gradle关闭
     */
    protected void initKLog() {
        KLog.init(BuildConfig.DEBUG, getResources().getString(R.string.app_name));
    }

    /**
     * 初始化公共工具类
     */
    protected void initCommonUtils() {
        Utils.init(this);
    }

    /**
     * 初始化崩溃日志工具
     */
    protected void initCrashUtils() {
        CrashUtils.init();
    }

    /**
     * 初始化网络请求框架
     * 【子类可重写此方法初始化网络请求框架】
     */
    protected void initNetUtils() {
        OkUtils.get().init(this);
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        KLog.d("onActivityCreated: " + activity.getClass().getSimpleName());
        ActivityStack.getInstance().add(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {
        KLog.d("onActivityStarted: " + activity.getClass().getSimpleName());
    }

    @Override
    public void onActivityResumed(Activity activity) {
        KLog.d("onActivityResumed: " + activity.getClass().getSimpleName());
    }

    @Override
    public void onActivityPaused(Activity activity) {
        KLog.d("onActivityPaused: " + activity.getClass().getSimpleName());
    }

    @Override
    public void onActivityStopped(Activity activity) {
        KLog.d("onActivityStopped: " + activity.getClass().getSimpleName());
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        KLog.d("onActivitySaveInstanceState: " + activity.getClass().getSimpleName());
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        KLog.d("onActivityDestroyed: " + activity.getClass().getSimpleName());
        ActivityStack.getInstance().remove(activity);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ActivityStack.getInstance().finishAll();
        unregisterActivityLifecycleCallbacks(this);
    }
}
