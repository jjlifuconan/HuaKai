package com.social.happychat.ui.login;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;

import com.gyf.immersionbar.ImmersionBar;
import com.social.basecommon.activity.BaseActivity;
import com.social.basecommon.util.KeyboardUtils;
import com.social.basecommon.util.ToastUtil;
import com.social.happychat.R;
import com.social.happychat.bean.BaseBean;
import com.social.happychat.databinding.ActivityRegisterBinding;
import com.social.happychat.http.HttpClient;
import com.social.happychat.ui.main.MainActivity;
import com.social.happychat.util.RequestBody;
import com.social.happychat.widget.SexBox;


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
public class RegisterActivity extends BaseActivity {
    ActivityRegisterBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        ImmersionBar.with(activity)
                .statusBarDarkFont(true, 0.2f)
                .init();
        ImmersionBar.setTitleBar(this, binding.titlebar);
        setListener();


    }

    private void setListener() {
        binding.vpClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KeyboardUtils.hideSoftInput(activity);
                finish();
            }
        });

        binding.sex.setListener(new SexBox.OnChooseListener() {
            @Override
            public void onChoose() {
                if(!TextUtils.isEmpty(binding.edtPhone.getText().toString()) && !TextUtils.isEmpty(binding.edtPassword.getText().toString())
                        && !TextUtils.isEmpty(binding.edtYzCode.getText().toString()) && binding.edtPassword.getText().toString().length() >= 6
                        && binding.sex.getstatu() != 0){
                    binding.register.setEnabled(true);
                }else{
                    binding.register.setEnabled(false);
                }
            }
        });

        MyTextWatcher textWatcher = new MyTextWatcher();

        binding.edtPhone.addTextChangedListener(textWatcher);
        binding.edtPassword.addTextChangedListener(textWatcher);
        binding.edtYzCode.addTextChangedListener(textWatcher);

        binding.tvGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(binding.edtPhone.getText().toString())||  !binding.edtPhone.getText().toString().startsWith("1")|| binding.edtPhone.getText().toString().length() < 11) {
                    ToastUtil.showShort(activity, "手机号码格式不正确");
                    return;
                }
                Map map = new HashMap();
                map.put("mobile",binding.edtPhone.getText().toString());
                map.put("type","1");
                sendLoginCodeMessage(map);
            }
        });

        binding.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                map.put("userSex",binding.sex.getstatu());
                map.put("password",binding.edtPassword.getText().toString());
                map.put("verificationCode",binding.edtYzCode.getText().toString());
                submitRegister(map);
            }
        });
    }

    /**
     * 提交注册信息
     */
    private void submitRegister(Map params) {
        Subscription subscription = HttpClient.Builder.getRealServer().register(RequestBody.as(params))
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseBean>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        int i = 0;
                    }

                    @Override
                    public void onNext(BaseBean baseBean) {
                        if(baseBean.isValid()){
                            Intent intent = new Intent(activity, MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }else{
                            ToastUtil.show(activity, baseBean.getMsg());
                        }
                    }
                });
        addSubscription(subscription);
    }



    /**
     * 获取验证码
     */
    public void sendLoginCodeMessage(Map params) {
        Subscription subscription = HttpClient.Builder.getRealServer().getValidateCode(RequestBody.as(params))
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtil.showShort(activity,"验证码获取失败");
                    }

                    @Override
                    public void onNext(Object object) {
                        //验证码按钮倒计时1min
//                        ToastUtil.showShort(activity,"验证码获取失败");
                        timer.start();
                    }
                });
        addSubscription(subscription);
    }

    private class MyTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if(!TextUtils.isEmpty(binding.edtPhone.getText().toString()) && !TextUtils.isEmpty(binding.edtPassword.getText().toString())
                    && !TextUtils.isEmpty(binding.edtYzCode.getText().toString()) && binding.edtPassword.getText().toString().length() >= 6
                    && binding.sex.getstatu() != 0){
                binding.register.setEnabled(true);
            }else{
                binding.register.setEnabled(false);
            }
        }
    }

    private CountDownTimer timer = new CountDownTimer(60000, 1000) {

        @Override
        public void onTick(long millisUntilFinished) {
            binding.tvGetCode.setEnabled(false);
            binding.tvGetCode.setTextColor(ContextCompat.getColor(activity, R.color.color_bfbfbf));
            binding.tvGetCode.setText(millisUntilFinished / 1000 + "S");
        }

        @Override
        public void onFinish() {
            binding.tvGetCode.setEnabled(true);
            binding.tvGetCode.setTextColor(ContextCompat.getColor(activity, R.color.colorPrimary));
            binding.tvGetCode.setText("获取验证码");
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}
