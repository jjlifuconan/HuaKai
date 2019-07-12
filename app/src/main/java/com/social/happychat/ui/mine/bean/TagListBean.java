package com.social.happychat.ui.mine.bean;

import com.social.happychat.bean.BaseBean;

import java.util.List;

/**
 * @author Administrator
 * @date 2019/7/12 0012
 * @description:
 */
public class TagListBean extends BaseBean<List<TagListBean>> {
    /**
     * classifyCode : P0001
     * classifyName : 运动健身
     * id : 1
     */

    private String classifyCode;
    private String classifyName;
    private int id;

    public String getClassifyCode() {
        return classifyCode;
    }

    public void setClassifyCode(String classifyCode) {
        this.classifyCode = classifyCode;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
