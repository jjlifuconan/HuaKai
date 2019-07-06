package com.social.happychat.ui.home.interfaces;

import java.util.Map;

/**
 * Created by showzeng on 17-8-11.
 * Email: kingstageshow@gmail.com
 * GitHub: https://github.com/showzeng
 */

public interface DialogFragmentDataCallback {

    String getCommentText();

    void setCommentText(String commentTextTemp);

    //根据用户id，记录评论，如果还点击的当前userid的评论，评论内容带过去，否则清空
    void setCommentToWhichUserid(String userId);

    String getCommentToWhichUserid();

    String getCommentId();

    void setCommentId(String commentId);

    /**
     * 评论帖子
     * @param commentTextTemp
     */
    void submitCommentToPost(String commentTextTemp);

    /**
     * 掉起评论fragment并传递id
     * @param commentId  被回复的评论id
     * @param commentTextTemp
     */
    void submitCommentToSb(String commentTextTemp, String commentId);

    /**
     * 回复人的对话框
     * @param item
     */
    void alertCommentSbDialog(Map item);

}
