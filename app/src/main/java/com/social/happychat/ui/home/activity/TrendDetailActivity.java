package com.social.happychat.ui.home.activity;

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
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import com.gyf.immersionbar.ImmersionBar;
import com.jaeger.ninegridimageview.ItemImageClickListener;
import com.jaeger.ninegridimageview.ItemImageLongClickListener;
import com.jaeger.ninegridimageview.NineGridImageViewAdapter;
import com.social.basecommon.activity.BaseActivity;
import com.social.basecommon.util.DensityUtil;
import com.social.basecommon.util.ImageLoadUtil;
import com.social.basecommon.util.PerfectClickListener;
import com.social.basecommon.util.SPUtils;
import com.social.basecommon.util.ToastUtil;
import com.social.basecommon.viewbigimage.ViewBigImageActivity;
import com.social.happychat.R;
import com.social.happychat.bean.BaseBean;
import com.social.happychat.constant.Constant;
import com.social.happychat.databinding.ActivityDetailTrendBinding;
import com.social.happychat.event.RefreshCommentNumEvent;
import com.social.happychat.event.RefreshPraiseEvent;
import com.social.happychat.event.RefreshSingleItemEvent;
import com.social.happychat.http.HttpClient;
import com.social.happychat.http.RequestImpl;
import com.social.happychat.ui.home.bean.NeteaseList;
import com.social.happychat.ui.home.bean.TrendListBean;
import com.social.happychat.ui.home.fragment.CommentDialogFragment;
import com.social.happychat.ui.home.fragment.CommentListFragment;
import com.social.happychat.ui.home.fragment.GiftRecordListFragment;
import com.social.happychat.ui.home.fragment.PraiseListFragment;
import com.social.happychat.ui.home.interfaces.DialogFragmentDataCallback;
import com.social.happychat.ui.home.interfaces.SimpleTrendNavigator;
import com.social.happychat.ui.home.present.TrendPresent;
import com.social.happychat.ui.login.bean.UserBean;
import com.social.happychat.ui.main.MainActivity;
import com.social.happychat.util.RequestBody;
import com.social.happychat.widget.ScaleTransitionPagerTitleView;
import com.social.happychat.widget.TouchImageView;

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

public class TrendDetailActivity extends BaseActivity implements DialogFragmentDataCallback {
    ActivityDetailTrendBinding binding;
    int position = -1;//标记列表的位置，返回更新数据
    TrendListBean.ListBean bean;
    private String commentText = "";
    private int replyUserId = 0;

