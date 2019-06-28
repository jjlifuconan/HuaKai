package com.social.basecommon.activity;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.gyf.immersionbar.ImmersionBar;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * @author Administrator
 * @date 2019/6/26 0026
 * @description:
 */
public class BaseActivity extends SupportActivity {
    protected Activity activity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
