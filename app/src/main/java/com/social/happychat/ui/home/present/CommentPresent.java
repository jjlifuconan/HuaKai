package com.social.happychat.ui.home.present;


import com.example.http.HttpUtils;
import com.social.happychat.http.RequestImpl;
import com.social.happychat.ui.home.bean.CommentListBean;
import com.social.happychat.ui.home.interfaces.CommentNavigator;
import com.social.happychat.ui.home.model.CommentViewModel;

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
    private int dynamicId;

    public CommentPresent(CommentNavigator navigator, int dynamicId) {
        this.navigator = navigator;
        this.dynamicId = dynamicId;
        mModel = new CommentViewModel();
    }

    public void loadCommentData() {
        mModel.setData( mPage, HttpUtils.per_page_more, dynamicId);
        mModel.getCommentData(new RequestImpl() {
            @Override
            public void loadSuccess(Object object) {
                navigator.showLoadSuccessView();
                CommentListBean CommentListBean = (CommentListBean) object;
                if (mPage == 1) {
                    if (CommentListBean == null || !CommentListBean.isValid()|| CommentListBean.getData() == null
                            || CommentListBean.getData().getList() == null || CommentListBean.getData().getList().size() <= 0) {
                        navigator.showLoadFailedView();
//                        return;  取消评论后数据没有了，要清空，所以不能return
                    }
                } else {
                    if (CommentListBean == null || !CommentListBean.isValid()|| CommentListBean.getData() == null
                            || CommentListBean.getData().getList() == null || CommentListBean.getData().getList().size() <= 0) {
                        navigator.showListNoMoreLoading();
                        return;
                    }
                }
                if(CommentListBean != null && !CommentListBean.isHasNextPage()){
                    navigator.showListNoMoreLoading();
                }
                navigator.showAdapterView(CommentListBean.getData().getList());
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
