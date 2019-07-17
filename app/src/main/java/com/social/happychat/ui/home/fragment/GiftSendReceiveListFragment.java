package com.social.happychat.ui.home.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.social.basecommon.databinding.FragmentRefreshListBinding;
import com.social.basecommon.fragment.BaseFragment;
import com.social.happychat.R;
import com.social.happychat.ui.home.adapter.GiftSendReceiveAdapter;
import com.social.happychat.ui.home.bean.GiftShopBean;
import com.social.happychat.ui.home.interfaces.GiftShopNavigator;
import com.social.happychat.ui.home.present.GiftShopPresent;
import com.social.happychat.ui.login.bean.UserBean;
import com.social.happychat.widget.DividerGridItemDecoration;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;

/**
 * @author Administrator
 * @date 2019/7/2 0002
 * @description:礼物列表
 */
public class GiftSendReceiveListFragment extends BaseFragment<FragmentRefreshListBinding> {
    private GiftSendReceiveAdapter GiftAdapter;
    ArrayList<UserBean.GiftListBean> giftListBeanList;

    public static GiftSendReceiveListFragment newInstance(ArrayList<UserBean.GiftListBean> giftListBeanList) {

        Bundle args = new Bundle();
        args.putSerializable("giftListBeanList",giftListBeanList);
        GiftSendReceiveListFragment fragment = new GiftSendReceiveListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        showContentView();
        giftListBeanList = (ArrayList<UserBean.GiftListBean>) getArguments().getSerializable("giftListBeanList");
        int dp14 = (int) getResources().getDimension(R.dimen.dp14);
        binding.recyclerView.setPadding(dp14,dp14,dp14,dp14);
        binding.recyclerView.setClipToPadding(false);
        binding.refreshLayout.setEnableRefresh(false);
        binding.refreshLayout.setEnableAutoLoadMore(false);

        GiftAdapter = new GiftSendReceiveAdapter(activity);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(activity, 4));
        DividerGridItemDecoration divider = new DividerGridItemDecoration(activity);
        divider.setDrawable(ContextCompat.getDrawable(activity, R.drawable.divider_grid_layout_manager_transparent2));
        binding.recyclerView.addItemDecoration(divider);
        binding.recyclerView.setAdapter(GiftAdapter);
        GiftAdapter.getItems().addAll(giftListBeanList);

    }


    @Override
    public int setContent() {
        return R.layout.fragment_refresh_list;
    }
}
