package com.social.happychat.ui.home.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.gyf.immersionbar.ImmersionBar;
import com.social.basecommon.activity.BaseActivity;
import com.social.happychat.R;
import com.social.happychat.databinding.ActivityGiftShopBinding;
import com.social.happychat.ui.home.fragment.GiftShopListFragment;


public class GiftShopActivity extends BaseActivity {
    ActivityGiftShopBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_gift_shop);
        ImmersionBar.with(this).init();
        ImmersionBar.setTitleBar(this, binding.titlebar);
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        loadRootFragment(R.id.content, new GiftShopListFragment());
    }

    public static void action(Context context){
        Intent intent = new Intent(context, GiftShopActivity.class);
        context.startActivity(intent);
    }
}
