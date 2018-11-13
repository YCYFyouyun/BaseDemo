package com.template.base.net;

import android.text.TextUtils;

import com.template.base.net.Interceptor.ReceivedInterceptor;
import com.template.base.net.converter.FastJsonConverterFactory;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class RetrofitUtil {

    public static final String MULTIPART_FORM_DATA = "multipart/form-data; boundary=--------------------------545253583086219858499430";

    private RetrofitUtil() {
    }

    private static final class InnerHolder {
        public static final RetrofitUtil instance = new RetrofitUtil();
    }

    public static RetrofitUtil get() {
        return InnerHolder.instance;
    }

    private Retrofit mRetrofit;

    /**
     * @param host      服务器地址（必须以 / 结尾）
     * @param rxFacotry 适配器
     */
    public void init(String host, OkHttpClient okHttpClient, CallAdapter.Factory rxFacotry) {
        if (TextUtils.isEmpty(host)) {
            host = "http://localhost/";
        }
        mRetrofit = new Retrofit.Builder()
                .baseUrl(host)
                .client(okHttpClient)
                .addConverterFactory(FastJsonConverterFactory.create())  // 添加 fastJson 解析器
                .addCallAdapterFactory(rxFacotry) // 添加 RxJava 适配器
                .build();
    }

    /**
     * @param tClass retrofitAPI 类
     * @return
     */
    public <T> T getService(Class<T> tClass) {
        return mRetrofit.create(tClass);
    }

    /**
     * Retrofit 使用 OkHttp 上传时的 RequestBody
     *
     * @param value 参数
     * @return
     */
    public static RequestBody getRequestBody(String value) {
        return RequestBody.create(MediaType.parse("multipart/form-data"), value);
    }

    /**
     * Retrofit 使用 OkHttp 上传时的 file 包装
     *
     * @param key  参数
     * @param file 文件名
     * @return
     */
    public static MultipartBody.Part getRequestPart(String key, File file) {
        RequestBody fileBody = MultipartBody.create(MediaType.parse("image/*"), file);
        return MultipartBody.Part.createFormData(key, file.getName(), fileBody);
    }

    /**
     * @param key
     * @param file
     * @return
     */
    public static MultipartBody.Part getRequestPartFile(String key, File file) {
        RequestBody fileBody = RequestBody.create(MediaType.parse("application/otcet-stream"), file);
        return MultipartBody.Part.createFormData(key, file.getName(), fileBody);
    }

    /**
     * @param key   参数
     * @param files 文件名列表
     * @return
     */
    public static MultipartBody.Part[] getRequestParts(String key, File... files) {
        MultipartBody.Part[] parts = new MultipartBody.Part[files.length];
        for (int i = 0; i < files.length; i++) {
            parts[i] = getRequestPart(key, files[i]);
        }
        return parts;
    }

    /**
     * @param key
     * @param file
     * @return
     */
    public static MultipartBody.Part prepareFilePart(String key, File file) {
        RequestBody requestBody = RequestBody.create(MediaType.parse(MULTIPART_FORM_DATA), file);
        return MultipartBody.Part.createFormData(key, file.getName(), requestBody);
    }

    /**
     * @param key
     * @param files
     * @return
     */
    public static MultipartBody.Part[] prepareFileParts(String key, File... files) {
        MultipartBody.Part[] parts = new MultipartBody.Part[files.length];
        for (int i = 0; i < files.length; i++) {
            parts[i] = prepareFilePart(key, files[i]);
        }
        return parts;
    }

    /**
     * 获取不添加 "RequestInterceptor拦截器"的OkHttpClient 的网络请求对象
     *
     * @param host
     * @param tClass
     * @param <T>
     * @return
     */
    public <T> T getServiceWithNewCleint(String host, Class<T> tClass) {
        if (TextUtils.isEmpty(host)) {
            host = "http://localhost/";
        }
        OkHttpClient.Builder okBuilder = new OkHttpClient.Builder().addInterceptor(new ReceivedInterceptor());
        OkHttpClient client = OkUtils.get().getUploadOkClient(okBuilder);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(host)
                .client(client)
                .addConverterFactory(FastJsonConverterFactory.create())  // 添加 fastJson 解析器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 添加 RxJava 适配器
                .build();
        return retrofit.create(tClass);
    }

}
