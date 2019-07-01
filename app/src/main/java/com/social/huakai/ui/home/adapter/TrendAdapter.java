package com.social.huakai.ui.home.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.jaeger.ninegridimageview.ItemImageClickListener;
import com.jaeger.ninegridimageview.ItemImageLongClickListener;
import com.jaeger.ninegridimageview.NineGridImageView;
import com.jaeger.ninegridimageview.NineGridImageViewAdapter;
import com.social.basecommon.adapter.BaseBindingAdapter;
import com.social.basecommon.adapter.BaseBindingViewHolder;
import com.social.basecommon.util.DensityUtil;
import com.social.basecommon.util.ImageLoadUtil;
import com.social.basecommon.viewbigimage.ViewBigImageActivity;
import com.social.huakai.R;
import com.social.huakai.databinding.ItemFindBinding;
import com.social.huakai.databinding.ItemTrendBinding;
import com.social.huakai.ui.find.bean.GankIoDataBean;
import com.social.huakai.ui.home.bean.NeteaseList;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * @author Administrator
 * @date 2019/6/27 0027
 * @description:
 */
public class TrendAdapter extends BaseBindingAdapter<NeteaseList.DataBean, ItemTrendBinding> {

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
    protected void onBindItem(ItemTrendBinding binding, NeteaseList.DataBean item) {
        binding.setBean(item);
        List<NeteaseList.DataBean.ImagesBean> imagesBeans = item.getImages();
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
                return super.generateImageView(context);
            }

            @Override
            protected void onItemImageClick(Context context, ImageView imageView, int index, List<String> list) {
                Toast.makeText(context, "image position is " + index, Toast.LENGTH_SHORT).show();
                ViewBigImageActivity.startImageList(context, index, (ArrayList<String>) list, null);
            }

            @Override
            protected boolean onItemImageLongClick(Context context, ImageView imageView, int index, List<String> list) {
                Toast.makeText(context, "image long click position is " + index, Toast.LENGTH_SHORT).show();
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
