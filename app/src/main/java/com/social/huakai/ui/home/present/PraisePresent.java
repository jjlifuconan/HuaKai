package com.social.huakai.ui.home.present;


import com.example.http.HttpUtils;
import com.social.huakai.http.RequestImpl;
import com.social.huakai.ui.home.bean.PraiseListBean;
import com.social.huakai.ui.home.interfaces.PraiseNavigator;
import com.social.huakai.ui.home.model.PraiseViewModel;

import rx.Subscription;

/**
 * @author Administrator
 * @date 2019/6/27 0027
 * @description:
 */
public class PraisePresent {
    private PraiseNavigator navigator;
    private PraiseViewModel mModel;
    private int mPage = 1;

    public PraisePresent(PraiseNavigator navigator) {
        this.navigator = navigator;
        mModel = new PraiseViewModel();
    }

    public void loadPraiseData() {
        mModel.setData( mPage, HttpUtils.per_page_more);
        mModel.getPraiseData(new RequestImpl() {
            @Override
            public void loadSuccess(Object object) {
                navigator.showLoadSuccessView();
                PraiseListBean PraiseListBean = (PraiseListBean) object;
                if (mPage == 1) {
                    if (PraiseListBean == null || PraiseListBean.getData() == null || PraiseListBean.getData().size() <= 0) {
                        navigator.showLoadFailedView();
                        return;
                    }
                } else {
                    if (PraiseListBean == null || PraiseListBean.getData() == null || PraiseListBean.getData().size() <= 0) {
                        navigator.showListNoMoreLoading();
                        return;
                    }
                }
                navigator.showAdapterView(PraiseListBean.getData());
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
