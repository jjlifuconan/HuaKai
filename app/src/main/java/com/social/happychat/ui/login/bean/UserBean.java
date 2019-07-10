package com.social.happychat.ui.login.bean;

import android.text.TextUtils;

import com.social.happychat.bean.BaseBean;

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
    private String userMobile;
    private String userSign;
    private int userSex;
    private boolean isReisterIM;
    private List<?> userFileDtos;
    private List<?> userTagDtos;

    public boolean isReisterIM() {
        //for test
        return true;
    }

    public void setReisterIM(boolean reisterIM) {
        isReisterIM = reisterIM;
    }

    public int getUserSex() {
        return userSex;
    }

    public void setUserSex(int userSex) {
        this.userSex = userSex;
    }

    public String getHeadPhotoUrl() {
        return headPhotoUrl;
    }

    public void setHeadPhotoUrl(String headPhotoUrl) {
        this.headPhotoUrl = headPhotoUrl;
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

    public List<?> getUserFileDtos() {
        return userFileDtos;
    }

    public void setUserFileDtos(List<?> userFileDtos) {
        this.userFileDtos = userFileDtos;
    }

    public List<?> getUserTagDtos() {
        return userTagDtos;
    }

    public void setUserTagDtos(List<?> userTagDtos) {
        this.userTagDtos = userTagDtos;
    }
}
