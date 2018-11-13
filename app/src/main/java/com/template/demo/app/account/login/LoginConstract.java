package com.template.demo.app.account.login;

import com.template.demo.bean.user.TokenBean;
import com.template.rxextention.mvp.IRxBaseView;
import com.template.rxextention.mvp.RxBasePresenter;

import io.reactivex.Observable;

public interface LoginConstract {

    interface IView extends IRxBaseView {

        void setViewDisplay();

        void setICodeDisplay(String icode); //设置国际区号的显示

        void loginSuccess(); //登录成功

        //......

    }

    interface IModel {

        /**
         * 调用登录接口
         *
         * @param icode 国际区号
         * @param phone 手机号
         * @param pwd   密码
         * @return
         */
        Observable<TokenBean> login(String icode, String phone, String pwd);

        //......

    }

    abstract class Presenter extends RxBasePresenter<IView, IModel> {

        abstract void login(String icode, String phone, String pwd);

        //......

    }

}
