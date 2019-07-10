package com.social.happychat.im;

import rx.Subscription;

/**
 * Created by conanaiflj on 2017/1/17.
 * 用于数据请求的回调
 */

public class IMImpl {
    public interface IMLoginImpl {
        void success();

        void fail();

        void exception();
    }

    public interface IMResisterImpl {
        void success();

        void failed();
    }
}
