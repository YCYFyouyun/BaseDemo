package com.template.demo.data.user;

import com.template.base.net.RetrofitUtil;
import com.template.demo.api.LoginApi;
import com.template.demo.api.SmsApi;
import com.template.demo.api.UserApi;
import com.template.demo.bean.user.TokenBean;

import java.util.HashMap;

import io.reactivex.Observable;

public class UserRepositoryRemote implements UserImpl {

    SmsApi mSmsApi;
    LoginApi mLoginApi;
    UserApi mUserApi;

    protected UserRepositoryRemote() {
        mSmsApi = RetrofitUtil.get().getService(SmsApi.class);
        mLoginApi = RetrofitUtil.get().getService(LoginApi.class);
        mUserApi = RetrofitUtil.get().getService(UserApi.class);
    }

    @Override
    public Observable<String> sendSms(HashMap<String, String> params) {
        return mSmsApi.send(params);
    }

    @Override
    public Observable<TokenBean> register(HashMap<String, String> params) {
        return mUserApi.register(params);
    }

    @Override
    public Observable<TokenBean> login(HashMap<String, String> params) {
        return mLoginApi.login(params);
    }

    @Override
    public Observable<String> logout() {
        return mLoginApi.logout();
    }

    //......

}
