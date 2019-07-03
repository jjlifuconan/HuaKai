package com.social.huakai.ui.home.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author Administrator
 * @date 2019/7/2 0002
 * @description:
 */
public class PraiseListBean implements Serializable {

    /**
     * code : 200
     * data : [{"ranking":"1","nickName":"形同陌路","photo":"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_93814250_1560997114407.png?x-oss-process=image/resize,m_mfit,h_200,w_200","signature":"其实我知道的，我在等待着快乐的救赎","sex":"1","age":"24"},{"ranking":"2","nickName":"孤心","photo":"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_56792081_1547782218848.jpeg?x-oss-process=image/resize,m_mfit,h_200,w_200","signature":"亲爱的孤单。。请你祝我生日快乐","sex":"1","age":"26"},{"ranking":"3","nickName":"作茧自缚","photo":"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_79501384_1560704521160.jpeg?x-oss-process=image/resize,m_mfit,h_200,w_200","signature":"明媚的忧伤、以后各自为安","sex":"1","age":"24"},{"ranking":"4","nickName":"入骨相思","photo":"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_93417520_1559667904089.png?x-oss-process=image/resize,m_mfit,h_200,w_200","signature":"◇ 悲伤都扎着蝴蝶结。","sex":"1","age":"24"},{"ranking":"5","nickName":"旧人、","photo":"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_58063291_1561161871992.jpeg?x-oss-process=image/resize,m_mfit,h_200,w_200","signature":"最可悲的是，你喜欢的人以为你喜欢着别人","sex":"1","age":"22"},{"ranking":"6","nickName":"握不住的沙","photo":"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_70539826_1556330493275.jpeg?x-oss-process=image/resize,m_mfit,h_200,w_200","signature":"为什么每份爱情结尾，无非别离，总是别离。","sex":"1","age":"21"},{"ranking":"7","nickName":"悲伤的季节","photo":"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_51307692_1558410121242.jpeg?x-oss-process=image/resize,m_mfit,h_200,w_200","signature":"我承认 悲伤是一种致命的情感。","sex":"1","age":"24"},{"ranking":"8","nickName":"心为你而葬","photo":"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_1469257_1561097657704.png?x-oss-process=image/resize,m_mfit,h_200,w_200","signature":"别离之夏，甜蜜做序曲，悲伤做结局。","sex":"1","age":"25"},{"ranking":"9","nickName":"笑如烟霞","photo":"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_84752093_1560134862786.jpeg?x-oss-process=image/resize,m_mfit,h_200,w_200","signature":"幸福的花儿，终究有一天陨落成泥滋润着悲伤。","sex":"1","age":"24"},{"ranking":"10","nickName":"荧光般温柔","photo":"user_header_36527940_1543761913496.jpeg?x-oss-process=image/resize,m_mfit,h_200,w_200 (200x200)","signature":"等爱的人，有最痛苦的希望，和最甜蜜的绝望。","sex":"1","age":"24"},{"ranking":"11","nickName":"寂静深夜 寂寞如雪","photo":"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_93814250_1560997114407.png?x-oss-process=image/resize,m_mfit,h_200,w_200","signature":"心到痛处，谁还卑微的挽留。","sex":"1","age":"24"}]
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
         * ranking : 1
         * nickName : 形同陌路
         * photo : http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_93814250_1560997114407.png?x-oss-process=image/resize,m_mfit,h_200,w_200
         * signature : 其实我知道的，我在等待着快乐的救赎
         * sex : 1
         * age : 24
         */

        private int ranking;
        private String nickName;
        private String photo;
        private String signature;
        private String sex;
        private String age;
        private String coin;

        public int getRanking() {
            return ranking;
        }

        public void setRanking(int ranking) {
            this.ranking = ranking;
        }

        public String getNickName() {
            return nickName;
        }

        public String getCoin() {
            return coin;
        }

        public void setCoin(String coin) {
            this.coin = coin;
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
    }
}
