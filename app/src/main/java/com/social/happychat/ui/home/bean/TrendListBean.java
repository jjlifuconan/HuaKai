package com.social.happychat.ui.home.bean;

import com.social.happychat.bean.BaseBean;

import java.io.Serializable;
import java.util.List;

/**
 * @author Administrator
 * @date 2019/7/7
 * @description:
 */
public class TrendListBean extends BaseBean<TrendListBean> {
    private boolean firstPage;
    private boolean hasNextPage;
    private boolean hasPreviousPage;
    private boolean lastPage;
    private int limit;
    private int pageNumber;
    private int pages;
    private int total;
    private List<ListBean> list;

    public boolean isFirstPage() {
        return firstPage;
    }

    public void setFirstPage(boolean firstPage) {
        this.firstPage = firstPage;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }

    public void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

    public boolean isLastPage() {
        return lastPage;
    }

    public void setLastPage(boolean lastPage) {
        this.lastPage = lastPage;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean implements Serializable {
        /**
         * commentCount : 0
         * commentList : []
         * dynamicInfo : 第一条发布
         * dynamicTitle :
         * giftCount : 0
         * id : 4
         * isPraise : 0
         * praiseCount : 0
         * publishLocation : 南京
         * readCount : 0
         * transferCount : 0
         * user : {"headPhotoUrl":"","nickName":"测试1","userAge":0,"userSex":0}
         * userFiles : [{"fileId":"8ccd3294bef2442eba8829ea51556ebe","fileUrl":"http://happychat-pic.oss-cn-hangzhou.aliyuncs.com/30cac2320cf145cb9525d92ef74c8bc5.jpeg?Expires=1877858800&OSSAccessKeyId=LTAIDqmQs3MHEI7Q&Signature=XvA5E6futUsnO%2FU2uSVwvrmLAuU%3D"},{"fileId":"17233b4d4d924294bb59f94c5aa10055","fileUrl":"http://happychat-pic.oss-cn-hangzhou.aliyuncs.com/5c799cba09df40eab111f2f231117e46.jpeg?Expires=1877858801&OSSAccessKeyId=LTAIDqmQs3MHEI7Q&Signature=obCoFFyzuA4JPCyYmiixBUGT%2FKw%3D"},{"fileId":"b25ac74cc7034198ad259adb9dab9a20","fileUrl":"http://happychat-pic.oss-cn-hangzhou.aliyuncs.com/b3ee6de583a54de58721c30ffaf328b4.jpeg?Expires=1877858801&OSSAccessKeyId=LTAIDqmQs3MHEI7Q&Signature=FvGfUESIwHjyX2NsbI9OfuGmOYI%3D"},{"fileId":"da206bf9405544a5b58d89ce2b14b86b","fileUrl":"http://happychat-pic.oss-cn-hangzhou.aliyuncs.com/a72a98be0230473284e30c8c0bcd392e.jpeg?Expires=1877858802&OSSAccessKeyId=LTAIDqmQs3MHEI7Q&Signature=8jWLfaJjXX4fn3LQ%2FIXlsMB6kek%3D"},{"fileId":"aafba76208194beb88b4efc851895086","fileUrl":"http://happychat-pic.oss-cn-hangzhou.aliyuncs.com/392418c88b7644a9871babcdac2c7211.jpeg?Expires=1877858803&OSSAccessKeyId=LTAIDqmQs3MHEI7Q&Signature=vQvGPJoroB4XNUJRwVdiibvnVvw%3D"},{"fileId":"ffdc4fd4e7b24470be7808cba8380243","fileUrl":"http://happychat-pic.oss-cn-hangzhou.aliyuncs.com/18d9b857e8a041d398d7c926c74ed89f.jpeg?Expires=1877858803&OSSAccessKeyId=LTAIDqmQs3MHEI7Q&Signature=CIzPtbktFAGy1ZlzseTNAyvVpmc%3D"},{"fileId":"ed06bd66eb0741feb231e9b4dd31aa73","fileUrl":"http://happychat-pic.oss-cn-hangzhou.aliyuncs.com/6ba3f100031a4ab79aad2e356c29f120.jpeg?Expires=1877858804&OSSAccessKeyId=LTAIDqmQs3MHEI7Q&Signature=%2BChB7WZVTpA4mDGUlaVhxg2zi%2FE%3D"}]
         */

        private int commentCount;
        private String dynamicInfo;
        private String dynamicTitle;
        private int giftCount;
        private int id;
        private int isPraise;
        private int praiseCount;
        private String publishLocation;
        private int readCount;
        private int transferCount;
        private UserBean user;
        private List<?> commentList;
        private List<UserFilesBean> userFiles;

        public int getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(int commentCount) {
            this.commentCount = commentCount;
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

        public int getGiftCount() {
            return giftCount;
        }

        public void setGiftCount(int giftCount) {
            this.giftCount = giftCount;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIsPraise() {
            return isPraise;
        }

        public void setIsPraise(int isPraise) {
            this.isPraise = isPraise;
        }

        public int getPraiseCount() {
            return praiseCount;
        }

        public void setPraiseCount(int praiseCount) {
            this.praiseCount = praiseCount;
        }

        public String getPublishLocation() {
            return publishLocation;
        }

        public void setPublishLocation(String publishLocation) {
            this.publishLocation = publishLocation;
        }

        public int getReadCount() {
            return readCount;
        }

        public void setReadCount(int readCount) {
            this.readCount = readCount;
        }

        public int getTransferCount() {
            return transferCount;
        }

        public void setTransferCount(int transferCount) {
            this.transferCount = transferCount;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public List<?> getCommentList() {
            return commentList;
        }

        public void setCommentList(List<?> commentList) {
            this.commentList = commentList;
        }

        public List<UserFilesBean> getUserFiles() {
            return userFiles;
        }

        public void setUserFiles(List<UserFilesBean> userFiles) {
            this.userFiles = userFiles;
        }

        public static class UserBean implements Serializable{
            /**
             * headPhotoUrl :
             * nickName : 测试1
             * userAge : 0
             * userSex : 0
             */

            private String headPhotoUrl;
            private String nickName;
            private int userAge;
            private int userSex;

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

            public int getUserSex() {
                return userSex;
            }

            public void setUserSex(int userSex) {
                this.userSex = userSex;
            }
        }

        public static class UserFilesBean implements Serializable{
            /**
             * fileId : 8ccd3294bef2442eba8829ea51556ebe
             * fileUrl : http://happychat-pic.oss-cn-hangzhou.aliyuncs.com/30cac2320cf145cb9525d92ef74c8bc5.jpeg?Expires=1877858800&OSSAccessKeyId=LTAIDqmQs3MHEI7Q&Signature=XvA5E6futUsnO%2FU2uSVwvrmLAuU%3D
             */

            private String fileId;
            private String fileUrl;

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
        }
    }
}
