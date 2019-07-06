package com.social.happychat.ui.home.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author Administrator
 * @date 2019/7/5 0005
 * @description:
 */
public class UserDetailBean implements Serializable {

    private int code;
    private String message;
    private UserDetailBean.DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        private List<String> images;//banner
        private List<String> tags;
        private List<String> sendList;
        private List<String> receiveList;
        private List<String> visitorList;
        private String receiveNum;
        private String sendNum;
        private String nickName;
        private String photo;
        private String signature;
        private String sex;
        private String age;
        private String socialId;
        private String job;
        private String registerTime;
        private String constellation;//星座
        private String homeTown;
        private String emotion;//情感状态

        public List<String> getVisitorList() {
            return visitorList;
        }

        public void setVisitorList(List<String> visitorList) {
            this.visitorList = visitorList;
        }

        public String getReceiveNum() {
            return receiveNum;
        }

        public void setReceiveNum(String receiveNum) {
            this.receiveNum = receiveNum;
        }

        public String getSendNum() {
            return sendNum;
        }

        public void setSendNum(String sendNum) {
            this.sendNum = sendNum;
        }

        public String getSocialId() {
            return socialId;
        }

        public void setSocialId(String socialId) {
            this.socialId = socialId;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        public List<String> getTags() {
            return tags;
        }

        public void setTags(List<String> tags) {
            this.tags = tags;
        }

        public List<String> getSendList() {
            return sendList;
        }

        public void setSendList(List<String> sendList) {
            this.sendList = sendList;
        }

        public List<String> getReceiveList() {
            return receiveList;
        }

        public void setReceiveList(List<String> receiveList) {
            this.receiveList = receiveList;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getJob() {
            return job;
        }

        public void setJob(String job) {
            this.job = job;
        }

        public String getRegisterTime() {
            return registerTime;
        }

        public void setRegisterTime(String registerTime) {
            this.registerTime = registerTime;
        }

        public String getConstellation() {
            return constellation;
        }

        public void setConstellation(String constellation) {
            this.constellation = constellation;
        }

        public String getHomeTown() {
            return homeTown;
        }

        public void setHomeTown(String homeTown) {
            this.homeTown = homeTown;
        }

        public String getEmotion() {
            return emotion;
        }

        public void setEmotion(String emotion) {
            this.emotion = emotion;
        }

    }

}
