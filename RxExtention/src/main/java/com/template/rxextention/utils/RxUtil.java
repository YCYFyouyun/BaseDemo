package com.template.rxextention.utils;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

/**
 * RxJava 工具类
 */
public final class RxUtil {

    private static ObservableTransformer<Object, Object> sApplyTransformer = new ObservableTransformer<Object, Object>() {
        @Override
        public ObservableSource<Object> apply(@NonNull Observable<Object> upstream) {
            return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        }
    };

    private static ObservableTransformer<Object, Object> sSaveTransformer = new ObservableTransformer<Object, Object>() {
        @Override
        public ObservableSource<Object> apply(@NonNull Observable<Object> upstream) {
            return upstream.observeOn(Schedulers.io());
        }
    };

    /**
     * 在 IO 线程执行数据操作，UI线程执行订阅操作
     * <p>
     * 效果等同于：
     * <p>
     * Observable.just().subscribeOn(Schedules.io()).observableOn(AndroidSchedulers.mainThread()).subscribe();
     * <p>
     * 使用方式：
     * <p>
     * Observable.just().compose(RxUtils.applySchedulers()).subscribe();
     *
     * @return
     */
    public static <T> ObservableTransformer<T, T> applySchedulers() {
        return (ObservableTransformer<T, T>) sApplyTransformer;
    }

    /**
     * 在 IO 线程执行订阅操作
     * <p>
     * 效果等同于：
     * <p>
     * Observable.just().observableOn(Schedules.io()).subscribe();
     * <p>
     * 使用方式：
     * <p>
     * Observable.just().compose(RxUtils.saveSchedulers()).subscribe();
     */
    public static <T> ObservableTransformer<T, T> saveSchedulers() {
        return (ObservableTransformer<T, T>) sSaveTransformer;
    }

    /**
     * @return Schedulers.io();
     */
    public static Scheduler ioThread() {
        return Schedulers.io();
    }

    /**
     * @return AndroidSchedulers.mainThread();
     */
    public static Scheduler mainThread() {
        return AndroidSchedulers.mainThread();
    }

}
