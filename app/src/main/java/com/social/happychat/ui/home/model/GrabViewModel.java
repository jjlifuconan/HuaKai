package com.social.happychat.ui.home.model;

import com.google.gson.GsonBuilder;
import com.social.happychat.bean.BaseBean;
import com.social.happychat.http.HttpClient;
import com.social.happychat.http.RequestImpl;
import com.social.happychat.ui.home.bean.GrabListBean;
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
public class GrabViewModel {
    private int page;
    private int per_page;

    public void setData(int page, int per_page) {
        this.page = page;
        this.per_page = per_page;
    }

    public void getGrabData(final RequestImpl listener) {
        Map map = new HashMap();
        map.put("pageNumber",page);
        map.put("pages",per_page);
        Subscription subscription = HttpClient.Builder.getRealServer().chatApplyList(RequestBody.as(map))
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GrabListBean>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.loadFailed();

                    }

                    @Override
                    public void onNext(GrabListBean baseBean) {
                        listener.loadSuccess(baseBean);

                    }
                });
        listener.addSubscription(subscription);
    }

}
