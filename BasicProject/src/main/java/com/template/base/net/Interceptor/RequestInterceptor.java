package com.template.base.net.Interceptor;

import android.text.TextUtils;

import com.template.base.net.RetrofitRequestTool;
import com.template.base.net.SignUtils;
import com.template.base.utils.UrlUtils;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.Response;

public class RequestInterceptor implements Interceptor {

    public static final String KEY_NONCESTR = "nonce";
    public static final String KEY_SIGN = "signature";
    public static final String KEY_TOKEN = "token";

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request original = chain.request();
        // 添加头部 token 等
        final Request.Builder builder = chain.request().newBuilder();
        Map<String, String> headers = RetrofitRequestTool.getHeaders();
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            builder.addHeader(entry.getKey(), entry.getValue());
        }
        // 获取处理后的Request
        Request request = original.method().equals("GET")
                ? newGetRequest(original, builder)
                : newPostRequest(original, builder);
        return chain.proceed(request);
    }

    /**
     * 处理Post请求
     *
     * @param original
     * @param builder
     * @return
     */
    private Request newPostRequest(Request original, final Request.Builder builder) {
        SortMap sortMap = new SortMap();
        // FormBody
        FormBody.Builder newFormBody = new FormBody.Builder();
        if (original.body() instanceof FormBody) {
            FormBody oidFormBody = (FormBody) original.body();
            for (int i = 0; i < oidFormBody.size(); i++) {
                String key = oidFormBody.encodedName(i);
                String value = oidFormBody.encodedValue(i);
                if (!TextUtils.isEmpty(key) && !TextUtils.isEmpty(value)) {
                    newFormBody.addEncoded(key, value);
                    sortMap.put(URLDecoder.decode(key), URLDecoder.decode(value));
                }
            }
        }
        // MultipartBody
        MultipartBody.Builder newMultipartBody = null;
        if (original.body() instanceof MultipartBody) {
            MultipartBody oldMultipartBody = (MultipartBody) original.body();
            newMultipartBody = new MultipartBody.Builder();
            for (int i = 0; i < oldMultipartBody.size(); i++) {
                MultipartBody.Part part = oldMultipartBody.parts().get(i);
                newMultipartBody.addPart(part);
            }
        }
        // nocestr
        String noncestr = String.valueOf(System.currentTimeMillis());
        if (!TextUtils.isEmpty(noncestr)) {
            sortMap.put(KEY_NONCESTR, noncestr);
            if (newFormBody != null) newFormBody.addEncoded(KEY_NONCESTR, noncestr);
            if (newMultipartBody != null)
                newMultipartBody.addFormDataPart(KEY_NONCESTR, noncestr);
        }
        // token
        String token = RetrofitRequestTool.getToken();
        if (!TextUtils.isEmpty(token)) {
            sortMap.put(KEY_TOKEN, token);
            if (newFormBody != null) newFormBody.addEncoded(KEY_TOKEN, token);
            if (newMultipartBody != null) newMultipartBody.addFormDataPart(KEY_TOKEN, token);
        }
        // sign
        String sign = SignUtils.getClientSign(sortMap);
        if (newFormBody != null) newFormBody.addEncoded(KEY_SIGN, sign);
        if (newMultipartBody != null) newMultipartBody.addFormDataPart(KEY_SIGN, sign);

        if (newFormBody != null) builder.method(original.method(), newFormBody.build());
        if (newMultipartBody != null) builder.method(original.method(), newMultipartBody.build());
        return builder.build();
    }

    /**
     * 处理Get请求
     *
     * @param original
     * @param builder
     * @return
     */
    private Request newGetRequest(Request original, final Request.Builder builder) {
        String url = original.url().toString();
        // 获取"请求的地址"及"请求参数的集合"
        String strPage = null;
        Map<String, String> params = null;
        if (url.contains("?")) {
            strPage = UrlUtils.UrlPage(url);
            params = UrlUtils.UrlRequest(url);
        } else {
            strPage = url;
            params = new HashMap<>();
        }

        // nocestr
        String noncestr = String.valueOf(System.currentTimeMillis());
        if (!TextUtils.isEmpty(noncestr)) params.put(KEY_NONCESTR, noncestr);
        // token
        String token = RetrofitRequestTool.getToken();
        if (!TextUtils.isEmpty(token)) params.put(KEY_TOKEN, token);

        // 获取新的url
        String newUrl = SignUtils.getUrlWithSign(strPage, params);
        return builder.url(newUrl).build();

    }


}
