package com.social.happychat.ui.home.bean;

import com.social.happychat.bean.BaseBean;

import java.util.List;

/**
 * @author Administrator
 * @date 2019/7/2 0002
 * @description:
 */
public class GiftRecordBean extends BaseBean<GiftRecordBean> {

    /**
     * firstPage : true
     * hasNextPage : false
     * hasPreviousPage : false
     * lastPage : false
     * limit : 20
     * list : [{"channel":1,"dynamicId":19,"giftCount":1,"giftId":1,"giftName":"守护符","giftPrice":1314,"id":29,"rewardUserHeadPhotoUrl":"","rewardUserId":28,"userId":25},{"channel":1,"dynamicId":19,"giftCount":1,"giftId":2,"giftName":"西瓜","giftPrice":500,"id":28,"rewardUserHeadPhotoUrl":"http://happychat-pic.oss-cn-hangzhou.aliyuncs.com/4b7546c1ac1f4ec09356806449ce9997.jpeg?Expires=1878275467&OSSAccessKeyId=LTAIDqmQs3MHEI7Q&Signature=%2FHbRNmpRZFAwlHgjYMHUEob3ATc%3D","rewardUserId":25,"userId":25},{"channel":1,"dynamicId":19,"giftCount":1,"giftId":1,"giftName":"守护符","giftPrice":1314,"id":27,"rewardUserHeadPhotoUrl":"http://happychat-pic.oss-cn-hangzhou.aliyuncs.com/4b7546c1ac1f4ec09356806449ce9997.jpeg?Expires=1878275467&OSSAccessKeyId=LTAIDqmQs3MHEI7Q&Signature=%2FHbRNmpRZFAwlHgjYMHUEob3ATc%3D","rewardUserId":25,"userId":25},{"channel":1,"dynamicId":19,"giftCount":1,"giftId":1,"giftName":"守护符","giftPrice":1314,"id":26,"rewardUserHeadPhotoUrl":"http://happychat-pic.oss-cn-hangzhou.aliyuncs.com/4b7546c1ac1f4ec09356806449ce9997.jpeg?Expires=1878275467&OSSAccessKeyId=LTAIDqmQs3MHEI7Q&Signature=%2FHbRNmpRZFAwlHgjYMHUEob3ATc%3D","rewardUserId":25,"userId":25},{"channel":1,"dynamicId":19,"giftCount":1,"giftId":2,"giftName":"西瓜","giftPrice":500,"id":25,"rewardUserHeadPhotoUrl":"http://happychat-pic.oss-cn-hangzhou.aliyuncs.com/4b7546c1ac1f4ec09356806449ce9997.jpeg?Expires=1878275467&OSSAccessKeyId=LTAIDqmQs3MHEI7Q&Signature=%2FHbRNmpRZFAwlHgjYMHUEob3ATc%3D","rewardUserId":25,"userId":25},{"channel":1,"dynamicId":19,"giftCount":1,"giftId":2,"giftName":"西瓜","giftPrice":500,"id":24,"rewardUserHeadPhotoUrl":"http://happychat-pic.oss-cn-hangzhou.aliyuncs.com/4b7546c1ac1f4ec09356806449ce9997.jpeg?Expires=1878275467&OSSAccessKeyId=LTAIDqmQs3MHEI7Q&Signature=%2FHbRNmpRZFAwlHgjYMHUEob3ATc%3D","rewardUserId":25,"userId":25},{"channel":1,"dynamicId":19,"giftCount":1,"giftId":2,"giftName":"西瓜","giftPrice":500,"id":23,"rewardUserHeadPhotoUrl":"http://happychat-pic.oss-cn-hangzhou.aliyuncs.com/4b7546c1ac1f4ec09356806449ce9997.jpeg?Expires=1878275467&OSSAccessKeyId=LTAIDqmQs3MHEI7Q&Signature=%2FHbRNmpRZFAwlHgjYMHUEob3ATc%3D","rewardUserId":25,"userId":25},{"channel":1,"dynamicId":19,"giftCount":1,"giftId":2,"giftName":"西瓜","giftPrice":500,"id":22,"rewardUserHeadPhotoUrl":"http://happychat-pic.oss-cn-hangzhou.aliyuncs.com/4b7546c1ac1f4ec09356806449ce9997.jpeg?Expires=1878275467&OSSAccessKeyId=LTAIDqmQs3MHEI7Q&Signature=%2FHbRNmpRZFAwlHgjYMHUEob3ATc%3D","rewardUserId":25,"userId":25},{"channel":1,"dynamicId":19,"giftCount":1,"giftId":2,"giftName":"西瓜","giftPrice":500,"id":21,"rewardUserHeadPhotoUrl":"http://happychat-pic.oss-cn-hangzhou.aliyuncs.com/4b7546c1ac1f4ec09356806449ce9997.jpeg?Expires=1878275467&OSSAccessKeyId=LTAIDqmQs3MHEI7Q&Signature=%2FHbRNmpRZFAwlHgjYMHUEob3ATc%3D","rewardUserId":25,"userId":25},{"channel":1,"dynamicId":19,"giftCount":1,"giftId":1,"giftName":"守护符","giftPrice":1314,"id":20,"rewardUserHeadPhotoUrl":"http://happychat-pic.oss-cn-hangzhou.aliyuncs.com/4b7546c1ac1f4ec09356806449ce9997.jpeg?Expires=1878275467&OSSAccessKeyId=LTAIDqmQs3MHEI7Q&Signature=%2FHbRNmpRZFAwlHgjYMHUEob3ATc%3D","rewardUserId":25,"userId":25},{"channel":1,"dynamicId":19,"giftCount":1,"giftId":1,"giftName":"守护符","giftPrice":1314,"id":19,"rewardUserHeadPhotoUrl":"http://happychat-pic.oss-cn-hangzhou.aliyuncs.com/4b7546c1ac1f4ec09356806449ce9997.jpeg?Expires=1878275467&OSSAccessKeyId=LTAIDqmQs3MHEI7Q&Signature=%2FHbRNmpRZFAwlHgjYMHUEob3ATc%3D","rewardUserId":25,"userId":25},{"channel":1,"dynamicId":19,"giftCount":1,"giftId":1,"giftName":"守护符","giftPrice":1314,"id":18,"rewardUserHeadPhotoUrl":"","rewardUserId":32,"userId":25},{"channel":1,"dynamicId":19,"giftCount":1,"giftId":1,"giftName":"守护符","giftPrice":1314,"id":15,"rewardUserHeadPhotoUrl":"","rewardUserId":32,"userId":25},{"channel":1,"dynamicId":19,"giftCount":1,"giftId":1,"giftName":"守护符","giftPrice":1314,"id":14,"rewardUserHeadPhotoUrl":"","rewardUserId":32,"userId":25}]
     * pageNumber : 1
     * pages : 1
     * total : 14
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
         * channel : 1
         * dynamicId : 19
         * giftCount : 1
         * giftId : 1
         * giftName : 守护符
         * giftPrice : 1314
         * id : 29
         * rewardUserHeadPhotoUrl :
         * rewardUserId : 28
         * userId : 25
         */

        private int channel;
        private int dynamicId;
        private int giftCount;
        private int giftId;
        private String giftName;
        private int giftPrice;
        private int id;
        private String rewardUserHeadPhotoUrl;
        private int rewardUserId;
        private int userId;

        public int getChannel() {
            return channel;
        }

        public void setChannel(int channel) {
            this.channel = channel;
        }

        public int getDynamicId() {
            return dynamicId;
        }

        public void setDynamicId(int dynamicId) {
            this.dynamicId = dynamicId;
        }

        public int getGiftCount() {
            return giftCount;
        }

        public void setGiftCount(int giftCount) {
            this.giftCount = giftCount;
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

        public int getGiftPrice() {
            return giftPrice;
        }

        public void setGiftPrice(int giftPrice) {
            this.giftPrice = giftPrice;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getRewardUserHeadPhotoUrl() {
            return rewardUserHeadPhotoUrl;
        }

        public void setRewardUserHeadPhotoUrl(String rewardUserHeadPhotoUrl) {
            this.rewardUserHeadPhotoUrl = rewardUserHeadPhotoUrl;
        }

        public int getRewardUserId() {
            return rewardUserId;
        }

        public void setRewardUserId(int rewardUserId) {
            this.rewardUserId = rewardUserId;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
