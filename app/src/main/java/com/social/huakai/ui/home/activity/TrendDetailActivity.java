package com.social.huakai.ui.home.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.gyf.immersionbar.ImmersionBar;
import com.social.basecommon.activity.BaseActivity;
import com.social.basecommon.util.DensityUtil;
import com.social.huakai.R;
import com.social.huakai.databinding.ActivityDetailTrendBinding;
import com.social.huakai.ui.home.bean.NeteaseList;
import com.social.huakai.ui.home.fragment.CommentDialogFragment;
import com.social.huakai.ui.home.fragment.FemaleGrabChatFragment;
import com.social.huakai.ui.home.fragment.MaleAskingForChatFragment;
import com.social.huakai.ui.home.fragment.TrendFragment;
import com.social.huakai.ui.home.interfaces.DialogFragmentDataCallback;
import com.social.huakai.widget.ScaleTransitionPagerTitleView;

import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;

import java.util.Map;

public class TrendDetailActivity extends BaseActivity implements DialogFragmentDataCallback {
    ActivityDetailTrendBinding binding;
    NeteaseList.DataBean bean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_trend);
        ImmersionBar.setTitleBar(this, binding.titlebar);

        bean = (NeteaseList.DataBean) getIntent().getSerializableExtra("bean");
        binding.setBean(bean);
        initView();
        setListener();


    }

    private void setListener() {
        binding.layoutBottom.tvComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initCommentDialog();
            }
        });
    }

    private void initView(){
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
                colorTransitionPagerTitleView.setTextSize(20.0f);
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

                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                indicator.setLineHeight((float) UIUtil.dip2px(context, 3.0d));
                indicator.setLineWidth((float) UIUtil.dip2px(context, 10.0d));
                indicator.setRoundRadius((float) UIUtil.dip2px(context, 3.0d));
                indicator.setStartInterpolator(new AccelerateInterpolator());
                indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));
                indicator.setColors(new Integer[]{Integer.valueOf(getResources().getColor(R.color.main_text_black))});
                return indicator;
            }
        });
        binding.indicator.setNavigator(commonNavigator);
        binding.viewpager.setOffscreenPageLimit(titles.length);
        binding.viewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                if(position == 0){
                    return TrendFragment.newInstance();
                }else if(position == 1){
                    return MaleAskingForChatFragment.newInstance();
                }else{
                    return FemaleGrabChatFragment.newInstance();
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
