package com.social.happychat.ui.rank.model;

import android.text.TextUtils;

import com.google.gson.GsonBuilder;
import com.social.happychat.constant.Constant;
import com.social.happychat.http.HttpClient;
import com.social.happychat.http.RequestImpl;
import com.social.happychat.ui.rank.bean.RankListBean;
import com.social.happychat.util.RequestBody;

import java.util.HashMap;
import java.util.Map;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * @author Administrator
 * @date 2019/6/27 0027
 * @description:
 */
public class RankViewModel {
    private String tabType;
    private String radioType;

    public void setData(String tabType, String radioType) {
        this.tabType = tabType;
        this.radioType = radioType;
    }

    public void getRankData(final RequestImpl listener) {
        Map map = new HashMap();
        map.put("listType", TextUtils.equals(tabType, Constant.TabType.MONEY)?1:2);
        int statisticType = 0;
        if(TextUtils.equals(radioType, Constant.RadioType.DAY)){
            statisticType = 1;
        }else if(TextUtils.equals(radioType, Constant.RadioType.WEEK)){
            statisticType = 2;
        }else if(TextUtils.equals(radioType, Constant.RadioType.MONTH)){
            statisticType = 3;
        }
        map.put("statisticType",statisticType);
        Subscription subscription = HttpClient.Builder.getRealServer().rankList(RequestBody.as(map))
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RankListBean>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.loadFailed();

                    }

                    @Override
                    public void onNext(RankListBean rankListBean) {
                        listener.loadSuccess(rankListBean);

                    }
                });
                listener.addSubscription(subscription);
    }

}
