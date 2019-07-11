package com.social.happychat.ui.home.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.afollestad.materialdialogs.MaterialDialog;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.social.basecommon.adapter.OnItemClickListener;
import com.social.basecommon.databinding.FragmentRefreshListBinding;
import com.social.basecommon.fragment.BaseFragment;
import com.social.happychat.R;
import com.social.happychat.databinding.DialogGiftSendBinding;
import com.social.happychat.ui.home.adapter.GiftSendReceiveAdapter;
import com.social.happychat.ui.home.bean.GiftShopBean;
import com.social.happychat.ui.home.interfaces.GiftShopNavigator;
import com.social.happychat.ui.home.present.GiftShopPresent;
import com.social.happychat.widget.DividerGridItemDecoration;

import java.util.List;

import rx.Subscription;

/**
 * @author Administrator
 * @date 2019/7/2 0002
 * @description:礼物列表
 */
public class GiftReceiveListFragment extends BaseFragment<FragmentRefreshListBinding> implements GiftShopNavigator {
    private GiftSendReceiveAdapter GiftAdapter;
    private GiftShopPresent present;

    public static GiftReceiveListFragment newInstance() {

        Bundle args = new Bundle();

        GiftReceiveListFragment fragment = new GiftReceiveListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        int dp14 = (int) getResources().getDimension(R.dimen.dp14);
        binding.recyclerView.setPadding(dp14,dp14,dp14,dp14);
        binding.recyclerView.setClipToPadding(false);
        present = new GiftShopPresent(this);
        binding.refreshLayout.setEnableRefresh(false);
        binding.refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                int page = present.getPage();
                page++;
                present.setPage(page);
                present.loadGiftReceiveData();
            }
        });

        GiftAdapter = new GiftSendReceiveAdapter(activity);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(activity, 4));
        DividerGridItemDecoration divider = new DividerGridItemDecoration(activity);
        divider.setDrawable(ContextCompat.getDrawable(activity, R.drawable.divider_grid_layout_manager_transparent2));
        binding.recyclerView.addItemDecoration(divider);
        binding.recyclerView.setAdapter(GiftAdapter);
        present.loadGiftReceiveData();
    }

    @Override
    public void showLoadSuccessView() {
        showContentView();
    }

    @Override
    public void showAdapterView(List<GiftShopBean.DataBean> dataBeans) {
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
        present.loadGiftReceiveData();
    }


    @Override
    public int setContent() {
        return R.layout.fragment_refresh_list;
    }
}
