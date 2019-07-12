package com.social.happychat.bean;

import android.databinding.BaseObservable;
import android.text.TextUtils;

import java.io.Serializable;

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
        return TextUtils.equals("1", code);
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
