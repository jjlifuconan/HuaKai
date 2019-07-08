package com.social.happychat.event;

import com.social.basecommon.event.BaseEvent;

/**
 * @author yaojun
 * @date 2019/7/8
 * @description:
 */
public class RefreshCommentNumEvent extends BaseEvent {
    public int commentNum;

    public RefreshCommentNumEvent(int commentNum) {
        this.commentNum = commentNum;
    }
}
