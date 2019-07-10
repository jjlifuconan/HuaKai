package com.social.happychat.ui.login.cookie;

import android.content.Intent;

import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.auth.AuthService;
import com.social.basecommon.util.SPUtils;
import com.social.happychat.app.HappyChatApplication;
import com.social.happychat.constant.Constant;
import com.social.happychat.im.LogoutHelper;
import com.social.happychat.im.Preferences;
import com.social.happychat.ui.login.LoginActivity;
import com.social.happychat.ui.login.bean.UserBean;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.wechat.friends.Wechat;

/**
 * @author Administrator
 * @date 2019/7/7
 * @description:
 */
public class LoginCookie {
    public static void clearLoginCookie(){
        //清楚保存的SP
        SPUtils.clearAllByFileName(HappyChatApplication.getInstance(), Constant.SP_HAPPY_CHAT);
        //退出微信授权
        Platform plat = ShareSDK.getPlatform(Wechat.NAME);
        if (plat.isAuthValid()) {
            plat.removeAccount(true);
        }
        //退出网易IM
        Preferences.saveUserToken("");
        // 清理缓存&注销监听
        LogoutHelper.logout();
        NIMClient.getService(AuthService.class).logout();
    }
}
