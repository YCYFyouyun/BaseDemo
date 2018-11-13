package com.template.base.app;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.template.base.utils.ToastUtils;


public abstract class BaseFragment extends Fragment implements View.OnClickListener {
    protected View mContentView;
    protected ViewFinder mFinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if (mContentView == null) {
            mContentView = inflater.inflate(getContentViewId(), container, false);
            mFinder = new ViewFinder(mContentView);
        }
        return mContentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getArgumentsData(getArguments());
        initUI(mContentView);
        initData(savedInstanceState);
    }

    /**
     * 子类可重写此方法获取传入的Argument数据
     */
    protected void getArgumentsData(Bundle bundle) {

    }

    /**
     * 子类实现此方法指定contentView的id
     */
    protected abstract int getContentViewId();

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
     * 显示Loading
     *
     * @param isCancelable 是否可手动撤销
     */
    public void showLoading(boolean isCancelable, long delay) {
        if (getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).showLoading(isCancelable, delay);
        }
    }

    /**
     * 显示Loading
     *
     * @param isCancelable 是否可手动撤销
     */
    public void showLoading(boolean isCancelable) {
        if (getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).showLoading(isCancelable);
        }
    }

    /**
     * 隐藏Loading
     */
    public void hideLoading() {
        if (getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).hideLoading();
        }
    }

    /**
     * 按键是否在缓冲
     *
     * @return
     */
    public boolean isBtnBuffering() {
        if (getActivity() instanceof BaseActivity) {
            return ((BaseActivity) getActivity()).isBtnBuffering();
        }
        return false;
    }
}
