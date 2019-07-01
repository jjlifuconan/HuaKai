package com.social.huakai.ui.message.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.gyf.immersionbar.ImmersionBar;
import com.netease.nim.uikit.business.recent.RecentContactsFragment;
import com.social.basecommon.fragment.BaseFragment;
import com.social.huakai.R;
import com.social.huakai.databinding.FragmentMessageMainBinding;
import com.social.huakai.databinding.FragmentTestBinding;

/**
 * @author Administrator
 * @date 2019/6/26 0026
 * @description:
 */
public class MessageFragment extends BaseFragment<FragmentMessageMainBinding> {
    private RecentContactsFragment fragment;

    public static MessageFragment newInstance() {

        Bundle args = new Bundle();

        MessageFragment fragment = new MessageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int setContent() {
        return R.layout.fragment_message_main;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        ImmersionBar.setTitleBar(this, binding.titlebar);
        showContentView();
        addRecentContactsFragment();
    }


    // 将最近联系人列表fragment动态集成进来。
    private void addRecentContactsFragment() {
        fragment = new RecentContactsFragment();
        getChildFragmentManager().beginTransaction().add(R.id.content, fragment).commit();
    }
}
