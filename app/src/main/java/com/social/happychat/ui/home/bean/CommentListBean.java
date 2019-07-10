package com.social.happychat.ui.home.bean;

import com.social.happychat.bean.BaseBean;

import java.util.List;

/**
 * @author Administrator
 * @date 2019/7/2 0002
 * @description:
 */
public class CommentListBean extends BaseBean<CommentListBean> {


    /**
     * firstPage : true
     * hasNextPage : false
     * hasPreviousPage : false
     * lastPage : false
     * limit : 2
     * list : [{"content":"电脑上你睡吧","dynamicId":5,"headPhotoUrl":"","id":10,"isPraise":0,"praiseCount":0,"replyUserId":0,"replyUserName":"","userId":25,"userName":"测试4","userSex":2},{"content":"军队狙击手就是","dynamicId":5,"headPhotoUrl":"","id":9,"isPraise":0,"praiseCount":0,"replyUserId":0,"replyUserName":"","userId":25,"userName":"测试4","userSex":2}]
     * pageNumber : 1
     * pages : 1
     * total : 2
     */

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

    public static class ListBean {
        /**
         * content : 电脑上你睡吧
         * dynamicId : 5
         * headPhotoUrl :
         * id : 10
         * isPraise : 0
         * praiseCount : 0
         * replyUserId : 0
         * replyUserName :
         * userId : 25
         * userName : 测试4
         * userSex : 2
         */

        private String content;
        private int dynamicId;
        private String time;
        private String headPhotoUrl;
        private int id;
        private int isPraise;
        private int praiseCount;
        private int replyUserId;
        private String replyUserName;
        private int userId;
        private String userName;
        private int userSex;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getDynamicId() {
            return dynamicId;
        }

        public void setDynamicId(int dynamicId) {
            this.dynamicId = dynamicId;
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

        public int getReplyUserId() {
            return replyUserId;
        }

        public void setReplyUserId(int replyUserId) {
            this.replyUserId = replyUserId;
        }

        public String getReplyUserName() {
            return replyUserName;
        }

        public void setReplyUserName(String replyUserName) {
            this.replyUserName = replyUserName;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getUserSex() {
            return userSex;
        }

        public void setUserSex(int userSex) {
            this.userSex = userSex;
        }
    }
}
