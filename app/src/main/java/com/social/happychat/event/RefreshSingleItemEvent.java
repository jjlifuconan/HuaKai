package com.social.happychat.event;

import com.social.basecommon.event.BaseEvent;

import java.util.Map;

/**
 * @author Administrator
 * @date 2019/7/11 0011
 * @description:
 */
public class RefreshSingleItemEvent extends BaseEvent {
    public int position;//找第几个
    public Map modify_map;//需要修改的参数集合

    public RefreshSingleItemEvent(int position, Map modify_map) {
        this.position = position;
        this.modify_map = modify_map;
    }
}
