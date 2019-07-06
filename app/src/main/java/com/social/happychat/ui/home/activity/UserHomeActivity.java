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

import com.google.gson.GsonBuilder;
import com.gyf.immersionbar.ImmersionBar;
import com.social.basecommon.activity.BaseActivity;
import com.social.basecommon.util.DensityUtil;
import com.social.basecommon.util.ImageLoadUtil;
import com.social.basecommon.util.PerfectClickListener;
import com.social.basecommon.viewbigimage.ViewBigImageActivity;
import com.social.happychat.R;
import com.social.happychat.databinding.ActivityUserHomeBinding;
import com.social.happychat.http.HttpClient;
import com.social.happychat.ui.home.bean.UserDetailBean;
import com.social.happychat.ui.home.fragment.TrendFragment;
import com.social.happychat.ui.home.fragment.UserInfoShowFragment;
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

import java.util.ArrayList;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Administrator
 * @date 2019/7/5 0005
 * @description:
 */
public class UserHomeActivity extends BaseActivity {
    ActivityUserHomeBinding binding;
    String[] titles;
    UserDetailBean bean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_home);
        ImmersionBar.with(this).init();
        ImmersionBar.setTitleBar(this, binding.toolbar);
        initView();
        setListener();
        getUserData();
    }

    private void getUserData() {
        Subscription subscription = HttpClient.Builder.getNeteaseServer().getNeteaseList(1, 10)
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
                        String sss = "{\"code\":200,\"data\":{\"tags\":[\"音乐\",\"健身\",\"美食\",\"交友\",\"看电影\"],\"receiveList\":[\"http://pic112.huitu.com/pic/20181209/1367568_20181209204106498015_0.jpg\",\"http://pic123.huitu.com/pic/20190622/1875964_20190622164913023060_0.jpg\",\"http://pic111.huitu.com/pic/20181117/21146_20181117084504603070_0.jpg\",\"http://pic0.huitu.com/pic/20190610/1028527_20190610221750779144_0.jpg\"],\"sendList\":[\"http://pic0.huitu.com/pic/20190624/1912_20190624130041628140_0.jpg\",\"http://pic192.nipic.com/pic/20181117/27018288_133229104081_4.jpg\",\"http://pic0.huitu.com/pic/20190602/1827356_20190602215214715149_0.jpg\",\"http://pic117.huitu.com/pic/20190413/1825142_20190413133218311020_0.jpg\"],\"visitorList\":[\"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_50832164_1561163576484.jpeg?x-oss-process=image/resize,m_mfit,h_200,w_200\",\"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_93814250_1560997114407.png?x-oss-process=image/resize,m_mfit,h_200,w_200\",\"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_92364017_1549162345087.png?x-oss-process=image/resize,m_mfit,h_200,w_200\",\"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_15472389_1548875114076.png?x-oss-process=image/resize,m_mfit,h_200,w_200\"],\"images\":[\"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3473128871,1574804327&fm=27&gp=0.jpg\",\"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=268042761,1199832267&fm=27&gp=0.jpg\",\"https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1487351610,315303232&fm=27&gp=0.jpg\",\"https://pics0.baidu.com/feed/b7fd5266d016092407882342bd2c20ffe7cd34ee.jpeg?token=465f69938285f98e4a70fc64b6cb7258&s=FB84DF08F4C036FEDF125D870300F088\",\"https://pics0.baidu.com/feed/a71ea8d3fd1f4134b75026134c3480cfd1c85e23.jpeg?token=5b163d06e91c7836d33fff7269ec545b&s=F100DF158D53F6C4CE8394C3030060B9\"],\"receiveNum\":16,\"sendNum\":13,\"praiseNum\":1,\"nickName\":\"单身闯天下\",\"photo\":\"http://huakai-api.oss-cn-shenzhen.aliyuncs.com/user_header/user_header_50832164_1561163576484.jpeg?x-oss-process=image/resize,m_mfit,h_200,w_200\",\"signature\":\"世界背叛了沵，我为沵背叛世界\",\"sex\":\"0\",\"socialId\":\"1968234653\",\"registerTime\":\"2017-10-13\",\"job\":\"银行职员\",\"age\":\"24\",\"constellation\":\"白羊座\",\"homeTown\":\"江苏南京\",\"emotion\":\"单身\"},\"message\":\"\"}";
                        bean = new GsonBuilder().serializeNulls().create().fromJson(sss, UserDetailBean.class);
                        setData(bean);
                    }
                });
        addSubscription(subscription);
    }

    private void setData(UserDetailBean bean){
        //设置图片集合
        binding.layoutHeader.banner.setImages(bean.getData().getImages());
//        //banner设置方法全部调用完毕时最后调用
        binding.layoutHeader.banner.start();
        binding.layoutHeader.setBean(bean.getData());

        binding.viewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                if(position == 0){
                    return UserInfoShowFragment.newInstance(bean.getData());
                }else{
                    return TrendFragment.newInstance();
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
                    colorTransitionPagerTitleView.setText(titles[index]);
                }else if(index == 1){
                    colorTransitionPagerTitleView.setText(titles[index]+"14");
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
                ViewBigImageActivity.startImageList(activity, position, (ArrayList<String>) bean.getData().getImages(), null);
            }
        });

        binding.vpSendGift.setOnClickListener(new PerfectClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                GiftShopActivity.action(v.getContext());
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

    public static void action(Context context, String userId){
        Intent intent = new Intent(context, UserHomeActivity.class);
        intent.putExtra("userId",userId);
        context.startActivity(intent);
    }


}
