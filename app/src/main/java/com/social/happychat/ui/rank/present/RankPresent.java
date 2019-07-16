package com.social.happychat.ui.rank.present;

import android.util.Log;

import com.social.happychat.http.RequestImpl;
import com.social.happychat.ui.rank.bean.RankListBean;
import com.social.happychat.ui.rank.interfaces.RankNavigator;
import com.social.happychat.ui.rank.model.RankViewModel;

import java.util.List;

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
        mModel.getRankData(new RequestImpl() {
            @Override
            public void loadSuccess(Object object) {
                navigator.showLoadSuccessView();
                RankListBean rankListBean = (RankListBean) object;
                if(rankListBean.getData()!=null && rankListBean.getData().size() > 0){
                    if(rankListBean.getData().size() > 3){
                        List<RankListBean> rankListBeanList = rankListBean.getData();
                        navigator.showTop3Views(rankListBeanList.subList(0,3));
                        rankListBeanList.subList(0,3).clear();
                        navigator.showAdapterView(rankListBeanList);
                    }else{
                        navigator.showTop3Views(rankListBean.getData());
                        navigator.clearList();
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
