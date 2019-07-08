package com.social.happychat.http;

import com.alibaba.fastjson.JSONObject;
import com.example.http.HttpUtils;
import com.example.http.utils.BuildFactory;
import com.social.happychat.bean.BaseBean;
import com.social.happychat.ui.compose.bean.ImageBean;
import com.social.happychat.ui.find.bean.GankIoDataBean;
import com.social.happychat.ui.home.bean.NeteaseList;
import com.social.happychat.ui.home.bean.TrendListBean;
import com.social.happychat.ui.login.bean.UserBean;


import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
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
     * 获取验证码
     */
    @POST("user/getValidateCode.do")
    Observable<Object> getValidateCode(@Body JSONObject parmas);

    /**
     * 注册
     */
    @POST("regist/regist.do")
    Observable<BaseBean> register(@Body JSONObject parmas);

    /**
     * 登录
     */
    @POST("login/login.do")
    Observable<UserBean> login(@Body JSONObject parmas);


    /**
     * 退出
     */
    @POST("login/private/logout.do")
    Observable<Object> loginout(@Body JSONObject parmas);

    /**
     * 修改密码
     */
    @POST("user/forgetPwd.do")
    Observable<BaseBean> forgetPwd(@Body JSONObject parmas);

    /**
     * 单个文件上传
     */
    @Multipart
    @POST("user/private/file/saveFile.do")
    Observable<ImageBean> uploadOneFile(@Part MultipartBody.Part body);

    /**
     * 单个文件上传 body上传
     */
    @POST("user/private/file/saveFile.do")
    Observable<Object> uploadOneFile(@Body RequestBody body);

    /**
     * 多个文件上传
     */
    @Multipart
    @POST("user/private/file/saveFile.do")
    Observable<Object> uploadFiles(@PartMap Map<String, RequestBody> map);

    /**
     * 动态发布
     */
    @POST("dynamic/private/publishDynamic.do")
    Observable<BaseBean> publishDynamic(@Body JSONObject parmas);

    /**
     * 动态列表
     */
    @POST("dynamic/private/dynamicList.do")
    Observable<TrendListBean> dynamicList(@Body JSONObject parmas);

    /**
     * 点赞
     */
    @POST("dynamic/private/doPraise.htm")
    Observable<BaseBean> doPraise(@Body JSONObject parmas);

    /**
     * 评论动态
     */
    @POST("dynamic/private/doComment.do")
    Observable<BaseBean> doComment(@Body JSONObject parmas);

}