package com.social.huakai.ui.home.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;

import com.noober.background.drawable.DrawableCreator;
import com.social.basecommon.adapter.BaseBindingAdapter;
import com.social.basecommon.util.DensityUtil;
import com.social.huakai.R;
import com.social.huakai.databinding.ItemGiftBinding;
import com.social.huakai.ui.home.bean.GiftListBean;

/**
 * @author Administrator
 * @date 2019/6/27 0027
 * @description:
 */
public class GiftAdapter extends BaseBindingAdapter<GiftListBean.DataBean, ItemGiftBinding> {

    public GiftAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutResId(int viewType) {
        return R.layout.item_gift;
    }

    @Override
    protected void onBindItem(ItemGiftBinding binding, GiftListBean.DataBean item) {
        binding.setBean(item);
    }
}
