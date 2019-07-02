package com.social.huakai.ui.mine.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.social.basecommon.fragment.BaseFragment;
import com.social.huakai.R;
import com.social.huakai.databinding.FragmentRankListBinding;

/**
 * @author Administrator
 * @date 2019/7/2 0002
 * @description:
 */
public class TrankListFragment extends BaseFragment<FragmentRankListBinding> {

    public static TrankListFragment newInstance() {

        Bundle args = new Bundle();

        TrankListFragment fragment = new TrankListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        showContentView();
    }

    @Override
    public int setContent() {
        return R.layout.fragment_rank_list;
    }
}
