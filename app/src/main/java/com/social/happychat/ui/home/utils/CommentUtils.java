package com.social.happychat.ui.home.utils;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;

import com.social.happychat.R;
import com.social.happychat.app.HappyChatApplication;
import com.social.happychat.util.TextStyleUtils;

/**
 * @author Administrator
 * @date 2019/7/12 0012
 * @description:
 */
public class CommentUtils {
    public static CharSequence geCommentText(String replyName, String comment){
        if(!TextUtils.isEmpty(replyName)){
            return TextStyleUtils.getBuilder("回复").append(replyName+"：")
                    .setForegroundColor(ContextCompat.getColor(HappyChatApplication.getInstance(), R.color.colorPrimary)).append(comment).create();
        }else{
            return comment;
        }
    }
}
