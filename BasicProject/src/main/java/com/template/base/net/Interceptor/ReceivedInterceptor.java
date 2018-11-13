package com.template.base.net.Interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class ReceivedInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        Response originalResponse = chain.proceed(chain.request());
//        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
//            Observable.from(originalResponse.headers("Set-Cookie"))
//                    .map(s -> {
//                        String[] cookieArray = s.split(";"); // JSESSIONID=aaaQElHouiqmGh-oaQCtv; path=/
//                        return cookieArray[0];
//                    })
//                    .subscribe(cookie -> {
//                        RetrofitRequestTool.addHeader("Cookie", cookie);
//                    });
//        }
        return originalResponse;
    }
}
