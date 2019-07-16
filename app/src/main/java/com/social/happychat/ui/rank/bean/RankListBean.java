package com.social.happychat.ui.rank.bean;

import com.social.happychat.bean.BaseBean;

import java.util.List;

/**
 * @author Administrator
 * @date 2019/7/2 0002
 * @description:
 */
public class RankListBean extends BaseBean<List<RankListBean>> {


    /**
     * giftPrice : 15012
     * headPhotoUrl : http://happychat-pic.oss-cn-hangzhou.aliyuncs.com/3640665809504d7996ec4277d23fbdd7.jpeg?Expires=1878519205&OSSAccessKeyId=LTAIDqmQs3MHEI7Q&Signature=8sXTvLYqBoNUZfqbsF5thpjzuGA%3D
     * nickName : 发胶的野猪uu
     * userAge : 26
     * userId : 25
     * userSign : 奈雪的茶
     */

    private int giftPrice;
    private String headPhotoUrl;
    private String nickName;
    private int userAge;
    private int userId;
    private int userSex;
    private int rankNum;
    private String userSign;

    public int getUserSex() {
        return userSex;
    }

    public void setUserSex(int userSex) {
        this.userSex = userSex;
    }

    public int getRankNum() {
        return rankNum;
    }

    public void setRankNum(int rankNum) {
        this.rankNum = rankNum;
    }

    public int getGiftPrice() {
        return giftPrice;
    }

    public void setGiftPrice(int giftPrice) {
        this.giftPrice = giftPrice;
    }

    public String getHeadPhotoUrl() {
        return headPhotoUrl;
    }

    public void setHeadPhotoUrl(String headPhotoUrl) {
        this.headPhotoUrl = headPhotoUrl;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserSign() {
        return userSign;
    }

    public void setUserSign(String userSign) {
        this.userSign = userSign;
    }
}
