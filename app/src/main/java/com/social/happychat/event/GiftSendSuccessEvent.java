package com.social.happychat.event;

public class GiftSendSuccessEvent {
    public int position;//找列表第几个

    public GiftSendSuccessEvent(int position) {
        this.position = position;
    }
}
