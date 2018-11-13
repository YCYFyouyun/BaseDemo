package com.template.base.net;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class OkUtils {
    private OkUtils() {
        mOkBuilder = new OkHttpClient.Builder();
    }

    private static final class InnerHolder {
        public static final OkUtils instance = new OkUtils();
    }

    public static OkUtils get() {
        return InnerHolder.instance;
    }

    public static final long DEFAULT_CONNECT_MILLISECONDS = 120000;

    private OkHttpClient mOkClient;
    private OkHttpClient.Builder mOkBuilder;

    /**
     * 获取OkHttpClient
     */
    public OkHttpClient getOkClient() {
        return mOkClient;
    }

    /**
     * 获取OkHttpClient.Builder
     */
    public OkHttpClient.Builder getOkBuilder() {
        return mOkBuilder;
    }

    /**
     * 初始化入口，在Application调用
     */
    public void init(Context context) {
        mOkBuilder.addInterceptor(new OkLogInterceptor());
        mOkBuilder.connectTimeout(DEFAULT_CONNECT_MILLISECONDS, TimeUnit.MILLISECONDS);
        mOkBuilder.readTimeout(DEFAULT_CONNECT_MILLISECONDS, TimeUnit.MILLISECONDS);
        mOkBuilder.writeTimeout(DEFAULT_CONNECT_MILLISECONDS, TimeUnit.MILLISECONDS);
        mOkClient = mOkBuilder.build();
    }

    /**
     * @param mOkBuilder
     * @return
     */
    public OkHttpClient getUploadOkClient(OkHttpClient.Builder mOkBuilder) {
        mOkBuilder.addInterceptor(new OkLogInterceptor());
        return mOkBuilder.build();
    }

}
