package com.template.demo.app.account.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.template.base.utils.ToastUtils;
import com.template.base.utils.TypeUtil;
import com.template.demo.R;
import com.template.demo.app.main.MainActivity;
import com.template.demo.bean.comm.ICodeBean;
import com.template.demo.constant.AppValue;
import com.template.demo.event.ICodeEvent;
import com.template.demo.utils.core.LanguageUtils;
import com.template.rxextention.mvp.RxMvpBaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class LoginFragment extends RxMvpBaseFragment<LoginConstract.Presenter>
        implements LoginConstract.IView {

    @BindView(R.id.tv_icode)
    TextView mTvIcode;
    @BindView(R.id.edit_mobile)
    EditText mEditMobile;
    @BindView(R.id.edit_pwd)
    EditText mEditPwd;
    @BindView(R.id.tv_forget)
    TextView mTvForget;
    @BindView(R.id.btn_login)
    Button mBtnLogin;
    Unbinder unbinder;
    String mICode;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initUI(View contentView) {
        unbinder = ButterKnife.bind(this, contentView);
        setICodeDisplay(AppValue.ICode.Def);
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
        RxBus.get().register(this);
    }

    @Override
    protected void onClick(int id, View v) {

    }

    @Override
    public void onResume() {
        super.onResume();
        setViewDisplay();
    }

    @Override
    public void onDestroyView() {
        RxBus.get().unregister(this);
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tv_icode, R.id.tv_forget, R.id.btn_login})
    public void onViewClicked(View view) {
        if (isBtnBuffering()) return;

        switch (view.getId()) {
            case R.id.tv_icode:
                //......选择区号
                ToastUtils.showShort(LanguageUtils.get()
                        .getString("choose_zipCode_title", R.string.choose_zipCode_title));
                break;
            case R.id.tv_forget:
                //......忘记密码
                ToastUtils.showShort(LanguageUtils.get()
                        .getString("forget_pwd", R.string.forget_pwd));
                break;
            case R.id.btn_login:
                mPresenter.login(mICode,
                        mEditMobile.getEditableText().toString().trim(),
                        mEditPwd.getEditableText().toString().trim());
                break;
        }
    }

    /**
     * 监听消息
     *
     * @param event
     */
    @Subscribe
    public void onEvent(ICodeEvent event) {
        if (event.obj1 == null) return;
        switch (event.type) {
            case ICodeEvent.LOGIN:
                ICodeBean bean = (ICodeBean) event.obj1;
                setICodeDisplay(TypeUtil.getValue(bean.getCode()));
                break;
        }
    }

    @Override
    public void setViewDisplay() {
        mEditMobile.setHint(LanguageUtils.get().getString("phone", R.string.phone));
        mEditPwd.setHint(LanguageUtils.get().getString("pwd_length", R.string.pwd_length));
        mTvForget.setText(LanguageUtils.get().getString("forget_pwd", R.string.forget_pwd));
        mBtnLogin.setText(LanguageUtils.get().getString("login", R.string.login));
    }

    @Override
    public void setICodeDisplay(String icode) {
        mICode = icode;
        mTvIcode.setText(AppValue.ICode.Pre + icode);
    }

    @Override
    public void loginSuccess() {
        ToastUtils.showShort(LanguageUtils.get().getString("success_login", R.string.success_login));
        MainActivity.actionStart(getContext());
        getActivity().finish();
    }

    //......
}
