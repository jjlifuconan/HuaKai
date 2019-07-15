package com.social.happychat.ui.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.animation.AnimationUtils;

import com.social.basecommon.adapter.BaseBindingAdapter;
import com.social.happychat.R;
import com.social.happychat.databinding.ItemCommentBinding;
import com.social.happychat.ui.home.activity.UserHomeActivity;
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
        binding.ivHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserHomeActivity.action(context,item.getUserId());
            }
        });
        binding.vpPraise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(item.getIsPraise() == 1){
                    item.setIsPraise(0);
                    if(listener != null){
                        listener.onPraise(item.getId(), 2);
                    }
                    item.setPraiseCount(item.getPraiseCount()-1);
                }else{
                    binding.ivPraise.startAnimation(AnimationUtils.loadAnimation(
                            context, R.anim.dianzan_anim));
                    item.setIsPraise(1);
                    if(listener != null){
                        listener.onPraise(item.getId(), 1);
                    }
                    item.setPraiseCount(item.getPraiseCount()+1);
                }
            }
        });
    }

    public interface OnPraiseClickListener{
        public void onPraise(int id, int type);
    }

    public void setOnPraiseClickListener(TrendAdapter.OnPraiseClickListener listener) {
        this.listener = listener;
    }

    private TrendAdapter.OnPraiseClickListener listener;
}
