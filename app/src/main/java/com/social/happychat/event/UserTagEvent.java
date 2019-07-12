package com.social.happychat.event;

import com.social.basecommon.event.BaseEvent;
import com.social.happychat.ui.mine.bean.TagListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @date 2019/7/12 0012
 * @description:
 */
public class UserTagEvent extends BaseEvent {
    public String tagsText;
    public List<TagListBean> selectBeans = new ArrayList<>();

    public UserTagEvent(String tagsText, List<TagListBean> selectBeans) {
        this.tagsText = tagsText;
        this.selectBeans = selectBeans;
    }
}
