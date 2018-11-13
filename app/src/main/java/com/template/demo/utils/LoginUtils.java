package com.template.demo.utils;

import android.app.Activity;
import android.content.Context;

import com.template.demo.app.account.login.LoginActivity;
import com.template.demo.data.user.UserRepository;

public class LoginUtils {

    /**
     * 是否登录
     *
     * @return
     */
    public static boolean isLogin() {
        return UserRepository.get().isLogin();
    }

    /**
     * 是否登录
     * 未登录，则跳转登录页
     *
     * @param context
     * @return
     */
    public static boolean isLoginWithSkip(Context context) {
        if (isLogin()) { //已登录
            return true;
        } else { //未登录
            LoginActivity.actionStart(context);
            return false;
        }
    }

    /**
     * 是否登录
     * 未登录，则跳转登录页，并关闭当前页面
     *
     * @param context
     * @return
     */
    public static boolean isLoginWithSkipAndClose(Context context) {
        if (isLogin()) { //已登录
            return true;
        } else { //未登录
            LoginActivity.actionStart(context);
            ((Activity) context).finish();
            return false;
        }
    }

}
