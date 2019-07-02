package com.social.huakai.ui.mine.present;

import com.social.huakai.http.RequestImpl;
import com.social.huakai.ui.mine.bean.RankListBean;
import com.social.huakai.ui.mine.interfaces.RankNavigator;
import com.social.huakai.ui.mine.model.RankViewModel;

import rx.Subscription;

/**
 * @author Administrator
 * @date 2019/6/27 0027
 * @description:
 */
public class RankPresent {
    private RankNavigator navigator;
    private RankViewModel mModel;

    public RankPresent(RankNavigator navigator) {
        this.navigator = navigator;
        mModel = new RankViewModel();
    }

    public void loadRankData() {
        mModel.setData("id", "type");
        mModel.getGankIoData(new RequestImpl() {
            @Override
            public void loadSuccess(Object object) {
                navigator.showLoadSuccessView();

                RankListBean rankListBean = (RankListBean) object;
                navigator.showAdapterView(rankListBean);
            }

            @Override
            public void loadFailed() {
                navigator.showLoadFailedView();
            }

            @Override
            public void addSubscription(Subscription subscription) {
                navigator.addRxSubscription(subscription);
            }
        });
    }



}
