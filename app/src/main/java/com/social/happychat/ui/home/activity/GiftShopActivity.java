package com.social.happychat.ui.home.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.gyf.immersionbar.ImmersionBar;
import com.social.happychat.R;
import com.social.happychat.base.BaseCookieActivity;
import com.social.happychat.databinding.ActivityGiftShopBinding;
import com.social.happychat.ui.home.fragment.GiftReceiveListFragment;
import com.social.happychat.ui.home.fragment.GiftSendListFragment;
import com.social.happychat.ui.home.fragment.GiftShopListFragment;


/**
 * 礼物商店
 */
public class GiftShopActivity extends BaseCookieActivity {
    ActivityGiftShopBinding binding;
    public static final int TYPE_SHOP = 0;
    public static final int TYPE_RECEIVE = 1;
    public static final int TYPE_SEND = 2;

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
        if(getIntent().getIntExtra("type", GiftShopActivity.TYPE_SHOP) == GiftShopActivity.TYPE_SHOP){
            binding.title.setText("礼物商店");
            loadRootFragment(R.id.content, GiftShopListFragment.newInstance());
        }else if(getIntent().getIntExtra("type", GiftShopActivity.TYPE_SHOP) == GiftShopActivity.TYPE_RECEIVE){
            binding.title.setText("收到的礼物");
            loadRootFragment(R.id.content, GiftReceiveListFragment.newInstance());
        }else if(getIntent().getIntExtra("type", GiftShopActivity.TYPE_SHOP) == GiftShopActivity.TYPE_SEND){
            binding.title.setText("送出的礼物");
            loadRootFragment(R.id.content, GiftSendListFragment.newInstance());
        }
    }

    public static void action(Context context, int type){
        Intent intent = new Intent(context, GiftShopActivity.class);
        intent.putExtra("type",type);
        context.startActivity(intent);
    }
}
