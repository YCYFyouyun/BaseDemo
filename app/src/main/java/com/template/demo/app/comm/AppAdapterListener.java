package com.template.demo.app.comm;

import android.view.View;

import com.lwkandroid.rcvadapter.holder.RcvHolder;

public interface AppAdapterListener<T> {
    
    void onItemViewClicked(View view, RcvHolder holder, T t, int position);

}
