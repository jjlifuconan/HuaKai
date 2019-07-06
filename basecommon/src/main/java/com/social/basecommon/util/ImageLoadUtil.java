package com.social.basecommon.util;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.social.basecommon.R;


import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * Created by jingbin on 2016/11/26.
 */

public class ImageLoadUtil {

    private static ImageLoadUtil instance;

    private ImageLoadUtil() {
    }

    public static ImageLoadUtil getInstance() {
        if (instance == null) {
            instance = new ImageLoadUtil();
        }
        return instance;
    }


    /**
     * 显示随机的图片(每日推荐)
     *
     * @param imgNumber 有几张图片要显示,对应默认图
     * @param imageUrl  显示图片的url
     * @param imageView 对应图片控件
     */
    public static void displayRandom(int imgNumber, String imageUrl, ImageView imageView) {
        GlideApp.with(imageView.getContext())
                .load(imageUrl)
                .placeholder(getMusicDefaultPic(imgNumber))
                .error(getMusicDefaultPic(imgNumber))
                .transition(withCrossFade(1500))
                .into(imageView);
    }

    private static int getMusicDefaultPic(int imgNumber) {
        switch (imgNumber) {
            case 1:
                return R.drawable.img_two_bi_one;
            case 2:
                return R.drawable.img_four_bi_three;
            case 3:
                return R.drawable.img_one_bi_one;
            case 4:
                return R.drawable.shape_bg_loading;
            default:
                break;
        }
        return R.drawable.img_four_bi_three;
    }

//--------------------------------------

    /**
     * 用于干货item，将gif图转换为静态图
     */
    public static void displayGif(String url, ImageView imageView) {

        GlideApp.with(imageView.getContext()).load(url)
//                .asBitmap()
                .placeholder(R.drawable.img_one_bi_one)
                .error(R.drawable.img_one_bi_one)
//                .skipMemoryCache(true) //跳过内存缓存
//                .crossFade(1000)
//                .diskCacheStrategy(DiskCacheStrategy.SOURCE)// 缓存图片源文件（解决加载gif内存溢出问题）
//                .into(new GlideDrawableImageViewTarget(imageView, 1));
                .into(imageView);
    }

    /**
     * 用于干货item，将gif图转换为静态图
     */
    public static void displayLocalGif(ImageView imageView, int resId) {
        GlideApp.with(imageView.getContext()).asGif().load(resId)
//                .asBitmap()
//                .placeholder(R.drawable.img_one_bi_one)
//                .error(R.drawable.img_one_bi_one)
//                .skipMemoryCache(true) //跳过内存缓存
//                .crossFade(1000)
//                .diskCacheStrategy(DiskCacheStrategy.SOURCE)// 缓存图片源文件（解决加载gif内存溢出问题）
//                .into(new GlideDrawableImageViewTarget(imageView, 1));
                .into(imageView);
    }


    /**
     * 书籍、妹子图、电影列表图
     * 默认图区别
     */
    public static void displayEspImage(String url, ImageView imageView, int type) {
        GlideApp.with(imageView.getContext())
                .load(url)
                .transition(withCrossFade(500))
                .placeholder(getDefaultPic(type))
                .error(getDefaultPic(type))
                .into(imageView);
    }

    private static int getDefaultPic(int type) {
        switch (type) {
            case 0:// 电影
                return R.drawable.img_default_movie;
            case 1:// 妹子
                return R.drawable.img_default_meizi;
            case 2:// 书籍
                return R.drawable.img_default_gift;
            case 3:// 男性
                return R.drawable.ic_boy;
            case 4:// 女性
                return R.drawable.ic_girl;
            case 5:
                return R.drawable.shape_bg_loading;
            default:
                break;
        }
        return R.drawable.img_default_meizi;
    }

    /**
     * 显示高斯模糊效果（电影详情页）
     */
    private static void displayGaussian(Context context, String url, ImageView imageView) {
        // "23":模糊度；"4":图片缩放4倍后再进行模糊
        GlideApp.with(context)
                .load(url)
                .error(R.drawable.stackblur_default)
                .placeholder(R.drawable.stackblur_default)
                .transition(withCrossFade(500))
                .into(imageView);
    }

