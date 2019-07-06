package com.social.happychat.ui.find.model;

import com.social.happychat.http.HttpClient;
import com.social.happychat.http.RequestImpl;
import com.social.happychat.ui.find.bean.GankIoDataBean;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Administrator
 * @date 2019/6/27 0027
 * @description:
 */
public class FindViewModel {
    private String id;
    private int page;
    private int per_page;

    public void setData(String id, int page, int per_page) {
        this.id = id;
        this.page = page;
        this.per_page = per_page;
    }

    public void getGankIoData(final RequestImpl listener) {
        Subscription subscription = HttpClient.Builder.getGankIOServer().getGankIoData(id, page, per_page)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GankIoDataBean>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.loadFailed();

                    }

                    @Override
                    public void onNext(GankIoDataBean gankIoDataBean) {
                        listener.loadSuccess(gankIoDataBean);

                    }
                });
        listener.addSubscription(subscription);
    }

}
