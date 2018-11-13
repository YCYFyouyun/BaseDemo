package com.template.rxextention.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.template.base.mvp.MvpBaseActivity;
import com.template.rxextention.constant.RxLifecycle;

import io.reactivex.subjects.PublishSubject;

public abstract class RxMvpBaseActivity<P extends RxBasePresenter> extends MvpBaseActivity<P> {
    protected PublishSubject<Integer> mLifecycleSubject = PublishSubject.create();

    public PublishSubject<Integer> getLifecycleSubject() {
        return mLifecycleSubject;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLifecycleSubject.onNext(RxLifecycle.ON_CREATE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mLifecycleSubject.onNext(RxLifecycle.ON_START);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mLifecycleSubject.onNext(RxLifecycle.ON_RESUME);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mLifecycleSubject.onNext(RxLifecycle.ON_STOP);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLifecycleSubject.onNext(RxLifecycle.ON_DESTROY);
    }
}
