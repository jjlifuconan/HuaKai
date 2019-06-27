package com.social.huakai.ui.find.interfaces;


/**
 * @author jingbin
 * @data 2018/1/16
 * @Description
 */

public interface FindNavigator {

    /**
     * 请求成功
     */
    void showLoadSuccessView();

    /**
     * 显示adapter数据
     */
    void showAdapterView();

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
//    void addRxSubscription(Subscription subscription);
}
