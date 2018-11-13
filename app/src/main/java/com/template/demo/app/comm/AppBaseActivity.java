package com.template.demo.app.comm;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.template.rxextention.mvp.RxBasePresenter;
import com.template.rxextention.mvp.RxMvpBaseActivity;
import com.wuhenzhizao.titlebar.utils.AppUtils;


public abstract class AppBaseActivity<P extends RxBasePresenter> extends RxMvpBaseActivity<P> {
    @Override
    protected void beforeOnCreate(Bundle savedInstanceState) {
        super.beforeOnCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); //设置为竖屏
        setStatusBar(); //设置状态栏
    }
    
    /**
     * 可统一在此处设置状态栏
     */
    protected void setStatusBar() {
        AppUtils.transparencyBar(getWindow());
        AppUtils.StatusBarLightMode(getWindow());
    }

}
