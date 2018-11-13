package com.template.demo.data.comm;

import com.template.demo.bean.comm.GoodsBean;
import com.template.demo.bean.comm.LanguageBeanList;

import java.util.HashMap;

import io.reactivex.Observable;

public interface CommImpl {

    Observable<LanguageBeanList> languages(HashMap<String, String> params);

    Observable<GoodsBean> goodPictures();

    //......

}