    /**
     * 加载圆角图,暂时用到显示头像
     */
    @BindingAdapter("android:displayCircle")
    public static void displayCircle(ImageView imageView, String imageUrl) {
        GlideApp.with(imageView.getContext())
                .load(imageUrl)
                .transition(withCrossFade(500))
                .error(R.drawable.ic_avatar_default)
//                .listener(new RequestListener<Drawable>() {
//                    @Override
//                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                        return false;
//                    }
//
//                    @Override
//                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                        return false;
//                    }
//                })
                .transform(new GlideCircleTransform(imageView.getContext()))
                .into(imageView);
    }

    /**
     * 加载圆角图,暂时用到显示头像
     */
    @BindingAdapter({"android:displayCircle", "android:defaultPicType"})
    public static void displayCircle(ImageView imageView, String imageUrl, int defaultPicType) {
        GlideApp.with(imageView.getContext())
                .load(imageUrl)
                .transition(withCrossFade(500))
                .error(getDefaultPic(defaultPicType))
                .transform(new GlideCircleTransform(imageView.getContext()))
                .into(imageView);
    }

    /**
     * 妹子，电影列表图
     *
     * @param defaultPicType 电影：0；妹子：1； 书籍：2
     */
    @BindingAdapter({"android:displayFadeImage", "android:defaultPicType"})
    public static void displayFadeImage(ImageView imageView, String url, int defaultPicType) {
        Log.e("FLJ","url-->"+url);
        displayEspImage(url, imageView, defaultPicType);
    }

    /**
     * 妹子，电影列表图
     *
     * @param defaultPicType 电影：0；妹子：1； 书籍：2
     */
    @BindingAdapter({"android:displayWrapImage", "android:defaultPicType"})
    public static void displayWrapImage(ImageView imageView, String url, int defaultPicType) {
        Log.e("FLJ","url-->"+url);
        GlideApp.with(imageView.getContext())
                .load(url)
                .centerCrop()
                .placeholder(getDefaultPic(defaultPicType))
                .error(getDefaultPic(defaultPicType))
                .into(imageView);
    }

    /**
     * 电影详情页显示电影图片(等待被替换)（测试的还在，已可以弃用）
     * 没有加载中的图
     */
    @BindingAdapter("android:showImg")
    public static void showImg(ImageView imageView, String url) {
        GlideApp.with(imageView.getContext())
                .load(url)
                .transition(withCrossFade(500))
                .error(getDefaultPic(0))
                .into(imageView);
    }

    /**
     * 电影列表图片
     */
    @BindingAdapter("android:showMovieImg")
    public static void showMovieImg(ImageView imageView, String url) {
        GlideApp.with(imageView.getContext())
                .load(url)
                .transition(withCrossFade(500))
                .override((int) CommonUtils.getDimens(imageView.getContext(), R.dimen.movie_detail_width), (int) CommonUtils.getDimens(imageView.getContext(), R.dimen.movie_detail_height))
                .placeholder(getDefaultPic(0))
                .error(getDefaultPic(0))
                .into(imageView);
    }

    /**
     * 书籍列表图片
     */
    @BindingAdapter("android:showBookImg")
    public static void showBookImg(ImageView imageView, String url) {
        GlideApp.with(imageView.getContext())
                .load(url)
                .transition(withCrossFade(500))
                .override((int) CommonUtils.getDimens(imageView.getContext(), R.dimen.book_detail_width), (int) CommonUtils.getDimens(imageView.getContext(), R.dimen.book_detail_height))
                .placeholder(getDefaultPic(2))
                .error(getDefaultPic(2))
                .into(imageView);
    }

    /**
     * 电影详情页显示高斯背景图
     */
    @BindingAdapter("android:showImgBg")
    public static void showImgBg(ImageView imageView, String url) {
        displayGaussian(imageView.getContext(), url, imageView);
    }


    /**
     * 热门电影头部图片
     */
    @BindingAdapter({"android:displayRandom", "android:imgType"})
    public static void displayRandom(ImageView imageView, int imageUrl, int imgType) {
        GlideApp.with(imageView.getContext())
                .load(imageUrl)
                .placeholder(getMusicDefaultPic(imgType))
                .error(getMusicDefaultPic(imgType))
                .transition(withCrossFade(1500))
                .into(imageView);
    }
}
