package com.template.demo.app.comm;

import android.os.StrictMode;
import android.support.multidex.MultiDex;

import com.template.base.app.BaseApplication;
import com.template.base.net.Interceptor.ReceivedInterceptor;
import com.template.base.net.Interceptor.RequestInterceptor;
import com.template.base.net.OkUtils;
import com.template.base.net.RetrofitRequestTool;
import com.template.base.net.RetrofitUtil;
import com.template.base.utils.ToastImpl;
import com.template.base.utils.ToastUtils;
import com.template.demo.R;
import com.template.demo.api.ApiConstants;
import com.template.demo.constant.AppConfig;
import com.template.demo.utils.core.LanguageUtils;
import com.template.rxextention.utils.RxAction;

import java.lang.reflect.Field;

import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;


public class AppApplication extends BaseApplication {
    private static AppApplication instance;

    public static AppApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        MultiDex.install(this);
        initAppConfig();
        super.onCreate();
        instance = this;
        initToast();
        RetrofitRequestTool.setKey(AppConfig.SIGN_KEY); //设置key
        RxAction.setRxAction(new AppRxAction());
        initFileProvider();
        //.......
    }

    @Override
    protected void initNetUtils() {
        OkUtils.get().getOkBuilder().addInterceptor(new RequestInterceptor());
        OkUtils.get().getOkBuilder().addInterceptor(new ReceivedInterceptor());
        super.initNetUtils();
        RetrofitUtil.get().init(ApiConstants.URL.HOST, OkUtils.get().getOkClient()
                , RxJava2CallAdapterFactory.create());
    }

    /**
     * 获取build.gradle里映射的参数
     */
    private void initAppConfig() {
        try {
            String packageName = getPackageName();
            //根据BuildConfig路径获取实例对象
            Class buildConfig = Class.forName(packageName + ".BuildConfig");
            AppConfig.BUGLY_APP_ID = getConfigField(buildConfig, "BUGLY_APP_ID");
            AppConfig.SIGN_KEY = getConfigField(buildConfig, "SIGN_KEY");
            AppConfig.WX_APP_ID = getConfigField(buildConfig, "WX_APP_ID");
            AppConfig.WX_APP_SECRET = getConfigField(buildConfig, "WX_APP_SECRET");
            AppConfig.SINA_APP_KEY = getConfigField(buildConfig, "SINA_APP_KEY");
            AppConfig.SINA_APP_SECRET = getConfigField(buildConfig, "SINA_APP_SECRET");
            AppConfig.SINA_REDIRECT_URL = getConfigField(buildConfig, "SINA_REDIRECT_URL");
            AppConfig.SINA_SCOPE = getConfigField(buildConfig, "SINA_SCOPE");
            AppConfig.QQ_APP_ID = getConfigField(buildConfig, "QQ_APP_ID");
            AppConfig.QQ_APP_KEY = getConfigField(buildConfig, "QQ_APP_KEY");
            AppConfig.XIAOMI_APP_ID = getConfigField(buildConfig, "XIAOMI_APP_ID");
            AppConfig.XIAOMI_APP_KEY = getConfigField(buildConfig, "XIAOMI_APP_KEY");
            ApiConstants.URL.HOST = getConfigField(buildConfig, "HOST_URL");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    private <T> T getConfigField(Class buildConfig, String field)
            throws NoSuchFieldException, IllegalAccessException {
        Field field1 = buildConfig.getField(field);
        boolean access = field1.isAccessible();
        if (!access)
            field1.setAccessible(true);
        T value = (T) field1.get(buildConfig);
        if (!access)
            field1.setAccessible(false);
        return value;
    }

    /**
     * In this way the VM ignores the file URI exposure
     */
    private void initFileProvider() {
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
    }

    private void initToast() {
        ToastUtils.setToastImpl(new ToastImpl() {
            @Override
            public String getNetError() {
                return LanguageUtils.get().getString("net_error", R.string.net_error);
            }
        });
    }

}
