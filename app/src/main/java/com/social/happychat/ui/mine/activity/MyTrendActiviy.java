package com.social.happychat.ui.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;

import com.gyf.immersionbar.ImmersionBar;
import com.social.basecommon.util.KeyboardUtils;
import com.social.basecommon.util.PerfectClickListener;
import com.social.happychat.R;
import com.social.happychat.base.BaseCookieActivity;
import com.social.happychat.databinding.ActivityMyTrendBinding;
import com.social.happychat.databinding.ActivityUserSingleattrEditBinding;
import com.social.happychat.event.UserSingleAttriteEditEvent;
import com.social.happychat.ui.home.fragment.TrendFragment;

import org.greenrobot.eventbus.EventBus;

/**
 * @author Administrator
 * @date 2019/7/12 0012
 * @description:
 */
public class MyTrendActiviy extends BaseCookieActivity {
    ActivityMyTrendBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_trend);
        ImmersionBar.with(activity)
                .statusBarDarkFont(true, 0.2f)
                .keyboardEnable(true)
                .init();
        ImmersionBar.setTitleBar(this, binding.titlebar);
        initView();
        binding.vpClose.setOnClickListener(new PerfectClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                KeyboardUtils.hideSoftInput(activity);
                finish();
            }
        });



    }

    private void initView() {
        loadRootFragment(R.id.content, TrendFragment.newInstance(1));
    }

    public static void action(Context context){
        Intent intent = new Intent(context, MyTrendActiviy.class);
        context.startActivity(intent);
    }



}
