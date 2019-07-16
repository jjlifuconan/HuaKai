package com.social.happychat.ui.rank.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.RadioGroup;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.social.basecommon.adapter.OnItemClickListener;
import com.social.basecommon.fragment.BaseFragment;
import com.social.basecommon.util.ImageLoadUtil;
import com.social.basecommon.util.PerfectClickListener;
import com.social.happychat.R;
import com.social.happychat.constant.Constant;
import com.social.happychat.databinding.FragmentRankListBinding;
import com.social.happychat.ui.home.activity.UserHomeActivity;
import com.social.happychat.ui.rank.adapter.RankAdapter;
import com.social.happychat.ui.rank.bean.RankListBean;
import com.social.happychat.ui.rank.interfaces.RankNavigator;
import com.social.happychat.ui.rank.present.RankPresent;

import java.util.List;

import rx.Subscription;

/**
 * @author Administrator
 * @date 2019/7/2 0002
 * @description:
 */
public class RankListFragment extends BaseFragment<FragmentRankListBinding> implements RankNavigator {
    private RankAdapter rankAdapter;
    private RankPresent present;
    private String tabType;
    private String radioType = Constant.RadioType.DAY;
    private static final int NO_USERID = 0;


    public static RankListFragment newInstance(String tabType) {

        Bundle args = new Bundle();
        args.putString("tabType",tabType);
        RankListFragment fragment = new RankListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        tabType = getArguments().getString("tabType");
        ImageLoadUtil.displayLocalGif(binding.ivTop1Bg, R.drawable.ic_top1_bg_gif);
        showContentView();
        present = new RankPresent(this);
        binding.groupRank.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.rank_rb_day){
                    radioType = Constant.RadioType.DAY;
                }else if(checkedId == R.id.rank_rb_week){
                    radioType = Constant.RadioType.WEEK;
                }else if(checkedId == R.id.rank_rb_month){
                    radioType = Constant.RadioType.MONTH;
                }
                binding.refreshLayout.autoRefresh();
            }
        });
        binding.refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
//                present.setPage(1);
                present.loadRankData(tabType, radioType);
            }
        });
        rankAdapter = new RankAdapter(activity);
        rankAdapter.setOnItemClickListener(new OnItemClickListener<RankListBean>() {
            @Override
            public void onClick(RankListBean item) {
                UserHomeActivity.action(activity,item.getUserId());
            }
        });
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        binding.recyclerView.setAdapter(rankAdapter);
        present.loadRankData(tabType, radioType);
        binding.liTop1.setOnClickListener(new PerfectClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                if(getIntTag(binding.liTop1.getTag().toString()) != NO_USERID){
                    UserHomeActivity.action(activity,getIntTag(binding.liTop1.getTag().toString()));
                }

            }
        });

        binding.liTop2.setOnClickListener(new PerfectClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                if(getIntTag(binding.liTop2.getTag().toString()) != NO_USERID){
                    UserHomeActivity.action(activity,getIntTag(binding.liTop2.getTag().toString()));
                }
            }
        });


        binding.liTop3.setOnClickListener(new PerfectClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                if(getIntTag(binding.liTop3.getTag().toString()) != NO_USERID){
                    UserHomeActivity.action(activity,getIntTag(binding.liTop3.getTag().toString()));
                }
            }
        });

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
    public void showAdapterView(List<RankListBean> dataBean) {
        rankAdapter.getItems().clear();
        binding.refreshLayout.finishRefresh();
        rankAdapter.getItems().addAll(dataBean);
    }

    @Override
    public void showTop3Views(List<RankListBean> dataBean) {
        binding.refreshLayout.finishRefresh();
        if(dataBean.size() == 1){
            ImageLoadUtil.displayCircle(binding.ivTop1, dataBean.get(0).getHeadPhotoUrl(), dataBean.get(0).getUserSex() == 1?3:4);
            binding.tvTop1.setText(dataBean.get(0).getNickName());
            binding.liTop1.setTag(dataBean.get(0).getUserId());

            binding.liTop2.setTag(NO_USERID);
            binding.liTop3.setTag(NO_USERID);
            binding.ivTop2.setImageResource(0);
            binding.ivTop3.setImageResource(0);
            binding.tvTop2.setText("");
            binding.tvTop3.setText("");

        }else if(dataBean.size() == 2){
            ImageLoadUtil.displayCircle(binding.ivTop1, dataBean.get(0).getHeadPhotoUrl(), dataBean.get(0).getUserSex() == 1?3:4);
            binding.liTop1.setTag(dataBean.get(0).getUserId());
            binding.tvTop1.setText(dataBean.get(0).getNickName());

            ImageLoadUtil.displayCircle(binding.ivTop2, dataBean.get(1).getHeadPhotoUrl(), dataBean.get(1).getUserSex() == 1?3:4);
            binding.liTop2.setTag(dataBean.get(1).getUserId());
            binding.tvTop2.setText(dataBean.get(1).getNickName());

            binding.liTop3.setTag(NO_USERID);
            binding.ivTop3.setImageResource(0);
            binding.tvTop3.setText("");
        }else if(dataBean.size() == 3){
            ImageLoadUtil.displayCircle(binding.ivTop1, dataBean.get(0).getHeadPhotoUrl(), dataBean.get(0).getUserSex() == 1?3:4);
            binding.tvTop1.setText(dataBean.get(0).getNickName());
            binding.liTop1.setTag(dataBean.get(0).getUserId());

            ImageLoadUtil.displayCircle(binding.ivTop2, dataBean.get(1).getHeadPhotoUrl(), dataBean.get(1).getUserSex() == 1?3:4);
            binding.tvTop2.setText(dataBean.get(1).getNickName());
            binding.liTop2.setTag(dataBean.get(1).getUserId());

            ImageLoadUtil.displayCircle(binding.ivTop3, dataBean.get(2).getHeadPhotoUrl(), dataBean.get(2).getUserSex() == 1?3:4);
            binding.tvTop3.setText(dataBean.get(2).getNickName());
            binding.liTop3.setTag(dataBean.get(2).getUserId());
        }

    }

    @Override
    public void clearList() {
        rankAdapter.getItems().clear();
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
        present.loadRankData(tabType, radioType);
    }

    private int getIntTag(String tag){
        try {
            return Integer.parseInt(tag);
        }catch (Exception e){

        }
        return NO_USERID;
    }
}
