package com.template.rxextention.utils;

import android.content.Context;

import com.template.base.net.RequestError;


public class RxAction {

    private static RxActionImpl mRxActionImpl;
    private static RxActionImpl mRxActionImplDef = new DefRxActionImpl();

    public static RxActionImpl get() {
        return mRxActionImpl != null ? mRxActionImpl : mRxActionImplDef;
    }

    /**
     * 获取token失效对应的错误码
     *
     * @return
     */
    public static int getTokenInvalid() {
        return get().getTokenInvalidErrorCode();
    }

    /**
     * 设置对应值
     *
     * @param mRxActionImpl
     */
    public static void setRxAction(RxActionImpl mRxActionImpl) {
        RxAction.mRxActionImpl = mRxActionImpl;
    }


    public static class DefRxActionImpl implements RxActionImpl {

        public DefRxActionImpl() {
        }

        @Override
        public int getTokenInvalidErrorCode() {
            return 107; //token失效默认为107
        }

        @Override
        public void tokenInvaildAction(Context context, RequestError error) {

        }
    }

}
