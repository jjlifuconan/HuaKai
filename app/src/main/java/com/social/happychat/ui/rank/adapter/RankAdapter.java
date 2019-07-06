package com.social.happychat.ui.rank.adapter;

import android.content.Context;

import com.social.basecommon.adapter.BaseBindingAdapter;
import com.social.happychat.R;
import com.social.happychat.databinding.ItemRankBinding;
import com.social.happychat.ui.rank.bean.RankListBean;

/**
 * @author Administrator
 * @date 2019/6/27 0027
 * @description:
 */
public class RankAdapter extends BaseBindingAdapter<RankListBean.DataBean, ItemRankBinding> {

    public RankAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutResId(int viewType) {
        return R.layout.item_rank;
    }

    @Override
    protected void onBindItem(ItemRankBinding binding, RankListBean.DataBean item) {
        binding.setBean(item);
    }
}
