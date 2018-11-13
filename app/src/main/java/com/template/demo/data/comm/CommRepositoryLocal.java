package com.template.demo.data.comm;

import com.template.base.utils.SpUtils;
import com.template.demo.bean.comm.GoodsBean;

public class CommRepositoryLocal {

    private final static String GOOD_PICTURES = "GOOD_PICTURES";
    
    protected CommRepositoryLocal() {
    }

    public void setGoodPictures(GoodsBean bean) {
        SpUtils.putObject(GOOD_PICTURES, bean);
    }

    public GoodsBean getGoodPictures() {
        return SpUtils.getObject(GOOD_PICTURES, GoodsBean.class);
    }

    //......

}
