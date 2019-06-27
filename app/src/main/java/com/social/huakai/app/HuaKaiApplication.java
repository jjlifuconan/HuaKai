package com.social.huakai.app;

import android.support.multidex.MultiDexApplication;

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
    }
}
