package com.social.happychat.ui.rank.interfaces;


import com.social.happychat.ui.rank.bean.RankListBean;

import java.util.List;

import rx.Subscription;

/**
 * @author jingbin
 * @data 2018/1/16
 * @Description
 */

public interface RankNavigator {

    /**
     * 请求成功
     */
    void showLoadSuccessView();

    /**
     * 显示adapter数据
     */
    void showAdapterView(List<RankListBean> dataBean);

    /**
     * 显示列表没有更多数据布局
     */
    void showTop3Views(List<RankListBean> dataBean);

    /**
     * 清空列表
     */
    void clearList();

    /**
     * 无数据或请求失败
     */
    void showLoadFailedView();

    /**
     * 取消注册
     */
    void addRxSubscription(Subscription subscription);
}
