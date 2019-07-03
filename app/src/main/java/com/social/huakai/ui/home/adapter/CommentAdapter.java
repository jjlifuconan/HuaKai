package com.social.huakai.ui.home.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;

import com.noober.background.drawable.DrawableCreator;
import com.social.basecommon.adapter.BaseBindingAdapter;
import com.social.basecommon.util.DensityUtil;
import com.social.huakai.R;
import com.social.huakai.databinding.ItemCommentBinding;
import com.social.huakai.ui.home.bean.CommentListBean;

/**
 * @author Administrator
 * @date 2019/6/27 0027
 * @description:
 */
public class CommentAdapter extends BaseBindingAdapter<CommentListBean.DataBean, ItemCommentBinding> {

    public CommentAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutResId(int viewType) {
        return R.layout.item_comment;
    }

    @Override
    protected void onBindItem(ItemCommentBinding binding, CommentListBean.DataBean item) {
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
