package com.template.base.app;

import android.app.Activity;

import java.util.Stack;

public class ActivityStack {
    private ActivityStack() {
        mStack = new Stack<Activity>();
    }

    private static final class InnerHolder {
        private static ActivityStack instance = new ActivityStack();
    }

    public static ActivityStack getInstance() {
        return InnerHolder.instance;
    }

    private Stack<Activity> mStack;

    /**
     * 添加一个Activity实例
     */
    public boolean add(Activity activity) {
        return mStack != null && mStack.add(activity);
    }

    /**
     * 移除一个Activity实例
     */
    public boolean remove(Activity activity) {
        return activity != null && mStack != null && mStack.remove(activity);
    }

    /**
     * 获取栈顶实例
     */
    public Activity getTop() {
        if (mStack != null && !mStack.isEmpty())
            return mStack.lastElement();
        return null;
    }

    /**
     * Remove all activity instances
     */
    public void removeAll() {
        if (mStack != null)
            mStack.removeAllElements();
    }

    /**
     * 移除并finish一个实例
     */
    public boolean finish(Activity activity) {
        if (remove(activity)) {
            activity.finish();
            return true;
        } else {
            return false;
        }
    }

    /**
     * 移除并finish所有实例
     */
    public void finishAll() {
        if (mStack != null) {
            for (Activity activity : mStack) {
                activity.finish();
            }
            mStack.removeAllElements();
        }
    }
}
