package com.social.happychat.ui.home.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.social.basecommon.fragment.BaseFragment;
import com.social.basecommon.util.ImageLoadUtil;
import com.social.happychat.R;
import com.social.happychat.constant.Constant;
import com.social.happychat.databinding.FragmentUserinfoShowBinding;
import com.social.happychat.ui.home.activity.GiftShopActivity;
import com.social.happychat.ui.home.bean.UserDetailBean;
import com.social.happychat.ui.login.bean.UserBean;
import com.social.happychat.ui.mine.bean.TagListBean;

/**
 * @author Administrator
 * @date 2019/7/5 0005
 * @description:
 */
public class UserInfoShowFragment extends BaseFragment<FragmentUserinfoShowBinding> {

    public static UserInfoShowFragment newInstance(UserBean bean) {

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
        UserBean bean = (UserBean) getArguments().getSerializable("bean");
        binding.setBean(bean);

        if(bean.getUserTagDtos()!=null && !bean.getUserTagDtos().isEmpty()){
            for(TagListBean tag: bean.getUserTagDtos()){
                TextView tv_tag= (TextView) LayoutInflater.from(activity).inflate(R.layout.cell_tag, null);
                tv_tag.setText(tag.getClassifyName());
                binding.flexbox.addView(tv_tag);
            }
        }

        if(bean.getReceiveGiftList()!=null && !bean.getReceiveGiftList().isEmpty()){
            for(UserBean.ReceiveGiftListBean gift: bean.getReceiveGiftList()){
                ImageView img = (ImageView) LayoutInflater.from(activity).inflate(R.layout.cell_gift, null);
                ImageLoadUtil.displayCircle(img,gift.getGiftIcon(),2);
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

        if(bean.getGiveGiftList()!=null && !bean.getGiveGiftList().isEmpty()){
            for(UserBean.GiveGiftListBean gift: bean.getGiveGiftList()){
                ImageView img = (ImageView) LayoutInflater.from(activity).inflate(R.layout.cell_gift, null);
                ImageLoadUtil.displayCircle(img,gift.getGiftIcon(),2);
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

        if(bean.getBrowseInfoDtoList()!=null && !bean.getBrowseInfoDtoList().isEmpty()){
            for(UserBean.BrowseInfoDtoListBean vistor: bean.getBrowseInfoDtoList()){
                ImageView img = (ImageView) LayoutInflater.from(activity).inflate(R.layout.cell_gift, null);
                ImageLoadUtil.displayCircle(img,vistor.getBrowseHeadPhotoUrl(),vistor.getBrowseUserSex() == 1?3:4);
                int dp40 = (int) activity.getResources().getDimension(R.dimen.dp40);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(dp40,dp40);
                binding.liVisitor.addView(img,lp);
            }
        }



    }
}
