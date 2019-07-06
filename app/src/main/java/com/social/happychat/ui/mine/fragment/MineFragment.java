package com.social.happychat.ui.mine.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.social.basecommon.fragment.BaseFragment;
import com.social.basecommon.util.GlideApp;
import com.social.basecommon.util.ImageLoadUtil;
import com.social.basecommon.util.SPUtils;
import com.social.happychat.R;
import com.social.happychat.constant.Constant;
import com.social.happychat.databinding.FragmentMineBinding;
import com.social.happychat.ui.login.bean.WechatUserBean;

/**
 * @author Administrator
 * @date 2019/7/3 0003
 * @description:
 */
public class MineFragment extends BaseFragment<FragmentMineBinding> {

    public static MineFragment newInstance() {

        Bundle args = new Bundle();

        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int setContent() {
        return R.layout.fragment_mine;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
//        ImmersionBar.setTitleBar(this, binding.headerLayout);
        showContentView();
        WechatUserBean wechatUserBean = SPUtils.getObject(activity, Constant.SP_HAPPY_CHAT, Constant.WECHAT_USER_INFO, WechatUserBean.class);
        if(wechatUserBean != null){
//            GlideApp.with(activity).load(R.mipmap.asd).into(binding.ivHead);
            ImageLoadUtil.displayCircle(binding.ivHead, wechatUserBean.getHeadimgurl());
            binding.tvNickname.setText(wechatUserBean.getNickname());
        }
    }
}
