package com.social.happychat.ui.home.bean;

import com.social.happychat.bean.BaseBean;

import java.util.List;

/**
 * @author Administrator
 * @date 2019/7/2 0002
 * @description:
 */
public class GrabListBean extends BaseBean<GrabListBean> {


    /**
     * firstPage : true
     * hasNextPage : false
     * hasPreviousPage : false
     * lastPage : false
     * limit : 20
     * list : [{"headPhotoUrl":"","id":1,"nickName":"测试5","status":0,"userAge":0,"userId":26,"userSex":1,"userSign":""}]
     * pageNumber : 1
     * pages : 1
     * total : 1
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
         * headPhotoUrl :
         * id : 1
         * nickName : 测试5
         * status : 0
         * userAge : 0
         * userId : 26
         * userSex : 1
         * userSign :
         */

        private String headPhotoUrl;
        private int id;
        private String nickName;
        private int status;
        private int userAge;
        private int userId;
        private int userSex;
        private String userSign;

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
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
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

        public int getUserSex() {
            return userSex;
        }

        public void setUserSex(int userSex) {
            this.userSex = userSex;
        }

        public String getUserSign() {
            return userSign;
        }

        public void setUserSign(String userSign) {
            this.userSign = userSign;
        }
    }
}
