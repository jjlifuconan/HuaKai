package com.social.huakai.ui.mine.present;

import android.util.Log;

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

    public void loadRankData(String tabType, String radioType) {
        Log.e("FLJ","tabType-->"+tabType+",radioType-->"+radioType);
        mModel.setData(tabType , radioType);
        mModel.getGankIoData(new RequestImpl() {
            @Override
            public void loadSuccess(Object object) {
                navigator.showLoadSuccessView();
                RankListBean rankListBean = (RankListBean) object;
                if(rankListBean.getData()!=null && rankListBean.getData().size() > 0){
                    if(rankListBean.getData().size() > 3){
                        navigator.showTop3Views(rankListBean.getData().subList(0,3));
                        rankListBean.getData().subList(0,3).clear();
                        navigator.showAdapterView(rankListBean.getData());
                    }else{
                        navigator.showTop3Views(rankListBean.getData());
                    }
                }else{
                    navigator.showLoadFailedView();
                }
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
