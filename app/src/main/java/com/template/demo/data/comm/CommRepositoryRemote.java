package com.template.demo.data.comm;

import com.template.base.net.RetrofitUtil;
import com.template.demo.api.CommonApi;
import com.template.demo.bean.comm.GoodsBean;
import com.template.demo.bean.comm.LanguageBeanList;

import java.util.HashMap;

import io.reactivex.Observable;

public class CommRepositoryRemote implements CommImpl {

    CommonApi mCommonApi;

    protected CommRepositoryRemote() {
        mCommonApi = RetrofitUtil.get().getService(CommonApi.class);
    }

    @Override
    public Observable<LanguageBeanList> languages(HashMap<String, String> params) {
        return mCommonApi.languages(params);
    }

    @Override
    public Observable<GoodsBean> goodPictures() {
        return mCommonApi.goodPictures();
    }

    //......
}
