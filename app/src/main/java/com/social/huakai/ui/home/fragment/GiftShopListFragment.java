package com.social.huakai.ui.home.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.ViewGroup;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.social.basecommon.databinding.FragmentRefreshListBinding;
import com.social.basecommon.fragment.BaseFragment;
import com.social.basecommon.util.DensityUtil;
import com.social.huakai.R;
import com.social.huakai.ui.home.adapter.GiftShopAdapter;
import com.social.huakai.ui.home.bean.GiftRecordBean;
import com.social.huakai.ui.home.bean.GiftShopBean;
import com.social.huakai.ui.home.interfaces.GiftShopNavigator;
import com.social.huakai.ui.home.present.GiftShopPresent;
import com.social.huakai.widget.DividerGridItemDecoration;

import java.util.List;

import rx.Subscription;

/**
 * @author Administrator
 * @date 2019/7/2 0002
 * @description:礼物列表
 */
public class GiftShopListFragment extends BaseFragment<FragmentRefreshListBinding> implements GiftShopNavigator {
    private GiftShopAdapter GiftAdapter;
    private GiftShopPresent present;

    public static GiftShopListFragment newInstance() {

        Bundle args = new Bundle();

        GiftShopListFragment fragment = new GiftShopListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        present = new GiftShopPresent(this);
        binding.refreshLayout.setEnableRefresh(false);
        binding.refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                int page = present.getPage();
                page++;
                present.setPage(page);
                present.loadGiftData();
            }
        });

        GiftAdapter = new GiftShopAdapter(activity);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(activity, 4));
        DividerGridItemDecoration divider = new DividerGridItemDecoration(activity);
        divider.setDrawable(ContextCompat.getDrawable(activity, R.drawable.divider_grid_layout_manager_transparent2));
        binding.recyclerView.addItemDecoration(divider);
        binding.recyclerView.setAdapter(GiftAdapter);
        present.loadGiftData();
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
        present.loadGiftData();
    }

    @Override
    protected void modifyParms(ConstraintLayout.LayoutParams params) {
//        params.leftMargin = DensityUtil.dip2px(activity, R.dimen.dp14);
//        params.rightMargin = DensityUtil.dip2px(activity, R.dimen.dp14);
//        params.topMargin = DensityUtil.dip2px(activity, R.dimen.dp14);
//        params.bottomMargin = DensityUtil.dip2px(activity, R.dimen.dp14);
    }

    @Override
    public int setContent() {
        return R.layout.fragment_refresh_list;
    }
}
