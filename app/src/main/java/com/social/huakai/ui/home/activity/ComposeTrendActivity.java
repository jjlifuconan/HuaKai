package com.social.huakai.ui.home.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.gyf.immersionbar.ImmersionBar;
import com.social.basecommon.activity.BaseActivity;
import com.social.basecommon.util.KeyboardUtils;
import com.social.huakai.R;
import com.social.huakai.databinding.ActivityComposeTrendBinding;
import com.social.huakai.databinding.ActivityDetailTrendBinding;
import com.social.huakai.ui.home.bean.NeteaseList;

/**
 * @author Administrator
 * @date 2019/7/6
 * @description:
 */
public class ComposeTrendActivity extends BaseActivity {
    ActivityComposeTrendBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_compose_trend);
        ImmersionBar.with(activity)
                .statusBarDarkFont(true, 0.2f)
                .init();
        ImmersionBar.setTitleBar(this, binding.titlebar);
        setListener();
    }

    private void setListener() {
        binding.vpClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                KeyboardUtils.hideSoftInput(activity);
                finish();
            }
        });

        binding.compose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public static void action(Context context){
        Intent intent = new Intent(context, ComposeTrendActivity.class);
        context.startActivity(intent);
    }
}
