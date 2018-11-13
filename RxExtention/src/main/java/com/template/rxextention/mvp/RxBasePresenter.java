package com.template.rxextention.mvp;

import com.template.base.mvp.BasePresenter;
import com.template.base.net.RequestError;
import com.template.base.utils.ToastUtils;
import com.template.rxextention.constant.RxLifecycle;
import com.template.rxextention.utils.RxAction;

import java.net.ConnectException;
import java.net.UnknownHostException;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
 * RX MVP模版中Presenter基类
 */

public abstract class RxBasePresenter<V extends IRxBaseView, M> extends BasePresenter<V, M> {
    protected final static long DELAY = 400;
    protected CompositeDisposable mCompositeDisposable;

    @Override
    public void attachToView(V v) {
        super.attachToView(v);
        mCompositeDisposable = new CompositeDisposable();
    }

    //统一管理所有Composites
    protected void addComposites(Disposable... disposable) {
        mCompositeDisposable.addAll(disposable);
    }

    /**
     * 提供生命周期自动解绑，用于配合takeUntil操作符
     */
    protected Observable<Integer> withRxLifecycle() {
        return withRxLifecycle(RxLifecycle.ON_DESTROY);
    }

    /**
     * 提供生命周期自动解绑，用于配合takeUntil操作符
     *
     * @param target 某个生命周期事件(RxLifecycle)
     */
    protected Observable<Integer> withRxLifecycle(@RxLifecycle.Event final int target) {
        return mViewImpl.getLifecycleSubject().filter(new Predicate<Integer>() {
            @Override
            public boolean test(@NonNull Integer integer) throws Exception {
                return integer == target;
            }
        }).take(1);
    }

    @Override
    public void onDestory() {
        if (mCompositeDisposable != null && !mCompositeDisposable.isDisposed())
            mCompositeDisposable.dispose();
        mCompositeDisposable = null;
        super.onDestory();
    }

    /**
     * 配合subscribe操作符使用
     */
    protected Consumer<Throwable> withOnError() {
        return new Consumer<Throwable>() {
            @Override
            public void accept(@NonNull Throwable throwable) throws Exception {
                if (throwable instanceof ConnectException
                        || throwable instanceof UnknownHostException) {
                    //if (mViewImpl != null) mViewImpl.showShortToast("网络不给力");
                    ToastUtils.showNetError();
                    return;
                }

                // 判断token是否失效
                if (throwable instanceof RequestError) {
                    RequestError error = (RequestError) throwable;
                    if (error.getReturnCode() == RxAction.getTokenInvalid()
                            && mViewImpl.getActivityContext() != null) {
                        RxAction.get().tokenInvaildAction(mViewImpl.getActivityContext(), error);
                        return;
                    }
                }
                if (mViewImpl != null) mViewImpl.showShortToast(throwable.getMessage());
            }
        };
    }

    /**
     * 配合doAfterTerminate操作符使用
     */
    protected Action withDoAfterTerminate() {
        return new Action() {
            @Override
            public void run() throws Exception {
                if (mViewImpl != null) mViewImpl.hideLoading();
            }
        };
    }

    /**
     * 配合doOnSubscribe操作符使用
     */
    protected Consumer withDoOnSubscribe(final boolean isCancelable, final long delay) {
        return new Consumer() {
            @Override
            public void accept(@NonNull Object o) throws Exception {
                if (mViewImpl != null) mViewImpl.showLoading(isCancelable, delay);
            }
        };
    }

}
