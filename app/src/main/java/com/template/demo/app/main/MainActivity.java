package com.template.demo.app.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.template.demo.R;
import com.template.demo.app.comm.AppBaseActivity;
import com.template.demo.app.main.goods.GoodsListFragment;
import com.template.demo.app.shop.ShopActivity;
import com.template.demo.utils.LoginUtils;
import com.wuhenzhizao.titlebar.widget.CommonTitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppBaseActivity<MainConstract.Presenter>
        implements MainConstract.IView {

    @BindView(R.id.title)
    CommonTitleBar mTitle;
    private Fragment mCurrent;
    private GoodsListFragment mGoodsListFragment;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initUI(View contentView) {
        ButterKnife.bind(this);
        mTitle.setListener(new CommonTitleBar.OnTitleBarListener() {
            @Override
            public void onClicked(View v, int action, String extra) {
                if (isBtnBuffering()) return;
                switch (action) {
                    case CommonTitleBar.ACTION_LEFT_BUTTON:
                        ShopActivity.actionStart(MainActivity.this);
                        break;
                    case CommonTitleBar.ACTION_RIGHT_BUTTON:
                        if (LoginUtils.isLoginWithSkip(MainActivity.this)) {
                            //......
                        }
                        break;
                }

            }
        });
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
        mPresenter.reqLanguageList();
        //......
    }

    @Override
    protected void onClick(int id, View v) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        setIconDisplay(LoginUtils.isLogin());
        setFrgContentDisplay();
    }

    @Override
    public void setFrgContentDisplay() {
        //......根据实际情况显示不同的Fragment
        showGoodsFragment();
    }

    @Override
    public void showGoodsFragment() {
        //setIconDisplay(false);
        //setTitleContent(getString(R.string.app_name));
        if (mCurrent != null && mCurrent instanceof GoodsListFragment) {
            return;
        }
        if (mGoodsListFragment == null) {
            mGoodsListFragment = GoodsListFragment.newInstance();
        }
        mCurrent = mGoodsListFragment;
        replaceFragment(R.id.frg_content, mCurrent);
    }

    @Override
    public void setIconDisplay(boolean highLight) {
        mTitle.getRightImageButton().setSelected(highLight);
    }

    @Override
    public void setTitleContent(String content) {
        mTitle.getCenterTextView().setText(content);
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        if (fragment == null) return;
        if (fragment instanceof GoodsListFragment) {
            mGoodsListFragment = (GoodsListFragment) fragment;
        }
    }

    //......
}
