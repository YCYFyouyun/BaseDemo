package com.template.base.net;

import com.template.base.utils.SpUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RetrofitRequestTool {

    private static Map<String, String> mHeaders;
    private static final String REQUEST_HEADERS = "REQUEST_HEADERS";
    private static final String SPLITER = "--BASE--";
    private static final String KEY_TOKEN = "RetrofitRequestTool#KEY_TOKEN";

    private static String mAppid;
    private static String key = "";
    private static String token;

    public static String getAppid() {
        return mAppid;
    }

    public static void setAppid(String appid) {
        mAppid = appid;
    }

    public static String getKey() {
        return key;
    }

    public static void setKey(String key) {
        RetrofitRequestTool.key = key;
    }

    public static void saveToken(String token) {
        RetrofitRequestTool.token = token;
        SpUtils.putString(KEY_TOKEN, token);
    }

    public static String getToken() {
        if (token != null) {
            return token;
        }
        token = SpUtils.getString(KEY_TOKEN, null);
        return token;
    }

    public static Map<String, String> getHeaders() {
        initHeaders();
        return mHeaders;
    }

    public static void addHeader(String key, String value) {
        initHeaders();
        mHeaders.put(key, value);
        save(mHeaders);
    }

    public static String getHeader(String key) {
        initHeaders();
        return mHeaders.get(key);
    }

    public static void setHeaders(Map<String, String> headers) {
        initHeaders();
        mHeaders.clear();
        for (String key : headers.keySet()) {
            mHeaders.put(key, headers.get(key));
        }
        save(mHeaders);
    }

    public static void remove(String key) {
        initHeaders();
        if (mHeaders.containsKey(key)) {
            mHeaders.remove(key);
            save(mHeaders);
        }
    }

    public static void removeAll() {
        initHeaders();
        mHeaders.clear();
        save(mHeaders);
    }

    private static void save(Map<String, String> headers) {
        Set<String> strings = new HashSet<>();
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            strings.add(entry.getKey() + SPLITER + entry.getValue());
        }
        SpUtils.putStringSet(REQUEST_HEADERS, strings);
    }

    private static void initHeaders() {
        if (mHeaders == null) {
            mHeaders = new HashMap<>();
            Set<String> strings = SpUtils.getStringSet(REQUEST_HEADERS);
            for (String string : strings) {
                String[] sts = string.split(SPLITER);
                if (sts.length > 1) {
                    mHeaders.put(sts[0], sts[1]);
                }
            }
        }
    }
}
