package com.social.happychat.ui.home.present;


import com.example.http.HttpUtils;
import com.social.happychat.http.RequestImpl;
import com.social.happychat.ui.home.bean.GrabListBean;
import com.social.happychat.ui.home.interfaces.GrabNavigator;
import com.social.happychat.ui.home.model.GrabViewModel;

import rx.Subscription;

/**
 * @author Administrator
 * @date 2019/6/27 0027
 * @description:
 */
public class GrabPresent {
    private GrabNavigator navigator;
    private GrabViewModel mModel;
    private int mPage = 1;

    public GrabPresent(GrabNavigator navigator) {
        this.navigator = navigator;
        mModel = new GrabViewModel();
    }

    public void loadGrabData() {
        mModel.setData( mPage, HttpUtils.per_page_more);
        mModel.getGrabData(new RequestImpl() {
            @Override
            public void loadSuccess(Object object) {
                navigator.showLoadSuccessView();
                GrabListBean grabListBean = (GrabListBean) object;
                if (mPage == 1) {
                    if (grabListBean == null || grabListBean.getData() == null || grabListBean.getData().size() <= 0) {
                        navigator.showLoadFailedView();
                        return;
                    }
                } else {
                    if (grabListBean == null || grabListBean.getData() == null || grabListBean.getData().size() <= 0) {
                        navigator.showListNoMoreLoading();
                        return;
                    }
                }
                navigator.showAdapterView(grabListBean.getData());
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
