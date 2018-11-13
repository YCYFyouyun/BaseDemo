package com.template.demo.app.main.goods;

import com.template.demo.bean.comm.GoodsBean;
import com.template.rxextention.mvp.IRxBaseView;
import com.template.rxextention.mvp.RxBasePresenter;

import java.util.List;

import io.reactivex.Observable;

public interface GoodsListConstract {

    interface IView extends IRxBaseView {

        void refreshComplete();

        void showGoodsList(List<String> list);

    }

    interface IModel {

        Observable<GoodsBean> goodPictures();

    }

    abstract class Presenter extends RxBasePresenter<IView, IModel> {

        abstract void reqGoodsListFromLocal();

        abstract void reqGoodsList();

    }

}
