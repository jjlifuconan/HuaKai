package com.social.happychat.im;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.netease.nim.uikit.api.NimUIKit;
import com.netease.nim.uikit.common.ToastHelper;
import com.netease.nim.uikit.common.util.string.MD5;
import com.netease.nim.uikit.common.util.sys.NetworkUtil;
import com.netease.nimlib.sdk.AbortableFuture;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.social.happychat.R;
import com.social.happychat.ui.login.LoginActivity;
import com.social.happychat.ui.main.MainActivity;

/**
 * @author Administrator
 * @date 2019/7/10 0010
 * @description:
 */
public class IMUtils {
    /**
     * im 注册
     * @param context
     * @param account
     * @param nickName
     * @param password
     */
    public static final String TAG = "FLJ";

    public void register(Context context, String account, String nickName, String password, IMImpl.IMResisterImpl listener){
        if (!NetworkUtil.isNetAvailable(context)) {
            ToastHelper.showToast(context, R.string.network_is_not_available);
            return;
        }
        ContactHttpClient.getInstance().register(account, nickName, password, new ContactHttpClient.ContactHttpCallback<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
//                ToastHelper.showToast(context, "注册成功");
                Log.e(TAG,"IM注册成功");
                listener.success();
            }

            @Override
            public void onFailed(int code, String errorMsg) {
//                ToastHelper.showToast(context, "注册失败");
                if(code == 414){
                    Log.e(TAG,"IM已注册");
                    listener.success();
                }else{
                    Log.e(TAG,"IM注册失败");
                    ToastHelper.showToast(context, errorMsg);
                    listener.failed();
                }
            }
        });
    }

    public AbortableFuture<LoginInfo> login(Context context, String account, String token, IMImpl.IMLoginImpl listener){
        AbortableFuture<LoginInfo> loginRequest = NimUIKit.login(new LoginInfo(account, MD5.getStringMD5(token)), new RequestCallback<LoginInfo>() {
            @Override
            public void onSuccess(LoginInfo param) {
                Log.e("FLJ", "IM login success");
                DemoCache.setAccount(account);
                saveLoginInfo(account, token);
                listener.success();
                // 初始化消息提醒配置
//                        initNotificationConfig();
                // 进入主界面
                Intent intent = new Intent(context, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }

            @Override
            public void onFailed(int code) {
                listener.fail();
                Log.e("FLJ", "IM login onFailed");
                if (code == 302 || code == 404) {
                    ToastHelper.showToast(context, "帐号或密码错误");
                } else {
                    ToastHelper.showToast(context, "登录失败: " + code);
                }
            }

            @Override
            public void onException(Throwable exception) {
                ToastHelper.showToast(context, "无效输入");
                Log.e("FLJ", "IM login onException");
                listener.exception();
            }
        });
        return loginRequest;
    }

    private void saveLoginInfo(final String account, final String token) {
        Preferences.saveUserAccount(account);
        Preferences.saveUserToken(token);
    }
}
