package com.social.happychat.ui.home.interfaces;


import com.social.happychat.ui.home.bean.PraiseListBean;

import java.util.List;

import rx.Subscription;

/**
 * @author jingbin
 * @data 2018/1/16
 * @Description
 */

public interface PraiseNavigator {

    /**
     * 请求成功
     */
    void showLoadSuccessView();

    /**
     * 显示adapter数据
     */
    void showAdapterView(List<PraiseListBean.ListBean> dataBean);

    /**
     * 显示列表没有更多数据布局
     */
    void showListNoMoreLoading();

    /**
     * 无数据或请求失败
     */
    void showLoadFailedView();

    /**
     * 取消注册
     */
    void addRxSubscription(Subscription subscription);
}
