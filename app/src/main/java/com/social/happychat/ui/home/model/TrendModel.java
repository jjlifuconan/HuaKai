package com.social.happychat.ui.home.model;

import com.social.happychat.bean.BaseBean;
import com.social.happychat.http.HttpClient;
import com.social.happychat.http.RequestImpl;
import com.social.happychat.ui.home.bean.NeteaseList;
import com.social.happychat.ui.home.bean.TrendListBean;
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
public class TrendModel {
    private int page;
    private int per_page;

    public void setData( int page, int per_page) {
        this.page = page;
        this.per_page = per_page;
    }

    public void getTrendList(final RequestImpl listener) {
        Map map = new HashMap();
        map.put("publishLocation","南京");
        map.put("pageNumber ",page);
        map.put("pages ",per_page);
        Subscription subscription = HttpClient.Builder.getRealServer().dynamicList(RequestBody.as(map))
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TrendListBean>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.loadFailed();

                    }

                    @Override
                    public void onNext(TrendListBean trendListBean) {
                        listener.loadSuccess(trendListBean);

                    }
                });
        listener.addSubscription(subscription);
    }

    public void praise(int businessId, int operateType, int type, final RequestImpl listener) {
        Map map = new HashMap();
        map.put("businessId",businessId);
        map.put("operateType",operateType);
        map.put("type",type);
        Subscription subscription = HttpClient.Builder.getRealServer().doPraise(RequestBody.as(map))
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseBean>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.loadFailed();

                    }

                    @Override
                    public void onNext(BaseBean baseBean) {
                        listener.loadSuccess(baseBean);

                    }
                });
        listener.addSubscription(subscription);
    }
}
