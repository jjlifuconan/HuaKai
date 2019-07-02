package com.social.huakai.ui.mine.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.social.basecommon.fragment.BaseFragment;
import com.social.huakai.R;
import com.social.huakai.databinding.FragmentRankListBinding;
import com.social.huakai.ui.mine.adapter.RankAdapter;
import com.social.huakai.ui.mine.bean.RankListBean;
import com.social.huakai.ui.mine.interfaces.RankNavigator;
import com.social.huakai.ui.mine.present.RankPresent;

import rx.Subscription;

/**
 * @author Administrator
 * @date 2019/7/2 0002
 * @description:
 */
public class RankListFragment extends BaseFragment<FragmentRankListBinding> implements RankNavigator {
    private RankAdapter rankAdapter;
    private RankPresent present;

    public static RankListFragment newInstance() {

        Bundle args = new Bundle();

        RankListFragment fragment = new RankListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        showContentView();
        present = new RankPresent(this);
        binding.refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                present.loadRankData();
            }
        });
        rankAdapter = new RankAdapter(activity);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        binding.recyclerView.setAdapter(rankAdapter);
        present.loadRankData();
    }

    @Override
    public int setContent() {
        return R.layout.fragment_rank_list;
    }

    @Override
    public void showLoadSuccessView() {
        showContentView();

    }

    @Override
    public void showAdapterView(RankListBean listBean) {
        rankAdapter.getItems().clear();
        binding.refreshLayout.finishRefresh();
        rankAdapter.getItems().addAll(listBean.getData());
    }

    @Override
    public void showListNoMoreLoading() {

    }

    @Override
    public void showLoadFailedView() {
        binding.refreshLayout.finishRefresh();
        if (rankAdapter.getItemCount() == 0) {
            showError();
        }
    }

    @Override
    public void addRxSubscription(Subscription subscription) {
        addSubscription(subscription);
    }

    @Override
    protected void onRefresh() {
        present.loadRankData();
    }
}
