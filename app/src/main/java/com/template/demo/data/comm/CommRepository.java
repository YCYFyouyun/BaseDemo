package com.template.demo.data.comm;


import com.template.demo.bean.comm.GoodsBean;
import com.template.demo.bean.comm.LanguageBeanList;
import com.template.demo.utils.core.LanguageUtils;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class CommRepository implements CommImpl {

    private static CommRepository mInstance;

    public static CommRepository get() {
        if (mInstance == null) {
            synchronized (CommRepository.class) {
                if (mInstance == null) mInstance = new CommRepository();
            }
        }
        return mInstance;
    }

    private CommRepository() {
        mLocal = new CommRepositoryLocal();
        mRemote = new CommRepositoryRemote();
    }

    private CommRepositoryLocal mLocal; //本地数据仓库
    private CommRepositoryRemote mRemote; //远程数据仓库

    /**
     * 从本地获取 商品列表
     *
     * @return
     */
    public GoodsBean getGoodPicturesFromLocal() {
        return mLocal.getGoodPictures();
    }
    
    @Override
    public Observable<LanguageBeanList> languages(HashMap<String, String> params) {
        return mRemote.languages(params).doOnNext(new Consumer<LanguageBeanList>() {
            @Override
            public void accept(LanguageBeanList list) throws Exception {
                if (list.getLanguages() != null) { //有数据
                    LanguageUtils.get().setLanguageData(list);
                }
            }
        });
    }

    @Override
    public Observable<GoodsBean> goodPictures() {
        return mRemote.goodPictures().doOnNext(new Consumer<GoodsBean>() {
            @Override
            public void accept(GoodsBean bean) throws Exception {
                mLocal.setGoodPictures(bean);
            }
        });
    }

    //......

}
