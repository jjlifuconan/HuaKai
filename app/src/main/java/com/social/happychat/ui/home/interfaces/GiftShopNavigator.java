package com.social.happychat.ui.home.interfaces;


import com.social.happychat.ui.home.bean.GiftShopBean;

import java.util.List;

import rx.Subscription;

/**
 * @author jingbin
 * @data 2018/1/16
 * @Description
 */

public interface GiftShopNavigator {

    /**
     * 请求成功
     */
    void showLoadSuccessView();

    /**
     * 礼物赠送成功
     */
    void sendGiftSuccess();

    /**
     * 显示adapter数据
     */
    void showAdapterView(List<GiftShopBean> dataBean);

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
