package com.social.huakai.ui.home.model;

import com.social.huakai.http.HttpClient;
import com.social.huakai.http.RequestImpl;
import com.social.huakai.ui.find.bean.GankIoDataBean;
import com.social.huakai.ui.home.bean.NeteaseList;

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

    public void getNeteaseData(final RequestImpl listener) {
        Subscription subscription = HttpClient.Builder.getNeteaseServer().getNeteaseList(page, per_page)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NeteaseList>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.loadFailed();

                    }

                    @Override
                    public void onNext(NeteaseList gankIoDataBean) {
                        listener.loadSuccess(gankIoDataBean);

                    }
                });
        listener.addSubscription(subscription);
    }
}
