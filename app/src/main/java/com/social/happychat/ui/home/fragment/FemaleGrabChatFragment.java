package com.social.happychat.ui.home.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.social.basecommon.adapter.OnItemClickListener;
import com.social.basecommon.databinding.FragmentRefreshListBinding;
import com.social.basecommon.fragment.BaseFragment;
import com.social.happychat.R;
import com.social.happychat.ui.home.activity.UserHomeActivity;
import com.social.happychat.ui.home.adapter.GrabAdapter;
import com.social.happychat.ui.home.bean.GrabListBean;
import com.social.happychat.ui.home.interfaces.GrabNavigator;
import com.social.happychat.ui.home.present.GrabPresent;

import java.util.List;

import rx.Subscription;

/**
 * @author Administrator
 * @date 2019/7/2 0002
 * @description:女性抢聊
 */
public class FemaleGrabChatFragment extends BaseFragment<FragmentRefreshListBinding> implements GrabNavigator {
    private GrabAdapter grabAdapter;
    private GrabPresent present;

    public static FemaleGrabChatFragment newInstance() {

        Bundle args = new Bundle();

        FemaleGrabChatFragment fragment = new FemaleGrabChatFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        present = new GrabPresent(this);
        binding.refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                int page = present.getPage();
                page++;
                present.setPage(page);
                present.loadGrabData();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                present.setPage(1);
                present.loadGrabData();
            }
        });


        grabAdapter = new GrabAdapter(activity);
        grabAdapter.setOnItemClickListener(new OnItemClickListener<GrabListBean.ListBean>() {
            @Override
            public void onClick(GrabListBean.ListBean item) {
                UserHomeActivity.action(activity,item.getUserId());
            }
        });
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        DividerItemDecoration divider = new DividerItemDecoration(activity, DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(activity, R.drawable.divider_linear_transparent2));
        binding.recyclerView.addItemDecoration(divider);
        binding.recyclerView.setAdapter(grabAdapter);
        present.loadGrabData();
    }

    @Override
    public void showLoadSuccessView() {
        showContentView();
    }

    @Override
    public void showAdapterView(List<GrabListBean.ListBean> dataBeans) {
        binding.refreshLayout.setNoMoreData(false);

        if (present.getPage() == 1) {
            grabAdapter.getItems().clear();
            binding.refreshLayout.finishRefresh();
        }else{
            binding.refreshLayout.finishLoadMore();
        }
        grabAdapter.getItems().addAll(dataBeans);

    }

    @Override
    public void showListNoMoreLoading() {
        binding.refreshLayout.finishLoadMoreWithNoMoreData();
    }

    @Override
    public void showLoadFailedView() {
        binding.refreshLayout.finishRefresh();
        if (grabAdapter.getItemCount() == 0) {
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
        present.loadGrabData();
    }

    @Override
    public int setContent() {
        return R.layout.fragment_refresh_list;
    }
}
