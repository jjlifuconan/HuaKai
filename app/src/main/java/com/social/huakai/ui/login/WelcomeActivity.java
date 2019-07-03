package com.social.huakai.ui.login;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.netease.nim.uikit.api.NimUIKit;
import com.netease.nim.uikit.common.ToastHelper;
import com.netease.nim.uikit.common.util.string.MD5;
import com.netease.nimlib.sdk.AbortableFuture;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.social.basecommon.activity.BaseActivity;
import com.social.huakai.R;
import com.social.huakai.databinding.ActivityLoginBinding;
import com.social.huakai.databinding.ActivityWelcomeBinding;
import com.social.huakai.im.ContactHttpClient;
import com.social.huakai.im.DemoCache;
import com.social.huakai.ui.main.MainActivity;

/**
 * @author Administrator
 * @date 2019/6/26 0026
 * @description:
 */
public class WelcomeActivity extends BaseActivity {
    ActivityWelcomeBinding binding;
    private AbortableFuture<LoginInfo> loginRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_welcome);
        playVideo();
        ContactHttpClient.getInstance().register("conanaiflj", "conanaiflj", "123456aa", new ContactHttpClient.ContactHttpCallback<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                ToastHelper.showToast(WelcomeActivity.this, "注册成功");
            }

            @Override
            public void onFailed(int code, String errorMsg) {
                ToastHelper.showToast(WelcomeActivity.this, "注册失败");
            }
        });
        setListener();
    }

    private void setListener() {
        binding.tomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginRequest = NimUIKit.login(new LoginInfo("conanaiflj", MD5.getStringMD5("123456aa")), new RequestCallback<LoginInfo>() {
                    @Override
                    public void onSuccess(LoginInfo param) {
                        Log.e("FLJ", "IM login success");
                        onLoginDone();
                        DemoCache.setAccount("conanaiflj");
//                        saveLoginInfo(account, token);
                        // 初始化消息提醒配置
//                        initNotificationConfig();
                        // 进入主界面
//                        MainActivity.start(WelcomeActivity.this, null);
                        startActivity(new Intent(activity, LoginActivity.class));
                    }

                    @Override
                    public void onFailed(int code) {
                        onLoginDone();
                        if (code == 302 || code == 404) {
                            ToastHelper.showToast(WelcomeActivity.this, "帐号或密码错误");
                        } else {
                            ToastHelper.showToast(WelcomeActivity.this, "登录失败: " + code);
                        }
                    }

                    @Override
                    public void onException(Throwable exception) {
                        ToastHelper.showToast(WelcomeActivity.this, "无效输入");
                        onLoginDone();
                    }
                });


            }
        });
    }

    private void onLoginDone() {
        loginRequest = null;
    }

    private void playVideo() {
        binding.videoview.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.loginmovie));
        binding.videoview.start();
        binding.videoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
                mp.setLooping(true);
            }
        });
    }
}
