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
import com.social.basecommon.util.PerfectClickListener;
import com.social.basecommon.util.ToastUtil;
import com.social.happychat.R;
import com.social.happychat.constant.Constant;
import com.social.happychat.databinding.DialogGiftSendBinding;
import com.social.happychat.ui.home.adapter.GiftShopAdapter;
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
public class GiftShopListFragment extends BaseFragment<FragmentRefreshListBinding> implements GiftShopNavigator {
    private GiftShopAdapter GiftAdapter;
    private GiftShopPresent present;
    MaterialDialog dialog;

    public static GiftShopListFragment newInstance(int channel, int dynamicId, int userId) {

        Bundle args = new Bundle();
        args.putInt("channel",channel);
        args.putInt("dynamicId",dynamicId);
        args.putInt("userId",userId);
        GiftShopListFragment fragment = new GiftShopListFragment();
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
                present.loadGiftShopData();
            }
        });

        GiftAdapter = new GiftShopAdapter(activity);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(activity, 4));
        DividerGridItemDecoration divider = new DividerGridItemDecoration(activity);
        divider.setDrawable(ContextCompat.getDrawable(activity, R.drawable.divider_grid_layout_manager_transparent2));
        binding.recyclerView.addItemDecoration(divider);
        binding.recyclerView.setAdapter(GiftAdapter);
        GiftAdapter.setOnItemClickListener(new OnItemClickListener<GiftShopBean>() {
            @Override
            public void onClick(GiftShopBean item) {
                DialogGiftSendBinding binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.dialog_gift_send, null,false);
                binding.setBean(item);

                dialog = new MaterialDialog.Builder(activity).customView(binding.getRoot(),false)
                        .show();
                binding.send.setOnClickListener(new PerfectClickListener() {
                    @Override
                    protected void onNoDoubleClick(View v) {
                        int dynamicId = getArguments().getInt("dynamicId", 0);
                        int userId = getArguments().getInt("userId", 0);
                        present.sendGift(Constant.SendGiftType.TREND,dynamicId,item.getId(),userId);
                    }
                });
                binding.vpClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });
        present.loadGiftShopData();
    }

    @Override
    public void showLoadSuccessView() {
        showContentView();
    }

    @Override
    public void sendGiftSuccess() {
        ToastUtil.show(activity,"赠送礼物成功");
        binding.refreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
                activity.finish();
            }
        },500);
    }

    @Override
    public void showAdapterView(List<GiftShopBean> dataBeans) {
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
        present.loadGiftShopData();
    }


    @Override
    public int setContent() {
        return R.layout.fragment_refresh_list;
    }
}
