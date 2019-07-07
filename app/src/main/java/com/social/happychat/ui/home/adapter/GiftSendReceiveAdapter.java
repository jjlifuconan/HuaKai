package com.social.happychat.ui.home.adapter;

import android.content.Context;

import com.social.basecommon.adapter.BaseBindingAdapter;
import com.social.happychat.R;
import com.social.happychat.databinding.ItemGiftSendreceiveBinding;
import com.social.happychat.databinding.ItemGiftShopBinding;
import com.social.happychat.ui.home.bean.GiftShopBean;

/**
 * @author Administrator
 * @date 2019/6/27 0027
 * @description:
 */
public class GiftSendReceiveAdapter extends BaseBindingAdapter<GiftShopBean.DataBean, ItemGiftSendreceiveBinding> {

    public GiftSendReceiveAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutResId(int viewType) {
        return R.layout.item_gift_sendreceive;
    }

    @Override
    protected void onBindItem(ItemGiftSendreceiveBinding binding, GiftShopBean.DataBean item) {
        binding.setBean(item);
    }
}
