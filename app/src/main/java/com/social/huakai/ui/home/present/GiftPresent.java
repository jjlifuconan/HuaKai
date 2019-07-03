package com.social.huakai.ui.home.present;


import com.example.http.HttpUtils;
import com.social.huakai.http.RequestImpl;
import com.social.huakai.ui.home.bean.GiftListBean;
import com.social.huakai.ui.home.interfaces.GiftNavigator;
import com.social.huakai.ui.home.model.GiftViewModel;

import rx.Subscription;

/**
 * @author Administrator
 * @date 2019/6/27 0027
 * @description:
 */
public class GiftPresent {
    private GiftNavigator navigator;
    private GiftViewModel mModel;
    private int mPage = 1;

    public GiftPresent(GiftNavigator navigator) {
        this.navigator = navigator;
        mModel = new GiftViewModel();
    }

    public void loadGiftData() {
        mModel.setData( mPage, HttpUtils.per_page_more);
        mModel.getGiftData(new RequestImpl() {
            @Override
            public void loadSuccess(Object object) {
                navigator.showLoadSuccessView();
                GiftListBean GiftListBean = (GiftListBean) object;
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
