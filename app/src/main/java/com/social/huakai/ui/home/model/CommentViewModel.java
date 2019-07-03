package com.social.huakai.ui.home.model;

import com.google.gson.GsonBuilder;
import com.social.huakai.http.RequestImpl;
import com.social.huakai.ui.home.bean.CommentListBean;


/**
 * @author Administrator
 * @date 2019/6/27 0027
 * @description:
 */
public class CommentViewModel {
    private int page;
    private int per_page;

    public void setData(int page, int per_page) {
        this.page = page;
        this.per_page = per_page;
    }

    public void getCommentData(final RequestImpl listener) {
//        Subscription subscription = HttpClient.Builder.getGankIOServer().getCommentData(id, page, per_page)
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
        sss = "{\"code\":200,\"data\":[{\"ranking\":1,\"nickName\":\"单身闯天下\",\"photo\":\"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_50832164_1561163576484.jpeg?x-oss-process=image/resize,m_mfit,h_200,w_200\",\"signature\":\"世界背叛了沵，我为沵背叛世界\",\"sex\":\"0\",\"coin\":\"7.88万\",\"age\":\"24\"},{\"ranking\":1,\"nickName\":\"形同陌路\",\"photo\":\"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_93814250_1560997114407.png?x-oss-process=image/resize,m_mfit,h_200,w_200\",\"signature\":\"其实我知道的，我在等待着快乐的救赎\",\"sex\":\"1\",\"coin\":\"7.88万\",\"age\":\"24\"},{\"ranking\":2,\"nickName\":\"笑看你变狗\",\"photo\":\"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_31978625_1561346091154.jpeg?x-oss-process=image/resize,m_mfit,h_200,w_200\",\"signature\":\"经常被打击，从未被打到\",\"sex\":\"0\",\"coin\":\"7.14万\",\"age\":\"26\"},{\"ranking\":4,\"nickName\":\"男人必须傲↑\",\"photo\":\"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_92364017_1549162345087.png?x-oss-process=image/resize,m_mfit,h_200,w_200\",\"signature\":\"爺一般不生气，生气时不一般。\",\"sex\":\"0\",\"coin\":\"6.36万\",\"age\":\"24\"},{\"ranking\":5,\"nickName\":\"此人须珍藏\",\"photo\":\"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_80675219_1559037818657.jpeg?x-oss-process=image/resize,m_mfit,h_200,w_200\",\"signature\":\"こ現在的尒皒只是簡單的陌生人。\",\"sex\":\"0\",\"coin\":\"6.28万\",\"age\":\"22\"},{\"ranking\":3,\"nickName\":\"作茧自缚\",\"photo\":\"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_79501384_1560704521160.jpeg?x-oss-process=image/resize,m_mfit,h_200,w_200\",\"signature\":\"明媚的忧伤、以后各自为安\",\"sex\":\"1\",\"coin\":\"7.01万\",\"age\":\"24\"},{\"ranking\":4,\"nickName\":\"入骨相思\",\"photo\":\"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_93417520_1559667904089.png?x-oss-process=image/resize,m_mfit,h_200,w_200\",\"signature\":\"◇ 悲伤都扎着蝴蝶结。\",\"sex\":\"1\",\"coin\":\"6.36万\",\"age\":\"24\"},{\"ranking\":6,\"nickName\":\"霸天绝地\",\"photo\":\"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_26578941_1560079406628.png?x-oss-process=image/resize,m_mfit,h_200,w_200\",\"signature\":\"让女人为你改变之前，你先问问你有没有装修她的本钱。\",\"sex\":\"0\",\"coin\":\"6.17万\",\"age\":\"21\"},{\"ranking\":7,\"nickName\":\"御剑狂嚣\",\"photo\":\"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_51307692_1558410121242.jpeg?x-oss-process=image/resize,m_mfit,h_200,w_200\",\"signature\":\"爱他就要把他锁在屋里，他要是想逃跑的话就把腿给他打断。\",\"sex\":\"0\",\"coin\":\"6.06万\",\"age\":\"24\"},{\"ranking\":9,\"nickName\":\"朝夕盼兮\",\"photo\":\"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_15472389_1548875114076.png?x-oss-process=image/resize,m_mfit,h_200,w_200\",\"signature\":\"爷的爱你永远不会懂。\",\"coin\":\"5.69万\",\"sex\":\"0\",\"age\":\"24\"},{\"ranking\":8,\"nickName\":\"心为你而葬\",\"photo\":\"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_1469257_1561097657704.png?x-oss-process=image/resize,m_mfit,h_200,w_200\",\"signature\":\"别离之夏，甜蜜做序曲，悲伤做结局。\",\"sex\":\"1\",\"coin\":\"5.94万\",\"age\":\"25\"},{\"ranking\":1,\"nickName\":\"单身闯天下\",\"photo\":\"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_50832164_1561163576484.jpeg?x-oss-process=image/resize,m_mfit,h_200,w_200\",\"signature\":\"世界背叛了沵，我为沵背叛世界\",\"sex\":\"0\",\"coin\":\"7.88万\",\"age\":\"24\"},{\"ranking\":2,\"nickName\":\"笑看你变狗\",\"photo\":\"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_31978625_1561346091154.jpeg?x-oss-process=image/resize,m_mfit,h_200,w_200\",\"signature\":\"经常被打击，从未被打到\",\"sex\":\"0\",\"coin\":\"7.14万\",\"age\":\"26\"},{\"ranking\":4,\"nickName\":\"男人必须傲↑\",\"photo\":\"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_92364017_1549162345087.png?x-oss-process=image/resize,m_mfit,h_200,w_200\",\"signature\":\"爺一般不生气，生气时不一般。\",\"sex\":\"0\",\"coin\":\"6.36万\",\"age\":\"24\"},{\"ranking\":5,\"nickName\":\"此人须珍藏\",\"photo\":\"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_80675219_1559037818657.jpeg?x-oss-process=image/resize,m_mfit,h_200,w_200\",\"signature\":\"こ現在的尒皒只是簡單的陌生人。\",\"sex\":\"0\",\"coin\":\"6.28万\",\"age\":\"22\"},{\"ranking\":6,\"nickName\":\"霸天绝地\",\"photo\":\"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_26578941_1560079406628.png?x-oss-process=image/resize,m_mfit,h_200,w_200\",\"signature\":\"让女人为你改变之前，你先问问你有没有装修她的本钱。\",\"sex\":\"0\",\"coin\":\"6.17万\",\"age\":\"21\"},{\"ranking\":11,\"nickName\":\"寂静深夜 寂寞如雪\",\"photo\":\"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_93814250_1560997114407.png?x-oss-process=image/resize,m_mfit,h_200,w_200\",\"signature\":\"心到痛处，谁还卑微的挽留。\",\"coin\":\"4.41万\",\"sex\":\"1\",\"age\":\"24\"},{\"ranking\":1,\"nickName\":\"形同陌路\",\"photo\":\"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_93814250_1560997114407.png?x-oss-process=image/resize,m_mfit,h_200,w_200\",\"signature\":\"其实我知道的，我在等待着快乐的救赎\",\"sex\":\"1\",\"coin\":\"7.88万\",\"age\":\"24\"},{\"ranking\":7,\"nickName\":\"御剑狂嚣\",\"photo\":\"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_51307692_1558410121242.jpeg?x-oss-process=image/resize,m_mfit,h_200,w_200\",\"signature\":\"爱他就要把他锁在屋里，他要是想逃跑的话就把腿给他打断。\",\"sex\":\"0\",\"coin\":\"6.06万\",\"age\":\"24\"},{\"ranking\":9,\"nickName\":\"朝夕盼兮\",\"photo\":\"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_15472389_1548875114076.png?x-oss-process=image/resize,m_mfit,h_200,w_200\",\"signature\":\"爷的爱你永远不会懂。\",\"coin\":\"5.69万\",\"sex\":\"0\",\"age\":\"24\"}],\"message\":\"\"}";
        CommentListBean bean = new GsonBuilder().serializeNulls().create().fromJson(sss, CommentListBean.class);
        listener.loadSuccess(bean);
//        listener.addSubscription(subscription);
    }

}
