package com.template.demo.app.main;

import com.template.demo.bean.comm.LanguageBeanList;
import com.template.rxextention.mvp.IRxBaseView;
import com.template.rxextention.mvp.RxBasePresenter;

import io.reactivex.Observable;

public interface MainConstract {

    interface IView extends IRxBaseView {

        void setFrgContentDisplay();

        void showGoodsFragment(); //显示商品列表

        void setIconDisplay(boolean highLight); //设置"右上角图标"的显示

        void setTitleContent(String content); //设置标题的显示

        //......
    }

    interface IModel {

        Observable<LanguageBeanList> languages(String ver);

        //......
    }

    abstract class Presenter extends RxBasePresenter<IView, IModel> {

        abstract void reqLanguageList(); //刷新语言数据

        //......
    }

}
