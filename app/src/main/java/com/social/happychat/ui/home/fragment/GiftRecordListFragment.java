package com.social.happychat.ui.home.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.social.basecommon.databinding.FragmentRefreshListBinding;
import com.social.basecommon.fragment.BaseFragment;
import com.social.happychat.R;
import com.social.happychat.constant.Constant;
import com.social.happychat.ui.home.adapter.GiftRecordAdapter;
import com.social.happychat.ui.home.bean.GiftRecordBean;
import com.social.happychat.ui.home.interfaces.GiftRecordNavigator;
import com.social.happychat.ui.home.present.GiftRecordPresent;

import java.util.List;

import rx.Subscription;

/**
 * @author Administrator
 * @date 2019/7/2 0002
 * @description:礼物列表
 */
public class GiftRecordListFragment extends BaseFragment<FragmentRefreshListBinding> implements GiftRecordNavigator {
    private GiftRecordAdapter GiftAdapter;
    private GiftRecordPresent present;
    private int dynamicId;


    public static GiftRecordListFragment newInstance(int dynamicId) {

        Bundle args = new Bundle();
        args.putInt("dynamicId", dynamicId);

        GiftRecordListFragment fragment = new GiftRecordListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        dynamicId = getArguments().getInt("dynamicId");
        present = new GiftRecordPresent(this);
        binding.refreshLayout.setEnableRefresh(false);
        binding.refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                int page = present.getPage();
                page++;
                present.setPage(page);
                present.loadGiftData(Constant.SendGiftType.TREND, dynamicId, 0);
            }
        });

        GiftAdapter = new GiftRecordAdapter(activity);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        binding.recyclerView.setAdapter(GiftAdapter);
        present.loadGiftData(Constant.SendGiftType.TREND, dynamicId, 0);
    }

    @Override
    public void showLoadSuccessView() {
        showContentView();
    }

    @Override
    public void showAdapterView(List<GiftRecordBean.ListBean> dataBeans) {
        binding.refreshLayout.setNoMoreData(false);

        if (present.getPage() == 1) {
            GiftAdapter.getItems().clear();
            binding.refreshLayout.finishRefresh();
        }else{
            binding.refreshLayout.finishLoadMore();
        }
        GiftAdapter.getItems().addAll(dataBeans);

    }

    @Override
    public void showListNoMoreLoading() {
        binding.refreshLayout.finishLoadMoreWithNoMoreData();
    }

    @Override
    public void showLoadFailedView() {
        binding.refreshLayout.finishRefresh();
        if (GiftAdapter.getItemCount() == 0) {
            showError();
        }
    }

    @Override
    public void addRxSubscription(Subscription subscription) {
        addSubscription(subscription);
    }

    @Override
    protected void onRefresh() {
        present.setPage(1);
        present.loadGiftData(Constant.SendGiftType.TREND, dynamicId, 0);
    }

    @Override
    public int setContent() {
        return R.layout.fragment_refresh_list;
    }
}
