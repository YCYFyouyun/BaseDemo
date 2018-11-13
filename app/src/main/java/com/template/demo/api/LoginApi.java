package com.template.demo.api;

import com.template.demo.bean.user.TokenBean;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 登录API
 */
public interface LoginApi {

    /**
     * 通过密码登录
     *
     * @param params code－国际区号、phone－手机号、pwd－经过特定规则加密后的密文
     * @return
     */
    @POST("xxxx/login/pwd")
    @FormUrlEncoded
    Observable<TokenBean> login(@FieldMap HashMap<String, String> params);

    /**
     * 退出登录
     *
     * @return
     */
    @POST("xxxx/token/logout")
    Observable<String> logout();

    //......

}
