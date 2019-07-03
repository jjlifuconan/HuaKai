package com.social.huakai.ui.login;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;

import com.gyf.immersionbar.ImmersionBar;
import com.social.basecommon.activity.BaseActivity;
import com.social.huakai.R;
import com.social.huakai.databinding.ActivityLoginBinding;
import com.social.huakai.databinding.ActivityRegisterBinding;

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
                finish();
            }
        });

        MyTextWatcher textWatcher = new MyTextWatcher();

        binding.edtPhone.addTextChangedListener(textWatcher);
        binding.edtPassword.addTextChangedListener(textWatcher);
        binding.edtYzCode.addTextChangedListener(textWatcher);
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
            if(!TextUtils.isEmpty(binding.edtPhone.getText().toString()) && !TextUtils.isEmpty(binding.edtPassword.getText().toString()) && !TextUtils.isEmpty(binding.edtYzCode.getText().toString())){
                binding.register.setEnabled(true);
            }else{
                binding.register.setEnabled(false);
            }
        }
    }
}
