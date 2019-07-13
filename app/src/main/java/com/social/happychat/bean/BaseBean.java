package com.social.happychat.bean;

import android.databinding.BaseObservable;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.social.basecommon.event.LoginExpireEvent;
import com.social.basecommon.util.SPUtils;
import com.social.happychat.app.HappyChatApplication;
import com.social.happychat.constant.Constant;
import com.social.happychat.ui.login.bean.UserBean;
import com.social.happychat.ui.login.cookie.LoginCookie;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 * @date 2019/7/7
 * @description:
 */
public class BaseBean<T> extends BaseObservable implements Serializable {
    private String code;
    private String msg;
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isValid(){
        if(TextUtils.equals("1", code)){
            return true;
        }else if(TextUtils.equals("0003", code)){
            EventBus.getDefault().post(new LoginExpireEvent());
            return false;
        }else{
            return false;
        }
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
