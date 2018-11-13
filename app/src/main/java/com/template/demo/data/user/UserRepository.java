package com.template.demo.data.user;


import com.template.demo.bean.user.TokenBean;
import com.template.demo.bean.user.UserBean;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * 用户仓库
 */
public class UserRepository implements UserImpl {

    private static UserRepository mInstance;

    public static UserRepository get() {
        if (mInstance == null) {
            synchronized (UserRepository.class) {
                if (mInstance == null) mInstance = new UserRepository();
            }
        }
        return mInstance;
    }

    private UserRepository() {
        mLocal = new UserRepositoryLocal();
        mRemote = new UserRepositoryRemote();
    }

    private UserRepositoryLocal mLocal; //本地数据仓库
    private UserRepositoryRemote mRemote; //远程数据仓库

    /**
     * 是否登录
     *
     * @return
     */
    public boolean isLogin() {
        return mLocal.isLogin();
    }

    /**
     * 从本地获取用户信息
     *
     * @return
     */
    public UserBean getUserBeanFromLocal() {
        return mLocal.getUserBean();
    }

    /**
     * 更新本地用户信息
     *
     * @param userBean
     */
    public void updateUserBeanToLocal(UserBean userBean) {
        if (userBean != null) mLocal.setUserBean(userBean);
    }

    /**
     * 清空本地用户信息
     */
    public void clearUserBean() {
        mLocal.logout();
    }

    @Override
    public Observable<String> sendSms(HashMap<String, String> params) {
        return mRemote.sendSms(params);
    }

    @Override
    public Observable<TokenBean> register(HashMap<String, String> params) {
        return mRemote.register(params).doOnNext(new Consumer<TokenBean>() {
            @Override
            public void accept(TokenBean bean) throws Exception {
                mLocal.saveToken(bean.getToken());
            }
        });
    }

    @Override
    public Observable<TokenBean> login(HashMap<String, String> params) {
        return mRemote.login(params).doOnNext(new Consumer<TokenBean>() {
            @Override
            public void accept(TokenBean bean) throws Exception {
                mLocal.saveToken(bean.getToken());
            }
        });
    }

    @Override
    public Observable<String> logout() {
        return mRemote.logout().doAfterTerminate(new Action() {
            @Override
            public void run() throws Exception {
                mLocal.logout();
            }
        });
    }

    //......
}
