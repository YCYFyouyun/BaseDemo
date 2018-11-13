package com.template.demo.app.account.register;


import com.template.base.net.RequestParams;
import com.template.demo.bean.user.TokenBean;
import com.template.demo.constant.AppValue;
import com.template.demo.data.user.UserRepository;
import com.template.demo.utils.core.EncryptPwdUtils;

import io.reactivex.Observable;

public class RegisterModel implements RegisterConstract.IModel {

    @Override
    public Observable<String> sendSms(String icode, String phone) {
        return UserRepository.get().sendSms(
                new RequestParams.Builder()
                        .putParam("icode", icode)
                        .putParam("phone", phone)
                        .putParam("type", "" + AppValue.CodeType.Register)
                        .build()
        );
    }
    
    @Override
    public Observable<TokenBean> register(String icode, String phone,
                                          String code, String pwd) {
        return UserRepository.get().register(
                new RequestParams.Builder()
                        .putParam("icode", icode)
                        .putParam("phone", phone)
                        .putParam("code", code)
                        .putParam("pwd", EncryptPwdUtils.encryptPwd(pwd))
                        .build()
        );
    }

}
