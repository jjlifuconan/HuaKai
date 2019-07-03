package com.social.huakai.ui.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.gyf.immersionbar.ImmersionBar;
import com.social.basecommon.activity.BaseActivity;
import com.social.huakai.R;
import com.social.huakai.databinding.ActivityMainBinding;
import com.social.huakai.ui.find.fragment.FindFragment;
import com.social.huakai.ui.home.fragment.HomeFragment;
import com.social.huakai.ui.message.fragment.MessageFragment;
import com.social.huakai.ui.mine.fragment.MineFragment;
import com.social.huakai.widget.bottombarTitle.BottomBar;
import com.social.huakai.widget.bottombarTitle.BottomBarTab;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * @author Administrator
 * @date 2019/6/26 0026
 * @description:
 */
public class MainActivity extends BaseActivity {
    ActivityMainBinding binding;
    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FOURTH = 3;

    private SupportFragment[] mFragments = new SupportFragment[4];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        ImmersionBar.with(activity)
                .statusBarDarkFont(true, 0.2f)
                .init();
        SupportFragment firstFragment = findFragment(HomeFragment.class);
        if (firstFragment == null) {
            mFragments[FIRST] = HomeFragment.newInstance();
            mFragments[SECOND] = FindFragment.newInstance();
            mFragments[THIRD] = MessageFragment.newInstance();
            mFragments[FOURTH] = MineFragment.newInstance();

            loadMultipleRootFragment(R.id.fl_container, FIRST,
                    mFragments[FIRST],
                    mFragments[SECOND],
                    mFragments[THIRD],
                    mFragments[FOURTH]);
        } else {
            // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题

            // 这里我们需要拿到mFragments的引用
            mFragments[FIRST] = firstFragment;
            mFragments[SECOND] = findFragment(FindFragment.class);
            mFragments[THIRD] = findFragment(MessageFragment.class);
            mFragments[FOURTH] = findFragment(MineFragment.class);
        }

        initView();
    }

    private void initView() {

        binding.bottomBar.addItem(new BottomBarTab(this, R.drawable.ic_bottom_trend, R.drawable.ic_bottom_trend_selected,"首页"))
                .addItem(new BottomBarTab(this, R.drawable.ic_bottom_main, R.drawable.ic_bottom_main_selected,"发现"))
                .addItem(new BottomBarTab(this, R.drawable.ic_bottom_message_normal, R.drawable.ic_bottom_message,"消息"))
                .addItem(new BottomBarTab(this, R.drawable.ic_bottom_rank_normal, R.drawable.ic_bottom_rank_selected,"榜单"));

        binding.bottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {
                if(position == 0){
                    ImmersionBar.with(activity)
                            .statusBarDarkFont(true, 0.2f)
                            .init();
                }else{
                    ImmersionBar.with(activity)
                            .statusBarDarkFont(false)
                            .init();
                }
                showHideFragment(mFragments[position], mFragments[prePosition]);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
    }
}
