package com.social.huakai.ui.home.model;

import com.google.gson.GsonBuilder;
import com.social.huakai.http.RequestImpl;
import com.social.huakai.ui.home.bean.GiftRecordBean;


/**
 * @author Administrator
 * @date 2019/6/27 0027
 * @description:
 */
public class GiftViewModel {
    private int page;
    private int per_page;

    public void setData(int page, int per_page) {
        this.page = page;
        this.per_page = per_page;
    }

    public void getGiftData(final RequestImpl listener) {
//        Subscription subscription = HttpClient.Builder.getGankIOServer().getGiftData(id, page, per_page)
//                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<GankIoDataBean>() {
//                    @Override
//                    public void onCompleted() {
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        listener.loadFailed();
//
//                    }
//
//                    @Override
//                    public void onNext(GankIoDataBean gankIoDataBean) {
//                        listener.loadSuccess(gankIoDataBean);
//
//                    }
//                });
        String sss = "";
        sss = "{\"code\":200,\"data\":[{\"giftNum\":1,\"nickName\":\"单身闯天下\",\"photo\":\"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_50832164_1561163576484.jpeg?x-oss-process=image/resize,m_mfit,h_200,w_200\",\"giftName\":\"飞机\",\"sex\":\"0\",\"time\":\"刚刚\",\"age\":\"24\"},{\"giftNum\":1,\"nickName\":\"形同陌路\",\"photo\":\"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_93814250_1560997114407.png?x-oss-process=image/resize,m_mfit,h_200,w_200\",\"giftName\":\"火箭\",\"sex\":\"1\",\"time\":\"5分钟前\",\"age\":\"24\"},{\"giftNum\":2,\"nickName\":\"笑看你变狗\",\"photo\":\"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_31978625_1561346091154.jpeg?x-oss-process=image/resize,m_mfit,h_200,w_200\",\"giftName\":\"玫瑰花\",\"sex\":\"0\",\"time\":\"7-4 16:43\",\"age\":\"26\"},{\"giftNum\":4,\"nickName\":\"男人必须傲↑\",\"photo\":\"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_92364017_1549162345087.png?x-oss-process=image/resize,m_mfit,h_200,w_200\",\"giftName\":\"钻戒\",\"sex\":\"0\",\"time\":\"7-4 16:23\",\"age\":\"24\"},{\"giftNum\":5,\"nickName\":\"此人须珍藏\",\"photo\":\"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_80675219_1559037818657.jpeg?x-oss-process=image/resize,m_mfit,h_200,w_200\",\"giftName\":\"飞机\",\"sex\":\"0\",\"time\":\"7-4 16:11\",\"age\":\"22\"},{\"giftNum\":3,\"nickName\":\"作茧自缚\",\"photo\":\"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_79501384_1560704521160.jpeg?x-oss-process=image/resize,m_mfit,h_200,w_200\",\"giftName\":\"玫瑰花\",\"sex\":\"1\",\"time\":\"7-4 16:10\",\"age\":\"24\"},{\"giftNum\":4,\"nickName\":\"入骨相思\",\"photo\":\"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_93417520_1559667904089.png?x-oss-process=image/resize,m_mfit,h_200,w_200\",\"giftName\":\"玫瑰花\",\"sex\":\"1\",\"time\":\"7-4 15:55\",\"age\":\"24\"},{\"giftNum\":6,\"nickName\":\"霸天绝地\",\"photo\":\"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_26578941_1560079406628.png?x-oss-process=image/resize,m_mfit,h_200,w_200\",\"giftName\":\"让女人为你改变之前，你先问问你有没有装修她的本钱。\",\"sex\":\"0\",\"time\":\"7-4 15:43\",\"age\":\"21\"},{\"giftNum\":7,\"nickName\":\"御剑狂嚣\",\"photo\":\"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_51307692_1558410121242.jpeg?x-oss-process=image/resize,m_mfit,h_200,w_200\",\"giftName\":\"钻戒\",\"sex\":\"0\",\"time\":\"7-4 15:26\",\"age\":\"24\"},{\"giftNum\":9,\"nickName\":\"朝夕盼兮\",\"photo\":\"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_15472389_1548875114076.png?x-oss-process=image/resize,m_mfit,h_200,w_200\",\"giftName\":\"玫瑰花\",\"time\":\"7-4 15:24\",\"sex\":\"0\",\"age\":\"24\"},{\"giftNum\":8,\"nickName\":\"心为你而葬\",\"photo\":\"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_1469257_1561097657704.png?x-oss-process=image/resize,m_mfit,h_200,w_200\",\"giftName\":\"跑车\",\"sex\":\"1\",\"time\":\"7-4 15:11\",\"age\":\"25\"},{\"giftNum\":1,\"nickName\":\"单身闯天下\",\"photo\":\"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_50832164_1561163576484.jpeg?x-oss-process=image/resize,m_mfit,h_200,w_200\",\"giftName\":\"跑车\",\"sex\":\"0\",\"time\":\"7-4 15:08\",\"age\":\"24\"},{\"giftNum\":2,\"nickName\":\"笑看你变狗\",\"photo\":\"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_31978625_1561346091154.jpeg?x-oss-process=image/resize,m_mfit,h_200,w_200\",\"giftName\":\"飞机\",\"sex\":\"0\",\"time\":\"7-4 14:54\",\"age\":\"26\"},{\"giftNum\":4,\"nickName\":\"男人必须傲↑\",\"photo\":\"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_92364017_1549162345087.png?x-oss-process=image/resize,m_mfit,h_200,w_200\",\"giftName\":\"跑车\",\"sex\":\"0\",\"time\":\"7-4 14:33\",\"age\":\"24\"},{\"giftNum\":5,\"nickName\":\"此人须珍藏\",\"photo\":\"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_80675219_1559037818657.jpeg?x-oss-process=image/resize,m_mfit,h_200,w_200\",\"giftName\":\"钻戒\",\"sex\":\"0\",\"time\":\"7-4 14:26\",\"age\":\"22\"},{\"giftNum\":6,\"nickName\":\"霸天绝地\",\"photo\":\"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_26578941_1560079406628.png?x-oss-process=image/resize,m_mfit,h_200,w_200\",\"giftName\":\"跑车\",\"sex\":\"0\",\"time\":\"7-4 14:16\",\"age\":\"21\"},{\"giftNum\":11,\"nickName\":\"寂静深夜 寂寞如雪\",\"photo\":\"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_93814250_1560997114407.png?x-oss-process=image/resize,m_mfit,h_200,w_200\",\"giftName\":\"飞机\",\"time\":\"7-4 14:11\",\"sex\":\"1\",\"age\":\"24\"},{\"giftNum\":1,\"nickName\":\"形同陌路\",\"photo\":\"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_93814250_1560997114407.png?x-oss-process=image/resize,m_mfit,h_200,w_200\",\"giftName\":\"钻戒\",\"sex\":\"1\",\"time\":\"7-4 14:04\",\"age\":\"24\"},{\"giftNum\":7,\"nickName\":\"御剑狂嚣\",\"photo\":\"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_51307692_1558410121242.jpeg?x-oss-process=image/resize,m_mfit,h_200,w_200\",\"giftName\":\"玫瑰花\",\"sex\":\"0\",\"time\":\"7-2 14:44\",\"age\":\"24\"},{\"giftNum\":9,\"nickName\":\"朝夕盼兮\",\"photo\":\"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_15472389_1548875114076.png?x-oss-process=image/resize,m_mfit,h_200,w_200\",\"giftName\":\"玫瑰花\",\"time\":\"7-2 12:54\",\"sex\":\"0\",\"age\":\"24\"}],\"message\":\"\"}";
        GiftRecordBean bean = new GsonBuilder().serializeNulls().create().fromJson(sss, GiftRecordBean.class);
        listener.loadSuccess(bean);
//        listener.addSubscription(subscription);
    }

}
