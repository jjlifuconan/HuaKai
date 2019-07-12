package com.social.happychat.ui.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;

import com.gyf.immersionbar.ImmersionBar;
import com.social.basecommon.activity.BaseActivity;
import com.social.basecommon.util.KeyboardUtils;
import com.social.basecommon.util.PerfectClickListener;
import com.social.happychat.R;
import com.social.happychat.databinding.ActivityUserSingleattrEditBinding;
import com.social.happychat.event.UserSingleAttriteEditEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * @author Administrator
 * @date 2019/7/12 0012
 * @description:
 */
public class UserSingleAttributeEditActiviy extends BaseActivity {
    ActivityUserSingleattrEditBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_singleattr_edit);
        ImmersionBar.with(activity)
                .statusBarDarkFont(true, 0.2f)
                .keyboardEnable(true)
                .init();
        ImmersionBar.setTitleBar(this, binding.titlebar);
        initView();
        binding.vpClose.setOnClickListener(new PerfectClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                KeyboardUtils.hideSoftInput(activity);
                finish();
            }
        });
        if(!getIntent().getBooleanExtra("supportNullSave", false)){
            binding.edtAttr.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    if(TextUtils.isEmpty(editable.toString().trim())){
                        binding.save.setEnabled(false);
                    }else{
                        binding.save.setEnabled(true);
                    }

                }
            });
        }

        binding.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                KeyboardUtils.hideSoftInput(activity);
                EventBus.getDefault().post(new UserSingleAttriteEditEvent(getIntent().getStringExtra("attrName"), binding.edtAttr.getText().toString().trim()));
                finish();
            }
        });


    }

    private void initView() {
        binding.title.setText(getIntent().getStringExtra("attrName"));
        binding.edtAttr.setHint(getIntent().getStringExtra("attrName"));
        binding.edtAttr.setText(getIntent().getStringExtra("value"));
        if(!getIntent().getBooleanExtra("supportNullSave", false)){
            if(TextUtils.isEmpty(getIntent().getStringExtra("value"))){
                binding.save.setEnabled(false);
            }else{
                binding.save.setEnabled(true);
            }
        }
        if(getIntent().getIntExtra("minLines", 0) != 0){
            binding.edtAttr.setMinLines(getIntent().getIntExtra("minLines", 0));
        }
        binding.edtAttr.setFilters(new InputFilter[]{new InputFilter.LengthFilter((getIntent().getIntExtra("maxLength", 0)))});
        binding.vpInputlayout.setCounterMaxLength(getIntent().getIntExtra("maxLength", 0));
        binding.vpInputlayout.setCounterEnabled(true);
        binding.vpInputlayout.setHintEnabled(false);
    }

    public static void action(Context context, String attrName, int maxLength, String value){
        Intent intent = new Intent(context, UserSingleAttributeEditActiviy.class);
        intent.putExtra("attrName", attrName);
        intent.putExtra("maxLength", maxLength);
        intent.putExtra("value", value);
        context.startActivity(intent);
    }

    public static void action(Context context, String attrName, int maxLength, int minLines, String value, boolean supportNullSave){
        Intent intent = new Intent(context, UserSingleAttributeEditActiviy.class);
        intent.putExtra("attrName", attrName);
        intent.putExtra("maxLength", maxLength);
        intent.putExtra("minLines", minLines);
        intent.putExtra("value", value);
        intent.putExtra("supportNullSave", supportNullSave);
        context.startActivity(intent);
    }

}
