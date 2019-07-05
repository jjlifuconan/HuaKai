package com.social.huakai.ui.home.model;

import com.google.gson.GsonBuilder;
import com.social.huakai.http.RequestImpl;
import com.social.huakai.ui.home.bean.GiftRecordBean;
import com.social.huakai.ui.home.bean.GiftShopBean;


/**
 * @author Administrator
 * @date 2019/6/27 0027
 * @description:
 */
public class GiftShopViewModel {
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
        sss = "{\"code\":200,\"data\":[{\"price\":1,\"nickName\":\"单身闯天下\",\"photo\":\"http://pic0.huitu.com/pic/20190624/1912_20190624130041628140_0.jpg\",\"giftName\":\"飞机\",\"sex\":\"0\",\"time\":\"刚刚\",\"age\":\"24\"},{\"price\":1,\"nickName\":\"形同陌路\",\"photo\":\"http://pic192.nipic.com/pic/20181117/27018288_133229104081_4.jpg\",\"giftName\":\"火箭\",\"sex\":\"1\",\"time\":\"5分钟前\",\"age\":\"24\"},{\"price\":2,\"nickName\":\"笑看你变狗\",\"photo\":\"http://pic0.huitu.com/pic/20190602/1827356_20190602215214715149_0.jpg\",\"giftName\":\"玫瑰花\",\"sex\":\"0\",\"time\":\"7-4 16:43\",\"age\":\"26\"},{\"price\":4,\"nickName\":\"男人必须傲↑\",\"photo\":\"http://pic117.huitu.com/pic/20190413/1825142_20190413133218311020_0.jpg\",\"giftName\":\"钻戒\",\"sex\":\"0\",\"time\":\"7-4 16:23\",\"age\":\"24\"},{\"price\":5,\"nickName\":\"此人须珍藏\",\"photo\":\"http://pic124.huitu.com/pic/20190703/1841574_20190703083512250020_0.jpg\",\"giftName\":\"西瓜\",\"sex\":\"0\",\"time\":\"7-4 16:11\",\"age\":\"22\"},{\"price\":3,\"nickName\":\"作茧自缚\",\"photo\":\"http://pic0.huitu.com/pic/20190610/1028527_20190610221750779144_0.jpg\",\"giftName\":\"啤酒\",\"sex\":\"1\",\"time\":\"7-4 16:10\",\"age\":\"24\"},{\"price\":4,\"nickName\":\"入骨相思\",\"photo\":\"http://pic112.huitu.com/pic/20181209/1367568_20181209204106498015_0.jpg\",\"giftName\":\"棒棒糖\",\"sex\":\"1\",\"time\":\"7-4 15:55\",\"age\":\"24\"},{\"price\":6,\"nickName\":\"霸天绝地\",\"photo\":\"http://pic123.huitu.com/pic/20190622/1875964_20190622164913023060_0.jpg\",\"giftName\":\"手表\",\"sex\":\"0\",\"time\":\"7-4 15:43\",\"age\":\"21\"},{\"price\":7,\"nickName\":\"御剑狂嚣\",\"photo\":\"http://pic111.huitu.com/pic/20181117/21146_20181117084504603070_0.jpg\",\"giftName\":\"法拉利\",\"sex\":\"0\",\"time\":\"7-4 15:26\",\"age\":\"24\"},{\"price\":9,\"nickName\":\"朝夕盼兮\",\"photo\":\"http://pic193.nipic.com/pic/20181124/27627108_152829930000_4.jpg\",\"giftName\":\"海洋之心\",\"time\":\"7-4 15:24\",\"sex\":\"0\",\"age\":\"24\"},{\"price\":8,\"nickName\":\"心为你而葬\",\"photo\":\"http://pic122.huitu.com/pic/20190529/874816_20190529100448670020_0.jpg\",\"giftName\":\"跑车\",\"sex\":\"1\",\"time\":\"7-4 15:11\",\"age\":\"25\"},{\"price\":1,\"nickName\":\"单身闯天下\",\"photo\":\"http://pic123.huitu.com/pic/20190620/1632004_20190620121033385070_0.jpg\",\"giftName\":\"红酒\",\"sex\":\"0\",\"time\":\"7-4 15:08\",\"age\":\"24\"},{\"price\":2,\"nickName\":\"笑看你变狗\",\"photo\":\"http://pic0.huitu.com/pic/20190408/1593369_20190408162542725145_0.jpg\",\"giftName\":\"口红\",\"sex\":\"0\",\"time\":\"7-4 14:54\",\"age\":\"26\"},{\"price\":4,\"nickName\":\"男人必须傲↑\",\"photo\":\"http://pic116.huitu.com/pic/20190329/1888910_20190329202231166070_0.jpg\",\"giftName\":\"耳环\",\"sex\":\"0\",\"time\":\"7-4 14:33\",\"age\":\"24\"},{\"price\":5,\"nickName\":\"此人须珍藏\",\"photo\":\"http://pic122.huitu.com/pic/20190609/2143707_20190609095713272070_0.jpg\",\"giftName\":\"墨镜\",\"sex\":\"0\",\"time\":\"7-4 14:26\",\"age\":\"22\"},{\"price\":6,\"nickName\":\"霸天绝地\",\"photo\":\"http://pic122.huitu.com/pic/20190602/2110735_20190602144458038060_0.jpg\",\"giftName\":\"巧克力\",\"sex\":\"0\",\"time\":\"7-4 14:16\",\"age\":\"21\"},{\"price\":11,\"nickName\":\"寂静深夜 寂寞如雪\",\"photo\":\"http://pic120.huitu.com/pic/20190418/79255_20190418094537779060_0.jpg\",\"giftName\":\"包包\",\"time\":\"7-4 14:11\",\"sex\":\"1\",\"age\":\"24\"},{\"price\":1,\"nickName\":\"形同陌路\",\"photo\":\"http://pic124.huitu.com/pic/20190630/2052109_20190630233542555020_0.jpg\",\"giftName\":\"蛋糕\",\"sex\":\"1\",\"time\":\"7-4 14:04\",\"age\":\"24\"},{\"price\":7,\"nickName\":\"御剑狂嚣\",\"photo\":\"http://pic216.nipic.com/pic/20190514/26447405_111634954089_4.jpg\",\"giftName\":\"香水\",\"sex\":\"0\",\"time\":\"7-2 14:44\",\"age\":\"24\"},{\"price\":9,\"nickName\":\"朝夕盼兮\",\"photo\":\"http://pic106.huitu.com/pic/20180603/1105521_20180603111351890020_0.jpg\",\"giftName\":\"劳斯莱斯\",\"time\":\"7-2 12:54\",\"sex\":\"0\",\"age\":\"24\"}],\"message\":\"\"}";
        GiftShopBean bean = new GsonBuilder().serializeNulls().create().fromJson(sss, GiftShopBean.class);
        listener.loadSuccess(bean);
//        listener.addSubscription(subscription);
    }

}
