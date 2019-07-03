package com.social.huakai.ui.home.present;


import com.example.http.HttpUtils;
import com.social.huakai.http.RequestImpl;
import com.social.huakai.ui.home.bean.CommentListBean;
import com.social.huakai.ui.home.interfaces.CommentNavigator;
import com.social.huakai.ui.home.model.CommentViewModel;

import rx.Subscription;

/**
 * @author Administrator
 * @date 2019/6/27 0027
 * @description:
 */
public class CommentPresent {
    private CommentNavigator navigator;
    private CommentViewModel mModel;
    private int mPage = 1;

    public CommentPresent(CommentNavigator navigator) {
        this.navigator = navigator;
        mModel = new CommentViewModel();
    }

    public void loadCommentData() {
        mModel.setData( mPage, HttpUtils.per_page_more);
        mModel.getCommentData(new RequestImpl() {
            @Override
            public void loadSuccess(Object object) {
                navigator.showLoadSuccessView();
                CommentListBean CommentListBean = (CommentListBean) object;
                if (mPage == 1) {
                    if (CommentListBean == null || CommentListBean.getData() == null || CommentListBean.getData().size() <= 0) {
                        navigator.showLoadFailedView();
                        return;
                    }
                } else {
                    if (CommentListBean == null || CommentListBean.getData() == null || CommentListBean.getData().size() <= 0) {
                        navigator.showListNoMoreLoading();
                        return;
                    }
                }
                navigator.showAdapterView(CommentListBean.getData());
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
