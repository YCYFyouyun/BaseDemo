package com.template.rxextention.mvp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.template.base.mvp.MvpBaseFragment;
import com.template.rxextention.constant.RxLifecycle;

import io.reactivex.subjects.PublishSubject;

public abstract class RxMvpBaseFragment<P extends RxBasePresenter> extends MvpBaseFragment<P> {
    protected PublishSubject<Integer> mLifecycleSubject = PublishSubject.create();

    public PublishSubject<Integer> getLifecycleSubject() {
        return mLifecycleSubject;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mLifecycleSubject.onNext(RxLifecycle.ON_ATTACH);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLifecycleSubject.onNext(RxLifecycle.ON_CREATE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mLifecycleSubject.onNext(RxLifecycle.ON_CREATE_VIEW);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mLifecycleSubject.onNext(RxLifecycle.ON_ACTIVITY_CREATED);
    }

    @Override
    public void onStart() {
        super.onStart();
        mLifecycleSubject.onNext(RxLifecycle.ON_START);
    }

    @Override
    public void onResume() {
        super.onResume();
        mLifecycleSubject.onNext(RxLifecycle.ON_RESUME);
    }

    @Override
    public void onPause() {
        super.onPause();
        mLifecycleSubject.onNext(RxLifecycle.ON_PAUSE);
    }

    @Override
    public void onStop() {
        super.onStop();
        mLifecycleSubject.onNext(RxLifecycle.ON_STOP);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mLifecycleSubject.onNext(RxLifecycle.ON_DESTROY_VIEW);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mLifecycleSubject.onNext(RxLifecycle.ON_DESTROY);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mLifecycleSubject.onNext(RxLifecycle.ON_DETACH);
    }
}
