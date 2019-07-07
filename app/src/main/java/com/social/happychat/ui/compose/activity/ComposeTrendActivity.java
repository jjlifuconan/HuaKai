package com.social.happychat.ui.compose.activity;

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
import com.gyf.immersionbar.ImmersionBar;
import com.social.basecommon.activity.BaseActivity;
import com.social.basecommon.util.KeyboardUtils;
import com.social.basecommon.util.PerfectClickListener;
import com.social.basecommon.util.ToastUtil;
import com.social.happychat.R;
import com.social.happychat.bean.BaseBean;
import com.social.happychat.databinding.ActivityComposeTrendBinding;
import com.social.happychat.http.HttpClient;
import com.social.happychat.ui.compose.adapter.ComposePicAdapter;
import com.social.happychat.ui.compose.bean.ImageBean;
import com.social.happychat.ui.compose.gifsize.GifSizeFilter;
import com.social.happychat.ui.compose.gifsize.GlideEngine;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
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
public class ComposeTrendActivity extends BaseActivity {
    ActivityComposeTrendBinding binding;
    private ComposePicAdapter adapter;
    private static final int REQUEST_CODE_CHOOSE = 23;
//    private List<String> mImageList = new ArrayList<>();//准备上传的一压缩过的图片
    private List<ImageBean> imageBeans = new ArrayList<>();//准备上传的一压缩过的图片

    private ProgressDialog uploadDialog;
    private int countReadyToUpload = 0;
//    private Map<String, Integer> compressToOrigin = new HashMap<>();//压缩->原图的位置，不能对应原图的图片地址，有可能2个cell地址一样


    private int autoIndex = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_compose_trend);
        ImmersionBar.with(activity)
                .statusBarDarkFont(true, 0.2f)
                .init();
        ImmersionBar.setTitleBar(this, binding.titlebar);
        initView();
        setListener();
    }

    private void initView() {
        initProgressBar();
        adapter = new ComposePicAdapter(activity);
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
                    //TODO iterator删除
                    for(ImageBean imageBean : imageBeans){
                        if(TextUtils.equals(imageBean.getLocalCompressFileName(), path)){
                            imageBeans.remove(imageBean);
                        }
                    }
                }
                Log.e(TAG,"after delete imageBeans.size->"+imageBeans.size());
                binding.compose.setEnabled(changeComposeButtonState());
            }
        });
        binding.recyclerView.setLayoutManager(new GridLayoutManager(activity, 3));
        DividerItemDecoration divider = new DividerItemDecoration(activity,DividerItemDecoration.HORIZONTAL);
        divider.setDrawable(ContextCompat.getDrawable(activity, R.drawable.divider_grid_layout_manager_transparent2));
        binding.recyclerView.addItemDecoration(divider);
        binding.recyclerView.setAdapter(adapter);
    }

    private void setListener() {
        binding.edtContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                binding.compose.setEnabled(changeComposeButtonState());
            }
        });
        binding.vpClose.setOnClickListener(new PerfectClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                KeyboardUtils.hideSoftInput(activity);
                if(!TextUtils.isEmpty(binding.edtContent.getText().toString()) || adapter.getItems().size()!=0){
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

        binding.compose.setOnClickListener(new PerfectClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                KeyboardUtils.hideSoftInput(activity);
//                showProgressBar();
                Map map = new HashMap();
                map.put("dynamicInfo",binding.edtContent.getText().toString());
                map.put("dynamicType","1");
                map.put("publishLocation","南京");
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
                                    finish();
                                }else{
                                    ToastUtil.show(activity, baseBean.getMsg());
                                }
                            }
                        });
                addSubscription(subscription);
            }
        });
    }


//    List<File> originPhotos = new ArrayList<>();

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
//            for(int i = 0;i<mSelected.size();i++){
//                Log.e(TAG,"origin pic 第"+i+"个->"+mSelected.get(i));
//            }
//            for(int i = 0;i<mSelected.size();i++){
//                originPhotos.add(new File(mSelected.get(i)));
//            }
            withLs(mSelected);
//            adapter.getItems().addAll(mSelected);
//            adapter.notifyDataSetChanged();
            binding.compose.setEnabled(changeComposeButtonState());
        }
    }

    public static void action(Context context){
        Intent intent = new Intent(context, ComposeTrendActivity.class);
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
//                .setRenameListener(new OnRenameListener() {
//                    @Override
//                    public String rename(String filePath) {
//                        Log.e(TAG,"rename->"+filePath);
//                        try {
//                            MessageDigest md = MessageDigest.getInstance("MD5");
//                            md.uuploadDialogate(filePath.getBytes());
//                            return new BigInteger(1, md.digest()).toString(32);
//                        } catch (NoSuchAlgorithmException e) {
//                            e.printStackTrace();
//                        }
//                        return filePath;
//                    }
//                })
                .setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() { }

                    @Override
                    public void onSuccess(File file) {
                        Log.e(TAG,"setCompressListener file->"+file.getAbsolutePath());
//                        compressToOrigin.put(file.getName(), autoIndex);
//                        autoIndex++;
//                        if(autoIndex == photos.size()){
//                            originToCompress设置完成 autoIndex重置
//                            autoIndex = 0;
//                        }
                        adapter.getItems().add(file.getAbsolutePath());
                        adapter.notifyDataSetChanged();
                        uploadOnePic(file);
//                        mImageList.add(file.getAbsolutePath());
//                        Log.i(TAG, file.getAbsolutePath() + ",all size->"+mImageList.size());
//                        showResult(originPhotos, file);
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
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(ImageBean imageBean) {
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

//    private void showResult(List<File> photos, File file) {
//        int[] originSize = computeSize(photos.get(photos.size()-1));
//        int[] thumbSize = computeSize(file);
//        String originArg = String.format(Locale.CHINA, "原图参数：%d*%d, %dk", originSize[0], originSize[1], photos.get(photos.size()-1).length() >> 10);
//        String thumbArg = String.format(Locale.CHINA, "压缩后参数：%d*%d, %dk", thumbSize[0], thumbSize[1], file.length() >> 10);
//        Log.i(TAG, originArg);
//        Log.e(TAG, thumbArg);
//    }

    private boolean changeComposeButtonState(){
        if(!TextUtils.isEmpty(binding.edtContent.getText().toString()) && adapter.getItems().size() > 0){
            return true;
        }else{
            return false;
        }
    }

    private int[] computeSize(File srcImg) {
        int[] size = new int[2];

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inSampleSize = 1;

        BitmapFactory.decodeFile(srcImg.getAbsolutePath(), options);
        size[0] = options.outWidth;
        size[1] = options.outHeight;

        return size;
    }

    private String getPath() {
        String path = Environment.getExternalStorageDirectory() + "/Luban/image/";
        File file = new File(path);
        if (file.mkdirs()) {
            return path;
        }
        return path;
    }

    private void initProgressBar(){
        uploadDialog = new ProgressDialog(activity);
        //设置提示信息
        uploadDialog.setMessage("正在上传...");
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
}
