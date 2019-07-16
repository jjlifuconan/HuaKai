package com.social.happychat.ui.login;

import android.Manifest;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.netease.nimlib.sdk.AbortableFuture;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.social.basecommon.activity.BaseActivity;
import com.social.basecommon.util.KeyboardUtils;
import com.social.basecommon.util.SPUtils;
import com.social.basecommon.util.ToastUtil;
import com.social.happychat.R;
import com.social.happychat.bean.BaseBean;
import com.social.happychat.constant.Constant;
import com.social.happychat.databinding.ActivityAdvertiseBinding;
import com.social.happychat.http.HttpClient;
import com.social.happychat.im.DemoCache;
import com.social.happychat.im.IMConstant;
import com.social.happychat.im.IMImpl;
import com.social.happychat.im.IMUtils;
import com.social.happychat.ui.login.bean.UserBean;
import com.social.happychat.ui.main.MainActivity;

import java.util.HashMap;
import java.util.Map;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@RuntimePermissions
public class AdvertiseActivity extends BaseActivity {
    ActivityAdvertiseBinding binding;
    private AbortableFuture<LoginInfo> loginRequest;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_advertise);
        AdvertiseActivityPermissionsDispatcher.toWelcomeWithPermissionCheck(AdvertiseActivity.this);

    }


    @NeedsPermission({Manifest.permission.ACCESS_COARSE_LOCATION ,Manifest.permission.ACCESS_FINE_LOCATION ,Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE, Manifest.permission.RECORD_AUDIO})
    void toWelcome() {
        UserBean userBean = SPUtils.getObject(activity, Constant.SP_HAPPY_CHAT, Constant.PLATFORM_HAPPYCHAT_USER_INFO, UserBean.class);
        binding.advertise.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(userBean != null){
                    if (!TextUtils.isEmpty(DemoCache.getAccount())) {
                        //已有IM登录信息，直接进主页
                        //跳转欢迎页  让他注册或者登录
                        startActivity(new Intent(activity, MainActivity.class));
                        finish();
                    }else{
                        handleIMAcount(userBean);
                    }
                }else{
                    //跳转欢迎页  让他注册或者登录
                    startActivity(new Intent(activity, WelcomeActivity.class));
                    finish();

                }
            }
        }, 1000);

    }

    /**
     * im账户注册或登录
     */
    private void handleIMAcount(UserBean userBean){
        //本地没有IM信息
        if(userBean.isOpenIm()){
            //注册过，直接登录IM
            loginRequest = new IMUtils().login(activity, userBean.getUserMobile(), new IMImpl.IMLoginImpl() {
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
            new IMUtils().register(activity, userBean.getUserMobile(), new IMImpl.IMResisterImpl() {
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
                            loginRequest = new IMUtils().login(activity, userBean.getUserMobile(), new IMImpl.IMLoginImpl() {
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



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        AdvertiseActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @OnShowRationale({Manifest.permission.ACCESS_COARSE_LOCATION ,Manifest.permission.ACCESS_FINE_LOCATION ,Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE, Manifest.permission.RECORD_AUDIO})
    public void showRationaleForCamera(PermissionRequest request) {
        showRationaleDialog(R.string.permission_camera_rationale, request);
    }

    @OnPermissionDenied({Manifest.permission.ACCESS_COARSE_LOCATION ,Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE, Manifest.permission.RECORD_AUDIO})
    public void onCameraDenied() {
        Toast.makeText(activity, R.string.permission_camera_denied, Toast.LENGTH_SHORT).show();
    }

    @OnNeverAskAgain({Manifest.permission.ACCESS_COARSE_LOCATION ,Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE, Manifest.permission.RECORD_AUDIO})
    public void onCameraNeverAskAgain() {
        Toast.makeText(activity, R.string.permission_camera_denied, Toast.LENGTH_SHORT).show();
    }


    private void showRationaleDialog(@StringRes int messageResId, final PermissionRequest request) {
        new MaterialDialog.Builder(activity).positiveText("允许").negativeText("拒绝")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        request.proceed();
                    }
                }).onNegative(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                request.cancel();
            }
        }).content(messageResId)
                .show();
    }


}
