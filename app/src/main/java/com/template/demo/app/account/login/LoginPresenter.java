package com.template.demo.app.account.login;

import android.text.TextUtils;

import com.template.base.utils.ToastUtils;
import com.template.demo.R;
import com.template.demo.bean.user.TokenBean;
import com.template.demo.constant.AppValue;
import com.template.demo.utils.core.LanguageUtils;
import com.template.rxextention.utils.RxUtil;

import io.reactivex.functions.Consumer;


public class LoginPresenter extends LoginConstract.Presenter {

    @Override
    protected LoginConstract.IModel createModel() {
        return new LoginModel();
    }

    @Override
    void login(String icode, String phone, String pwd) {
        if (TextUtils.isEmpty(phone)) {
            ToastUtils.showShort(LanguageUtils.get().getString("hint_plz_input_phone", R.string.hint_plz_input_phone));
            return;
        }
        if (TextUtils.isEmpty(pwd) || pwd.length() < AppValue.PWD_LENGTH_MIN) {
            ToastUtils.showShort(LanguageUtils.get().getString("hint_plz_input_pwd", R.string.hint_plz_input_pwd));
            return;
        }

        mModelImpl.login(icode, phone, pwd)
                .takeUntil(withRxLifecycle())
                .compose(RxUtil.<TokenBean>applySchedulers())
                .doOnSubscribe(withDoOnSubscribe(false, DELAY))
                .doAfterTerminate(withDoAfterTerminate())
                .subscribe(new Consumer<TokenBean>() {
                    @Override
                    public void accept(TokenBean bean) throws Exception {
                        mViewImpl.loginSuccess();
                    }
                }, withOnError());
    }

    //......

}
