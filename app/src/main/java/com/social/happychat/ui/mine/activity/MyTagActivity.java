package com.social.happychat.ui.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.social.basecommon.activity.BaseActivity;
import com.social.basecommon.util.PerfectClickListener;
import com.social.basecommon.util.ToastUtil;
import com.social.happychat.R;
import com.social.happychat.databinding.ActivityMyTagBinding;
import com.social.happychat.event.UserTagEvent;
import com.social.happychat.http.HttpClient;
import com.social.happychat.ui.mine.bean.TagListBean;


import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
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
    List<TagListBean> listBeans;//接口请求到的总的tags
    List<TagListBean> userTags;//用户选中的数据


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_tag);
        ImmersionBar.with(activity)
                .statusBarDarkFont(true, 0.2f)
                .keyboardEnable(true)
                .init();
        ImmersionBar.setTitleBar(this, binding.titlebar);
        userTags = (List<TagListBean>) getIntent().getSerializableExtra("userTags");
        binding.vpClose.setOnClickListener(new PerfectClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                finish();
            }
        });
        binding.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.flexbox.getChildCount() != 0){
                    List<TagListBean> selectBeans = new ArrayList<>();
                    StringBuilder sb = new StringBuilder();
                    for(int i = 0;i<binding.flexbox.getChildCount();i++){
                        TextView textView = (TextView) binding.flexbox.getChildAt(i);
                        if(textView.isSelected()){
                            selectBeans.add(listBeans.get(i));
                            sb.append(listBeans.get(i).getClassifyName()).append(",");
                        }
                    }
                    if(selectBeans.size() > 0){
                        String tagsText = sb.toString();
                        if(tagsText.endsWith(",")){
                            tagsText = tagsText.substring(0,tagsText.length() - 1);
                        }
                        EventBus.getDefault().post(new UserTagEvent(tagsText, selectBeans));
                    }
                }
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
                            listBeans = baseBean.getData();
                            if(listBeans != null && listBeans.size() > 0){
                                for(TagListBean tag: listBeans){
                                    TextView tv_tag= (TextView) LayoutInflater.from(activity).inflate(R.layout.cell_tag, null);
                                    Log.e(TAG,"tv_tag  setOnClickListener");
                                    tv_tag.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            tv_tag.setSelected(!tv_tag.isSelected());
                                        }
                                    });
                                    tv_tag.setText(tag.getClassifyName());
                                    if(userTags != null){
                                        if(userTags.contains(tag)){
                                            tv_tag.setSelected(true);
                                        }
                                    }
                                    binding.flexbox.addView(tv_tag);
                                }
                            }
                        } else {
                            ToastUtil.show(activity, baseBean.getMsg());
                        }
                    }
                });
        addSubscription(subscription);
    }

    public static void action(Context context, ArrayList<TagListBean> userTags) {
        Intent intent = new Intent(context, MyTagActivity.class);
        intent.putExtra("userTags", userTags);
        context.startActivity(intent);
    }
}
