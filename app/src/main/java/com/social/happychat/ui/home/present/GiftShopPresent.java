package com.social.happychat.ui.home.present;


import com.example.http.HttpUtils;
import com.social.basecommon.util.ToastUtil;
import com.social.happychat.app.HappyChatApplication;
import com.social.happychat.bean.BaseBean;
import com.social.happychat.event.GiftSendSuccessEvent;
import com.social.happychat.http.RequestImpl;
import com.social.happychat.ui.home.bean.GiftShopBean;
import com.social.happychat.ui.home.interfaces.GiftShopNavigator;
import com.social.happychat.ui.home.model.GiftShopViewModel;

import org.greenrobot.eventbus.EventBus;

import rx.Subscription;

/**
 * @author Administrator
 * @date 2019/6/27 0027
 * @description:
 */
public class GiftShopPresent {
    private GiftShopNavigator navigator;
    private GiftShopViewModel mModel;
    private int mPage = 1;

    public GiftShopPresent(GiftShopNavigator navigator) {
        this.navigator = navigator;
        mModel = new GiftShopViewModel();
    }

    public void loadGiftShopData() {
        mModel.setData( mPage, HttpUtils.per_page_more);
        mModel.getGiftData(new RequestImpl() {
            @Override
            public void loadSuccess(Object object) {
                navigator.showLoadSuccessView();
                GiftShopBean GiftListBean = (GiftShopBean) object;
                if (mPage == 1) {
                    if (GiftListBean == null || GiftListBean.getData() == null || GiftListBean.getData().size() <= 0) {
                        navigator.showLoadFailedView();
                        return;
                    }
                } else {
                    if (GiftListBean == null || GiftListBean.getData() == null || GiftListBean.getData().size() <= 0) {
                        navigator.showListNoMoreLoading();
                        return;
                    }
                }
                navigator.showAdapterView(GiftListBean.getData());
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

    public void sendGift(int channel, int dynamicId, int giftId, int userId) {
        mModel.setData(channel, dynamicId, giftId, userId);
        mModel.sendGift(new RequestImpl() {
            @Override
            public void loadSuccess(Object object) {
//                navigator.showLoadSuccessView();
                BaseBean baseBean = (BaseBean) object;
                if(baseBean.isValid()){
                    //刷新礼物数  刷新列表的第几个
                    EventBus.getDefault().post(new GiftSendSuccessEvent(dynamicId));
                    navigator.sendGiftSuccess();
                }else{
                    ToastUtil.show(HappyChatApplication.getInstance(), baseBean.getMsg());
                }
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

    public void loadGiftReceiveData() {
        mModel.setData( mPage, HttpUtils.per_page_more);
        mModel.getGiftData(new RequestImpl() {
            @Override
            public void loadSuccess(Object object) {
                navigator.showLoadSuccessView();
                GiftShopBean GiftListBean = (GiftShopBean) object;
                if (mPage == 1) {
                    if (GiftListBean == null || GiftListBean.getData() == null || GiftListBean.getData().size() <= 0) {
                        navigator.showLoadFailedView();
                        return;
                    }
                } else {
                    if (GiftListBean == null || GiftListBean.getData() == null || GiftListBean.getData().size() <= 0) {
                        navigator.showListNoMoreLoading();
                        return;
                    }
                }
                navigator.showAdapterView(GiftListBean.getData());
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

    public void loadGiftSendData() {
        mModel.setData( mPage, HttpUtils.per_page_more);
        mModel.getGiftData(new RequestImpl() {
            @Override
            public void loadSuccess(Object object) {
                navigator.showLoadSuccessView();
                GiftShopBean GiftListBean = (GiftShopBean) object;
                if (mPage == 1) {
                    if (GiftListBean == null || GiftListBean.getData() == null || GiftListBean.getData().size() <= 0) {
                        navigator.showLoadFailedView();
                        return;
                    }
                } else {
                    if (GiftListBean == null || GiftListBean.getData() == null || GiftListBean.getData().size() <= 0) {
                        navigator.showListNoMoreLoading();
                        return;
                    }
                }
                navigator.showAdapterView(GiftListBean.getData());
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
