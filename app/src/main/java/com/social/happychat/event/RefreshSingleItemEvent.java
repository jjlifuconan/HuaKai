package com.social.happychat.event;

import com.social.basecommon.event.BaseEvent;

import java.util.Map;

/**
 * @author Administrator
 * @date 2019/7/11 0011
 * @description:
 */
public class RefreshSingleItemEvent extends BaseEvent {
    public int dynamicId;//找第几个
    public Map modify_map;//需要修改的参数集合

    public RefreshSingleItemEvent(int dynamicId, Map modify_map) {
        this.dynamicId = dynamicId;
        this.modify_map = modify_map;
    }
}
