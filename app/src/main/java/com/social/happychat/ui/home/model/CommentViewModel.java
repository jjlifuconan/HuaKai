package com.social.happychat.ui.home.model;

import com.google.gson.GsonBuilder;
import com.social.happychat.bean.BaseBean;
import com.social.happychat.http.HttpClient;
import com.social.happychat.http.RequestImpl;
import com.social.happychat.ui.find.bean.GankIoDataBean;
import com.social.happychat.ui.home.bean.CommentListBean;
import com.social.happychat.util.RequestBody;

import java.util.HashMap;
import java.util.Map;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * @author Administrator
 * @date 2019/6/27 0027
 * @description:
 */
public class CommentViewModel {
    private int page;
    private int per_page;
    private int dynamicId;

    public void setData(int page, int per_page, int dynamicId) {
        this.page = page;
        this.per_page = per_page;
        this.dynamicId = dynamicId;
    }

    public void getCommentData(final RequestImpl listener) {
        Map map = new HashMap();
        map.put("pageNumber ",page);
        map.put("pages ",per_page);
        map.put("businessId",dynamicId);
        Subscription subscription = HttpClient.Builder.getRealServer().dynamicCommentList(RequestBody.as(map))
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CommentListBean>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.loadFailed();

                    }

                    @Override
                    public void onNext(CommentListBean baseBean) {
                        listener.loadSuccess(baseBean);

                    }
                });
