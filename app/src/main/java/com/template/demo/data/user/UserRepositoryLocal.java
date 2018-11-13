package com.template.demo.data.user;

import android.text.TextUtils;

import com.template.base.net.RetrofitRequestTool;
import com.template.base.utils.SpUtils;
import com.template.demo.bean.user.UserBean;


public class UserRepositoryLocal {

    protected UserRepositoryLocal() {
    }

    private final static String USER_BEAN = "USER";
    UserBean mUserBean;

    /**
     * 是否登录
     *
     * @return
     */
    public boolean isLogin() {
        String token = RetrofitRequestTool.getToken();
        return !TextUtils.isEmpty(token);
    }

    /**
     * 退出登录，清空本地的信息
     */
    public void logout() {
        saveToken(null); //清空token
        setUserBean(null); //清空用户信息
        //......
    }

    /**
     * 保存token
     *
     * @param token
     */
    public void saveToken(String token) {
        RetrofitRequestTool.saveToken(token);
        //......
    }

    /**
     * 获取本地用户信息
     *
     * @return
     */
    public UserBean getUserBean() {
        if (mUserBean == null) mUserBean = SpUtils.getObject(USER_BEAN, UserBean.class);
        return mUserBean;
    }

    /**
     * 设置本地用户信息
     *
     * @param userBean
     */
    public void setUserBean(UserBean userBean) {
        mUserBean = userBean;
        SpUtils.putObject(USER_BEAN, userBean);
    }

    //......

}
