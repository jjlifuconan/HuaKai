package com.social.huakai.ui.home.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import com.gyf.immersionbar.ImmersionBar;
import com.jaeger.ninegridimageview.ItemImageClickListener;
import com.jaeger.ninegridimageview.ItemImageLongClickListener;
import com.jaeger.ninegridimageview.NineGridImageViewAdapter;
import com.social.basecommon.activity.BaseActivity;
import com.social.basecommon.util.DensityUtil;
import com.social.basecommon.util.ImageLoadUtil;
import com.social.basecommon.viewbigimage.ViewBigImageActivity;
import com.social.huakai.R;
import com.social.huakai.databinding.ActivityDetailTrendBinding;
import com.social.huakai.ui.home.bean.NeteaseList;
import com.social.huakai.ui.home.fragment.CommentDialogFragment;
import com.social.huakai.ui.home.fragment.CommentListFragment;
import com.social.huakai.ui.home.fragment.FemaleGrabChatFragment;
import com.social.huakai.ui.home.fragment.GiftListFragment;
import com.social.huakai.ui.home.fragment.MaleAskingForChatFragment;
import com.social.huakai.ui.home.fragment.PraiseListFragment;
import com.social.huakai.ui.home.fragment.TrendFragment;
import com.social.huakai.ui.home.interfaces.DialogFragmentDataCallback;
import com.social.huakai.widget.ScaleTransitionPagerTitleView;
import com.social.huakai.widget.TouchImageView;

