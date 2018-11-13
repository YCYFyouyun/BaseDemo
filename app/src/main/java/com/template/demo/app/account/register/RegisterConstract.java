package com.template.demo.app.account.register;

import com.template.demo.bean.user.TokenBean;
import com.template.rxextention.mvp.IRxBaseView;
import com.template.rxextention.mvp.RxBasePresenter;

import io.reactivex.Observable;

public interface RegisterConstract {

    interface IView extends IRxBaseView {

        void setViewDisplay();

        void setICodeDisplay(String icode); //设置国际区号的显示

        void showCoolingTime(int remainTime); //设置缓冲时间的显示

        void registerSuccess(); //注册成功

    }

    interface IModel {

        /**
         * 发送验证码
         *
         * @param icode 国际区号
         * @param phone 手机号
         * @return
         */
        Observable<String> sendSms(String icode, String phone);

        /**
         * 调用注册接口
         *
         * @param icode 国际区号
         * @param phone 手机号
         * @param code  验证码
         * @param pwd   密码
         * @return
         */
        Observable<TokenBean> register(String icode, String phone,
                                       String code, String pwd);
    }

    abstract class Presenter extends RxBasePresenter<IView, IModel> {

        abstract void sendSms(String icode, String phone);

        abstract void register(String icode, String phone,
                               String code, String pwd);

    }

}
