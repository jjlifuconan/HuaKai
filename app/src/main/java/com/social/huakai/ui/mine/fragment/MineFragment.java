package com.social.huakai.ui.mine.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.social.basecommon.fragment.BaseFragment;
import com.social.huakai.R;

/**
 * @author Administrator
 * @date 2019/6/26 0026
 * @description:
 */
public class MineFragment extends BaseFragment {
    public static MineFragment newInstance() {

        Bundle args = new Bundle();

        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int setContent() {
        return R.layout.fragment_test;
    }

}
