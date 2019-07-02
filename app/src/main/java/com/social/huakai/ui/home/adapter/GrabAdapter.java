package com.social.huakai.ui.home.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;

import com.noober.background.drawable.DrawableCreator;
import com.social.basecommon.adapter.BaseBindingAdapter;
import com.social.basecommon.util.DensityUtil;
import com.social.huakai.R;
import com.social.huakai.databinding.ItemGrabBinding;
import com.social.huakai.databinding.ItemRankBinding;
import com.social.huakai.ui.home.bean.GrabListBean;
import com.social.huakai.ui.mine.bean.RankListBean;

/**
 * @author Administrator
 * @date 2019/6/27 0027
 * @description:
 */
public class GrabAdapter extends BaseBindingAdapter<GrabListBean.DataBean, ItemGrabBinding> {

    public GrabAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutResId(int viewType) {
        return R.layout.item_grab;
    }

    @Override
    protected void onBindItem(ItemGrabBinding binding, GrabListBean.DataBean item) {
        binding.setBean(item);
        if(TextUtils.equals("1",item.getSex())){
            Drawable drawable4 = new DrawableCreator.Builder().setCornersRadius(DensityUtil.dip2px(context,3))
                    .setSolidColor(ContextCompat.getColor(context,R.color.age_girl_tv))
                    .build();
            binding.contact.setBackground(drawable4);
        }else{
            Drawable drawable4 = new DrawableCreator.Builder().setCornersRadius(DensityUtil.dip2px(context,3))
                    .setSolidColor(ContextCompat.getColor(context,R.color.age_boy_tv))
                    .build();
            binding.contact.setBackground(drawable4);
        }
    }
}
