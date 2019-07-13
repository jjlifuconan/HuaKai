package com.social.happychat.ui.mine.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.gyf.immersionbar.ImmersionBar;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citypickerview.CityPickerView;
import com.social.basecommon.util.KeyboardUtils;
import com.social.basecommon.util.PerfectClickListener;
import com.social.basecommon.util.SPUtils;
import com.social.basecommon.util.ToastUtil;
import com.social.happychat.R;
import com.social.happychat.base.BaseCookieActivity;
import com.social.happychat.bean.BaseBean;
import com.social.happychat.constant.Constant;
import com.social.happychat.databinding.ActivityComposeTrendBinding;
import com.social.happychat.databinding.ActivityModifyUserinfoBinding;
import com.social.happychat.event.RefreshMineEvent;
import com.social.happychat.event.RefreshTrendListEvent;
import com.social.happychat.event.UserSingleAttriteEditEvent;
import com.social.happychat.event.UserTagEvent;
import com.social.happychat.http.HttpClient;
import com.social.happychat.ui.compose.adapter.ComposePicAdapter;
import com.social.happychat.ui.compose.bean.ImageBean;
import com.social.happychat.ui.compose.gifsize.GifSizeFilter;
import com.social.happychat.ui.compose.gifsize.GlideEngine;
import com.social.happychat.ui.login.bean.UserBean;
import com.social.happychat.ui.mine.bean.TagListBean;
import com.social.happychat.util.OtherUtils;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.addapp.pickers.listeners.OnMoreItemPickListener;
import cn.addapp.pickers.picker.LinkagePicker;
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
 * @description: compressToOrigin映射是必须的，如果上传2张同样的图片，本地地址都一抹一样。删除的时候删哪张？
 * 要保证加载的图片和删除的图片资源一样，就要压缩好了加载到上传ui九宫格内
 * 还要保证的加载的路径每个cell都不一样，这样才知道删除哪一个
 */
public class ModifyUserInfoActivity extends BaseCookieActivity {
    ActivityModifyUserinfoBinding binding;
    private ComposePicAdapter adapter;
    private static final int REQUEST_CODE_CHOOSE_LIST = 23;//个人相册图集
    private static final int REQUEST_CODE_CHOOSE_HEADPHOTO = 24;//我的头像
    private List<ImageBean> imageBeans = new ArrayList<>();//准备上传的一压缩过的图片

    private ProgressDialog uploadDialog;
    private int countReadyToUpload = 0;
    private MaterialDialog dateDialog;

    /**
     * 城市选择器
     */
    CityPickerView mPicker = new CityPickerView();

