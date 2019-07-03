package com.social.huakai.ui.mine.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.gyf.immersionbar.ImmersionBar;
import com.social.basecommon.fragment.BaseFragment;
import com.social.huakai.R;
import com.social.huakai.databinding.FragmentMineBinding;

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
    }
}
