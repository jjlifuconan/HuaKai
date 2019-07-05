package com.social.huakai.ui.home.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.social.basecommon.fragment.BaseFragment;
import com.social.huakai.R;
import com.social.huakai.databinding.FragmentUserinfoShowBinding;
import com.social.huakai.ui.home.bean.UserDetailBean;

/**
 * @author Administrator
 * @date 2019/7/5 0005
 * @description:
 */
public class UserInfoShowFragment extends BaseFragment<FragmentUserinfoShowBinding> {

    public static UserInfoShowFragment newInstance(UserDetailBean bean) {

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
        binding.setBean((UserDetailBean.DataBean) getArguments().getSerializable("bean"));

    }
}
