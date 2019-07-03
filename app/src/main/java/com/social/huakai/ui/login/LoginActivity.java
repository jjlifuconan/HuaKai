package com.social.huakai.ui.login;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.gyf.immersionbar.ImmersionBar;
import com.social.basecommon.activity.BaseActivity;
import com.social.huakai.R;
import com.social.huakai.databinding.ActivityLoginBinding;

/**
 * @author Administrator
 * @date 2019/7/3 0003
 * @description:
 */
public class LoginActivity extends BaseActivity {
    ActivityLoginBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        ImmersionBar.with(activity)
                .statusBarDarkFont(true, 0.2f)
                .init();
        ImmersionBar.setTitleBar(this, binding.titlebar);


    }
}
