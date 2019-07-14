package com.social.happychat.ui.home.model;

import com.google.gson.GsonBuilder;
import com.social.happychat.bean.BaseBean;
import com.social.happychat.http.HttpClient;
import com.social.happychat.http.RequestImpl;
import com.social.happychat.ui.home.bean.CommentListBean;
import com.social.happychat.ui.home.bean.GiftShopBean;
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
public class GiftShopViewModel {
    private int page;
    private int per_page;

    //赠送礼物参数
    private int channel;//1-动态页  2-用户主页
    private int dynamicId;//动态id(channel为2不传)
    private int giftCount = 1;//礼物数
    private int giftId;//礼物id
    private int userId;//受赠用户id


    public void setData(int page, int per_page) {
        this.page = page;
        this.per_page = per_page;
    }

    public void setData(int channel, int dynamicId, int giftId, int userId) {
        this.channel = channel;
        this.dynamicId = dynamicId;
        this.giftId = giftId;
        this.userId = userId;
    }

    public void getGiftData(final RequestImpl listener) {
        Subscription subscription = HttpClient.Builder.getRealServer().giftGoodsList()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GiftShopBean>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.loadFailed();

                    }

                    @Override
                    public void onNext(GiftShopBean baseBean) {
                        listener.loadSuccess(baseBean);

                    }
                });
        listener.addSubscription(subscription);
    }

    public void sendGift(final RequestImpl listener) {
        Map map = new HashMap();
        if(channel == 1){
            map.put("dynamicId",dynamicId);
        }
        map.put("channel",channel);
        map.put("giftCount",giftCount);
        map.put("giftId",giftId);
        map.put("userId",userId);
        Subscription subscription = HttpClient.Builder.getRealServer().giveGift(RequestBody.as(map))
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
