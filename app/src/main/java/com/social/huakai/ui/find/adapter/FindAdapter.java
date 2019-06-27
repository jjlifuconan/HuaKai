package com.social.huakai.ui.find.adapter;

import android.content.Context;
import android.databinding.ViewDataBinding;

import com.social.basecommon.adapter.BaseBindingAdapter;
import com.social.huakai.R;
import com.social.huakai.databinding.ItemFindBinding;
import com.social.huakai.ui.find.bean.GankIoDataBean;

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
