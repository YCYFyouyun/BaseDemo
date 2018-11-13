package com.template.demo.app.main.goods;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lwkandroid.rcvadapter.holder.RcvHolder;
import com.lwkandroid.rcvadapter.listener.RcvItemViewClickListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.template.demo.R;
import com.template.demo.app.shop.ShopActivity;
import com.template.rxextention.mvp.RxMvpBaseFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class GoodsListFragment extends RxMvpBaseFragment<GoodsListPresenter>
        implements GoodsListConstract.IView, OnRefreshListener {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    Unbinder unbinder;
    GoodsListAdapter mAdapter;
    boolean mGetList;


    public static GoodsListFragment newInstance() {
        return new GoodsListFragment();
    }

    @Override
    protected GoodsListPresenter createPresenter() {
        return new GoodsListPresenter();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_goods_list;
    }

    @Override
    protected void initUI(View contentView) {
        unbinder = ButterKnife.bind(this, contentView);
        mRefreshLayout.setEnableLoadMore(false);//禁止上拉加载
        mRefreshLayout.setOnRefreshListener(this);
        mAdapter = new GoodsListAdapter(getContext());
        mAdapter.setEmptyView(R.layout.layout_empty);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new RcvItemViewClickListener<String>() {
            @Override
            public void onItemViewClicked(RcvHolder holder, String s, int position) {
                ShopActivity.actionStart(getContext());
            }
        });
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
        mPresenter.reqGoodsListFromLocal();
    }

    @Override
    protected void onClick(int id, View v) {

    }

    @Override
    public void onResume() {
        super.onResume();
        if (!mGetList) mRefreshLayout.autoRefresh();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        mPresenter.reqGoodsList();
    }

    @Override
    public void refreshComplete() {
        mRefreshLayout.finishRefresh();
    }

    @Override
    public void showGoodsList(List<String> list) {
        mGetList = true;
        mAdapter.refreshDatas(list);
    }

}
