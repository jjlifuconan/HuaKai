package com.social.happychat.ui.login.bean;

import android.databinding.Bindable;
import android.text.TextUtils;

import com.social.happychat.BR;
import com.social.happychat.bean.BaseBean;
import com.social.happychat.ui.compose.bean.ImageBean;
import com.social.happychat.ui.mine.bean.TagListBean;

import java.io.Serializable;
import java.util.List;


/**
 * @author Administrator
 * @date 2019/7/7
 * @description:
 */
public class UserBean extends BaseBean<UserBean> {
    private static final long serialVersionUID = 3056413938024922220L;
    /**
     * headPhotoUrl :
     * id : 22
     * nickName :
     * userBirthday : Sun Jul 07 00:46:11 CST 2019
     * userFileDtos : []
     * userMobile : 18652032521
     * userSign :
     * userTagDtos : []
     */

    private String headPhotoUrl;
    private int id;
    private String nickName;
    private String userBirthday;
    private String userProfession;
    private String userMobile;
    private String userSign;
    private String constellation;
    private String emotionStatus;
    private String userAddress;
    private int userSex;
    private int userAge;
    private int isOpenIm;
    private int giveGiftSum;
    private int receiveGiftSum;
    private int dynamicNum;
    private String socialNumber;


    private List<ImageBean> userFileDtos;
    private List<TagListBean> userTagDtos;


    private String registTime;
    private List<BrowseInfoDtoListBean> browseInfoDtoList;
    private List<GiftListBean> giveGiftList;
    private List<GiftListBean> receiveGiftList;

    public int getDynamicNum() {
        return dynamicNum;
    }

    public void setDynamicNum(int dynamicNum) {
        this.dynamicNum = dynamicNum;
    }

    public int getGiveGiftSum() {
        return giveGiftSum;
    }

    public void setGiveGiftSum(int giveGiftSum) {
        this.giveGiftSum = giveGiftSum;
    }

    public int getReceiveGiftSum() {
        return receiveGiftSum;
    }

    public void setReceiveGiftSum(int receiveGiftSum) {
        this.receiveGiftSum = receiveGiftSum;
    }

    public String getSocialNumber() {
        return socialNumber;
    }

