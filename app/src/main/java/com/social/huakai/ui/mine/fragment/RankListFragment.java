package com.social.huakai.ui.mine.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.widget.RadioGroup;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.social.basecommon.fragment.BaseFragment;
import com.social.basecommon.util.ImageLoadUtil;
import com.social.huakai.R;
import com.social.huakai.constant.Constant;
import com.social.huakai.databinding.FragmentRankListBinding;
import com.social.huakai.ui.mine.adapter.RankAdapter;
import com.social.huakai.ui.mine.bean.RankListBean;
import com.social.huakai.ui.mine.interfaces.RankNavigator;
import com.social.huakai.ui.mine.present.RankPresent;

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
                present.loadRankData(tabType, radioType);
            }
        });
        rankAdapter = new RankAdapter(activity);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        binding.recyclerView.setAdapter(rankAdapter);
        present.loadRankData(tabType, radioType);
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
    public void showAdapterView(List<RankListBean.DataBean> dataBean) {
        rankAdapter.getItems().clear();
        binding.refreshLayout.finishRefresh();
        rankAdapter.getItems().addAll(dataBean);
    }

    @Override
    public void showTop3Views(List<RankListBean.DataBean> dataBean) {
        if(dataBean.size() == 1){
            ImageLoadUtil.displayCircle(binding.ivTop1, dataBean.get(0).getPhoto(), TextUtils.equals("1",dataBean.get(0).getSex())?4:3);
            binding.tvTop1.setText(dataBean.get(0).getNickName());
        }else if(dataBean.size() == 2){
            ImageLoadUtil.displayCircle(binding.ivTop1, dataBean.get(0).getPhoto(), TextUtils.equals("1",dataBean.get(0).getSex())?4:3);
            binding.tvTop1.setText(dataBean.get(0).getNickName());

            ImageLoadUtil.displayCircle(binding.ivTop2, dataBean.get(1).getPhoto(), TextUtils.equals("1",dataBean.get(1).getSex())?4:3);
            binding.tvTop2.setText(dataBean.get(1).getNickName());
        }else if(dataBean.size() == 3){
            ImageLoadUtil.displayCircle(binding.ivTop1, dataBean.get(0).getPhoto(), TextUtils.equals("1",dataBean.get(0).getSex())?4:3);
            binding.tvTop1.setText(dataBean.get(0).getNickName());

            ImageLoadUtil.displayCircle(binding.ivTop2, dataBean.get(1).getPhoto(), TextUtils.equals("1",dataBean.get(1).getSex())?4:3);
            binding.tvTop2.setText(dataBean.get(1).getNickName());

            ImageLoadUtil.displayCircle(binding.ivTop3, dataBean.get(2).getPhoto(), TextUtils.equals("1",dataBean.get(2).getSex())?4:3);
            binding.tvTop3.setText(dataBean.get(2).getNickName());
        }

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
}
