package com.social.huakai.ui.home.present;

import com.example.http.HttpUtils;
import com.social.huakai.http.RequestImpl;
import com.social.huakai.ui.find.bean.GankIoDataBean;
import com.social.huakai.ui.find.model.FindViewModel;
import com.social.huakai.ui.home.bean.NeteaseList;
import com.social.huakai.ui.home.interfaces.TrendNavigator;
import com.social.huakai.ui.home.model.TrendModel;

import rx.Subscription;

/**
 * @author Administrator
 * @date 2019/6/27 0027
 * @description:
 */
public class TrendPresent {
    private TrendNavigator navigator;
    private TrendModel mModel;
    private int mPage = 1;

    public TrendPresent(TrendNavigator navigator) {
        this.navigator = navigator;
        mModel = new TrendModel();
    }

    public void loadFindData() {
        mModel.setData( mPage, HttpUtils.per_page_more);
        mModel.getNeteaseData(new RequestImpl() {
            @Override
            public void loadSuccess(Object object) {
                navigator.showLoadSuccessView();

                NeteaseList neteaseList = (NeteaseList) object;
                if (mPage == 1) {
                    if (neteaseList == null || neteaseList.getData() == null || neteaseList.getData().size() <= 0) {
                        navigator.showLoadFailedView();
                        return;
                    }
                } else {
                    if (neteaseList == null || neteaseList.getData() == null || neteaseList.getData().size() <= 0) {
                        navigator.showListNoMoreLoading();
                        return;
                    }
                }
                navigator.showAdapterView(neteaseList);
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
