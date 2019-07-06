package com.social.huakai.ui.home.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;

import com.noober.background.drawable.DrawableCreator;
import com.social.basecommon.adapter.BaseBindingAdapter;
import com.social.basecommon.util.DensityUtil;
import com.social.huakai.R;
import com.social.huakai.databinding.ItemPraiseBinding;
import com.social.huakai.ui.home.bean.PraiseListBean;

/**
 * @author Administrator
 * @date 2019/6/27 0027
 * @description:
 */
public class PraiseAdapter extends BaseBindingAdapter<PraiseListBean.DataBean, ItemPraiseBinding> {

    public PraiseAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutResId(int viewType) {
        return R.layout.item_praise;
    }

    @Override
    protected void onBindItem(ItemPraiseBinding binding, PraiseListBean.DataBean item) {
        binding.setBean(item);
    }
}