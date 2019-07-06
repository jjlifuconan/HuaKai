package com.social.happychat.ui.home.present;


import com.example.http.HttpUtils;
import com.social.happychat.http.RequestImpl;
import com.social.happychat.ui.home.bean.GiftRecordBean;
import com.social.happychat.ui.home.interfaces.GiftRecordNavigator;
import com.social.happychat.ui.home.model.GiftRecordViewModel;

import rx.Subscription;

/**
 * @author Administrator
 * @date 2019/6/27 0027
 * @description:
 */
public class GiftRecordPresent {
    private GiftRecordNavigator navigator;
    private GiftRecordViewModel mModel;
    private int mPage = 1;

    public GiftRecordPresent(GiftRecordNavigator navigator) {
        this.navigator = navigator;
        mModel = new GiftRecordViewModel();
    }

    public void loadGiftData() {
        mModel.setData( mPage, HttpUtils.per_page_more);
        mModel.getGiftData(new RequestImpl() {
            @Override
            public void loadSuccess(Object object) {
                navigator.showLoadSuccessView();
                GiftRecordBean GiftListBean = (GiftRecordBean) object;
                if (mPage == 1) {
                    if (GiftListBean == null || GiftListBean.getData() == null || GiftListBean.getData().size() <= 0) {
                        navigator.showLoadFailedView();
                        return;
                    }
                } else {
                    if (GiftListBean == null || GiftListBean.getData() == null || GiftListBean.getData().size() <= 0) {
                        navigator.showListNoMoreLoading();
                        return;
                    }
                }
                navigator.showAdapterView(GiftListBean.getData());
            }

            @Override
            public void loadFailed() {
                navigator.showLoadFailedView();
                if (mPage > 1) {
                    mPage--;
                }
            }

            @Override
            public void addSubscription(Subscription subscription) {
                navigator.addRxSubscription(subscription);
            }
        });
    }

    public int getPage() {
        return mPage;
    }

    public void setPage(int mPage) {
        this.mPage = mPage;
    }


}