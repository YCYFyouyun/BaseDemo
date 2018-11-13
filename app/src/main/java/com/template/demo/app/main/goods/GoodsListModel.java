package com.template.demo.app.main.goods;


import com.template.demo.bean.comm.GoodsBean;
import com.template.demo.data.comm.CommRepository;

import io.reactivex.Observable;

public class GoodsListModel implements GoodsListConstract.IModel {

    @Override
    public Observable<GoodsBean> goodPictures() {
        return CommRepository.get().goodPictures();
    }

}
