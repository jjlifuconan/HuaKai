package com.social.happychat.ui.mine.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.databinding.DataBindingUtil;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.gyf.immersionbar.ImmersionBar;
import com.social.basecommon.activity.BaseActivity;
import com.social.basecommon.util.KeyboardUtils;
import com.social.basecommon.util.PerfectClickListener;
import com.social.basecommon.util.ToastUtil;
import com.social.happychat.R;
import com.social.happychat.app.HappyChatApplication;
import com.social.happychat.bean.BaseBean;
import com.social.happychat.databinding.ActivityComposeTrendBinding;
import com.social.happychat.databinding.ActivityModifyUserinfoBinding;
import com.social.happychat.event.RefreshTrendListEvent;
import com.social.happychat.http.HttpClient;
import com.social.happychat.ui.compose.adapter.ComposePicAdapter;
import com.social.happychat.ui.compose.bean.ImageBean;
import com.social.happychat.ui.compose.gifsize.GifSizeFilter;
import com.social.happychat.ui.compose.gifsize.GlideEngine;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import top.zibin.luban.CompressionPredicate;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

/**
 * @author Administrator
 * @date 2019/7/6
 * @description:
 * compressToOrigin映射是必须的，如果上传2张同样的图片，本地地址都一抹一样。删除的时候删哪张？
 * 要保证加载的图片和删除的图片资源一样，就要压缩好了加载到上传ui九宫格内
 * 还要保证的加载的路径每个cell都不一样，这样才知道删除哪一个
 */
public class ModifyUserInfoActivity extends BaseActivity {
    ActivityModifyUserinfoBinding binding;
    private ComposePicAdapter adapter;
    private static final int REQUEST_CODE_CHOOSE = 23;
    private List<ImageBean> imageBeans = new ArrayList<>();//准备上传的一压缩过的图片

