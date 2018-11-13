package com.template.demo.api;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 短信API
 */
public interface SmsApi {

    /**
     * 发送短信
     *
     * @param params icode－国际区号、phone－手机号、type－验证码类型(100登录、200注册、300密码修改)
     * @return
     */
    @POST("xxxx/sms/sendcode")
    @FormUrlEncoded
    Observable<String> send(@FieldMap HashMap<String, String> params);

    //......

}
