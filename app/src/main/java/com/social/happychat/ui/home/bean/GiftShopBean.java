package com.social.happychat.ui.home.bean;


import com.social.happychat.bean.BaseBean;

import java.util.List;

/**
 * @author Administrator
 * @date 2019/7/2 0002
 * @description:
 */
public class GiftShopBean extends BaseBean<List<GiftShopBean>> {


    /**
     * giftCashPrice : 13.14
     * giftDesc : 守护符
     * giftIcon : http://happychat-pic.oss-cn-hangzhou.aliyuncs.com/e43c53d2a5dc47e0854541219dc580e8.jpeg?Expires=1878218360&OSSAccessKeyId=LTAIDqmQs3MHEI7Q&Signature=oaPyR3THV6lqBIH4ymo9Umr00%2Fw%3D
     * giftName : 守护符
     * giftPrice : 1314
     * id : 1
     * paymentType : 0
     */

    private double giftCashPrice;
    private String giftDesc;
    private String giftIcon;
    private String giftName;
    private int giftPrice;
    private int id;
    private int paymentType;

    public double getGiftCashPrice() {
        return giftCashPrice;
    }

    public void setGiftCashPrice(double giftCashPrice) {
        this.giftCashPrice = giftCashPrice;
    }

    public String getGiftDesc() {
        return giftDesc;
    }

    public void setGiftDesc(String giftDesc) {
        this.giftDesc = giftDesc;
    }

    public String getGiftIcon() {
        return giftIcon;
    }

    public void setGiftIcon(String giftIcon) {
        this.giftIcon = giftIcon;
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }

    public int getGiftPrice() {
        return giftPrice;
    }

    public void setGiftPrice(int giftPrice) {
        this.giftPrice = giftPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(int paymentType) {
        this.paymentType = paymentType;
    }
}
