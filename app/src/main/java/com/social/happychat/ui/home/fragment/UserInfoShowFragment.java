package com.social.happychat.ui.home.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.social.basecommon.fragment.BaseFragment;
import com.social.basecommon.util.ImageLoadUtil;
import com.social.happychat.R;
import com.social.happychat.databinding.FragmentUserinfoShowBinding;
import com.social.happychat.ui.home.activity.GiftShopActivity;
import com.social.happychat.ui.home.bean.UserDetailBean;

/**
 * @author Administrator
 * @date 2019/7/5 0005
 * @description:
 */
public class UserInfoShowFragment extends BaseFragment<FragmentUserinfoShowBinding> {

    public static UserInfoShowFragment newInstance(UserDetailBean.DataBean bean) {

        Bundle args = new Bundle();
        args.putSerializable("bean",bean);
        UserInfoShowFragment fragment = new UserInfoShowFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int setContent() {
        return R.layout.fragment_userinfo_show;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        showContentView();
        UserDetailBean.DataBean bean = (UserDetailBean.DataBean) getArguments().getSerializable("bean");
        binding.setBean(bean);

        if(bean.getTags()!=null && !bean.getTags().isEmpty()){
            for(String tag: bean.getTags()){
                TextView tv_tag= (TextView) LayoutInflater.from(activity).inflate(R.layout.cell_tag, null);
                tv_tag.setText(tag);
                binding.flexbox.addView(tv_tag);
            }
        }

        if(bean.getReceiveList()!=null && !bean.getReceiveList().isEmpty()){
            for(String url: bean.getReceiveList()){
                ImageView img = (ImageView) LayoutInflater.from(activity).inflate(R.layout.cell_gift, null);
                ImageLoadUtil.displayCircle(img,url,2);
                int dp40 = (int) activity.getResources().getDimension(R.dimen.dp40);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(dp40,dp40);
                binding.liReceive.addView(img,lp);
            }
        }
        binding.liReceive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GiftShopActivity.action(view.getContext(), GiftShopActivity.TYPE_RECEIVE);
            }
        });

        if(bean.getSendList()!=null && !bean.getSendList().isEmpty()){
            for(String url: bean.getSendList()){
                ImageView img = (ImageView) LayoutInflater.from(activity).inflate(R.layout.cell_gift, null);
                ImageLoadUtil.displayCircle(img,url,2);
                int dp40 = (int) activity.getResources().getDimension(R.dimen.dp40);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(dp40,dp40);
                binding.liSend.addView(img,lp);
            }
        }
        binding.liSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GiftShopActivity.action(view.getContext(), GiftShopActivity.TYPE_SEND);
            }
        });

        if(bean.getVisitorList()!=null && !bean.getVisitorList().isEmpty()){
            for(String url: bean.getVisitorList()){
                ImageView img = (ImageView) LayoutInflater.from(activity).inflate(R.layout.cell_gift, null);
                ImageLoadUtil.displayCircle(img,url,2);
                int dp40 = (int) activity.getResources().getDimension(R.dimen.dp40);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(dp40,dp40);
                binding.liVisitor.addView(img,lp);
            }
        }



    }
}
