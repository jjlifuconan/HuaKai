package com.social.huakai.ui.home.adapter;

import android.content.Context;

import com.social.basecommon.adapter.BaseBindingAdapter;
import com.social.huakai.R;
import com.social.huakai.databinding.ItemFindBinding;
import com.social.huakai.databinding.ItemTrendBinding;
import com.social.huakai.ui.find.bean.GankIoDataBean;
import com.social.huakai.ui.home.bean.NeteaseList;

/**
 * @author Administrator
 * @date 2019/6/27 0027
 * @description:
 */
public class TrendAdapter extends BaseBindingAdapter<NeteaseList.DataBean, ItemTrendBinding> {

    public TrendAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutResId(int viewType) {
        return R.layout.item_trend;
    }

    @Override
    protected void onBindItem(ItemTrendBinding binding, NeteaseList.DataBean item) {
        binding.setBean(item);
    }
}
