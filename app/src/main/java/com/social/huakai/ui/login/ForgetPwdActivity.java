package com.social.huakai.ui.login;

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
import com.social.huakai.R;
import com.social.huakai.databinding.ActivityForgetPwdBinding;
import com.social.huakai.databinding.ActivityRegisterBinding;
import com.social.huakai.http.HttpClient;
import com.social.huakai.ui.home.bean.NeteaseList;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Administrator
 * @date 2019/7/3 0003
 * @description:
 */
public class ForgetPwdActivity extends BaseActivity {
    ActivityForgetPwdBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_forget_pwd);
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

        MyTextWatcher textWatcher = new MyTextWatcher();

        binding.edtPhone.addTextChangedListener(textWatcher);
        binding.edtPassword.addTextChangedListener(textWatcher);
        binding.edtConfirmPassword.addTextChangedListener(textWatcher);
        binding.edtYzCode.addTextChangedListener(textWatcher);

        binding.tvGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(binding.edtPhone.getText().toString())||  !binding.edtPhone.getText().toString().startsWith("1")|| binding.edtPhone.getText().toString().length() < 11) {
                    ToastUtil.showShort(activity, "手机号码格式不正确");
                    return;
                }
                sendLoginCodeMessage();
            }
        });

        binding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(binding.edtPhone.getText().toString())||  !binding.edtPhone.getText().toString().startsWith("1")|| binding.edtPhone.getText().toString().length() < 11) {
                    ToastUtil.showShort(activity, "手机号码格式不正确");
                    return;
                }
                if (!TextUtils.equals(binding.edtPassword.getText().toString(),binding.edtConfirmPassword.getText().toString())) {
                    ToastUtil.showShort(activity, "两次输入的密码不一致");
                    return;
                }
                submitModify();
            }
        });
    }

    /**
     * 提交修改信息
     */
    private void submitModify() {
        Subscription subscription = HttpClient.Builder.getNeteaseServer().getNeteaseList(1, 10)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(Object object) {
                    }
                });
        addSubscription(subscription);
    }


    /**
     * 获取验证码
     */
    public void sendLoginCodeMessage() {
        Subscription subscription = HttpClient.Builder.getNeteaseServer().getNeteaseList(1, 10)
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
            if(!TextUtils.isEmpty(binding.edtPhone.getText().toString()) && !TextUtils.isEmpty(binding.edtConfirmPassword.getText().toString())
                    && !TextUtils.isEmpty(binding.edtPassword.getText().toString()) && !TextUtils.isEmpty(binding.edtYzCode.getText().toString())){
                binding.submit.setEnabled(true);
            }else{
                binding.submit.setEnabled(false);
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
