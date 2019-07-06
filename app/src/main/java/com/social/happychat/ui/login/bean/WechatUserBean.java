package com.social.happychat.ui.login.bean;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @author Administrator
 * @date 2019/7/6
 * @description:
 */
public class WechatUserBean implements Serializable {
    private static final long serialVersionUID = -4208506896102557720L;
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
            country = map.get("country") + "";
            unionid = map.get("unionid") + "";
            province = map.get("province") + "";
            city =  map.get("city") + "";
            openid = map.get("openid") + "";
            sex =  map.get("sex") + "";
            nickname =  map.get("nickname") + "";
            headimgurl = map.get("headimgurl") + "";
            userTags = map.get("userTags") + "";
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
