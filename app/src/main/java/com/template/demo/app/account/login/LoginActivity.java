package com.template.demo.app.account.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.template.base.mvp.BasePresenter;
import com.template.base.utils.ToastUtils;
import com.template.demo.R;
import com.template.demo.app.comm.AppBaseActivity;
import com.template.demo.utils.core.LanguageUtils;
import com.template.demo.widget.CustomViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppBaseActivity {

    @BindView(R.id.img_close)
    ImageView mImgClose;
    @BindView(R.id.tv_language)
    TextView mTvLanguage;
    @BindView(R.id.tv_login)
    TextView mTvLogin;
    @BindView(R.id.tv_register)
    TextView mTvRegister;
    @BindView(R.id.view_pager)
    CustomViewPager mViewPager;
    LoginPagerAdapter mPagerAdapter;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void beforeOnCreate(Bundle savedInstanceState) {
        super.beforeOnCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initUI(View contentView) {
        ButterKnife.bind(this);
        mPagerAdapter = new LoginPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);
        setArrowDisplay(true);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setArrowDisplay(position == 0);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        setViewDisplay();
        mTvLanguage.setText(LanguageUtils.get().getLanguageName());
    }

    @Override
    protected void onClick(int id, View v) {

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @OnClick({R.id.img_close, R.id.tv_language, R.id.tv_login, R.id.tv_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_close:
                finish();
                break;
            case R.id.tv_language:
                //......选择语言
                ToastUtils.showShort(LanguageUtils.get()
                        .getString("select_language", R.string.select_language));
                break;
            case R.id.tv_login:
                mViewPager.setCurrentItem(0, false);
                break;
            case R.id.tv_register:
                mViewPager.setCurrentItem(1, false);
                break;
        }
    }

    private void setViewDisplay() {
        mTvLogin.setText(LanguageUtils.get().getString("login", R.string.login));
        mTvRegister.setText(LanguageUtils.get().getString("register", R.string.register));
    }

    /**
     * 设置"登录"及"注册"底部的显示
     */
    private void setArrowDisplay(boolean isLoginFragment) {
        mTvLogin.setSelected(isLoginFragment);
        mTvRegister.setSelected(!isLoginFragment);
    }

    //......

}
