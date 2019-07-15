package com.social.happychat.ui.home.adapter;

import android.content.Context;
import android.view.View;

import com.social.basecommon.adapter.BaseBindingAdapter;
import com.social.happychat.R;
import com.social.happychat.databinding.ItemPraiseBinding;
import com.social.happychat.ui.home.activity.UserHomeActivity;
import com.social.happychat.ui.home.bean.PraiseListBean;

/**
 * @author Administrator
 * @date 2019/6/27 0027
 * @description:
 */
public class PraiseAdapter extends BaseBindingAdapter<PraiseListBean.ListBean, ItemPraiseBinding> {

    public PraiseAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutResId(int viewType) {
        return R.layout.item_praise;
    }

    @Override
    protected void onBindItem(ItemPraiseBinding binding, PraiseListBean.ListBean item) {
        binding.setBean(item);
        binding.ivHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserHomeActivity.action(context,item.getUserId());
            }
        });
    }
}
