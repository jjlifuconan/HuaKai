package com.social.happychat.ui.home.present;


import com.example.http.HttpUtils;
import com.social.happychat.constant.Constant;
import com.social.happychat.http.RequestImpl;
import com.social.happychat.ui.home.bean.GiftRecordBean;
import com.social.happychat.ui.home.bean.TrendListBean;
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

    public void loadGiftData(int channel, int dynamicId, int userId) {
        mModel.setData( mPage, HttpUtils.per_page_more, channel, dynamicId, userId);
        mModel.getGiftData(new RequestImpl() {
            @Override
            public void loadSuccess(Object object) {
                navigator.showLoadSuccessView();

                GiftRecordBean giftRecordBean = (GiftRecordBean) object;
                if (mPage == 1) {
                    if (giftRecordBean == null || !giftRecordBean.isValid() ||  giftRecordBean.getData() == null
                            || giftRecordBean.getData().getList() == null || giftRecordBean.getData().getList().size() <= 0) {
                        navigator.showLoadFailedView();
                        return;
                    }
                } else {
                    if (giftRecordBean == null || !giftRecordBean.isValid() ||  giftRecordBean.getData() == null
                            || giftRecordBean.getData().getList() == null || giftRecordBean.getData().getList().size() <= 0) {
                        navigator.showListNoMoreLoading();
                        return;
                    }
                }
                if(giftRecordBean != null && !giftRecordBean.isHasNextPage()){
                    navigator.showListNoMoreLoading();
                }
                navigator.showAdapterView(giftRecordBean.getData().getList());
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
