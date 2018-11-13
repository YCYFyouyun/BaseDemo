package com.template.demo.app.account.register;

import android.text.TextUtils;

import com.template.base.utils.ToastUtils;
import com.template.demo.R;
import com.template.demo.bean.user.TokenBean;
import com.template.demo.constant.AppValue;
import com.template.demo.utils.core.LanguageUtils;
import com.template.rxextention.utils.RxUtil;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class RegisterPresenter extends RegisterConstract.Presenter {

    @Override
    protected RegisterConstract.IModel createModel() {
        return new RegisterModel();
    }

    @Override
    void sendSms(String icode, String phone) {
        if (TextUtils.isEmpty(phone)) {
            ToastUtils.showShort(LanguageUtils.get().getString("hint_plz_input_phone", R.string.hint_plz_input_phone));
            return;
        }

        mModelImpl.sendSms(icode, phone)
                .compose(RxUtil.<String>applySchedulers())
                .doOnSubscribe(withDoOnSubscribe(true, DELAY))
                .doAfterTerminate(withDoAfterTerminate())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        ToastUtils.showShort(LanguageUtils.get().getString("success_authCode", R.string.success_authCode));
                        startCoolingTime(AppValue.COOLING_TIME_COUNT);
                    }
                }, withOnError());

    }

    private void startCoolingTime(final int sec) {
        Observable.interval(0, 1, TimeUnit.SECONDS)
                .take(sec + 1)
                .map(new Function<Long, Object>() {
                    @Override
                    public Object apply(@NonNull Long a) throws Exception {
                        return (sec - a);
                    }
                })
                .takeUntil(withRxLifecycle())
                .compose(RxUtil.applySchedulers())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object aLong) throws Exception {
                        mViewImpl.showCoolingTime((int) ((long) aLong));
                    }
                }, withOnError());
    }

    @Override
    void register(String icode, String phone, String code, String pwd) {
        if (TextUtils.isEmpty(phone)) {
            ToastUtils.showShort(LanguageUtils.get().getString("hint_plz_input_phone", R.string.hint_plz_input_phone));
            return;
        }
        if (TextUtils.isEmpty(code) || code.length() != AppValue.CODE_LENGTH) {
            ToastUtils.showShort(LanguageUtils.get().getString("hint_plz_input_authCode", R.string.hint_plz_input_authCode));
            return;
        }
        if (TextUtils.isEmpty(pwd) || pwd.length() < AppValue.PWD_LENGTH_MIN) {
            ToastUtils.showShort(LanguageUtils.get().getString("hint_plz_input_pwd", R.string.hint_plz_input_pwd));
            return;
        }

        mModelImpl.register(icode, phone, code, pwd)
                .compose(RxUtil.<TokenBean>applySchedulers())
                .doOnSubscribe(withDoOnSubscribe(false, DELAY))
                .doAfterTerminate(withDoAfterTerminate())
                .subscribe(new Consumer<TokenBean>() {
                    @Override
                    public void accept(TokenBean bean) throws Exception {
                        ToastUtils.showShort(LanguageUtils.get().getString("success_register", R.string.success_register));
                        mViewImpl.registerSuccess();
                    }
                }, withOnError());
    }

}
