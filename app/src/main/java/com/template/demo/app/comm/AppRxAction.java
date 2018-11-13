package com.template.demo.app.comm;

import android.content.Context;

import com.template.base.net.RequestError;
import com.template.base.utils.ToastUtils;
import com.template.demo.R;
import com.template.demo.api.ApiConstants;
import com.template.demo.utils.core.LanguageUtils;
import com.template.rxextention.utils.RxActionImpl;

public class AppRxAction implements RxActionImpl {

    @Override
    public int getTokenInvalidErrorCode() {
        return ApiConstants.ErrorCode.ILLEGAL_TOKEN;
    }

    @Override
    public void tokenInvaildAction(Context context, RequestError error) {
        ToastUtils.showShort(LanguageUtils.get().getString("error_remote_login", R.string.error_remote_login));
        
//        UserRepository.get().clearUserBean(); // 清除token及用户信息等
//        LoginActivity.actionStart(context); // 打开登录页
    }

}
