package com.template.base.net;

import com.template.base.net.Interceptor.SortMap;
import com.template.base.utils.EncryptUtils;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class SignUtils {

    /**
     * 计算签名
     *
     * @param map
     * @return
     */
    public static String getClientSign(Map<String, String> map) {
        // TODO 根据实际签名生成规则配置，这里只是简单举例
        StringBuilder params = new StringBuilder();
        Set<String> keySet = map.keySet();
        Iterator<String> iter = keySet.iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            String val = map.get(key);
            params.append(key);
            params.append("=");
            params.append(val);
            params.append("&");
        }
        params.append("xxxx=" + RetrofitRequestTool.getKey());
        return EncryptUtils.encryptMD5ToString(params.toString()).toLowerCase();
    }

    /**
     * 获取拼接sign的url
     *
     * @return
     */
    public static String getUrlWithSign(String strPage, Map<String, String> map) {
        // TODO 根据实际签名生成规则配置，这里只是简单举例
        // 计算sign
        SortMap sortMap = new SortMap();
        sortMap.putAll(map);
        String sign = getClientSign(sortMap);
        // 拼接参数
        StringBuilder params = new StringBuilder();
        params.append(strPage);
        params.append("?");
        Set<String> keySet = map.keySet();
        Iterator<String> iter = keySet.iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            String val = map.get(key);
            params.append(key);
            params.append("=");
            params.append(val);
            params.append("&");
        }
        //......
        params.append("xxxx");
        params.append("=");
        params.append(sign);
        return params.toString();
    }


}
