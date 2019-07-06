package com.social.happychat.util;




import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * @author Administrator
 * @date 2019/7/6
 * @description:
 */
public class RequestBody {

    /**
     * 不要用org.JSON
     * https://blog.csdn.net/setsail_z/article/details/62430346
     * @param map
     * @return
     */
    public static JSONObject as(Map map){
        JSONObject json = new JSONObject(map);
        return json;
    }
}
