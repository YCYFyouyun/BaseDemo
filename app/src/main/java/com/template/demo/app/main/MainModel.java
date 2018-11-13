package com.template.demo.app.main;


import com.template.base.net.RequestParams;
import com.template.demo.bean.comm.LanguageBeanList;
import com.template.demo.data.comm.CommRepository;

import io.reactivex.Observable;

public class MainModel implements MainConstract.IModel {

    @Override
    public Observable<LanguageBeanList> languages(String ver) {
        return CommRepository.get().languages(
                new RequestParams.Builder()
                        .putParam("ver", ver)
                        .build()
        );
    }

}
