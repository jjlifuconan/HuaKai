package com.social.happychat.ui.home.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.social.basecommon.fragment.BaseFragment;
import com.social.happychat.R;
import com.social.happychat.databinding.FragmentMaleAskForChatBinding;

/**
 * @author Administrator
 * @date 2019/7/2 0002
 * @description:男性抢聊
 */
public class MaleAskingForChatFragment extends BaseFragment<FragmentMaleAskForChatBinding> {

    public static MaleAskingForChatFragment newInstance() {

        Bundle args = new Bundle();

        MaleAskingForChatFragment fragment = new MaleAskingForChatFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int setContent() {
        return R.layout.fragment_male_ask_for_chat;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        showContentView();
        binding.vpSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.layoutRippleAnimation.isRippleRunning()) {
                    binding.layoutRippleAnimation.stopRippleAnimation();
                } else {
                    binding.layoutRippleAnimation.startRippleAnimation();
                }
            }
        });
    }
}
