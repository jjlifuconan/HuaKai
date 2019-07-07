package com.social.happychat.ui.compose.bean;

import com.social.happychat.bean.BaseBean;

/**
 * @author Administrator
 * @date 2019/7/7
 * @description:
 */
public class ImageBean extends BaseBean<ImageBean> {

    /**
     * fileFormart : jpg
     * fileId : b0522be5bf5e4c18850061ecb2f1ead1
     * fileName : magazine-unlock-01-2.3.1399-_7767cb705cbc4c61a1bf184038609815.jpg
     * fileSize : 265538
     * fileUrl : http://happychat-pic.oss-cn-hangzhou.aliyuncs.com/32129fe20e164639a1afabd9c1f8295b.jpg?Expires=1877854580&OSSAccessKeyId=LTAIDqmQs3MHEI7Q&Signature=5PVq6ylECsEO497hUm0gTEdYLrs%3D
     * userId : 22
     */

    private String fileFormart;
    private String fileId;
    private String fileName;
    private int fileSize;
    private String fileUrl;
    private int userId;

    public String getFileFormart() {
        return fileFormart;
    }

    public void setFileFormart(String fileFormart) {
        this.fileFormart = fileFormart;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
