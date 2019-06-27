package com.social.huakai.ui.message.fragment;

import android.os.Bundle;

import com.social.basecommon.fragment.BaseFragment;
import com.social.huakai.R;
import com.social.huakai.databinding.FragmentTestBinding;

/**
 * @author Administrator
 * @date 2019/6/26 0026
 * @description:
 */
public class MessageFragment extends BaseFragment<FragmentTestBinding> {
    public static MessageFragment newInstance() {

        Bundle args = new Bundle();

        MessageFragment fragment = new MessageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int setContent() {
        return R.layout.fragment_test;
    }
}
