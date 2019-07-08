package com.social.happychat.ui.home.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.jaeger.ninegridimageview.ItemImageClickListener;
import com.jaeger.ninegridimageview.ItemImageLongClickListener;
import com.jaeger.ninegridimageview.NineGridImageView;
import com.jaeger.ninegridimageview.NineGridImageViewAdapter;
import com.social.basecommon.adapter.BaseBindingAdapter;
import com.social.basecommon.adapter.BaseBindingViewHolder;
import com.social.basecommon.util.DensityUtil;
import com.social.basecommon.util.ImageLoadUtil;
import com.social.basecommon.viewbigimage.ViewBigImageActivity;
import com.social.happychat.R;
import com.social.happychat.databinding.ItemTrendBinding;
import com.social.happychat.ui.home.activity.GiftShopActivity;
import com.social.happychat.ui.home.bean.NeteaseList;
import com.social.happychat.ui.home.bean.TrendListBean;
import com.social.happychat.widget.TouchImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @date 2019/6/27 0027
 * @description:
 */
public class TrendAdapter extends BaseBindingAdapter<TrendListBean.ListBean, ItemTrendBinding> {

    public TrendAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutResId(int viewType) {
        return R.layout.item_trend;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemTrendBinding binding = DataBindingUtil.inflate(LayoutInflater.from(this.context), this.getLayoutResId(viewType), parent, false);
        return new TrendHolder(binding.getRoot());
    }

    @Override
    protected void onBindItem(ItemTrendBinding binding, TrendListBean.ListBean item) {
        binding.setBean(item);
        binding.vpPraise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(item.getIsPraise() == 1){
                    item.setIsPraise(0);
                    item.setPraiseCount(item.getPraiseCount()-1);
                }else{
                    binding.ivPraise.startAnimation(AnimationUtils.loadAnimation(
                            context, R.anim.dianzan_anim));
                    item.setIsPraise(1);
                    item.setPraiseCount(item.getPraiseCount()+1);
                }
            }
        });

        binding.vpGift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GiftShopActivity.action(view.getContext(), GiftShopActivity.TYPE_SHOP);
            }
        });
        List<TrendListBean.ListBean.UserFilesBean> imagesBeans = item.getUserFiles();
        ArrayList strList = new ArrayList();
        if(imagesBeans!=null && !imagesBeans.isEmpty()){
            for(int i=0;i<imagesBeans.size();i++){
                strList.add(imagesBeans.get(i).getFileUrl());
            }
        }
        binding.nineimage.setImagesData(strList);

    }

    public class TrendHolder extends BaseBindingViewHolder{
        private NineGridImageView<String> mNglContent;

        private NineGridImageViewAdapter<String> mAdapter = new NineGridImageViewAdapter<String>() {
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

        public TrendHolder(View itemView) {
            super(itemView);
            mNglContent = (NineGridImageView<String>) itemView.findViewById(R.id.nineimage);
            mNglContent.setAdapter(mAdapter);
            mNglContent.setItemImageClickListener(new ItemImageClickListener<String>() {
                @Override
                public void onItemImageClick(Context context, ImageView imageView, int index, List<String> list) {
                    Log.d("onItemImageClick", list.get(index));
                }
            });
            mNglContent.setItemImageLongClickListener(new ItemImageLongClickListener<String>() {
                @Override
                public boolean onItemImageLongClick(Context context, ImageView imageView, int index, List<String> list) {
                    Log.d("onItemImageLongClick", list.get(index));
                    return true;
                }
            });
        }
    }
}