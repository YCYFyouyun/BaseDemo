package com.template.demo.api;


import com.template.demo.bean.comm.GoodsBean;
import com.template.demo.bean.comm.LanguageBeanList;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * 通用API
 */
public interface CommonApi {

    /**
     * 获取语言列表
     *
     * @param params ver－客户端当前语言版本
     * @return
     */
    @GET("xxxx/common/languageList")
    Observable<LanguageBeanList> languages(@QueryMap HashMap<String, String> params);

    /**
     * 获取商品图片
     *
     * @return
     */
    @GET("xxxx/common/goods")
    Observable<GoodsBean> goodPictures();

    //......

}
