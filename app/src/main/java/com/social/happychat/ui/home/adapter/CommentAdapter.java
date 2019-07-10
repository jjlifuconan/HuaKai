package com.social.happychat.ui.home.adapter;

import android.content.Context;

import com.social.basecommon.adapter.BaseBindingAdapter;
import com.social.happychat.R;
import com.social.happychat.databinding.ItemCommentBinding;
import com.social.happychat.ui.home.bean.CommentListBean;

/**
 * @author Administrator
 * @date 2019/6/27 0027
 * @description:
 */
public class CommentAdapter extends BaseBindingAdapter<CommentListBean.ListBean, ItemCommentBinding> {

    public CommentAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutResId(int viewType) {
        return R.layout.item_comment;
    }

    @Override
    protected void onBindItem(ItemCommentBinding binding, CommentListBean.ListBean item) {
        binding.setBean(item);
    }
}
