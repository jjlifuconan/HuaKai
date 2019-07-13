package com.social.happychat.base;

import android.content.Intent;

import com.social.basecommon.activity.BaseActivity;
import com.social.basecommon.util.SPUtils;
import com.social.happychat.constant.Constant;
import com.social.happychat.http.HttpClient;
import com.social.happychat.ui.login.LoginActivity;
import com.social.happychat.ui.login.bean.UserBean;
import com.social.happychat.ui.login.cookie.LoginCookie;
import com.social.happychat.util.RequestBody;

import java.util.HashMap;
import java.util.Map;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class BaseCookieActivity extends BaseActivity {


    @Override
    protected void logout() {
        UserBean userBean = SPUtils.getObject(activity, Constant.SP_HAPPY_CHAT, Constant.PLATFORM_HAPPYCHAT_USER_INFO, UserBean.class);
        int userId = userBean.getId();
        Map map = new HashMap();
        map.put("userId",userId);
        logout(map);
    }

    private void logout(Map params){
        Subscription subscription = HttpClient.Builder.getRealServer().loginout(RequestBody.as(params))
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        LoginCookie.clearLoginCookie();
                        Intent intent = new Intent(activity, LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }

                    @Override
                    public void onNext(Object object) {
                        LoginCookie.clearLoginCookie();
                        Intent intent = new Intent(activity, LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });
        addSubscription(subscription);
    }
}
