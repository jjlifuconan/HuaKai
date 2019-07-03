package com.social.huakai.ui.rank.interfaces;


import com.social.huakai.ui.rank.bean.RankListBean;

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
    void showAdapterView(List<RankListBean.DataBean> dataBean);

    /**
     * 显示列表没有更多数据布局
     */
    void showTop3Views(List<RankListBean.DataBean> dataBean);

    /**
     * 无数据或请求失败
     */
    void showLoadFailedView();

    /**
     * 取消注册
     */
    void addRxSubscription(Subscription subscription);
}
