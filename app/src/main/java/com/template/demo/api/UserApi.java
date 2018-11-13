package com.template.demo.api;

import com.template.demo.bean.user.TokenBean;
import com.template.demo.bean.user.UserBeanData;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * 用户API
 */
public interface UserApi {

    /**
     * 注册
     *
     * @param params code－国际区号、phone－手机号、type－验证码、
     *               pwd－经过特定规则加密后的密文
     * @return
     */
    @POST("xxxx/registerUser")
    @FormUrlEncoded
    Observable<TokenBean> register(@FieldMap HashMap<String, String> params);

    /**
     * 获取用户详情
     *
     * @return
     */
    @GET("xxxx/token/user/detail")
    Observable<UserBeanData> userInfo();

    //.......

}
