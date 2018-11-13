package com.template.base.app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.template.base.dialog.LoadingDialog;
import com.template.base.utils.ToastUtils;

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    protected View mContentView;
    protected ViewFinder mFinder;
    protected LoadingDialog mLoadingDialog;
    protected Handler mLoadingHandler;
    protected long mBtnClickStamp; //记录按键点击的时间戳

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //执行预操作
        beforeOnCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        //处理Intent
        getIntentData(getIntent(), false);
        //设置ContentView
        if (mContentView == null)
            mContentView = getLayoutInflater().inflate(getContentViewId(), null);
        setContentView(mContentView);
        //初始化UI
        mFinder = new ViewFinder(mContentView);
        initUI(mContentView);
        //初始化数据
        initData(savedInstanceState);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        getIntentData(intent, true);
    }

    /**
     * 子类可重写此方法在onCreate之前执行一些准备操作
     */
    protected void beforeOnCreate(Bundle savedInstanceState) {

    }

    /**
     * 子类可重写此方法获取Intent传递过来的参数
     */
    protected void getIntentData(Intent data, boolean onNewIntent) {

    }

    /**
     * 子类实现此方法指定contentView的id
     */
    public abstract int getContentViewId();

    /**
     * 子类实现此方法初始化UI
     */
    protected abstract void initUI(View contentView);

    /**
     * 子类实现此方法初始化数据
     */
    protected abstract void initData(@Nullable Bundle savedInstanceState);

    /**
     * 查找View
     */
    protected <T extends View> T find(int resId) {
        return mFinder.findView(resId);
    }

    /**
     * 添加点击事件
     */
    protected void addClick(View view) {
        mFinder.addClick(view, this);
    }

    /**
     * 添加点击事件
     */
    protected void addClick(int id) {
        mFinder.addClick(id, this);
    }

    /**
     * 添加点击事件
     */
    protected void addClick(int... ids) {
        for (int i = 0; i < ids.length; i++) {
            mFinder.addClick(ids[i], this);
        }
    }

    @Override
    public void onClick(View v) {
        onClick(v.getId(), v);
    }

    /**
     * 弹出时间较短的Toast
     */
    public void showShortToast(CharSequence message) {
        ToastUtils.showShort(message);
    }

    /**
     * 弹出时间较短的Toast
     */
    public void showShortToast(@StringRes int resId) {
        ToastUtils.showShort(resId);
    }

    /**
     * 弹出时间较长的Toast
     */
    public void showLongToast(CharSequence message) {
        ToastUtils.showLong(message);
    }

    /**
     * 弹出时间较长的Toast
     */
    public void showLongToast(@StringRes int resId) {
        ToastUtils.showLong(resId);
    }

    /**
     * 子类实现此方法处理点击事件
     */
    protected abstract void onClick(int id, View v);

    /**
     * 添加 fragment
     *
     * @param idRes    容器ID
     * @param fragment 待添加的 fragment
     */
    protected void addFragment(@IdRes int idRes, Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(idRes, fragment);
        ft.commit();
    }

    /**
     * 替换 fragment
     *
     * @param idRes    容器ID
     * @param fragment 待替换的 fragment
     */
    protected void replaceFragment(@IdRes int idRes, Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(idRes, fragment);
        ft.commit();
    }

    /**
     * 隐藏控件
     *
     * @param ids
     */
    protected void visible(int... ids) {
        for (int i = 0; i < ids.length; i++) {
            find(ids[i]).setVisibility(View.VISIBLE);
        }
    }

    /**
     * 显示控件
     *
     * @param ids
     */
    protected void gone(int... ids) {
        for (int i = 0; i < ids.length; i++) {
            find(ids[i]).setVisibility(View.GONE);
        }
    }

    /**
     * 显示Loading
     *
     * @param isCancelable 是否可手动撤销
     * @param delay        延迟显示loading的时间
     */
    public void showLoading(final boolean isCancelable, long delay) {
        if (mLoadingHandler == null) mLoadingHandler = new Handler();
        if (delay <= 0) {
            showLoading(isCancelable);
            return;
        }
        mLoadingHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                showLoading(isCancelable);
            }
        }, delay);
    }

    /**
     * 显示Loading
     *
     * @param isCancelable 是否可手动撤销
     */
    public void showLoading(boolean isCancelable) {
        if (mLoadingDialog != null) mLoadingDialog.dismiss();
        mLoadingDialog = new LoadingDialog(this, isCancelable);
        mLoadingDialog.show();
    }

    /**
     * 隐藏Loading
     */
    public void hideLoading() {
        //移除所有可执行任务
        if (mLoadingHandler != null) {
            mLoadingHandler.removeMessages(0);
            mLoadingHandler = null;
        }
        //隐藏Loading
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
            mLoadingDialog = null;
        }
    }

    /**
     * 按键是否在缓冲
     *
     * @return
     */
    public boolean isBtnBuffering() {
        long duration = System.currentTimeMillis() - mBtnClickStamp;
        mBtnClickStamp = System.currentTimeMillis(); //记录点击时间
        return Math.abs(duration) <= 400;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        hideLoading();
    }

}
