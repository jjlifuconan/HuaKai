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
    void setCommentToWhichUserid(int replyUserId);

    int getCommentToWhichUserid();

    String getCommentId();

    void setCommentId(String commentId);

    /**1
     * 评论帖子
     * @param commentTextTemp
     */
    void submitCommentToPost(String commentTextTemp);

    /**
     * 掉起评论fragment并传递id
     * @param replyUserid  被回复人的id
     * @param replyName    被回复人的名字
     * @param content      内容
     */
    void submitCommentToSb(int replyUserid, String replyName, String content);

    /**
     *
     * @param replyUserid
     * @param replyName
     */
    void alertCommentSbDialog(int replyUserid, String replyName);

}
