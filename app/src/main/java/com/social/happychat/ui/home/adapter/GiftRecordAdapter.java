package com.social.happychat.ui.home.adapter;

import android.content.Context;
import android.view.View;

import com.social.basecommon.adapter.BaseBindingAdapter;
import com.social.happychat.R;
import com.social.happychat.databinding.ItemGiftRecordBinding;
import com.social.happychat.ui.home.activity.UserHomeActivity;
import com.social.happychat.ui.home.bean.GiftRecordBean;

/**
 * @author Administrator
 * @date 2019/6/27 0027
 * @description:
 */
public class GiftRecordAdapter extends BaseBindingAdapter<GiftRecordBean.ListBean, ItemGiftRecordBinding> {

    public GiftRecordAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutResId(int viewType) {
        return R.layout.item_gift_record;
    }

    @Override
    protected void onBindItem(ItemGiftRecordBinding binding, GiftRecordBean.ListBean item) {
        binding.setBean(item);
        binding.ivHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserHomeActivity.action(context,item.getUserId());
            }
        });
    }
}
