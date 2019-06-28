package com.social.huakai.ui.home.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.social.basecommon.databinding.FragmentRefreshListBinding;
import com.social.basecommon.fragment.BaseFragment;
import com.social.huakai.R;
import com.social.huakai.ui.home.adapter.TrendAdapter;
import com.social.huakai.ui.home.bean.NeteaseList;
import com.social.huakai.ui.home.interfaces.TrendNavigator;
import com.social.huakai.ui.home.present.TrendPresent;
import com.social.huakai.widget.DividerGridItemDecoration;

import rx.Subscription;

/**
 * @author Administrator
 * @date 2019/6/28 0028
 * @description:动态列表
 */
public class TrendFragment extends BaseFragment<FragmentRefreshListBinding> implements TrendNavigator {
    private TrendAdapter trendAdapter;
    private TrendPresent present;

    public static TrendFragment newInstance() {

        Bundle args = new Bundle();

        TrendFragment fragment = new TrendFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        present = new TrendPresent(this);
        present.loadFindData();
        binding.refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                int page = present.getPage();
                page++;
                present.setPage(page);
                present.loadFindData();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                present.setPage(1);
                present.loadFindData();
            }
        });


        trendAdapter = new TrendAdapter(activity);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(activity, 2));
        DividerGridItemDecoration divider = new DividerGridItemDecoration(activity);
        divider.setDrawable(ContextCompat.getDrawable(activity, R.drawable.divider_grid_layout_manager_transparent2));
        binding.recyclerView.addItemDecoration(divider);
        binding.recyclerView.setAdapter(trendAdapter);
    }

    @Override
    public void showLoadSuccessView() {
        showContentView();
    }

    @Override
    public void showAdapterView(NeteaseList neteaseList) {
        binding.refreshLayout.setNoMoreData(false);

        if (present.getPage() == 1) {
            trendAdapter.getItems().clear();
            binding.refreshLayout.finishRefresh();
        }else{
            binding.refreshLayout.finishLoadMore();
        }
        trendAdapter.getItems().addAll(neteaseList.getData());

    }

    @Override
    public void showListNoMoreLoading() {
        binding.refreshLayout.finishLoadMoreWithNoMoreData();
    }

    @Override
    public void showLoadFailedView() {
        binding.refreshLayout.finishRefresh();
        if (trendAdapter.getItemCount() == 0) {
            showError();
        }
    }

    @Override
    public void addRxSubscription(Subscription subscription) {
        addSubscription(subscription);
    }

    @Override
    protected void onRefresh() {
        present.loadFindData();
    }

    @Override
    public int setContent() {
        return R.layout.fragment_refresh_list;
    }
}
