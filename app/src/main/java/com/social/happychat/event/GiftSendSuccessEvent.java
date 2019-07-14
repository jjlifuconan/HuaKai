package com.social.happychat.event;

public class GiftSendSuccessEvent {
    public int dynamicId;//根据动态id找到更新

    public GiftSendSuccessEvent(int dynamicId) {
        this.dynamicId = dynamicId;
    }
}
