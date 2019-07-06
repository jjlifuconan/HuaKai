package com.social.huakai.app;

import android.support.multidex.MultiDexApplication;

import com.example.http.HttpUtils;
import com.mob.MobSDK;
import com.netease.nim.uikit.api.NimUIKit;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.netease.nimlib.sdk.util.NIMUtil;
import com.social.basecommon.util.DebugUtil;
import com.social.huakai.im.DemoCache;
import com.social.huakai.im.NimSDKOptionConfig;


/**
 * @author Administrator
 * @date 2019/6/27 0027
 * @description:
 */
public class HuaKaiApplication extends MultiDexApplication {
    private static HuaKaiApplication application;

    public static HuaKaiApplication getInstance(){
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        //sharesdk
        MobSDK.init(this);
        HttpUtils.getInstance().init(this, DebugUtil.DEBUG);
        // 初始化云信SDK
        DemoCache.setContext(this);
        NIMClient.init(this, getLoginInfo(), NimSDKOptionConfig.getSDKOptions(this));

        // 以下逻辑只在主进程初始化时执行
        if (NIMUtil.isMainProcess(this)) {
            // 在主进程中初始化UI组件，判断所属进程方法请参见demo源码。
            initUiKit();
        }
    }

    private LoginInfo getLoginInfo() {
//        String account = Preferences.getUserAccount();
////        String token = Preferences.getUserToken();
////
////        if (!TextUtils.isEmpty(account) && !TextUtils.isEmpty(token)) {
////            DemoCache.setAccount(account.toLowerCase());
////            return new LoginInfo(account, token);
////        } else {
            return null;
//        }
    }

    private void initUiKit() {
        // 初始化
        NimUIKit.init(this);

    }





}
