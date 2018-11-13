package com.template.base.image.glide;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GlideOkClient {
    private OkHttpClient mOkClient;
    private static final long TIMEOUT_DURATION = 10000;
    private DispatchProgressListener mDispatchProgressListener;

    private GlideOkClient() {
        mOkClient = new OkHttpClient.Builder()
                .connectTimeout(TIMEOUT_DURATION, TimeUnit.MILLISECONDS)
                .readTimeout(TIMEOUT_DURATION, TimeUnit.MILLISECONDS)
                .writeTimeout(TIMEOUT_DURATION, TimeUnit.MILLISECONDS)
                .addNetworkInterceptor(getInterceptor(new DispatchProgressListener()))
                .build();
        mDispatchProgressListener = new DispatchProgressListener();
    }

    private static final class InnerHolder {
        public static final GlideOkClient instance = new GlideOkClient();
    }

    public static GlideOkClient get() {
        return InnerHolder.instance;
    }

    public OkHttpClient getOkClient() {
        return mOkClient;
    }

    private Interceptor getInterceptor(final GlideResponseBody.OnTransferListener listener) {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Response response = chain.proceed(request);
                return response.newBuilder()
                        .body(new GlideResponseBody(request.url(), response.body(), listener))
                        .build();
            }
        };
    }

    public interface OnUIProgressListener {
        void onProgress(long bytesRead, long contentLength);
    }

    public void addUIProgressListener(String url, OnUIProgressListener listener) {
        mDispatchProgressListener.add(url, listener);
    }

    public void removeUIProgressListener(String url) {
        mDispatchProgressListener.remove(url);
    }

    private class DispatchProgressListener implements GlideResponseBody.OnTransferListener {
        private final Map<String, OnUIProgressListener> mListenerMap = new HashMap<>();
        private final Handler handler;

        private DispatchProgressListener() {
            this.handler = new Handler(Looper.getMainLooper());
        }

        public void remove(String url) {
            if (!TextUtils.isEmpty(url))
                mListenerMap.remove(url);
        }

        public void add(String url, OnUIProgressListener listener) {
            if (!TextUtils.isEmpty(url))
                mListenerMap.put(url, listener);
        }

        @Override
        public void onProgressing(HttpUrl url, final long bytesRead, final long contentLength) {
            String key = url.toString();
            final OnUIProgressListener listener = mListenerMap.get(key);
            if (listener == null)
                return;

            handler.post(new Runnable() {
                @Override
                public void run() {
                    listener.onProgress(bytesRead, contentLength);
                }
            });
        }
    }

}

