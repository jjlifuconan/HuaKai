package com.social.happychat.ui.login;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;

import com.gyf.immersionbar.ImmersionBar;
import com.netease.nimlib.sdk.AbortableFuture;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.social.basecommon.activity.BaseActivity;
import com.social.basecommon.util.KeyboardUtils;
import com.social.basecommon.util.PerfectClickListener;
import com.social.basecommon.util.SPUtils;
import com.social.basecommon.util.ToastUtil;
import com.social.happychat.R;
import com.social.happychat.constant.Constant;
import com.social.happychat.databinding.ActivityLoginBinding;
import com.social.happychat.http.HttpClient;
import com.social.happychat.im.IMConstant;
import com.social.happychat.im.IMImpl;
import com.social.happychat.im.IMUtils;
import com.social.happychat.ui.login.bean.UserBean;
import com.social.happychat.util.RequestBody;

import java.util.HashMap;
import java.util.Map;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Administrator
 * @date 2019/7/3 0003
 * @description:
 */
public class LoginActivity extends BaseActivity {
    ActivityLoginBinding binding;
    private AbortableFuture<LoginInfo> loginRequest;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        ImmersionBar.with(activity)
                .statusBarDarkFont(true, 0.2f)
                .init();
        ImmersionBar.setTitleBar(this, binding.titlebar);
        setListener();


    }

    private void setListener() {
        binding.vpClose.setOnClickListener(new PerfectClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                KeyboardUtils.hideSoftInput(activity);
                finish();
            }
        });

        binding.tvRegister.setOnClickListener(new PerfectClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                startActivity(new Intent(activity, RegisterActivity.class));
            }
        });

        binding.tvForget.setOnClickListener(new PerfectClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                startActivity(new Intent(activity, ForgetPwdActivity.class));
            }
        });

        binding.login.setOnClickListener(new PerfectClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                if (TextUtils.isEmpty(binding.edtPhone.getText().toString())||  !binding.edtPhone.getText().toString().startsWith("1")|| binding.edtPhone.getText().toString().length() < 11) {
                    ToastUtil.showShort(activity, "手机号码格式不正确");
                    return;
                }
                if (binding.edtPassword.getText().toString().length() < 6) {
                    ToastUtil.showShort(activity, "密码至少6位数");
                    return;
                }
                Map map = new HashMap();
                map.put("loginName",binding.edtPhone.getText().toString());
                map.put("loginType","1");
                map.put("password",binding.edtPassword.getText().toString());
                submitLogin(map);
            }

        });

        MyTextWatcher textWatcher = new MyTextWatcher();

        binding.edtPhone.addTextChangedListener(textWatcher);
        binding.edtPassword.addTextChangedListener(textWatcher);
    }

    /**
     * 提交登录信息
     */
    private void submitLogin(Map params) {
        Subscription subscription = HttpClient.Builder.getRealServer().login(RequestBody.as(params))
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserBean>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(UserBean userBean) {
                        if(userBean.isValid()){
                            SPUtils.saveObject(activity, Constant.SP_HAPPY_CHAT, Constant.PLATFORM_HAPPYCHAT_USER_INFO, userBean.getData());
                            handleIMAcount(userBean.getData());
                        }else{
                            ToastUtil.show(activity, userBean.getMsg());
                        }
                    }
                });
        addSubscription(subscription);
    }

    class MyTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if(!TextUtils.isEmpty(binding.edtPhone.getText().toString())
                    && !TextUtils.isEmpty(binding.edtPassword.getText().toString()) && binding.edtPassword.getText().toString().length() >= 6){
                binding.login.setEnabled(true);
            }else{
                binding.login.setEnabled(false);
            }
        }
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
                }

                @Override
                public void failed() {

                }
            });
        }
    }
}
