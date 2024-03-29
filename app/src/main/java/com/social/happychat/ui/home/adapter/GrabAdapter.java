package com.social.happychat.ui.home.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.noober.background.drawable.DrawableCreator;
import com.social.basecommon.adapter.BaseBindingAdapter;
import com.social.basecommon.util.DensityUtil;
import com.social.basecommon.util.PerfectClickListener;
import com.social.happychat.R;
import com.social.happychat.databinding.ItemGrabBinding;
import com.social.happychat.im.SessionHelper;
import com.social.happychat.ui.home.bean.GrabListBean;

/**
 * @author Administrator
 * @date 2019/6/27 0027
 * @description:
 */
public class GrabAdapter extends BaseBindingAdapter<GrabListBean.ListBean, ItemGrabBinding> {

    public GrabAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutResId(int viewType) {
        return R.layout.item_grab;
    }

    @Override
    protected void onBindItem(ItemGrabBinding binding, GrabListBean.ListBean item) {
        binding.setBean(item);
        if(item.getUserSex() == 2){
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
        binding.contact.setOnClickListener(new PerfectClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                SessionHelper.startP2PSession(context, "18652032528");
            }
        });
    }
}
