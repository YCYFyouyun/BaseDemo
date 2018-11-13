package com.template.demo.app.account.register;

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

import java.text.MessageFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class RegisterFragment extends RxMvpBaseFragment<RegisterPresenter>
        implements RegisterConstract.IView {

    @BindView(R.id.tv_icode)
    TextView mTvIcode;
    @BindView(R.id.edit_mobile)
    EditText mEditMobile;
    @BindView(R.id.edit_code)
    EditText mEditCode;
    @BindView(R.id.btn_get)
    Button mBtnGet;
    @BindView(R.id.edit_pwd)
    EditText mEditPwd;
    @BindView(R.id.btn_register)
    Button mBtnRegister;
    Unbinder unbinder;
    String mICode;

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Override
    protected RegisterPresenter createPresenter() {
        return new RegisterPresenter();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_register;
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

    @OnClick({R.id.tv_icode, R.id.btn_get, R.id.btn_register})
    public void onViewClicked(View view) {
        if (isBtnBuffering()) return;

        switch (view.getId()) {
            case R.id.tv_icode:
                //......选择区号
                ToastUtils.showShort(LanguageUtils.get()
                        .getString("choose_zipCode_title", R.string.choose_zipCode_title));
                break;
            case R.id.btn_get:
                mPresenter.sendSms(mICode,
                        mEditMobile.getEditableText().toString().trim());
                break;
            case R.id.btn_register:
                mPresenter.register(mICode,
                        mEditMobile.getEditableText().toString().trim(),
                        mEditCode.getEditableText().toString().trim(),
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
            case ICodeEvent.REGISTER:
                ICodeBean bean = (ICodeBean) event.obj1;
                setICodeDisplay(TypeUtil.getValue(bean.getCode()));
                break;
        }
    }

    @Override
    public void setViewDisplay() {
        mEditMobile.setHint(LanguageUtils.get().getString("phone", R.string.phone));
        mEditCode.setHint(LanguageUtils.get().getString("authCode", R.string.authCode));
        mEditPwd.setHint(LanguageUtils.get().getString("pwd_length", R.string.pwd_length));
        mBtnRegister.setText(LanguageUtils.get().getString("register", R.string.register));
        if (mBtnGet.isEnabled()) { //避免对缓冲时间显示的影响
            mBtnGet.setText(LanguageUtils.get().getString("get_authCode", R.string.get_authCode));
        }
    }

    @Override
    public void setICodeDisplay(String icode) {
        mICode = icode;
        mTvIcode.setText(AppValue.ICode.Pre + icode);
    }

    @Override
    public void showCoolingTime(int remainTime) {
        if (remainTime > 0) {
            mBtnGet.setText(MessageFormat.format(
                    LanguageUtils.get().getString("resend_code", R.string.resend_code),
                    new Object[]{remainTime}));
            mBtnGet.setEnabled(false);
        } else {
            mBtnGet.setText(LanguageUtils.get().getString("get_authCode", R.string.get_authCode));
            mBtnGet.setEnabled(true);
        }
    }

    @Override
    public void registerSuccess() {
        MainActivity.actionStart(getContext());
        getActivity().finish();
    }

}
