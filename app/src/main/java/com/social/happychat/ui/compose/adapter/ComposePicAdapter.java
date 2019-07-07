package com.social.happychat.ui.compose.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.social.basecommon.adapter.BaseBindingAdapter;
import com.social.basecommon.adapter.BaseBindingViewHolder;
import com.social.basecommon.util.PerfectClickListener;
import com.social.basecommon.viewbigimage.ViewBigImageActivity;
import com.social.happychat.R;
import com.social.happychat.databinding.ItemComposeAddBinding;
import com.social.happychat.databinding.ItemComposePicBinding;

import java.io.File;
import java.util.ArrayList;

/**
 * @author Administrator
 * @date 2019/6/27 0027
 * @description:
 */
public class ComposePicAdapter extends BaseBindingAdapter<String, ItemComposePicBinding> {
    private static final int TYPE_ADD = 0;
    private static final int TYPE_PIC = 1;

    private onAddPicListener listener;

    public ComposePicAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutResId(int viewType) {
        if(viewType == TYPE_ADD){
            return R.layout.item_compose_add;
        }else{
            return R.layout.item_compose_pic;
        }
    }

    @Override
    protected void onBindItem(ItemComposePicBinding binding, String item) {
        binding.setPath(item);
        binding.imgPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewBigImageActivity.startImageList(view.getContext(), getItems().indexOf(item), getItems(), null);
            }
        });
        binding.delImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null){
                    listener.deleteImage(new File(item).getName());
                }
                getItems().remove(getItems().indexOf(item));
            }
        });
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        if(viewType == TYPE_ADD){
            ItemComposeAddBinding binding = DataBindingUtil.inflate(LayoutInflater.from(this.context), this.getLayoutResId(viewType), parent, false);
            return new BaseBindingViewHolder(binding.getRoot());
        }else{
            ItemComposePicBinding binding = DataBindingUtil.inflate(LayoutInflater.from(this.context), this.getLayoutResId(viewType), parent, false);
            return new BaseBindingViewHolder(binding.getRoot());
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        if(position+1 <= this.items.size()){
            ItemComposePicBinding binding = DataBindingUtil.getBinding(holder.itemView);
            //防止越界
            this.onBindItem(binding, this.items.get(position));
            this.onBindItem(binding, this.items.get(position), position);
            setupItemViewClickListener(holder, this.items.get(position), position);
//            binding.delImg.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if(listener != null){
//                        listener.deleteImage(position);
//                    }
//                    getItems().remove(position);
//                }
//            });
        }else{
            ItemComposeAddBinding binding = DataBindingUtil.getBinding(holder.itemView);
            holder.itemView.setOnClickListener(new PerfectClickListener() {

                @Override
                protected void onNoDoubleClick(View v) {
                    if(listener != null){
                        listener.chooseImage();
                    }
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(getItems().size() < 9){
            if(position == getItemCount() - 1){
                return TYPE_ADD;
            }else{
                return TYPE_PIC;
            }
        }else{
            return TYPE_PIC;
        }
    }

    @Override
    public int getItemCount() {
        if(getItems().size() < 9){
            return getItems().size() + 1;
        }else{
            return 9;
        }
    }


    public void setOnAddPicListener(onAddPicListener listener){
        this.listener = listener;
    }

    public interface onAddPicListener{
        void chooseImage();

        /**
         *
         * @param path  此处应该是path  position删除了会变化
         */
        void deleteImage(String path);
    }
}