    private CommonNavigator commonNavigator;
    private TrendPresent trendPresent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_trend);
        ImmersionBar.with(this).init();
        ImmersionBar.setTitleBar(this, binding.titlebar);
        trendPresent = new TrendPresent(new SimpleTrendNavigator(){
            @Override
            public void refreshPraiseList(int operateType) {
                EventBus.getDefault().post(new RefreshPraiseEvent());
            }

            @Override
            public void addRxSubscription(Subscription subscription) {
                addSubscription(subscription);
            }
        });
        position = getIntent().getIntExtra("position", -1);
        bean = (TrendListBean.ListBean) getIntent().getSerializableExtra("bean");
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

        binding.layoutBottom.vpPraise.setOnClickListener(new PerfectClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                if(bean.getIsPraise() == 1){
                    bean.setIsPraise(0);
                    bean.setPraiseCount(bean.getPraiseCount()-1);
                    updateIndicatorTitle();
                    trendPresent.praiseAction(bean.getId(),2,1);
                }else{
                    binding.layoutBottom.ivPraise.startAnimation(AnimationUtils.loadAnimation(
                            activity, R.anim.dianzan_anim));
                    bean.setIsPraise(1);
                    bean.setPraiseCount(bean.getPraiseCount()+1);
                    updateIndicatorTitle();
                    trendPresent.praiseAction(bean.getId(),1,1);
                }
            }
        });
        binding.layoutBottom.tvComment.setOnClickListener(new PerfectClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                initCommentDialog();
            }
        });

        binding.layoutBottom.vpGift.setOnClickListener(new PerfectClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                GiftShopActivity.action(view.getContext(), GiftShopActivity.TYPE_SHOP);
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



        List<TrendListBean.ListBean.UserFilesBean> imagesBeans = bean.getUserFiles();
        ArrayList strList = new ArrayList();
        if(imagesBeans!=null && !imagesBeans.isEmpty()){
            for(int i=0;i<imagesBeans.size();i++){
                strList.add(imagesBeans.get(i).getFileUrl());
            }
        }
        binding.layoutHeader.nineimage.setImagesData(strList);

        final String[] titles = getResources().getStringArray(R.array.trendDetailTabTitle);

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
                    colorTransitionPagerTitleView.setText(titles[index]+(bean.getCommentCount()>0?bean.getCommentCount():""));
                }else if(index == 1){
                    colorTransitionPagerTitleView.setText(titles[index]+(bean.getPraiseCount()>0?bean.getPraiseCount():""));
                }else if(index == 2){
                    colorTransitionPagerTitleView.setText(titles[index]+(bean.getGiftCount()>0?bean.getGiftCount():""));
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
                    return CommentListFragment.newInstance(bean.getId());
                }else if(position == 1){
                    return PraiseListFragment.newInstance(bean.getId());
                }else{
                    return GiftRecordListFragment.newInstance();
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
        commentDialogFragment.setCancelable(true);
        commentDialogFragment.show(getSupportFragmentManager(), "CommentListDialogFragment");
    }

    /**
     * 回复评论的评论
     */
    private void initCommentDialog(int replyUserid, String replyName) {
        CommentDialogFragment commentDialogFragment = new CommentDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("replyUserid", replyUserid);
        bundle.putString("replyName", replyName);
        commentDialogFragment.setArguments(bundle);
        commentDialogFragment.setCancelable(true);
        commentDialogFragment.show(getSupportFragmentManager(), "CommentListDialogFragment");
    }

    public static void action(Context context, int position, TrendListBean.ListBean bean){
        Intent intent = new Intent(context, TrendDetailActivity.class);
        intent.putExtra("position",position);
        intent.putExtra("bean",bean);
        context.startActivity(intent);
    }

    @Override
    public String getCommentText() {
        return commentText;
    }

    @Override
    public void setCommentText(String commentTextTemp) {
        commentText = commentTextTemp;
    }

    @Override
    public void setCommentToWhichUserid(int replyUserId) {
        this.replyUserId = replyUserId;
    }

    @Override
    public int getCommentToWhichUserid() {
        return replyUserId;
    }

    @Override
    public void submitCommentToPost(String commentTextTemp) {
        UserBean userBean = SPUtils.getObject(activity, Constant.SP_HAPPY_CHAT, Constant.PLATFORM_HAPPYCHAT_USER_INFO, UserBean.class);
        Map map = new HashMap();
        map.put("dynamicId",bean.getId());
        map.put("content",commentTextTemp.trim());
        map.put("headPhotoUrl",userBean.getHeadPhotoUrl());
        map.put("userName",userBean.getNickName());
        map.put("userId",userBean.getId());
        Subscription subscription = HttpClient.Builder.getRealServer().doComment(RequestBody.as(map))
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
                            //刷新评论列表、详情评论数、列表评论数
                            bean.setCommentCount(bean.getCommentCount()+1);
                            updateIndicatorTitle();
                            EventBus.getDefault().post(new RefreshCommentNumEvent(bean.getCommentCount()));
                        }else{
                            ToastUtil.show(activity, userBean.getMsg());
                        }


                    }
                });
        addSubscription(subscription);


    }

    private void updateIndicatorTitle(){
        final String[] titles = getResources().getStringArray(R.array.trendDetailTabTitle);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return titles.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                ScaleTransitionPagerTitleView colorTransitionPagerTitleView = new ScaleTransitionPagerTitleView(context);
                if(index == 0){
                    colorTransitionPagerTitleView.setText(titles[index]+(bean.getCommentCount()>0?bean.getCommentCount():""));
                }else if(index == 1){
                    colorTransitionPagerTitleView.setText(titles[index]+(bean.getPraiseCount()>0?bean.getPraiseCount():""));
                }else if(index == 2){
                    colorTransitionPagerTitleView.setText(titles[index]+(bean.getGiftCount()>0?bean.getGiftCount():""));
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

    @Override
    public void submitCommentToSb(int replyUserid, String replyName, String commentTextTemp) {
        UserBean userBean = SPUtils.getObject(activity, Constant.SP_HAPPY_CHAT, Constant.PLATFORM_HAPPYCHAT_USER_INFO, UserBean.class);
        Map map = new HashMap();
        map.put("dynamicId",bean.getId());
        map.put("userId",userBean.getId());
        map.put("userName",userBean.getNickName());
        map.put("replyUserId",replyUserid);
        map.put("replyUserName",replyName);
        map.put("content",commentTextTemp.trim());
        map.put("headPhotoUrl",userBean.getHeadPhotoUrl());
        Subscription subscription = HttpClient.Builder.getRealServer().doComment(RequestBody.as(map))
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
                            //刷新评论列表、详情评论数、列表评论数
                            bean.setCommentCount(bean.getCommentCount()+1);
                            EventBus.getDefault().post(new RefreshCommentNumEvent(bean.getCommentCount()));
                        }else{
                            ToastUtil.show(activity, baseBean.getMsg());
                        }


                    }
                });
        addSubscription(subscription);

    }

    @Override
    public void alertCommentSbDialog(int replyUserid, String replyName) {
        initCommentDialog(replyUserid, replyName);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        postItemChangedEvent();
    }

    /**
     * 返回上级页面参数变化事件
     */
    public void postItemChangedEvent(){
        if(position != -1){
            Map modify_map = new HashMap();
            modify_map.put("isPraise",bean.getIsPraise());
            modify_map.put("praiseCount",bean.getPraiseCount());
            modify_map.put("commentCount",bean.getCommentCount());
            modify_map.put("giftCount",bean.getGiftCount());
            EventBus.getDefault().post(new RefreshSingleItemEvent(position, modify_map));
        }
    }
}
