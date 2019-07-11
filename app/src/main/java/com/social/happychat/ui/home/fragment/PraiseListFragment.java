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
import com.social.happychat.ui.home.adapter.PraiseAdapter;
import com.social.happychat.ui.home.bean.PraiseListBean;
import com.social.happychat.ui.home.interfaces.PraiseNavigator;
import com.social.happychat.ui.home.present.PraisePresent;

import java.util.List;

import rx.Subscription;

/**
 * @author Administrator
 * @date 2019/7/2 0002
 * @description:赞列表
 */
public class PraiseListFragment extends BaseFragment<FragmentRefreshListBinding> implements PraiseNavigator {
    private PraiseAdapter PraiseAdapter;
    private PraisePresent present;
    private int dynamicId;

    public static PraiseListFragment newInstance(int dynamicId) {

        Bundle args = new Bundle();
        args.putInt("dynamicId", dynamicId);
        PraiseListFragment fragment = new PraiseListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        dynamicId = getArguments().getInt("dynamicId");
        present = new PraisePresent(this, dynamicId);
        binding.refreshLayout.setEnableRefresh(false);
        binding.refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                int page = present.getPage();
                page++;
                present.setPage(page);
                present.loadPraiseData();
            }
        });

        PraiseAdapter = new PraiseAdapter(activity);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        binding.recyclerView.setAdapter(PraiseAdapter);
        present.loadPraiseData();
    }

    @Override
    public void showLoadSuccessView() {
        showContentView();
    }

    @Override
    public void showAdapterView(List<PraiseListBean.ListBean> dataBeans) {
        binding.refreshLayout.setNoMoreData(false);

        if (present.getPage() == 1) {
            PraiseAdapter.getItems().clear();
            binding.refreshLayout.finishRefresh();
        }else{
            binding.refreshLayout.finishLoadMore();
        }
        PraiseAdapter.getItems().addAll(dataBeans);

    }

    @Override
    public void showListNoMoreLoading() {
        binding.refreshLayout.finishLoadMoreWithNoMoreData();
    }

    @Override
    public void showLoadFailedView() {
        binding.refreshLayout.finishRefresh();
        if (PraiseAdapter.getItemCount() == 0) {
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
        present.loadPraiseData();
    }

    @Override
    public int setContent() {
        return R.layout.fragment_refresh_list;
    }
}
