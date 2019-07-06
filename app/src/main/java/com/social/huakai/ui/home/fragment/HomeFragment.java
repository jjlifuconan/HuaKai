package com.social.huakai.ui.home.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.gyf.immersionbar.ImmersionBar;
import com.social.basecommon.fragment.BaseFragment;
import com.social.basecommon.util.DensityUtil;
import com.social.huakai.R;
import com.social.huakai.databinding.FragmentHomeBinding;
import com.social.huakai.ui.home.activity.ComposeTrendActivity;
import com.social.huakai.ui.message.fragment.MessageFragment;
import com.social.huakai.widget.ScaleTransitionPagerTitleView;

import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;

/**
 * @author Administrator
 * @date 2019/6/26 0026
 * @description:
 */
public class HomeFragment extends BaseFragment<FragmentHomeBinding> {

    public static HomeFragment newInstance() {

        Bundle args = new Bundle();

        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int setContent() {
        return R.layout.fragment_home;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        ImmersionBar.setTitleBar(this, binding.titlebar);
        showContentView();
        binding.compose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ComposeTrendActivity.action(activity);
            }
        });
        final String[] titles = getResources().getStringArray(R.array.homeTabTitle);

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
                colorTransitionPagerTitleView.setText(titles[index]);
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

        binding.viewpager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
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

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();

    }
}
