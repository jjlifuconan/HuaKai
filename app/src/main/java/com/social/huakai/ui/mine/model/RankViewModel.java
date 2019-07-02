package com.social.huakai.ui.mine.model;

import com.google.gson.GsonBuilder;
import com.social.huakai.http.RequestImpl;
import com.social.huakai.ui.mine.bean.RankListBean;


/**
 * @author Administrator
 * @date 2019/6/27 0027
 * @description:
 */
public class RankViewModel {
    private String tabType;
    private String radioType;

    public void setData(String tabType, String radioType) {
        this.tabType = tabType;
        this.radioType = radioType;
    }

    public void getGankIoData(final RequestImpl listener) {
//        Subscription subscription = HttpClient.Builder.getGankIOServer().getGankIoData(id, page, per_page)
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
        String sss = "{\"code\":200,\"data\":[{\"ranking\":1,\"nickName\":\"形同陌路\",\"photo\":\"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_93814250_1560997114407.png?x-oss-process=image/resize,m_mfit,h_200,w_200\",\"signature\":\"其实我知道的，我在等待着快乐的救赎\",\"sex\":\"1\",\"coin\":\"7.88万\",\"age\":\"24\"},{\"ranking\":2,\"nickName\":\"孤心\",\"photo\":\"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_56792081_1547782218848.jpeg?x-oss-process=image/resize,m_mfit,h_200,w_200\",\"signature\":\"亲爱的孤单。。请你祝我生日快乐\",\"sex\":\"1\",\"coin\":\"7.14万\",\"age\":\"26\"},{\"ranking\":3,\"nickName\":\"作茧自缚\",\"photo\":\"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_79501384_1560704521160.jpeg?x-oss-process=image/resize,m_mfit,h_200,w_200\",\"signature\":\"明媚的忧伤、以后各自为安\",\"sex\":\"1\",\"coin\":\"7.01万\",\"age\":\"24\"},{\"ranking\":4,\"nickName\":\"入骨相思\",\"photo\":\"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_93417520_1559667904089.png?x-oss-process=image/resize,m_mfit,h_200,w_200\",\"signature\":\"◇ 悲伤都扎着蝴蝶结。\",\"sex\":\"1\",\"coin\":\"6.36万\",\"age\":\"24\"},{\"ranking\":5,\"nickName\":\"旧人、\",\"photo\":\"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_58063291_1561161871992.jpeg?x-oss-process=image/resize,m_mfit,h_200,w_200\",\"signature\":\"最可悲的是，你喜欢的人以为你喜欢着别人\",\"sex\":\"1\",\"coin\":\"6.28万\",\"age\":\"22\"},{\"ranking\":6,\"nickName\":\"握不住的沙\",\"photo\":\"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_70539826_1556330493275.jpeg?x-oss-process=image/resize,m_mfit,h_200,w_200\",\"signature\":\"为什么每份爱情结尾，无非别离，总是别离。\",\"sex\":\"1\",\"coin\":\"6.17万\",\"age\":\"21\"},{\"ranking\":7,\"nickName\":\"悲伤的季节\",\"photo\":\"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_51307692_1558410121242.jpeg?x-oss-process=image/resize,m_mfit,h_200,w_200\",\"signature\":\"我承认 悲伤是一种致命的情感。\",\"sex\":\"1\",\"coin\":\"6.06万\",\"age\":\"24\"},{\"ranking\":8,\"nickName\":\"心为你而葬\",\"photo\":\"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_1469257_1561097657704.png?x-oss-process=image/resize,m_mfit,h_200,w_200\",\"signature\":\"别离之夏，甜蜜做序曲，悲伤做结局。\",\"sex\":\"1\",\"coin\":\"5.94万\",\"age\":\"25\"},{\"ranking\":9,\"nickName\":\"笑如烟霞\",\"photo\":\"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_84752093_1560134862786.jpeg?x-oss-process=image/resize,m_mfit,h_200,w_200\",\"signature\":\"幸福的花儿，终究有一天陨落成泥滋润着悲伤。\",\"coin\":\"5.69万\",\"sex\":\"1\",\"age\":\"24\"},{\"ranking\":10,\"nickName\":\"荧光般温柔\",\"photo\":\"user_header_36527940_1543761913496.jpeg?x-oss-process=image/resize,m_mfit,h_200,w_200 (200x200)\",\"signature\":\"等爱的人，有最痛苦的希望，和最甜蜜的绝望。\",\"sex\":\"1\",\"coin\":\"5.49万\",\"age\":\"24\"},{\"ranking\":11,\"nickName\":\"寂静深夜 寂寞如雪\",\"photo\":\"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_93814250_1560997114407.png?x-oss-process=image/resize,m_mfit,h_200,w_200\",\"signature\":\"心到痛处，谁还卑微的挽留。\",\"coin\":\"4.41万\",\"sex\":\"1\",\"age\":\"24\"}],\"message\":\"\"}";
        RankListBean bean = new GsonBuilder().serializeNulls().create().fromJson(sss, RankListBean.class);
        listener.loadSuccess(bean);
//        listener.addSubscription(subscription);
    }

}
