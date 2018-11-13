package com.template.demo.app.shop;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.template.base.mvp.BasePresenter;
import com.template.demo.R;
import com.template.demo.api.ApiConstants;
import com.template.demo.app.comm.AppWebBaseActivity;
import com.template.demo.utils.core.LanguageUtils;
import com.wuhenzhizao.titlebar.widget.CommonTitleBar;

public class ShopActivity extends AppWebBaseActivity {

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, ShopActivity.class);
        context.startActivity(intent);
    }

    CommonTitleBar mTitle;

    @Override
    public int getContentViewId() {
        return R.layout.activity_shop;
    }

    @Override
    public void _initUI(View contentView) {
        mTitle = contentView.findViewById(R.id.title);
        mTitle.getLeftImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mTitle.getCenterTextView().setText(LanguageUtils.get()
                .getString("shop_title", R.string.shop_title));
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
        mWebView.loadUrl(ApiConstants.URL.Shop);
    }

    @Override
    protected void onClick(int id, View v) {

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

}
