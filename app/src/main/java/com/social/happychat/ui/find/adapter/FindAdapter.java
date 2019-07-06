package com.social.happychat.ui.find.adapter;

import android.content.Context;

import com.social.basecommon.adapter.BaseBindingAdapter;
import com.social.happychat.R;
import com.social.happychat.databinding.ItemFindBinding;
import com.social.happychat.ui.find.bean.GankIoDataBean;

/**
 * @author Administrator
 * @date 2019/6/27 0027
 * @description:
 */
public class FindAdapter extends BaseBindingAdapter<GankIoDataBean.ResultBean, ItemFindBinding> {

    public FindAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutResId(int viewType) {
        return R.layout.item_find;
    }

    @Override
    protected void onBindItem(ItemFindBinding binding, GankIoDataBean.ResultBean item) {
        binding.setBean(item);
    }
}
