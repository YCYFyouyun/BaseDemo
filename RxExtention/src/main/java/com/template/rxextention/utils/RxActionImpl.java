package com.template.rxextention.utils;

import android.content.Context;

import com.template.base.net.RequestError;


public interface RxActionImpl {

    /**
     * 获取token失效对应的错误码
     *
     * @return
     */
    int getTokenInvalidErrorCode();

    /**
     * token失效时的操作
     *
     * @param error
     */
    void tokenInvaildAction(Context context, RequestError error);

}
