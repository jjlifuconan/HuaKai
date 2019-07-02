package com.social.huakai.ui.mine.adapter;

import android.content.Context;

import com.social.basecommon.adapter.BaseBindingAdapter;
import com.social.huakai.R;
import com.social.huakai.databinding.ItemRankBinding;
import com.social.huakai.ui.mine.bean.RankListBean;

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