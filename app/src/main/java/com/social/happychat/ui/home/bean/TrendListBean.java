package com.social.happychat.ui.home.bean;

import com.social.happychat.bean.BaseBean;

import java.util.List;

/**
 * @author Administrator
 * @date 2019/7/7
 * @description:
 */
public class TrendListBean extends BaseBean<List<TrendListBean>> {
    private String dynamicInfo;
    private String dynamicTitle;
    private int dynamicType;
    private int isPraise;
    private int commentCount;
    private int praiseCount;
    private int readCount;
    private int giftCount;
    private int userId;

    private String publishLocation;
    private List<UserFilesBean> userFiles;

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getPraiseCount() {
        return praiseCount;
    }

    public void setPraiseCount(int praiseCount) {
        this.praiseCount = praiseCount;
    }

    public int getReadCount() {
        return readCount;
    }

    public void setReadCount(int readCount) {
        this.readCount = readCount;
    }

    public int getGiftCount() {
        return giftCount;
    }

    public void setGiftCount(int giftCount) {
        this.giftCount = giftCount;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDynamicInfo() {
        return dynamicInfo;
    }

    public void setDynamicInfo(String dynamicInfo) {
        this.dynamicInfo = dynamicInfo;
    }

    public String getDynamicTitle() {
        return dynamicTitle;
    }

    public void setDynamicTitle(String dynamicTitle) {
        this.dynamicTitle = dynamicTitle;
    }

    public int getDynamicType() {
        return dynamicType;
    }

    public void setDynamicType(int dynamicType) {
        this.dynamicType = dynamicType;
    }

    public int getIsPraise() {
        return isPraise;
    }

    public void setIsPraise(int isPraise) {
        this.isPraise = isPraise;
    }

    public String getPublishLocation() {
        return publishLocation;
    }

    public void setPublishLocation(String publishLocation) {
        this.publishLocation = publishLocation;
    }

    public List<UserFilesBean> getUserFiles() {
        return userFiles;
    }

    public void setUserFiles(List<UserFilesBean> userFiles) {
        this.userFiles = userFiles;
    }

    public static class UserFilesBean {
        /**
         * fileId : string
         * fileUrl : string
         * userId : string
         */

        private String fileId;
        private String fileUrl;
        private String userId;

        public String getFileId() {
            return fileId;
        }

        public void setFileId(String fileId) {
            this.fileId = fileId;
        }

        public String getFileUrl() {
            return fileUrl;
        }

        public void setFileUrl(String fileUrl) {
            this.fileUrl = fileUrl;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }
}
