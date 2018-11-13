package com.template.base.net.converter;

import com.alibaba.fastjson.JSON;
import com.template.base.net.RequestBean;
import com.template.base.net.RequestError;
import com.template.base.utils.TypeUtil;
import com.socks.library.KLog;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;


public class FastJsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private final Type type;

    FastJsonResponseBodyConverter(Type type) {
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String string = value.string();
        KLog.json(string);
        
        RequestBean bean = JSON.parseObject(string, RequestBean.class);
        if (bean.getReturnCode() == 0) {
            if (type == String.class)
                return bean.getData() == null ? (T) TypeUtil.getValue(bean.getMsg()) : (T) bean.getData();
            //return (T) bean.getResult();
            return JSON.parseObject(bean.getData() != null ? bean.getData() : "{}", type);
        }
        throw new RequestError(bean);
    }
}
