package com.social.happychat.ui.find.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.social.basecommon.databinding.FragmentRefreshListBinding;
import com.social.basecommon.fragment.BaseFragment;
import com.social.happychat.R;
import com.social.happychat.ui.find.adapter.FindAdapter;
import com.social.happychat.ui.find.bean.GankIoDataBean;
import com.social.happychat.ui.find.interfaces.FindNavigator;
import com.social.happychat.ui.find.present.FindPresent;
import com.social.happychat.widget.DividerGridItemDecoration;

import rx.Subscription;

/**
 * @author Administrator
 * @date 2019/6/26 0026
 * @description:发现页面
 */
public class FindChildFragment extends BaseFragment<FragmentRefreshListBinding> implements FindNavigator {
    private FindAdapter findAdapter;
    private FindPresent present;

    public static FindChildFragment newInstance() {

        Bundle args = new Bundle();

        FindChildFragment fragment = new FindChildFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        present = new FindPresent(this);
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


        findAdapter = new FindAdapter(activity);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(activity, 2));
        DividerGridItemDecoration divider = new DividerGridItemDecoration(activity);
        divider.setDrawable(ContextCompat.getDrawable(activity, R.drawable.divider_grid_layout_manager_transparent2));
        binding.recyclerView.addItemDecoration(divider);
        binding.recyclerView.setAdapter(findAdapter);
        present.loadFindData();
    }

    @Override
    public int setContent() {
        return R.layout.fragment_refresh_list;
    }

    @Override
    public void showLoadSuccessView() {
        showContentView();
    }

    @Override
    public void showAdapterView(GankIoDataBean gankIoDataBean) {
        binding.refreshLayout.setNoMoreData(false);

        if (present.getPage() == 1) {
            findAdapter.getItems().clear();
            binding.refreshLayout.finishRefresh();
        }else{
            binding.refreshLayout.finishLoadMore();
        }
        findAdapter.getItems().addAll(gankIoDataBean.getResults());

    }

    @Override
    public void showListNoMoreLoading() {
        binding.refreshLayout.finishLoadMoreWithNoMoreData();
    }

    @Override
    public void showLoadFailedView() {
        binding.refreshLayout.finishRefresh();
        if (findAdapter.getItemCount() == 0) {
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
}
