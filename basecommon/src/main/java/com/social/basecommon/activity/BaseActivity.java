package com.social.basecommon.activity;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.gyf.immersionbar.ImmersionBar;
import com.social.basecommon.event.BaseEvent;

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
    public void Event(BaseEvent event) {
    }

}