    UserBean userBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_modify_userinfo);
        userBean = SPUtils.getObject(activity, Constant.SP_HAPPY_CHAT, Constant.PLATFORM_HAPPYCHAT_USER_INFO, UserBean.class);
        binding.setBean(userBean);
        ImmersionBar.with(activity)
                .statusBarDarkFont(true, 0.2f)
                .keyboardEnable(true)
                .init();
        ImmersionBar.setTitleBar(this, binding.titlebar);
        initView();
        setListener();
    }

    private void initView() {
        initAreaChoose();
        initProgressBar();
        adapter = new ComposePicAdapter(activity, 8);
        if(userBean.getUserFileDtos() != null && userBean.getUserFileDtos().size()>0){
            imageBeans = userBean.getUserFileDtos();
            List<String> strs = new ArrayList<>();
            for(ImageBean imageBean : imageBeans){
                //本地地址字段设置和网络地址一样，删除要匹配
                imageBean.setLocalCompressFileName(imageBean.getFileUrl());
                strs.add(imageBean.getFileUrl());

            }
            adapter.getItems().addAll(strs);

        }
        adapter.setOnAddPicListener(new ComposePicAdapter.onAddPicListener() {
            @Override
            public void chooseImage() {
                Matisse.from(activity)
                        .choose(MimeType.ofImage())
                        .capture(true)
                        .captureStrategy(
                                new CaptureStrategy(true, "com.zhihu.matisse.sample.fileprovider", "test"))
                        .countable(true)
                        .maxSelectable(8 - adapter.getItems().size())
                        .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                        .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                        .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                        .thumbnailScale(0.85f)
                        .imageEngine(new GlideEngine())
                        .forResult(REQUEST_CODE_CHOOSE_LIST);
            }

            @Override
            public void deleteImage(String path) {
                //删除已经上传好的图片
                if (imageBeans != null && !imageBeans.isEmpty()) {

                    for (Iterator<ImageBean> it = imageBeans.iterator(); it.hasNext(); ) {
                        ImageBean imageBean = it.next();
                        if (TextUtils.equals(imageBean.getLocalCompressFileName(), path)) {
                            it.remove();
                        }
                    }
                }
                Log.e(TAG, "after delete imageBeans.size->" + imageBeans.size());

            }
        });
        binding.recyclerView.setLayoutManager(new GridLayoutManager(activity, 4));
        DividerItemDecoration divider = new DividerItemDecoration(activity, DividerItemDecoration.HORIZONTAL);
        divider.setDrawable(ContextCompat.getDrawable(activity, R.drawable.divider_grid_layout_manager_transparent2));
        binding.recyclerView.addItemDecoration(divider);
        binding.recyclerView.setAdapter(adapter);
    }

    private void setListener() {
        binding.vpHeadphoto.setOnClickListener(perfectClickListener);
        binding.vpClose.setOnClickListener(perfectClickListener);
        binding.save.setOnClickListener(perfectClickListener);
        binding.vpNickname.setOnClickListener(perfectClickListener);
        binding.vpBirthday.setOnClickListener(perfectClickListener);
        binding.vpEmotionState.setOnClickListener(perfectClickListener);
        binding.vpSignature.setOnClickListener(perfectClickListener);
        binding.vpHometown.setOnClickListener(perfectClickListener);
        binding.vpJob.setOnClickListener(perfectClickListener);
        binding.vpTag.setOnClickListener(perfectClickListener);
    }


    /**
     * 选择完成，先所有压缩，压缩完成显示，然后再上传
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE_LIST && resultCode == RESULT_OK) {
            List<String> mSelected = Matisse.obtainPathResult(data);
            withLs(mSelected);
        }
        if (requestCode == REQUEST_CODE_CHOOSE_HEADPHOTO && resultCode == RESULT_OK) {
            List<String> mSelected = Matisse.obtainPathResult(data);
            withLsHead(mSelected);
        }
    }

    public static void action(Context context) {
        Intent intent = new Intent(context, ModifyUserInfoActivity.class);
        context.startActivity(intent);
    }

    private void withLs(final List<String> photos) {
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
                    public void onStart() {
                    }

                    @Override
                    public void onSuccess(File file) {
                        Log.e(TAG, "setCompressListener file->" + file.getAbsolutePath());
//
                        adapter.getItems().add(file.getAbsolutePath());
                        adapter.notifyDataSetChanged();
                        uploadOnePic(file);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "compress onError->" + e.getMessage());
                    }
                }).launch();
    }

    private void withLsHead(final List<String> photos) {
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
                    public void onStart() {
                    }

                    @Override
                    public void onSuccess(File file) {
                        Log.e(TAG, "setCompressListener file->" + file.getAbsolutePath());
                        uploadHeadPic(file);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "compress onError->" + e.getMessage());
                    }
                }).launch();
    }

    private void uploadOnePic(File file) {
        RequestBody fileRQ = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), fileRQ);
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
                        countReadyToUpload--;
                        if (countReadyToUpload == 0) {
                            uploadDialog.dismiss();
                        }
                    }

                    @Override
                    public void onNext(ImageBean imageBean) {
//                        Log.e(TAG, "uploadOnePic onNext");
                        ImageBean imgBean = imageBean.getData();
                        imgBean.setLocalCompressFileName(file.getAbsolutePath());
                        imageBeans.add(imgBean);
                        countReadyToUpload--;
                        if (countReadyToUpload == 0) {
                            uploadDialog.dismiss();
                            userBean.setUserFileDtos(imageBeans);
                        }
                    }
                });
        addSubscription(subscription);
    }

    private void uploadHeadPic(File file) {
        RequestBody fileRQ = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), fileRQ);
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
                        countReadyToUpload--;
                        if (countReadyToUpload == 0) {
                            uploadDialog.dismiss();
                        }
                    }

                    @Override
                    public void onNext(ImageBean imageBean) {
//                        Log.e(TAG, "uploadOnePic onNext");
                        countReadyToUpload--;
                        if (countReadyToUpload == 0) {
                            uploadDialog.dismiss();
                        }
                        ImageBean imgBean = imageBean.getData();
                        userBean.setHeadPhotoUrl(imgBean.getFileUrl());
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
    private void sortImageBeans() {
        if (imageBeans != null && adapter.getItems().size() > 0) {
            List<ImageBean> sortBeans = new ArrayList<>();//准备上传的一压缩过的图片
            for (String path : adapter.getItems()) {
                for (Iterator<ImageBean> it = imageBeans.iterator(); it.hasNext(); ) {
                    ImageBean imageBean = it.next();
                    if (TextUtils.equals(imageBean.getLocalCompressFileName(), path)) {
                        sortBeans.add(imageBean);
                        it.remove();
                    }
                }
            }
            imageBeans.clear();
            Log.e("TAG", "sortImageBeans size->" + imageBeans.size());
            imageBeans.addAll(sortBeans);
        }

    }

    private void initProgressBar() {
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

    PerfectClickListener perfectClickListener = new PerfectClickListener() {
        @Override
        protected void onNoDoubleClick(View v) {
            switch (v.getId()) {
                case R.id.vp_headphoto:
                    Matisse.from(activity)
                            .choose(MimeType.ofImage())
                            .capture(true)
                            .captureStrategy(
                                    new CaptureStrategy(true, "com.zhihu.matisse.sample.fileprovider", "test"))
                            .countable(true)
                            .maxSelectable(1)
                            .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                            .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                            .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                            .thumbnailScale(0.85f)
                            .imageEngine(new GlideEngine())
                            .forResult(REQUEST_CODE_CHOOSE_HEADPHOTO);
                    break;
                case R.id.vp_nickname:
                    UserSingleAttributeEditActiviy.action(activity, getResources().getString(R.string.title_nickname), 12, binding.tvNickname.getText().toString());
                    break;
                case R.id.vp_birthday:
                    dateDialog = new MaterialDialog.Builder(activity)
                            .customView(R.layout.dialog_datepicker, false)
                            .positiveText(android.R.string.ok)
                            .negativeText(android.R.string.cancel)
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    DatePicker datePicker = dateDialog.getCustomView().findViewById(R.id.datePicker);
                                    String birthday = datePicker.getYear() + "-" + OtherUtils.addZeroForNumber(datePicker.getMonth()+1)+ "-"
                                            + OtherUtils.addZeroForNumber(datePicker.getDayOfMonth());
                                    binding.tvBirthday.setText(birthday);
                                    userBean.setUserBirthday(birthday);
                                }
                            })
                            .show();

                    break;
                case R.id.vp_emotion_state:
                    final String[] titles = getResources().getStringArray(R.array.emotion);
                    new MaterialDialog.Builder(activity).items(titles)
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog1, View itemView, int position, CharSequence text) {
                                binding.tvEmotionState.setText(titles[position]);
                                userBean.setEmotionStatus(titles[position]);
                            }
                        })
                        .show();
                    break;
                case R.id.vp_signature:
                    UserSingleAttributeEditActiviy.action(activity, getResources().getString(R.string.title_signature), 200, 4,binding.tvSignature.getText().toString(), true);
                    break;
                case R.id.vp_hometown:
                    mPicker.showCityPicker();
                    break;
                case R.id.vp_job:
                    onLinkagePicker();
                    break;
                case R.id.vp_tag:
                    MyTagActivity.action(activity, (ArrayList<TagListBean>) userBean.getUserTagDtos());
                    break;
                case R.id.vp_close:
                    KeyboardUtils.hideSoftInput(activity);
                    UserBean localBean = SPUtils.getObject(activity, Constant.SP_HAPPY_CHAT, Constant.PLATFORM_HAPPYCHAT_USER_INFO, UserBean.class);
                    if (userBean.isDataModify(localBean)) {
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
                        }).content("你的个人信息已经修改，确定要退出吗？")
                                .show();
                    } else {
                        finish();
                    }
                    break;
                case R.id.save:
                    KeyboardUtils.hideSoftInput(activity);
                    Map map = new HashMap();
                    map.put("headPhotoUrl", userBean.getHeadPhotoUrl());
                    map.put("nickName", userBean.getNickName());
                    map.put("userBirthday", userBean.getUserBirthday());
                    map.put("userProfession", userBean.getUserProfession());
                    map.put("userSign", userBean.getUserSign());
                    map.put("emotionStatus", userBean.getEmotionStatus());
                    map.put("userAddress", userBean.getUserAddress());
                    map.put("userTagDtos", userBean.getUserTagDtos());
                    //排序完成  set  然后最后保存成功要保存登录sp
                    sortImageBeans();
                    userBean.setUserFileDtos(imageBeans);
                    map.put("userFileDtos", userBean.getUserFileDtos());

                    Subscription subscription = HttpClient.Builder.getRealServer().modifyUser(com.social.happychat.util.RequestBody.as(map))
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
                                    if (baseBean.isValid()) {
                                        ToastUtil.show(activity, "修改成功");
                                        SPUtils.saveObject(activity, Constant.SP_HAPPY_CHAT, Constant.PLATFORM_HAPPYCHAT_USER_INFO, userBean);
                                        EventBus.getDefault().post(new RefreshMineEvent());
                                        finish();
                                    } else {
                                        ToastUtil.show(activity, baseBean.getMsg());
                                    }
                                }
                            });
                    addSubscription(subscription);
                    break;
            }
        }
    };


    /**
     * 初始化城市选择器
     */
    private void initAreaChoose() {
        //预先加载仿iOS滚轮实现的全部数据
        mPicker.init(this);
        //添加默认的配置，不需要自己定义，当然也可以自定义相关熟悉，详细属性请看demo
        CityConfig cityConfig = new CityConfig.Builder()
                .setCityWheelType(CityConfig.WheelType.PRO_CITY_DIS)
                //确认按钮文字颜色
                .confirTextColor("#fff05c5c")
                //默认显示的省份
                .province("江苏省")
                //默认显示省份下面的城市
                .city("南京市")
                //默认显示省份下面的城市的区
                .district("建邺区")
                .provinceCyclic(false)
                .cityCyclic(false)
                .districtCyclic(false)
                .build();
        mPicker.setConfig(cityConfig);

        //监听选择点击事件及返回结果
        mPicker.setOnCityItemClickListener(new OnCityItemClickListener() {
            @Override
            public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                binding.tvHometown.setText(province.getName()+city.getName()+district.getName());
                userBean.setUserAddress(province.getName()+city.getName()+district.getName());
            }

            @Override
            public void onCancel() {

            }
        });
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(UserSingleAttriteEditEvent event) {
        if(TextUtils.equals(getResources().getString(R.string.title_nickname), event.attriname)){
            binding.tvNickname.setText(event.value);
            userBean.setNickName(event.value);
        }
        if(TextUtils.equals(getResources().getString(R.string.title_signature), event.attriname)){
            binding.tvSignature.setText(event.value);
            userBean.setUserSign(event.value);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(UserTagEvent event) {
        binding.tvTag.setText(event.tagsText);
        userBean.setUserTagDtos(event.selectBeans);
    }

    /**
     * 职业选择框
     */
    public void onLinkagePicker() {
        LinkagePicker.DataProvider provider = new LinkagePicker.DataProvider() {

            @Override
            public boolean isOnlyTwo() {
                return true;
            }

            @Override
            public List<String> provideFirstData() {
                String[] mainClassifys = activity.getResources().getStringArray(R.array.jobMainClassify);
                List<String> firstList = Arrays.asList(mainClassifys);
                return firstList;
            }

            @Override
            public List<String> provideSecondData(int firstIndex) {
                List<String> secondList = null;
                if(firstIndex == 0){
                    String[] childClassfys = activity.getResources().getStringArray(R.array.jobclassify1);
                    secondList = Arrays.asList(childClassfys);
                }
                if(firstIndex == 1){
                    String[] childClassfys = activity.getResources().getStringArray(R.array.jobclassify2);
                    secondList = Arrays.asList(childClassfys);
                }
                if(firstIndex == 2){
                    String[] childClassfys = activity.getResources().getStringArray(R.array.jobclassify3);
                    secondList = Arrays.asList(childClassfys);
                }
                if(firstIndex == 3){
                    String[] childClassfys = activity.getResources().getStringArray(R.array.jobclassify4);
                    secondList = Arrays.asList(childClassfys);
                }
                if(firstIndex == 4){
                    String[] childClassfys = activity.getResources().getStringArray(R.array.jobclassify5);
                    secondList = Arrays.asList(childClassfys);
                }

                if(firstIndex == 5){
                    String[] childClassfys = activity.getResources().getStringArray(R.array.jobclassify6);
                    secondList = Arrays.asList(childClassfys);
                }
                if(firstIndex == 6){
                    String[] childClassfys = activity.getResources().getStringArray(R.array.jobclassify7);
                    secondList = Arrays.asList(childClassfys);
                }
                if(firstIndex == 7){
                    String[] childClassfys = activity.getResources().getStringArray(R.array.jobclassify8);
                    secondList = Arrays.asList(childClassfys);
                }
                if(firstIndex == 8){
                    String[] childClassfys = activity.getResources().getStringArray(R.array.jobclassify9);
                    secondList = Arrays.asList(childClassfys);
                }
                if(firstIndex == 9){
                    String[] childClassfys = activity.getResources().getStringArray(R.array.jobclassify10);
                    secondList = Arrays.asList(childClassfys);
                }
                return secondList;
            }

            @Override
            public List<String> provideThirdData(int firstIndex, int secondIndex) {
                return null;
            }

        };
        LinkagePicker picker = new LinkagePicker(this, provider);
        picker.setCanLoop(false);
        picker.setSelectedIndex(0, 0);
        //picker.setSelectedItem("12", "9");
        picker.setOnMoreItemPickListener(new OnMoreItemPickListener<String>() {

            @Override
            public void onItemPicked(String first, String second, String third) {
                binding.tvJob.setText(second);
                userBean.setUserProfession(second);
            }
        });
        picker.show();
    }

}
