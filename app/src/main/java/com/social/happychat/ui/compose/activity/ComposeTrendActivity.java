package com.social.happychat.ui.compose.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.view.View;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.gyf.immersionbar.ImmersionBar;
import com.social.basecommon.activity.BaseActivity;
import com.social.basecommon.util.KeyboardUtils;
import com.social.basecommon.util.PerfectClickListener;
import com.social.happychat.R;
import com.social.happychat.databinding.ActivityComposeTrendBinding;
import com.social.happychat.http.HttpClient;
import com.social.happychat.ui.compose.adapter.ComposePicAdapter;
import com.social.happychat.ui.compose.gifsize.GifSizeFilter;
import com.social.happychat.ui.compose.gifsize.GlideEngine;
import com.social.happychat.ui.main.MainActivity;
import com.social.happychat.widget.DividerGridItemDecoration;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Administrator
 * @date 2019/7/6
 * @description:
 */
public class ComposeTrendActivity extends BaseActivity {
    ActivityComposeTrendBinding binding;
    private ComposePicAdapter adapter;
    private static final int REQUEST_CODE_CHOOSE = 23;

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
        });
        binding.recyclerView.setLayoutManager(new GridLayoutManager(activity, 3));
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

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            List<String> mSelected = Matisse.obtainPathResult(data);
            adapter.getItems().addAll(mSelected);
            adapter.notifyDataSetChanged();
            String path = mSelected.get(0);
            File file = new File(path);
            RequestBody fileRQ = RequestBody.create(MediaType.parse("image/*"), file);
            MultipartBody.Part part =  MultipartBody.Part.createFormData("picture", file.getName(), fileRQ);
            Subscription subscription = HttpClient.Builder.getRealServer().uploadOneFile(part)
                    .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onError(Throwable e) {
                        }

                        @Override
                        public void onNext(Object object) {
                            int i = 0;
                        }
                    });
            addSubscription(subscription);
        }
    }

    public static void action(Context context){
        Intent intent = new Intent(context, ComposeTrendActivity.class);
        context.startActivity(intent);
    }
}