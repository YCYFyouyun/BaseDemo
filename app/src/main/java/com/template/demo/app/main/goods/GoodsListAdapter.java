package com.template.demo.app.main.goods;

import android.content.Context;
import android.widget.ImageView;

import com.lwkandroid.rcvadapter.RcvSingleAdapter;
import com.lwkandroid.rcvadapter.holder.RcvHolder;
import com.template.base.image.glide.GlideApp;
import com.template.demo.R;

import java.util.ArrayList;

public class GoodsListAdapter extends RcvSingleAdapter<String> {

    public GoodsListAdapter(Context context) {
        super(context, R.layout.adapter_goods_list, new ArrayList<String>());
    }

    @Override
    public void onBindView(RcvHolder holder, String itemData, int position) {
        GlideApp.with(getContext()).load(itemData)
                //.placeholder(R.drawable.default_pic_home)
                .into((ImageView) holder.findView(R.id.img_view));
    }

}

