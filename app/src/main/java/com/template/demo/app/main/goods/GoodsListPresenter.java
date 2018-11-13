package com.template.demo.app.main.goods;

import com.template.demo.bean.comm.GoodsBean;
import com.template.demo.data.comm.CommRepository;
import com.template.rxextention.utils.RxUtil;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class GoodsListPresenter extends GoodsListConstract.Presenter {

    @Override
    protected GoodsListConstract.IModel createModel() {
        return new GoodsListModel();
    }

    @Override
    void reqGoodsListFromLocal() {
        GoodsBean bean = CommRepository.get().getGoodPicturesFromLocal();
        if (bean != null) mViewImpl.showGoodsList(bean.getGoodPictures());
    }

    @Override
    void reqGoodsList() {
        mModelImpl.goodPictures()
                .takeUntil(withRxLifecycle())
                .compose(RxUtil.<GoodsBean>applySchedulers())
                .doAfterTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (mViewImpl != null) mViewImpl.refreshComplete();
                    }
                })
                .subscribe(new Consumer<GoodsBean>() {
                    @Override
                    public void accept(GoodsBean bean) throws Exception {
                        mViewImpl.showGoodsList(bean.getGoodPictures());
                    }
                }, withOnError());
    }

}
