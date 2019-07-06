package com.social.happychat.ui.find.present;

import com.example.http.HttpUtils;
import com.social.happychat.http.RequestImpl;
import com.social.happychat.ui.find.bean.GankIoDataBean;
import com.social.happychat.ui.find.interfaces.FindNavigator;
import com.social.happychat.ui.find.model.FindViewModel;

import rx.Subscription;

/**
 * @author Administrator
 * @date 2019/6/27 0027
 * @description:
 */
public class FindPresent {
    private FindNavigator navigator;
    private FindViewModel mModel;
    private int mPage = 1;

    public FindPresent(FindNavigator navigator) {
        this.navigator = navigator;
        mModel = new FindViewModel();
    }

    public void loadFindData() {
        mModel.setData("福利", mPage, HttpUtils.per_page_more);
        mModel.getGankIoData(new RequestImpl() {
            @Override
            public void loadSuccess(Object object) {
                navigator.showLoadSuccessView();

                GankIoDataBean gankIoDataBean = (GankIoDataBean) object;
                if (mPage == 1) {
                    if (gankIoDataBean == null || gankIoDataBean.getResults() == null || gankIoDataBean.getResults().size() <= 0) {
                        navigator.showLoadFailedView();
                        return;
                    }
                } else {
                    if (gankIoDataBean == null || gankIoDataBean.getResults() == null || gankIoDataBean.getResults().size() <= 0) {
                        navigator.showListNoMoreLoading();
                        return;
                    }
                }
//                handleImageList(gankIoDataBean);
                navigator.showAdapterView(gankIoDataBean);
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