    public void setSocialNumber(String socialNumber) {
        this.socialNumber = socialNumber;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public String getConstellation() {
        return constellation;
    }

    public void setConstellation(String constellation) {
        this.constellation = constellation;
    }

    public String getEmotionStatus() {
        return emotionStatus;
    }

    public void setEmotionStatus(String emotionStatus) {
        this.emotionStatus = emotionStatus;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public boolean isOpenIm() {
        //for test
//        return true;
        //注册流程变成服务端注册
        return isOpenIm == 1;
    }

    public void setIsOpenIm(int isOpenIm) {
        isOpenIm = isOpenIm;
    }

    public String getUserProfession() {
        return userProfession;
    }

    public void setUserProfession(String userProfession) {
        this.userProfession = userProfession;
    }

    public int getUserSex() {
        return userSex;
    }

    public void setUserSex(int userSex) {
        this.userSex = userSex;
    }

    @Bindable
    public String getHeadPhotoUrl() {
        return headPhotoUrl;
    }

    public void setHeadPhotoUrl(String headPhotoUrl) {
        this.headPhotoUrl = headPhotoUrl;
        notifyPropertyChanged(BR.headPhotoUrl);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickName() {
        if(!TextUtils.isEmpty(nickName) && nickName.length()>10){
            return nickName.substring(0,10);
        }
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(String userBirthday) {
        this.userBirthday = userBirthday;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getUserSign() {
        return userSign;
    }

    public void setUserSign(String userSign) {
        this.userSign = userSign;
    }

    public List<ImageBean> getUserFileDtos() {
        return userFileDtos;
    }

    public boolean isUserFileDtosEmpty(){
        if(userFileDtos == null || userFileDtos.size() == 0){
            return true;
        }else{
            return false;
        }
    }

    public boolean isUserTagDtosEmpty(){
        if(userTagDtos == null || userTagDtos.size() == 0){
            return true;
        }else{
            return false;
        }
    }

    public void setUserFileDtos(List<ImageBean> userFileDtos) {
        this.userFileDtos = userFileDtos;
    }

    public List<TagListBean> getUserTagDtos() {
        return userTagDtos;
    }

    public void setUserTagDtos(List<TagListBean> userTagDtos) {
        this.userTagDtos = userTagDtos;
    }

    public boolean isDataModify(UserBean localBean){
        //昵称
        if(!TextUtils.equals(localBean.getNickName(), getNickName())){
            return true;
        }
        //头像
        if(!TextUtils.equals(localBean.getHeadPhotoUrl(), getHeadPhotoUrl())){
            return true;
        }
        //生日
        if(!TextUtils.equals(localBean.getUserBirthday(), getUserBirthday())){
            return true;
        }
        //签名
        if(!TextUtils.equals(localBean.getUserSign(), getUserSign())){
            return true;
        }
        //相册  比较复杂
        if(localBean.isUserFileDtosEmpty() && !isUserFileDtosEmpty()){
            return true;
        }

        if(!localBean.isUserFileDtosEmpty() && isUserFileDtosEmpty()){
            return true;
        }

        if(!localBean.isUserFileDtosEmpty() && !isUserFileDtosEmpty()){
            if(localBean.getUserFileDtos().size() != getUserFileDtos().size()){
                return true;
            }
            for(ImageBean imageBean: localBean.getUserFileDtos()){
                if(!isExistFileId(getUserFileDtos(), imageBean)){
                    return true;
                }
            }
        }

        //情感状态

        if(!TextUtils.equals(localBean.getEmotionStatus(), getEmotionStatus())){
            return true;
        }

        //家乡
        if(!TextUtils.equals(localBean.getUserAddress(), getUserAddress())){
            return true;
        }
        //职业
        if(!TextUtils.equals(localBean.getUserProfession(), getUserProfession())){
            return true;
        }
        //标签
        if(localBean.isUserTagDtosEmpty() && !isUserTagDtosEmpty()){
            return true;
        }

        if(!localBean.isUserTagDtosEmpty() && isUserTagDtosEmpty()){
            return true;
        }

        if(!localBean.isUserTagDtosEmpty() && !isUserTagDtosEmpty()){
            if(localBean.getUserTagDtos().size() != getUserTagDtos().size()){
                return true;
            }
            for(TagListBean tagListBean: localBean.getUserTagDtos()){
                if(!isExistTagId(getUserTagDtos(), tagListBean)){
                    return true;
                }
            }
        }

        return false;
    }

    private boolean isExistFileId(List<ImageBean> list, ImageBean tartget){
        for(ImageBean imageBean:list){
            if(TextUtils.equals(tartget.getFileId(), imageBean.getFileId())){
                return true;
            }
        }
        return false;

    }

    private boolean isExistTagId(List<TagListBean> list, TagListBean tartget){
        for(TagListBean tagListBean:list){
            if(tagListBean.getId() == tartget.getId()){
                return true;
            }
        }
        return false;

    }

    public String getRegistTime() {
        return registTime;
    }

    public void setRegistTime(String registTime) {
        this.registTime = registTime;
    }

    public List<BrowseInfoDtoListBean> getBrowseInfoDtoList() {
        return browseInfoDtoList;
    }

    public void setBrowseInfoDtoList(List<BrowseInfoDtoListBean> browseInfoDtoList) {
        this.browseInfoDtoList = browseInfoDtoList;
    }

    public List<GiftListBean> getGiveGiftList() {
        return giveGiftList;
    }

    public void setGiveGiftList(List<GiftListBean> giveGiftList) {
        this.giveGiftList = giveGiftList;
    }

    public List<GiftListBean> getReceiveGiftList() {
        return receiveGiftList;
    }

    public void setReceiveGiftList(List<GiftListBean> receiveGiftList) {
        this.receiveGiftList = receiveGiftList;
    }

    public static class BrowseInfoDtoListBean implements Serializable {
        /**
         * browseHeadPhotoUrl : http://happychat-pic.oss-cn-hangzhou.aliyuncs.com/3640665809504d7996ec4277d23fbdd7.jpeg?Expires=1878519205&OSSAccessKeyId=LTAIDqmQs3MHEI7Q&Signature=8sXTvLYqBoNUZfqbsF5thpjzuGA%3D
         * browseTime : 2019-07-16
         * browseUserId : 21
         * browseUserSex : 2
         * userId : 25
         */

        private String browseHeadPhotoUrl;
        private String browseTime;
        private int browseUserId;
        private int browseUserSex;
        private int userId;

        public String getBrowseHeadPhotoUrl() {
            return browseHeadPhotoUrl;
        }

        public void setBrowseHeadPhotoUrl(String browseHeadPhotoUrl) {
            this.browseHeadPhotoUrl = browseHeadPhotoUrl;
        }

        public String getBrowseTime() {
            return browseTime;
        }

        public void setBrowseTime(String browseTime) {
            this.browseTime = browseTime;
        }

        public int getBrowseUserId() {
            return browseUserId;
        }

        public void setBrowseUserId(int browseUserId) {
            this.browseUserId = browseUserId;
        }

        public int getBrowseUserSex() {
            return browseUserSex;
        }

        public void setBrowseUserSex(int browseUserSex) {
            this.browseUserSex = browseUserSex;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }

    public static class GiftListBean implements Serializable{
        /**
         * giftCount : 8
         * giftIcon : http://happychat-pic.oss-cn-hangzhou.aliyuncs.com/e43c53d2a5dc47e0854541219dc580e8.jpeg?Expires=1878218360&OSSAccessKeyId=LTAIDqmQs3MHEI7Q&Signature=oaPyR3THV6lqBIH4ymo9Umr00%2Fw%3D
         * giftId : 1
         * giftName : 守护符
         */

        private int giftCount;
        private String giftIcon;
        private int giftId;
        private String giftName;

        public int getGiftCount() {
            return giftCount;
        }

        public void setGiftCount(int giftCount) {
            this.giftCount = giftCount;
        }

        public String getGiftIcon() {
            return giftIcon;
        }

        public void setGiftIcon(String giftIcon) {
            this.giftIcon = giftIcon;
        }

        public int getGiftId() {
            return giftId;
        }

        public void setGiftId(int giftId) {
            this.giftId = giftId;
        }

        public String getGiftName() {
            return giftName;
        }

        public void setGiftName(String giftName) {
            this.giftName = giftName;
        }
    }

}
