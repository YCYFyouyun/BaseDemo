package com.template.demo.app.main;


import com.template.demo.bean.comm.LanguageBeanList;
import com.template.demo.utils.core.LanguageUtils;
import com.template.rxextention.utils.RxUtil;

import io.reactivex.functions.Consumer;

public class MainPresenter extends MainConstract.Presenter {

    @Override
    protected MainConstract.IModel createModel() {
        return new MainModel();
    }

    @Override
    void reqLanguageList() {
        mModelImpl.languages(LanguageUtils.get().getVersion())
                .takeUntil(withRxLifecycle())
                .compose(RxUtil.<LanguageBeanList>applySchedulers())
                .subscribe(new Consumer<LanguageBeanList>() {
                    @Override
                    public void accept(LanguageBeanList list) throws Exception {
                        //无需处理
                    }
                }, withOnError());
    }

}
