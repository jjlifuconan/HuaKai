package com.social.happychat.ui.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.gson.GsonBuilder;
import com.gyf.immersionbar.ImmersionBar;
import com.social.basecommon.activity.BaseActivity;
import com.social.basecommon.util.KeyboardUtils;
import com.social.basecommon.util.PerfectClickListener;
import com.social.basecommon.util.SPUtils;
import com.social.basecommon.util.ToastUtil;
import com.social.happychat.R;
import com.social.happychat.bean.BaseBean;
import com.social.happychat.constant.Constant;
import com.social.happychat.databinding.ActivityMyTagBinding;
import com.social.happychat.event.RefreshMineEvent;
import com.social.happychat.http.HttpClient;
import com.social.happychat.ui.home.bean.GiftShopBean;
import com.social.happychat.ui.mine.bean.TagListBean;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Administrator
 * @date 2019/7/12 0012
 * @description:
 */
public class MyTagActivity extends BaseActivity {
    ActivityMyTagBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_tag);
        ImmersionBar.with(activity)
                .statusBarDarkFont(true, 0.2f)
                .keyboardEnable(true)
                .init();
        ImmersionBar.setTitleBar(this, binding.titlebar);
        binding.vpClose.setOnClickListener(new PerfectClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                finish();
            }
        });
        getData();

    }

    private void getData(){
        Map map = new HashMap();
        map.put("classifyModule", "tag");
        Subscription subscription = HttpClient.Builder.getRealServer().classifyList(com.social.happychat.util.RequestBody.as(map))
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TagListBean>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(TagListBean baseBean) {
                        if (baseBean.isValid()) {
                            List<TagListBean> listBeans = baseBean.getData();
                            if(listBeans != null && listBeans.size() > 0){
                                    for(TagListBean tag: listBeans){
                                        TextView tv_tag= (TextView) LayoutInflater.from(activity).inflate(R.layout.cell_tag, null);
                                        tv_tag.setText(tag.getClassifyName());
                                        binding.flexbox.addView(tv_tag);
                                    }
                            }
                        } else {
                            ToastUtil.show(activity, baseBean.getMsg());
                        }
                    }
                });
        addSubscription(subscription);

        String sss = "{\"api\":\"\",\"code\":\"1\",\"data\":[{\"classifyCode\":\"P0001\",\"classifyName\":\"运动健身\",\"id\":1},{\"classifyCode\":\"P0002\",\"classifyName\":\"美食\",\"id\":2},{\"classifyCode\":\"P0003\",\"classifyName\":\"宠物\",\"id\":3},{\"classifyCode\":\"P0004\",\"classifyName\":\"音乐\",\"id\":4},{\"classifyCode\":\"P0004\",\"classifyName\":\"嘻哈\",\"id\":4},{\"classifyCode\":\"P0004\",\"classifyName\":\"电影\",\"id\":4},{\"classifyCode\":\"P0004\",\"classifyName\":\"爱仕达多阿萨德\",\"id\":4},{\"classifyCode\":\"P0004\",\"classifyName\":\"号人发货人\",\"id\":4}],\"msg\":\"操作成功\"}";
        TagListBean baseBean = new GsonBuilder().serializeNulls().create().fromJson(sss, TagListBean.class);
        List<TagListBean> listBeans = baseBean.getData();
        if(listBeans != null && listBeans.size() > 0){
            for(TagListBean tag: listBeans){
                TextView tv_tag= (TextView) LayoutInflater.from(activity).inflate(R.layout.cell_tag, null);
                tv_tag.setText(tag.getClassifyName());
                binding.flexbox.addView(tv_tag);
            }
        }
    }

    public static void action(Context context) {
        Intent intent = new Intent(context, MyTagActivity.class);
        context.startActivity(intent);
    }
}
