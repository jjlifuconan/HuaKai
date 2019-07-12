package com.social.happychat.ui.login.bean;

import android.databinding.Bindable;
import android.text.TextUtils;

import com.social.happychat.BR;
import com.social.happychat.bean.BaseBean;
import com.social.happychat.ui.compose.bean.ImageBean;
import com.social.happychat.ui.mine.bean.TagListBean;

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
    private int userSex;
    private int isOpenIm;
    private List<ImageBean> userFileDtos;
    private List<TagListBean> userTagDtos;

    public boolean isOpenIm() {
        //for test
        return true;
//        return isOpenIm == 1;
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
                if(!getUserFileDtos().contains(imageBean)){
                    return true;
                }
            }
        }

        //情感状态
        //家乡
        //职业
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
                if(!getUserTagDtos().contains(tagListBean)){
                    return true;
                }
            }
        }

        return false;
    }
}
