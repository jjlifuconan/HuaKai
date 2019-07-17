package com.social.happychat.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.social.basecommon.util.RxAppTool;
import com.social.happychat.app.HappyChatApplication;

import java.io.File;

/**
 * @author Administrator
 * @date 2019/7/17 0017
 * @description:
 */
public class CheckUpdateUtils {
    /**
     * 例子
     * 下载APk文件并自动弹出安装
     */
   public void getFile(Context context, String url, final String filePath, String name) {
       Callback callback = new FileCallback(filePath, name) {
           @Override
           public void onSuccess(Response<File> response) {
               // file 即为文件数据，文件保存在指定目录
               File file = response.body();
               RxAppTool.InstallAPK(context,file.getAbsolutePath());
           }};
        OkGo.get(url)//
                .tag(this)//
                .execute(callback);
    }
}
