package com.social.happychat.ui.mine.utils;

import com.social.happychat.ui.mine.bean.TagListBean;

import java.util.List;

/**
 * @author Administrator
 * @date 2019/7/12 0012
 * @description:
 */
public class UserBindingUtils {
    public static String getTagText(List<TagListBean> userTagDtos){
        if(userTagDtos != null && userTagDtos.size() > 0){
            StringBuilder sb = new StringBuilder();
            for(int i = 0;i<userTagDtos.size();i++){
                sb.append(userTagDtos.get(i).getClassifyName()).append(",");
            }
            String tagsText = sb.toString();
            if(tagsText.endsWith(",")){
                tagsText = tagsText.substring(0,tagsText.length() - 1);
            }
            return tagsText;

        }
        return "";

    }
}
