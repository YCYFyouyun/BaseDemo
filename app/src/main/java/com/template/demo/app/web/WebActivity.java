package com.template.demo.app.web;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.template.base.mvp.BasePresenter;
import com.template.base.utils.TypeUtil;
import com.template.demo.R;
import com.template.demo.app.comm.AppWebBaseActivity;
import com.template.demo.constant.IConstants;
import com.wuhenzhizao.titlebar.widget.CommonTitleBar;

public class WebActivity extends AppWebBaseActivity {

    public static void actionStartWithHtml(Context context, String webTitle, String webHtml) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra(IConstants.WEB_TITLE, TypeUtil.getValue(webTitle));
        intent.putExtra(IConstants.WEB_HTML, TypeUtil.getValue(webHtml));
        context.startActivity(intent);
    }

    public static void actionStart(Context context, String webTitle, String webUrl) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra(IConstants.WEB_TITLE, TypeUtil.getValue(webTitle));
        intent.putExtra(IConstants.WEB_URL, TypeUtil.getValue(webUrl));
        context.startActivity(intent);
    }

    CommonTitleBar mTitle;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_web;
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
        String webTitle = getIntent().getStringExtra(IConstants.WEB_TITLE);
        String webUrl = getIntent().getStringExtra(IConstants.WEB_URL);
        String webHtml = getIntent().getStringExtra(IConstants.WEB_HTML);
        mTitle.getCenterTextView().setText(webTitle);
        if (!TextUtils.isEmpty(webUrl)) {
            mWebView.loadUrl(webUrl);
        } else {
            if (!TextUtils.isEmpty(webHtml)) mWebView.loadHtml(webHtml);
        }
    }

    @Override
    protected void onClick(int id, View v) {

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
    }

}
