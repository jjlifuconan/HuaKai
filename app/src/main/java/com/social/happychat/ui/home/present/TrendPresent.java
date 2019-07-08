package com.social.happychat.ui.home.present;

import com.example.http.HttpUtils;
import com.social.happychat.http.RequestImpl;
import com.social.happychat.ui.home.bean.NeteaseList;
import com.social.happychat.ui.home.bean.TrendListBean;
import com.social.happychat.ui.home.interfaces.TrendNavigator;
import com.social.happychat.ui.home.model.TrendModel;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

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

    public void loadTrendData() {
        mModel.setData( mPage, HttpUtils.per_page_more);
        mModel.getTrendList(new RequestImpl() {
            @Override
            public void loadSuccess(Object object) {
                navigator.showLoadSuccessView();

                TrendListBean trendListBean = (TrendListBean) object;
                if (mPage == 1) {
                    if (trendListBean == null || !trendListBean.isValid() ||  trendListBean.getData() == null
                            || trendListBean.getData().getList() == null || trendListBean.getData().getList().size() <= 0) {
                        navigator.showLoadFailedView();
                        return;
                    }
                } else {
                    if (trendListBean == null || !trendListBean.isValid() ||  trendListBean.getData() == null
                            || trendListBean.getData().getList() == null || trendListBean.getData().getList().size() <= 0) {
                        navigator.showListNoMoreLoading();
                        return;
                    }
                }
                if(trendListBean != null && !trendListBean.isHasNextPage()){
                    navigator.showListNoMoreLoading();
                }
                navigator.showAdapterView(trendListBean.getData().getList());
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

    public void praiseAction(int businessId, int operateType, int type) {
        mModel.praise(businessId, operateType, type, new RequestImpl() {
            @Override
            public void loadSuccess(Object object) {
//                nahowAdapterView(trendListBean.getData().getList());
            }

            @Override
            public void loadFailed() {

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
