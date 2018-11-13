package com.template.demo.app.account.login;


import com.template.base.net.RequestParams;
import com.template.demo.bean.user.TokenBean;
import com.template.demo.data.user.UserRepository;
import com.template.demo.utils.core.EncryptPwdUtils;

import io.reactivex.Observable;

public class LoginModel implements LoginConstract.IModel {

    @Override
    public Observable<TokenBean> login(String icode, String phone, String pwd) {
        return UserRepository.get().login(
                new RequestParams.Builder()
                        .putParam("icode", icode)
                        .putParam("phone", phone)
                        .putParam("pwd", EncryptPwdUtils.encryptPwd(pwd))
                        .build()
        );
    }

    //......

}
