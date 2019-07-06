package com.social.happychat.ui.mine.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.social.basecommon.fragment.BaseFragment;
import com.social.basecommon.util.GlideApp;
import com.social.basecommon.util.ImageLoadUtil;
import com.social.basecommon.util.SPUtils;
import com.social.happychat.R;
import com.social.happychat.constant.Constant;
import com.social.happychat.databinding.FragmentMineBinding;
import com.social.happychat.http.HttpClient;
import com.social.happychat.ui.login.LoginActivity;
import com.social.happychat.ui.login.bean.WechatUserBean;
import com.social.happychat.ui.main.MainActivity;
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
public class MineFragment extends BaseFragment<FragmentMineBinding> {

    public static MineFragment newInstance() {

        Bundle args = new Bundle();

        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int setContent() {
        return R.layout.fragment_mine;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
//        ImmersionBar.setTitleBar(this, binding.headerLayout);
        showContentView();
        WechatUserBean wechatUserBean = SPUtils.getObject(activity, Constant.SP_HAPPY_CHAT, Constant.WECHAT_USER_INFO, WechatUserBean.class);
        if(wechatUserBean != null){
//            GlideApp.with(activity).load(R.mipmap.file_paths_public).into(binding.ivHead);
            ImageLoadUtil.displayCircle(binding.ivHead, wechatUserBean.getHeadimgurl());
            binding.tvNickname.setText(wechatUserBean.getNickname());
        }

        binding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MaterialDialog.Builder(activity).positiveText("确定").negativeText("取消")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                String userId = "";
                                Map map = new HashMap();
                                map.put("userId",userId);
                                logout(map);
                            }
                        }).content("确定退出登录吗？")
                        .show();
            }
        });
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
                    }

                    @Override
                    public void onNext(Object object) {
                        Intent intent = new Intent(activity, LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });
        addSubscription(subscription);
    }
}
