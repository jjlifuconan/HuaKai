package com.social.happychat.ui.login;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.netease.nimlib.sdk.AbortableFuture;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.social.basecommon.activity.BaseActivity;
import com.social.basecommon.util.KeyboardUtils;
import com.social.basecommon.util.PerfectClickListener;
import com.social.basecommon.util.SPUtils;
import com.social.basecommon.util.ToastUtil;
import com.social.happychat.R;
import com.social.happychat.bean.BaseBean;
import com.social.happychat.constant.Constant;
import com.social.happychat.databinding.ActivityWelcomeBinding;
import com.social.happychat.http.HttpClient;
import com.social.happychat.im.DemoCache;
import com.social.happychat.im.IMConstant;
import com.social.happychat.im.IMImpl;
import com.social.happychat.im.IMUtils;
import com.social.happychat.ui.login.bean.UserBean;
import com.social.happychat.ui.login.bean.WechatUserBean;
import com.social.happychat.ui.main.MainActivity;

import java.util.HashMap;
import java.util.Map;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.wechat.friends.Wechat;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
        setListener();
    }

    private void setListener() {
        binding.imgPhone.setOnClickListener(new PerfectClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                startActivity(new Intent(activity, LoginActivity.class));
            }
        });
        binding.tomain.setOnClickListener(new PerfectClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                Platform plat = ShareSDK.getPlatform(Wechat.NAME);
//                if (plat.isAuthValid()) {
//                    plat.removeAccount(true);
//                    return;
//                }
//                plat.removeAccount(true); //移除授权状态和本地缓存，下次授权会重新授权
//                plat.SSOSetting(false); //SSO授权，传false默认是客户端授权，没有客户端授权或者不支持客户端授权会跳web授权
                plat.setPlatformActionListener(new PlatformActionListener() {
                    @Override
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                        binding.tomain.post(new Runnable() {
                            @Override
                            public void run() {
                                WechatUserBean wechatUserBean = new WechatUserBean(hashMap);
                                SPUtils.saveObject(activity, Constant.SP_HAPPY_CHAT, Constant.PLATFORM_WECHAT_USER_INFO, wechatUserBean);
                                startActivity(new Intent(activity, MainActivity.class));
                                finish();
                            }
                        });
                    }

                    @Override
                    public void onError(Platform platform, int i, Throwable throwable) {

                    }

                    @Override
                    public void onCancel(Platform platform, int i) {

                    }
                });//授权回调监听，监听oncomplete，onerror，oncancel三种状态
//                if(plat.isClientValid()){
                    //判断是否存在授权凭条的客户端，true是有客户端，false是无
//                }
//                if(plat.isAuthValid()){
                    //判断是否已经存在授权状态，可以根据自己的登录逻辑设置
//                    Toast.makeText(activity, "已经授权过了", 0).show();
//                    return;
//                }
                plat.showUser(null);    //要数据不要功能，主要体现在不会重复出现授权界面

            }
        });
    }


    private void playVideo() {
        UserBean userBean = SPUtils.getObject(activity, Constant.SP_HAPPY_CHAT, Constant.PLATFORM_HAPPYCHAT_USER_INFO, UserBean.class);
        if(userBean != null){
            if (!TextUtils.isEmpty(DemoCache.getAccount())) {
                //已有IM登录信息，直接进主页
                startActivity(new Intent(activity, MainActivity.class));
                finish();
                return;
            }else{
                handleIMAcount(userBean);
            }
        }
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

    /**
     * im账户注册或登录
     */
    private void handleIMAcount(UserBean userBean){
        //本地没有IM信息
        if(userBean.isOpenIm()){
            //注册过，直接登录IM
            loginRequest = new IMUtils().login(activity, userBean.getUserMobile(), IMConstant.IM_TOKEN, new IMImpl.IMLoginImpl() {
                @Override
                public void success() {
                    loginRequest = null;
                }

                @Override
                public void fail() {
                    loginRequest = null;
                }

                @Override
                public void exception() {
                    loginRequest = null;
                }
            });
        }else{
            //没注册过，注册IM
            new IMUtils().register(activity, userBean.getUserMobile(), userBean.getNickName(), IMConstant.IM_TOKEN, new IMImpl.IMResisterImpl() {
                @Override
                public void success() {
                    updateIsOpenIm(userBean);
                }

                @Override
                public void failed() {

                }
            });
        }
    }


    /**
     * 掉保存资料接口更新 isopenim字段
     */
    public void updateIsOpenIm(UserBean userBean){
        KeyboardUtils.hideSoftInput(activity);
        Map map = new HashMap();
        map.put("isOpenIm", 1);

        Subscription subscription = HttpClient.Builder.getRealServer().modifyUser(com.social.happychat.util.RequestBody.as(map))
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseBean>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(BaseBean baseBean) {
                        if (baseBean.isValid()) {
                            userBean.setIsOpenIm(1);
                            SPUtils.saveObject(activity, Constant.SP_HAPPY_CHAT, Constant.PLATFORM_HAPPYCHAT_USER_INFO, userBean);
                            loginRequest = new IMUtils().login(activity, userBean.getUserMobile(), IMConstant.IM_TOKEN, new IMImpl.IMLoginImpl() {
                                @Override
                                public void success() {
                                    loginRequest = null;
                                }

                                @Override
                                public void fail() {
                                    loginRequest = null;
                                }

                                @Override
                                public void exception() {
                                    loginRequest = null;
                                }
                            });
                        } else {
                            ToastUtil.show(activity, baseBean.getMsg());
                        }
                    }
                });
        addSubscription(subscription);

    }
}
