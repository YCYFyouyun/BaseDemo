package com.template.demo.data.user;

import com.template.demo.bean.user.TokenBean;

import java.util.HashMap;

import io.reactivex.Observable;

public interface UserImpl {

    Observable<String> sendSms(HashMap<String, String> params);

    Observable<TokenBean> register(HashMap<String, String> params);

    Observable<TokenBean> login(HashMap<String, String> params);

    Observable<String> logout();

    //......

}
