package com.social.huakai.ui.home.adapter;

import android.content.Context;

import com.social.basecommon.adapter.BaseBindingAdapter;
import com.social.huakai.R;
import com.social.huakai.databinding.ItemGiftShopBinding;
import com.social.huakai.ui.home.bean.GiftShopBean;

/**
 * @author Administrator
 * @date 2019/6/27 0027
 * @description:
 */
public class GiftShopAdapter extends BaseBindingAdapter<GiftShopBean.DataBean, ItemGiftShopBinding> {

    public GiftShopAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutResId(int viewType) {
        return R.layout.item_gift_shop;
    }

    @Override
    protected void onBindItem(ItemGiftShopBinding binding, GiftShopBean.DataBean item) {
        binding.setBean(item);
    }
}
