package com.social.happychat.ui.home.activity;

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
import android.widget.ImageView;

import com.gyf.immersionbar.ImmersionBar;
import com.social.basecommon.util.DensityUtil;
import com.social.basecommon.util.ImageLoadUtil;
import com.social.basecommon.util.PerfectClickListener;
import com.social.basecommon.util.SPUtils;
import com.social.basecommon.util.ToastUtil;
import com.social.basecommon.viewbigimage.ViewBigImageActivity;
import com.social.happychat.R;
import com.social.happychat.base.BaseCookieActivity;
import com.social.happychat.bean.BaseBean;
import com.social.happychat.constant.Constant;
import com.social.happychat.databinding.ActivityUserHomeBinding;
import com.social.happychat.event.RefreshCommentNumEvent;
import com.social.happychat.http.HttpClient;
import com.social.happychat.im.SessionHelper;
import com.social.happychat.ui.compose.bean.ImageBean;
import com.social.happychat.ui.home.fragment.TrendFragment;
import com.social.happychat.ui.home.fragment.UserInfoShowFragment;
import com.social.happychat.ui.login.bean.UserBean;
import com.social.happychat.util.RequestBody;
import com.social.happychat.widget.ScaleTransitionPagerTitleView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Administrator
 * @date 2019/7/5 0005
 * @description:
 */
public class UserHomeActivity extends BaseCookieActivity {
    ActivityUserHomeBinding binding;
    String[] titles;
    private int userId;
    private UserBean userBean;
    private CommonNavigator commonNavigator;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_home);
        ImmersionBar.with(this).init();
        ImmersionBar.setTitleBar(this, binding.toolbar);
        userId = getIntent().getIntExtra("userId",0);
        initView();
        setListener();
        getUserData();
        addVistorRecord();
    }

    private void addVistorRecord(){
        Subscription subscription = HttpClient.Builder.getRealServer().addVistor(userId)
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
//                        if(baseBean.isValid()){
//                        }else{
//                            ToastUtil.show(activity, userBean.getMsg());
//                        }

                    }
                });
        addSubscription(subscription);
    }

    private void getUserData() {
        Subscription subscription = HttpClient.Builder.getRealServer().userDetail(userId)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserBean>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        int i =0;
                    }

                    @Override
                    public void onNext(UserBean baseBean) {
                        if(baseBean.getData() != null){
                            userBean = baseBean.getData();
                            updateIndicatorTitle();
                            upBottomVisibility();
                            setData(userBean);
                        }
                    }
                });
        addSubscription(subscription);
    }

    private void setData(UserBean bean){
        //设置图片集合
        if(bean.getUserFileDtos() != null && bean.getUserFileDtos().size()>0){
            List<ImageBean> imageBeans = bean.getUserFileDtos();
            List<String> strs = new ArrayList<>();
            for(ImageBean imageBean : imageBeans){
                strs.add(imageBean.getFileUrl());
            }
            binding.layoutHeader.banner.setImages(strs);
            //banner设置方法全部调用完毕时最后调用
            binding.layoutHeader.banner.start();
        }else{
            //设置为空 默认占位图才会出现
            binding.layoutHeader.banner.start();
        }
        binding.layoutHeader.setBean(bean);

        binding.viewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                if(position == 0){
                    return UserInfoShowFragment.newInstance(bean);
                }else{
                    return TrendFragment.newInstance(1);
                }
            }

            @Override
            public int getCount() {
                return titles.length;
            }
        });

    }

    /**
     * 初始化indicator
     */
    private void initIndicator(){
        titles = getResources().getStringArray(R.array.userHomeTabTitle);

        commonNavigator = new CommonNavigator(activity);
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
                    colorTransitionPagerTitleView.setText(titles[index]);
                }else if(index == 1){
                    colorTransitionPagerTitleView.setText(titles[index]);
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
     * 更新底部按钮可见性
     */
    private void upBottomVisibility(){
        UserBean localUser = SPUtils.getObject(activity, Constant.SP_HAPPY_CHAT, Constant.PLATFORM_HAPPYCHAT_USER_INFO, UserBean.class);
        if(userBean.getId() == localUser.getId()){
            binding.vpChat.setVisibility(View.GONE);
            binding.vpSendGift.setVisibility(View.GONE);
        }else{
            binding.vpChat.setVisibility(View.VISIBLE);
            binding.vpSendGift.setVisibility(View.VISIBLE);
        }

    }

    private void updateIndicatorTitle(){
        titles = getResources().getStringArray(R.array.userHomeTabTitle);

        commonNavigator = new CommonNavigator(activity);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return titles.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                ScaleTransitionPagerTitleView colorTransitionPagerTitleView = new ScaleTransitionPagerTitleView(context);
                if(index == 0){
                    colorTransitionPagerTitleView.setText(titles[index]);
                }else if(index == 1){
                    colorTransitionPagerTitleView.setText(titles[index]+(userBean.getDynamicNum()>0?userBean.getDynamicNum():""));
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
    }




    private void initView() {
        initBanner();
        initIndicator();
    }

    private void initBanner(){
        Banner banner = binding.layoutHeader.banner;

        //设置banner样式
//        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
//        banner.setImages(images);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.Default);
        //设置标题集合（当banner样式有显示title时）
//        banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(3500);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
//        banner.start();

    }

    //如果你需要考虑更好的体验，可以这么操作
    @Override
    protected void onStart() {
        super.onStart();
        //开始轮播
        binding.layoutHeader.banner.startAutoPlay();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //结束轮播
        binding.layoutHeader.banner.stopAutoPlay();
    }


    private void setListener() {
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        binding.layoutHeader.banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                List<ImageBean> imageBeans = userBean.getUserFileDtos();
                List<String> strs = new ArrayList<>();
                for(ImageBean imageBean : imageBeans){
                    strs.add(imageBean.getFileUrl());
                }
                ViewBigImageActivity.startImageList(activity, position, (ArrayList<String>) strs, null);
            }
        });

        binding.vpSendGift.setOnClickListener(new PerfectClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                GiftShopActivity.action(v.getContext(), GiftShopActivity.TYPE_SHOP, Constant.SendGiftType.USER_HOME, 0,userId);
            }
        });

        binding.vpChat.setOnClickListener(new PerfectClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                SessionHelper.startP2PSession(activity, userBean.getUserMobile());
            }
        });
    }

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            ImageLoadUtil.displayFadeImage(imageView,path.toString(),1);

        }

        //提供createImageView 方法，如果不用可以不重写这个方法，主要是方便自定义ImageView的创建
        @Override
        public ImageView createImageView(Context context) {
            ImageView imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            return imageView;
        }
    }

    public static void action(Context context, int userId){
        Intent intent = new Intent(context, UserHomeActivity.class);
        intent.putExtra("userId",userId);
        context.startActivity(intent);
    }


}
