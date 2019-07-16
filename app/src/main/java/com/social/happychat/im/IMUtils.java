package com.social.happychat.im;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.netease.nim.uikit.api.NimUIKit;
import com.netease.nim.uikit.common.ToastHelper;
import com.netease.nim.uikit.common.util.string.MD5;
import com.netease.nim.uikit.common.util.sys.NetworkUtil;
import com.netease.nimlib.sdk.AbortableFuture;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.social.basecommon.util.SPUtils;
import com.social.basecommon.util.ToastUtil;
import com.social.happychat.R;
import com.social.happychat.bean.BaseBean;
import com.social.happychat.constant.Constant;
import com.social.happychat.event.RefreshMineEvent;
import com.social.happychat.http.HttpClient;
import com.social.happychat.ui.login.LoginActivity;
import com.social.happychat.ui.login.bean.UserBean;
import com.social.happychat.ui.main.MainActivity;
import com.social.happychat.util.RequestBody;

import org.greenrobot.eventbus.EventBus;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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

    public void register(Context context, String account, IMImpl.IMResisterImpl listener){
        if (!NetworkUtil.isNetAvailable(context)) {
            ToastHelper.showToast(context, R.string.network_is_not_available);
            return;
        }
        String curTime = String.valueOf((new Date()).getTime() / 1000L);
        String appSecret = "1c0ebc97514d";
        String nonce = "12345";
        String contentType = "application/x-www-form-urlencoded;charset=utf-8";
        String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce ,curTime);//参考 计算CheckSum的java代码
        HttpClient.Builder.getNeteaseServer().createIM(readAppKey(), contentType,nonce,curTime,checkSum,account, MD5.getStringMD5(IMConstant.IM_TOKEN))
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegisterImBean>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG,"IM注册失败");
                        ToastHelper.showToast(context, e.getMessage());
                        listener.failed();
                    }

                    @Override
                    public void onNext(RegisterImBean registerImBean) {
                        if(registerImBean.code == 200){
                            Log.e(TAG,"IM注册成功");
                            listener.success();
                        }else{
                            Log.e(TAG,"IM注册失败");
                            listener.failed();
                        }
                    }
                });
    }

    public AbortableFuture<LoginInfo> login(Context context, String account, IMImpl.IMLoginImpl listener){
        Log.e("FLJ", "IM login begin");
        AbortableFuture<LoginInfo> loginRequest = NimUIKit.login(new LoginInfo(account, MD5.getStringMD5(IMConstant.IM_TOKEN)), new RequestCallback<LoginInfo>() {
            @Override
            public void onSuccess(LoginInfo param) {
                Log.e("FLJ", "IM login success");
                DemoCache.setAccount(account);
                saveLoginInfo(account, MD5.getStringMD5(IMConstant.IM_TOKEN));
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

    private String readAppKey() {
        try {
            ApplicationInfo appInfo = DemoCache.getContext().
                    getPackageManager().
                    getApplicationInfo(DemoCache.getContext().getPackageName(), PackageManager.GET_META_DATA);
            if (appInfo != null) {
                return appInfo.metaData.getString("com.netease.nim.appKey");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