//        String sss = "";
//        sss = "{\"code\":200,\"data\":[{\"praiseNum\":1,\"nickName\":\"单身闯天下\",\"photo\":\"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_50832164_1561163576484.jpeg?x-oss-process=image/resize,m_mfit,h_200,w_200\",\"comment\":\"世界背叛了沵，我为沵背叛世界\",\"sex\":\"0\",\"time\":\"刚刚\",\"age\":\"24\"},{\"praiseNum\":1,\"nickName\":\"形同陌路\",\"photo\":\"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_93814250_1560997114407.png?x-oss-process=image/resize,m_mfit,h_200,w_200\",\"comment\":\"其实我知道的，我在等待着快乐的救赎\",\"sex\":\"1\",\"time\":\"5分钟前\",\"age\":\"24\"},{\"praiseNum\":2,\"nickName\":\"笑看你变狗\",\"photo\":\"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_31978625_1561346091154.jpeg?x-oss-process=image/resize,m_mfit,h_200,w_200\",\"comment\":\"经常被打击，从未被打到\",\"sex\":\"0\",\"time\":\"7-4 16:43\",\"age\":\"26\"},{\"praiseNum\":4,\"nickName\":\"男人必须傲↑\",\"photo\":\"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_92364017_1549162345087.png?x-oss-process=image/resize,m_mfit,h_200,w_200\",\"comment\":\"爺一般不生气，生气时不一般。\",\"sex\":\"0\",\"time\":\"7-4 16:23\",\"age\":\"24\"},{\"praiseNum\":5,\"nickName\":\"此人须珍藏\",\"photo\":\"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_80675219_1559037818657.jpeg?x-oss-process=image/resize,m_mfit,h_200,w_200\",\"comment\":\"こ現在的尒皒只是簡單的陌生人。\",\"sex\":\"0\",\"time\":\"7-4 16:11\",\"age\":\"22\"},{\"praiseNum\":3,\"nickName\":\"作茧自缚\",\"photo\":\"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_79501384_1560704521160.jpeg?x-oss-process=image/resize,m_mfit,h_200,w_200\",\"comment\":\"明媚的忧伤、以后各自为安\",\"sex\":\"1\",\"time\":\"7-4 16:10\",\"age\":\"24\"},{\"praiseNum\":4,\"nickName\":\"入骨相思\",\"photo\":\"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_93417520_1559667904089.png?x-oss-process=image/resize,m_mfit,h_200,w_200\",\"comment\":\"◇ 悲伤都扎着蝴蝶结。\",\"sex\":\"1\",\"time\":\"7-4 15:55\",\"age\":\"24\"},{\"praiseNum\":6,\"nickName\":\"霸天绝地\",\"photo\":\"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_26578941_1560079406628.png?x-oss-process=image/resize,m_mfit,h_200,w_200\",\"comment\":\"让女人为你改变之前，你先问问你有没有装修她的本钱。\",\"sex\":\"0\",\"time\":\"7-4 15:43\",\"age\":\"21\"},{\"praiseNum\":7,\"nickName\":\"御剑狂嚣\",\"photo\":\"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_51307692_1558410121242.jpeg?x-oss-process=image/resize,m_mfit,h_200,w_200\",\"comment\":\"爱他就要把他锁在屋里，他要是想逃跑的话就把腿给他打断。\",\"sex\":\"0\",\"time\":\"7-4 15:26\",\"age\":\"24\"},{\"praiseNum\":9,\"nickName\":\"朝夕盼兮\",\"photo\":\"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_15472389_1548875114076.png?x-oss-process=image/resize,m_mfit,h_200,w_200\",\"comment\":\"爷的爱你永远不会懂。\",\"time\":\"7-4 15:24\",\"sex\":\"0\",\"age\":\"24\"},{\"praiseNum\":8,\"nickName\":\"心为你而葬\",\"photo\":\"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_1469257_1561097657704.png?x-oss-process=image/resize,m_mfit,h_200,w_200\",\"comment\":\"别离之夏，甜蜜做序曲，悲伤做结局。\",\"sex\":\"1\",\"time\":\"7-4 15:11\",\"age\":\"25\"},{\"praiseNum\":1,\"nickName\":\"单身闯天下\",\"photo\":\"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_50832164_1561163576484.jpeg?x-oss-process=image/resize,m_mfit,h_200,w_200\",\"comment\":\"世界背叛了沵，我为沵背叛世界\",\"sex\":\"0\",\"time\":\"7-4 15:08\",\"age\":\"24\"},{\"praiseNum\":2,\"nickName\":\"笑看你变狗\",\"photo\":\"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_31978625_1561346091154.jpeg?x-oss-process=image/resize,m_mfit,h_200,w_200\",\"comment\":\"经常被打击，从未被打到\",\"sex\":\"0\",\"time\":\"7-4 14:54\",\"age\":\"26\"},{\"praiseNum\":4,\"nickName\":\"男人必须傲↑\",\"photo\":\"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_92364017_1549162345087.png?x-oss-process=image/resize,m_mfit,h_200,w_200\",\"comment\":\"爺一般不生气，生气时不一般。\",\"sex\":\"0\",\"time\":\"7-4 14:33\",\"age\":\"24\"},{\"praiseNum\":5,\"nickName\":\"此人须珍藏\",\"photo\":\"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_80675219_1559037818657.jpeg?x-oss-process=image/resize,m_mfit,h_200,w_200\",\"comment\":\"こ現在的尒皒只是簡單的陌生人。\",\"sex\":\"0\",\"time\":\"7-4 14:26\",\"age\":\"22\"},{\"praiseNum\":6,\"nickName\":\"霸天绝地\",\"photo\":\"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_26578941_1560079406628.png?x-oss-process=image/resize,m_mfit,h_200,w_200\",\"comment\":\"让女人为你改变之前，你先问问你有没有装修她的本钱。\",\"sex\":\"0\",\"time\":\"7-4 14:16\",\"age\":\"21\"},{\"praiseNum\":11,\"nickName\":\"寂静深夜 寂寞如雪\",\"photo\":\"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_93814250_1560997114407.png?x-oss-process=image/resize,m_mfit,h_200,w_200\",\"comment\":\"心到痛处，谁还卑微的挽留。\",\"time\":\"7-4 14:11\",\"sex\":\"1\",\"age\":\"24\"},{\"praiseNum\":1,\"nickName\":\"形同陌路\",\"photo\":\"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_93814250_1560997114407.png?x-oss-process=image/resize,m_mfit,h_200,w_200\",\"comment\":\"其实我知道的，我在等待着快乐的救赎\",\"sex\":\"1\",\"time\":\"7-4 14:04\",\"age\":\"24\"},{\"praiseNum\":7,\"nickName\":\"御剑狂嚣\",\"photo\":\"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_51307692_1558410121242.jpeg?x-oss-process=image/resize,m_mfit,h_200,w_200\",\"comment\":\"爱他就要把他锁在屋里，他要是想逃跑的话就把腿给他打断。\",\"sex\":\"0\",\"time\":\"7-2 14:44\",\"age\":\"24\"},{\"praiseNum\":9,\"nickName\":\"朝夕盼兮\",\"photo\":\"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_15472389_1548875114076.png?x-oss-process=image/resize,m_mfit,h_200,w_200\",\"comment\":\"爷的爱你永远不会懂。\",\"time\":\"7-2 12:54\",\"sex\":\"0\",\"age\":\"24\"}],\"message\":\"\"}";
//        CommentListBean bean = new GsonBuilder().serializeNulls().create().fromJson(sss, CommentListBean.class);
//        listener.loadSuccess(bean);
        listener.addSubscription(subscription);
    }

}
