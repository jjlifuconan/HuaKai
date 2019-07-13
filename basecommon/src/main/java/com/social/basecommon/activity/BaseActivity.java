package com.social.basecommon.activity;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.social.basecommon.event.LoginExpireEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import me.yokeyword.fragmentation.SupportActivity;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * @author Administrator
 * @date 2019/6/26 0026
 * @description:
 */
public class BaseActivity extends SupportActivity {
    protected Activity activity;
    private CompositeSubscription mCompositeSubscription;
    public static final String TAG = "FLJ";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        EventBus.getDefault().register(this);
    }

    public void addSubscription(Subscription s) {
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }
        this.mCompositeSubscription.add(s);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        if (this.mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            this.mCompositeSubscription.unsubscribe();
        }
    }

    /**
     * 注册了eventbus必须添加一个@Subscriber标记的方法
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(LoginExpireEvent event) {
        new MaterialDialog.Builder(activity).positiveText("确定").onPositive(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                logout();
            }
        }).content("当前账号在其他设备登录，请重新登录！").cancelable(false)
                .show();
        EventBus.getDefault().cancelEventDelivery(event);
    }

    protected void logout(){

    }

}