    private ProgressDialog uploadDialog;
    private int countReadyToUpload = 0;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_modify_userinfo);
        ImmersionBar.with(activity)
                .statusBarDarkFont(true, 0.2f)
                .keyboardEnable(true)
                .init();
        ImmersionBar.setTitleBar(this, binding.titlebar);
        initView();
        setListener();
    }

    private void initView() {
        initProgressBar();
        adapter = new ComposePicAdapter(activity, 8);
        adapter.setOnAddPicListener(new ComposePicAdapter.onAddPicListener() {
            @Override
            public void chooseImage() {
                Matisse.from(activity)
                        .choose(MimeType.ofImage())
                        .capture(true)
                        .captureStrategy(
                                new CaptureStrategy(true, "com.zhihu.matisse.sample.fileprovider","test"))
                        .countable(true)
                        .maxSelectable(9 - adapter.getItems().size())
                        .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                        .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                        .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                        .thumbnailScale(0.85f)
                        .imageEngine(new GlideEngine())
                        .forResult(REQUEST_CODE_CHOOSE);
            }

            @Override
            public void deleteImage(String path) {
                //删除已经上传好的图片
                if(imageBeans != null && !imageBeans.isEmpty()){

                    for(Iterator<ImageBean> it = imageBeans.iterator(); it.hasNext();){
                        ImageBean imageBean = it.next();
                        if(TextUtils.equals(imageBean.getLocalCompressFileName(), path)){
                            it.remove();
                        }
                    }
                }
                Log.e(TAG,"after delete imageBeans.size->"+imageBeans.size());

            }
        });
        binding.recyclerView.setLayoutManager(new GridLayoutManager(activity, 4));
        DividerItemDecoration divider = new DividerItemDecoration(activity,DividerItemDecoration.HORIZONTAL);
        divider.setDrawable(ContextCompat.getDrawable(activity, R.drawable.divider_grid_layout_manager_transparent2));
        binding.recyclerView.addItemDecoration(divider);
        binding.recyclerView.setAdapter(adapter);
    }

    private void setListener() {
        binding.vpClose.setOnClickListener(new PerfectClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                KeyboardUtils.hideSoftInput(activity);
                if( adapter.getItems().size()!=0){
                    new MaterialDialog.Builder(activity).positiveText("确定").negativeText("取消")
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    finish();
                                }
                            }).onNegative(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        }
                    }).content("要放弃发布动态吗？")
                            .show();
                }else{
                    finish();
                }
            }
        });

        binding.save.setOnClickListener(new PerfectClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                KeyboardUtils.hideSoftInput(activity);
//                showProgressBar();
                Map map = new HashMap();
                map.put("dynamicType","1");
                sortImageBeans();
                map.put("userFiles",imageBeans);

                Subscription subscription = HttpClient.Builder.getRealServer().publishDynamic(com.social.happychat.util.RequestBody.as(map))
                        .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<BaseBean>() {
                            @Override
                            public void onCompleted() {
                            }

                            @Override
                            public void onError(Throwable e) {
                            }

                            @Override
                            public void onNext(BaseBean baseBean) {
                                if(baseBean.isValid()){
                                    ToastUtil.show(activity,"发布成功");
                                    EventBus.getDefault().post(new RefreshTrendListEvent());
                                    finish();
                                }else{
                                    ToastUtil.show(activity, baseBean.getMsg());
                                }
                            }
                        });
                addSubscription(subscription);
            }
        });

        binding.vpNickname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }



    /**
     * 选择完成，先所有压缩，压缩完成显示，然后再上传
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            List<String> mSelected = Matisse.obtainPathResult(data);
            withLs(mSelected);
        }
    }

    public static void action(Context context){
        Intent intent = new Intent(context, ModifyUserInfoActivity.class);
        context.startActivity(intent);
    }

    private  void withLs(final List<String> photos) {
        uploadDialog.show();
        countReadyToUpload = photos.size();
        Luban.with(this)
                .load(photos)
                .ignoreBy(100)
                .setTargetDir(getPath())
                .setFocusAlpha(false)
                .filter(new CompressionPredicate() {
                    @Override
                    public boolean apply(String path) {
                        return !(TextUtils.isEmpty(path) || path.toLowerCase().endsWith(".gif"));
                    }
                })
//
                .setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() { }

                    @Override
                    public void onSuccess(File file) {
                        Log.e(TAG,"setCompressListener file->"+file.getAbsolutePath());
//
                        adapter.getItems().add(file.getAbsolutePath());
                        adapter.notifyDataSetChanged();
                        uploadOnePic(file);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG,"compress onError->"+e.getMessage());
                    }
                }).launch();
    }

    private void uploadOnePic(File file){
        RequestBody fileRQ = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part part =  MultipartBody.Part.createFormData("file", file.getName(), fileRQ);
        Subscription subscription = HttpClient.Builder.getRealServer().uploadOneFile(part)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ImageBean>() {
                    @Override
                    public void onCompleted() {
//                        Log.e(TAG, "uploadOnePic onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
//                        Log.e(TAG, "uploadOnePic onError");
                        countReadyToUpload --;
                        if(countReadyToUpload == 0){
                            uploadDialog.dismiss();
                        }
                    }

                    @Override
                    public void onNext(ImageBean imageBean) {
//                        Log.e(TAG, "uploadOnePic onNext");
                        countReadyToUpload --;
                        if(countReadyToUpload == 0){
                            uploadDialog.dismiss();
                        }
                        ImageBean imgBean = imageBean.getData();
                        imgBean.setLocalCompressFileName(file.getAbsolutePath());
                        imageBeans.add(imgBean);
                    }
                });
        addSubscription(subscription);
    }


    private String getPath() {
        String path = Environment.getExternalStorageDirectory() + "/Luban/image/";
        File file = new File(path);
        if (file.mkdirs()) {
            return path;
        }
        return path;
    }

    /**
     * 上传前对imagebeans进行排序，因为图片大小、网速影响，ImageBeans顺序不一定是列表展现顺序
     */
    private void sortImageBeans(){
        if(imageBeans != null && adapter.getItems().size() > 0){
            List<ImageBean> sortBeans = new ArrayList<>();//准备上传的一压缩过的图片
            for(String path : adapter.getItems()){
                for(Iterator<ImageBean> it = imageBeans.iterator(); it.hasNext();){
                    ImageBean imageBean = it.next();
                    if(TextUtils.equals(imageBean.getLocalCompressFileName(), path)){
                        sortBeans.add(imageBean);
                        it.remove();
                    }
                }
            }
            imageBeans.clear();
            Log.e("TAG","sortImageBeans size->"+imageBeans.size());
            imageBeans.addAll(sortBeans);
        }

    }

    private void initProgressBar(){
        uploadDialog = new ProgressDialog(activity);
        //设置提示信息
        uploadDialog.setMessage("正在上传图片...");
        //设置ProgressDialog 是否可以按返回键取消；
        uploadDialog.setCancelable(false);
        uploadDialog.setCanceledOnTouchOutside(false);// 设置在点击Dialog外是否取消Dialog进度条
        //显示ProgressDialog
//        uploadDialog.show();
    }

    @Override
    public void onBackPressedSupport() {
        binding.vpClose.performClick();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        imageBeans.clear();
        imageBeans = null;
    }

}
