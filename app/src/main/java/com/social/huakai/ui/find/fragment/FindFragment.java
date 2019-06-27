package com.social.huakai.ui.find.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.social.basecommon.fragment.BaseFragment;
import com.social.huakai.R;
import com.social.huakai.databinding.FragmentHomeBinding;
import com.social.huakai.databinding.FragmentTestBinding;

/**
 * @author Administrator
 * @date 2019/6/26 0026
 * @description:
 */
public class FindFragment extends BaseFragment<FragmentTestBinding> {
    public static FindFragment newInstance() {

        Bundle args = new Bundle();

        FindFragment fragment = new FindFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int setContent() {
        return R.layout.fragment_test;
    }
}