import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TrendDetailActivity extends BaseActivity implements DialogFragmentDataCallback {
    ActivityDetailTrendBinding binding;
    NeteaseList.DataBean bean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_trend);
        ImmersionBar.with(this).init();
        ImmersionBar.setTitleBar(this, binding.titlebar);

        bean = (NeteaseList.DataBean) getIntent().getSerializableExtra("bean");
        binding.setBean(bean);
        initView();
        setListener();


    }

    private void setListener() {
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.layoutBottom.tvComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initCommentDialog();
            }
        });
    }

    private void initView(){
        NineGridImageViewAdapter<String> mAdapter = new NineGridImageViewAdapter<String>() {
            @Override
            protected void onDisplayImage(Context context, ImageView imageView, String s) {
                ImageLoadUtil.displayFadeImage(imageView,s,1);
            }

            @Override
            protected ImageView generateImageView(Context context) {
                TouchImageView imageView = new TouchImageView(context);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                return imageView;
            }

            @Override
            protected void onItemImageClick(Context context, ImageView imageView, int index, List<String> list) {
//                Toast.makeText(context, "image position is " + index, Toast.LENGTH_SHORT).show();
                ViewBigImageActivity.startImageList(context, index, (ArrayList<String>) list, null);
            }

            @Override
            protected boolean onItemImageLongClick(Context context, ImageView imageView, int index, List<String> list) {
//                Toast.makeText(context, "image long click position is " + index, Toast.LENGTH_SHORT).show();
                return true;
            }
        };

        binding.layoutHeader.nineimage.setAdapter(mAdapter);
        binding.layoutHeader.nineimage.setItemImageClickListener(new ItemImageClickListener<String>() {
            @Override
            public void onItemImageClick(Context context, ImageView imageView, int index, List<String> list) {
                Log.d("onItemImageClick", list.get(index));
            }
        });
        binding.layoutHeader.nineimage.setItemImageLongClickListener(new ItemImageLongClickListener<String>() {
            @Override
            public boolean onItemImageLongClick(Context context, ImageView imageView, int index, List<String> list) {
                Log.d("onItemImageLongClick", list.get(index));
                return true;
            }
        });



        List<NeteaseList.DataBean.ImagesBean> imagesBeans = bean.getImages();
        ArrayList strList = new ArrayList();
        if(imagesBeans!=null && !imagesBeans.isEmpty()){
            if(imagesBeans.size() == 1){
                strList.add("https://nimg.ws.126.net/?url="+imagesBeans.get(0).getUrl()+"&thumbnail="+ DensityUtil.getWidth(binding.getRoot().getContext())+"x2147483647&quality=75&type=webp");
            }else{
                for(int i=0;i<imagesBeans.size();i++){
                    strList.add("https://nimg.ws.126.net/?url="+imagesBeans.get(i).getUrl()+"&thumbnail=480x2147483647&quality=75&type=webp");
                }
            }
        }
        binding.layoutHeader.nineimage.setImagesData(strList);

        final String[] titles = getResources().getStringArray(R.array.trendDetailTabTitle);

        CommonNavigator commonNavigator = new CommonNavigator(activity);
        commonNavigator.setScrollPivotX(0.8f);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return titles.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                ScaleTransitionPagerTitleView colorTransitionPagerTitleView = new ScaleTransitionPagerTitleView(context);
                if(index == 0){
                    colorTransitionPagerTitleView.setText(titles[index]+"12");
                }else if(index == 1){
                    colorTransitionPagerTitleView.setText(titles[index]+"14");
                }else if(index == 2){
                    colorTransitionPagerTitleView.setText(titles[index]+"27");
                }
                colorTransitionPagerTitleView.setTextSize(18f);
                colorTransitionPagerTitleView.setPadding(DensityUtil.dip2px(activity,3),DensityUtil.dip2px(activity,3),DensityUtil.dip2px(activity,3),DensityUtil.dip2px(activity,3));
                colorTransitionPagerTitleView.setNormalColor(getResources().getColor(R.color.main_text_grey));
                colorTransitionPagerTitleView.setSelectTypeBold(true);
                colorTransitionPagerTitleView.setSelectedColor(getResources().getColor(R.color.main_text_black));

                colorTransitionPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        binding.viewpager.setCurrentItem(index);
                    }
                });
                return colorTransitionPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);

                indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                indicator.setLineHeight((float) UIUtil.dip2px(context, 3.0d));
                indicator.setLineWidth((float) UIUtil.dip2px(context, 16.0d));
                indicator.setRoundRadius((float) UIUtil.dip2px(context, 3.0d));
                indicator.setStartInterpolator(new AccelerateInterpolator());
                indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));
                indicator.setColors(new Integer[]{Integer.valueOf(getResources().getColor(R.color.child_tab_yellow))});
                return indicator;
            }
        });
        binding.indicator.setNavigator(commonNavigator);
        binding.viewpager.setOffscreenPageLimit(titles.length);
        binding.viewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                if(position == 0){
                    return CommentListFragment.newInstance();
                }else if(position == 1){
                    return PraiseListFragment.newInstance();
                }else{
                    return GiftListFragment.newInstance();
                }
            }

            @Override
            public int getCount() {
                return titles.length;
            }
        });

        binding.viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                binding.indicator.onPageScrolled(position,positionOffset,positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                binding.indicator.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                binding.indicator.onPageScrollStateChanged(state);
            }
        });
    }

    /**
     * 回复帖子的评论
     */
    private void initCommentDialog() {
        CommentDialogFragment commentDialogFragment = new CommentDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("userId", "当前用户id");
        commentDialogFragment.setArguments(bundle);
        commentDialogFragment.setCancelable(true);
        commentDialogFragment.show(getSupportFragmentManager(), "CommentListDialogFragment");
    }

    /**
     * 回复评论的评论
     */
    private void initCommentDialog(Map commentItem) {
        CommentDialogFragment commentDialogFragment = new CommentDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("userId", "当前用户id");
        bundle.putString("commentId", "评论的那条id");
        bundle.putString("nickname", "对谁评论");
        commentDialogFragment.setArguments(bundle);
        commentDialogFragment.setCancelable(true);
        commentDialogFragment.show(getSupportFragmentManager(), "CommentListDialogFragment");
    }

    public static void action(Context context, NeteaseList.DataBean bean){
        Intent intent = new Intent(context, TrendDetailActivity.class);
        intent.putExtra("bean",bean);
        context.startActivity(intent);
    }

    @Override
    public String getCommentText() {
        return "";
    }

    @Override
    public void setCommentText(String commentTextTemp) {

    }

    @Override
    public void setCommentToWhichUserid(String userId) {

    }

    @Override
    public String getCommentToWhichUserid() {
        return null;
    }

    @Override
    public String getCommentId() {
        return null;
    }

    @Override
    public void setCommentId(String commentId) {

    }

    @Override
    public void submitCommentToPost(String commentTextTemp) {

    }

    @Override
    public void submitCommentToSb(String commentTextTemp, String commentId) {

    }

    @Override
    public void alertCommentSbDialog(Map item) {

    }
}
