package com.social.happychat.event;

import com.social.basecommon.event.BaseEvent;

/**
 * @author Administrator
 * @date 2019/7/8 0008
 * @description:
 */
public class UserSingleAttriteEditEvent extends BaseEvent {
    public String attriname;
    public String value;

    public UserSingleAttriteEditEvent(String attriname, String value) {
        this.attriname = attriname;
        this.value = value;
    }
}
