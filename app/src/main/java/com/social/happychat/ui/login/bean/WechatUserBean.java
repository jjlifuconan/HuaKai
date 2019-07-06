package com.social.happychat.ui.login.bean;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @author Administrator
 * @date 2019/7/6
 * @description:
 */
public class WechatUserBean implements Serializable {
    private String country;
    private String unionid;
    private String province;
    private String city;
    private String openid;
    private String sex;
    private String nickname;
    private String headimgurl;
    private String userTags;

    public WechatUserBean(HashMap map){
        if(map != null && !map.isEmpty()){
            country = (String) map.get("country");
            unionid = (String) map.get("unionid");
            province = (String) map.get("province");
            city = (String) map.get("city");
            openid = (String) map.get("openid");
            sex = (String) map.get("sex");
            headimgurl = (String) map.get("headimgurl");
            userTags = (String) map.get("userTags");
        }
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getUserTags() {
        return userTags;
    }

    public void setUserTags(String userTags) {
        this.userTags = userTags;
    }
}
