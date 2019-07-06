package com.social.huakai.http;

import com.example.http.HttpUtils;
import com.example.http.utils.BuildFactory;
import com.social.huakai.ui.find.bean.GankIoDataBean;
import com.social.huakai.ui.home.bean.NeteaseList;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author jingbin
 * @date 16/11/21
 * 网络请求类（一个接口一个方法）
 */
public interface HttpClient {

    class Builder {
        public static HttpClient getGankIOServer() {
            return BuildFactory.getInstance().create(HttpClient.class, HttpUtils.API_GANKIO);
        }
        public static HttpClient getNeteaseServer() {
            return BuildFactory.getInstance().create(HttpClient.class, HttpUtils.API_NETEASE);
        }
        public static HttpClient getRealServer() {
            return BuildFactory.getInstance().create(HttpClient.class, HttpUtils.API_REAL);
        }
    }


    /**
     * 分类数据: http://gank.io/api/data/数据类型/请求个数/第几页
     * 数据类型： 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
     * 请求个数： 数字，大于0
     * 第几页：数字，大于0
     * eg: http://gank.io/api/data/Android/10/1
     */
    @GET("data/{type}/{pre_page}/{page}")
    Observable<GankIoDataBean> getGankIoData(@Path("type") String id, @Path("page") int page, @Path("pre_page") int pre_page);

    /**
     * 分类数据: 网易圈子
     * 请求个数： 数字，大于0
     * 第几页：数字，大于0
     */
    @GET("recommend/readagent/getReadAgentTabRec?" +
            "channel=T1534831577502&subtab=RA_RECOM&fn=3&passport=&devId=cboONcMHVZDeIk" +
            "rlwhpANg%3D%3D&lat=9qRvioR8fQD9fP1NUn6U%2FA%3D%3D&version=57.3")
    Observable<NeteaseList> getNeteaseList(@Query("offset") int page, @Query("size") int size);

    /**
     * 注册
     */
    @GET("/regist/regist.do")
    Observable<Object> register(@Query("loginName") String loginName, @Query("loginType") String loginType, @Query("password") String password, @Query("verificationCode") String verificationCode);

}