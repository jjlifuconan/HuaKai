package com.social.happychat.ui.home.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author Administrator
 * @date 2019/7/2 0002
 * @description:
 */
public class CommentListBean implements Serializable {

    /**
     * code : 200
     * data : [{"praiseNum":"1","nickName":"形同陌路","photo":"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_93814250_1560997114407.png?x-oss-process=image/resize,m_mfit,h_200,w_200","comment":"其实我知道的，我在等待着快乐的救赎","sex":"1","age":"24"},{"praiseNum":"2","nickName":"孤心","photo":"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_56792081_1547782218848.jpeg?x-oss-process=image/resize,m_mfit,h_200,w_200","comment":"亲爱的孤单。。请你祝我生日快乐","sex":"1","age":"26"},{"praiseNum":"3","nickName":"作茧自缚","photo":"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_79501384_1560704521160.jpeg?x-oss-process=image/resize,m_mfit,h_200,w_200","comment":"明媚的忧伤、以后各自为安","sex":"1","age":"24"},{"praiseNum":"4","nickName":"入骨相思","photo":"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_93417520_1559667904089.png?x-oss-process=image/resize,m_mfit,h_200,w_200","comment":"◇ 悲伤都扎着蝴蝶结。","sex":"1","age":"24"},{"praiseNum":"5","nickName":"旧人、","photo":"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_58063291_1561161871992.jpeg?x-oss-process=image/resize,m_mfit,h_200,w_200","comment":"最可悲的是，你喜欢的人以为你喜欢着别人","sex":"1","age":"22"},{"praiseNum":"6","nickName":"握不住的沙","photo":"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_70539826_1556330493275.jpeg?x-oss-process=image/resize,m_mfit,h_200,w_200","comment":"为什么每份爱情结尾，无非别离，总是别离。","sex":"1","age":"21"},{"praiseNum":"7","nickName":"悲伤的季节","photo":"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_51307692_1558410121242.jpeg?x-oss-process=image/resize,m_mfit,h_200,w_200","comment":"我承认 悲伤是一种致命的情感。","sex":"1","age":"24"},{"praiseNum":"8","nickName":"心为你而葬","photo":"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_1469257_1561097657704.png?x-oss-process=image/resize,m_mfit,h_200,w_200","comment":"别离之夏，甜蜜做序曲，悲伤做结局。","sex":"1","age":"25"},{"praiseNum":"9","nickName":"笑如烟霞","photo":"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_84752093_1560134862786.jpeg?x-oss-process=image/resize,m_mfit,h_200,w_200","comment":"幸福的花儿，终究有一天陨落成泥滋润着悲伤。","sex":"1","age":"24"},{"praiseNum":"10","nickName":"荧光般温柔","photo":"user_header_36527940_1543761913496.jpeg?x-oss-process=image/resize,m_mfit,h_200,w_200 (200x200)","comment":"等爱的人，有最痛苦的希望，和最甜蜜的绝望。","sex":"1","age":"24"},{"praiseNum":"11","nickName":"寂静深夜 寂寞如雪","photo":"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_93814250_1560997114407.png?x-oss-process=image/resize,m_mfit,h_200,w_200","comment":"心到痛处，谁还卑微的挽留。","sex":"1","age":"24"}]
     * message :
     */

    private int code;
    private String message;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * praiseNum : 1
         * nickName : 形同陌路
         * photo : http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_93814250_1560997114407.png?x-oss-process=image/resize,m_mfit,h_200,w_200
         * comment : 其实我知道的，我在等待着快乐的救赎
         * sex : 1
         * age : 24
         */

        private int praiseNum;
        private String nickName;
        private String photo;
        private String comment;
        private String sex;
        private String age;
        private String time;

        public int getPraiseNum() {
            return praiseNum;
        }

        public void setPraiseNum(int praiseNum) {
            this.praiseNum = praiseNum;
        }

        public String getNickName() {
            return nickName;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
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

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
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
    }
}
