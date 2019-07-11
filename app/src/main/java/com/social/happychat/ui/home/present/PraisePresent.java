package com.social.happychat.ui.home.present;


import com.example.http.HttpUtils;
import com.social.happychat.http.RequestImpl;
import com.social.happychat.ui.home.bean.PraiseListBean;
import com.social.happychat.ui.home.interfaces.PraiseNavigator;
import com.social.happychat.ui.home.model.PraiseViewModel;

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
    private int dynamicId;

    public PraisePresent(PraiseNavigator navigator, int dynamicId) {
        this.navigator = navigator;
        this.dynamicId = dynamicId;
        mModel = new PraiseViewModel();
    }

    public void loadPraiseData() {
        mModel.setData( mPage, HttpUtils.per_page_more, dynamicId);
        mModel.getPraiseData(new RequestImpl() {
            @Override
            public void loadSuccess(Object object) {
                navigator.showLoadSuccessView();
                PraiseListBean PraiseListBean = (PraiseListBean) object;
                if (mPage == 1) {
                    if (PraiseListBean == null || !PraiseListBean.isValid()|| PraiseListBean.getData() == null
                            || PraiseListBean.getData().getList() == null || PraiseListBean.getData().getList().size() <= 0) {
                        navigator.showLoadFailedView();
//                        return;  取消赞后数据没有了，要清空，所以不能return
                    }
                } else {
                    if (PraiseListBean == null || !PraiseListBean.isValid()|| PraiseListBean.getData() == null
                            || PraiseListBean.getData().getList() == null || PraiseListBean.getData().getList().size() <= 0) {
                        navigator.showListNoMoreLoading();
                        return;
                    }
                }
                if(PraiseListBean != null && !PraiseListBean.isHasNextPage()){
                    navigator.showListNoMoreLoading();
                }
                navigator.showAdapterView(PraiseListBean.getData().getList());
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
