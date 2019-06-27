package com.social.basecommon.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.social.basecommon.util.PerfectClickListener;

import java.util.List;

/**
 * Created by zmy on 2017/7/26.
 */

public abstract class BaseBindingAdapter<M, B extends ViewDataBinding> extends RecyclerView.Adapter
{
    protected Context context;
    protected ObservableArrayList<M> items;
    protected ListChangedCallback itemsChangeCallback;
    protected OnItemClickListener<M> mItemClickListener;
    OnItemClickListener2 mItemClickListener2;
    OnItemClickListener3 mItemClickListener3;

    OnItemLongClickListener<M> mItemLongClickListener;
    private static final String TAG = "FLJ";

    public BaseBindingAdapter(Context context)
    {
        this.context = context;
        initObservableArrayList();
        this.itemsChangeCallback = new ListChangedCallback();
    }

    /**
     * 给子类重写进而控制动画效果
     */
    protected void initObservableArrayList(){
        this.items = new ObservableArrayList<>();
    }

    public ObservableArrayList<M> getItems()
    {
        return items;
    }

    @Override
    public int getItemCount()
    {
        return this.items.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        Log.e(TAG,"onCreateViewHolder viewType-->"+viewType);
        B binding = DataBindingUtil.inflate(LayoutInflater.from(this.context), this.getLayoutResId(viewType), parent, false);
        return new BaseBindingViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List payloads) {
        Log.e(TAG,"onBindViewHolder payloads position-->"+position);
        super.onBindViewHolder(holder, position, payloads);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        Log.e(TAG,"onBindViewHolder position-->"+position);
        B binding = DataBindingUtil.getBinding(holder.itemView);
        if(position+1 <= this.items.size()){
            //防止越界
            this.onBindItem(binding, this.items.get(position));
            this.onBindItem(binding, this.items.get(position), position);
            setupItemViewClickListener(holder, this.items.get(position), position);
        }else{
            this.bindExtraHolder(binding, position);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView)
    {
        super.onAttachedToRecyclerView(recyclerView);
        this.items.addOnListChangedCallback(itemsChangeCallback);
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView)
    {
        super.onDetachedFromRecyclerView(recyclerView);
        this.items.removeOnListChangedCallback(itemsChangeCallback);
    }

    //region 处理数据集变化
    protected void onChanged(ObservableArrayList<M> newItems)
    {
        resetItems(newItems);
        notifyDataSetChanged();
    }

    protected void onItemRangeChanged(ObservableArrayList<M> newItems, int positionStart, int itemCount)
    {
        resetItems(newItems);
        notifyItemRangeChanged(positionStart,itemCount);
    }

    protected void onItemRangeInserted(ObservableArrayList<M> newItems, int positionStart, int itemCount)
    {
        resetItems(newItems);
        notifyItemRangeInserted(positionStart,itemCount);
    }

    protected void onItemRangeMoved(ObservableArrayList<M> newItems)
    {
        resetItems(newItems);
        notifyDataSetChanged();
    }

    protected void onItemRangeRemoved(ObservableArrayList<M> newItems, int positionStart, int itemCount)
    {
        resetItems(newItems);
        notifyItemRangeRemoved(positionStart,itemCount);
    }

    protected void resetItems(ObservableArrayList<M> newItems)
    {
        this.items = newItems;
    }
    //endregion

    protected abstract @LayoutRes
    int getLayoutResId(int viewType);

    protected abstract void onBindItem(B binding, M item);

    /**
     * 额外的写死的holder,因为没有添加到items集合中所以判断越界了
     * @param binding
     * @param position
     */
    protected void bindExtraHolder(B binding, int position){

    }

    protected void onBindItem(B binding, M item, int position){

    }

    class ListChangedCallback extends ObservableArrayList.OnListChangedCallback<ObservableArrayList<M>>
    {
        @Override
        public void onChanged(ObservableArrayList<M> newItems)
        {
            BaseBindingAdapter.this.onChanged(newItems);
        }

        @Override
        public void onItemRangeChanged(ObservableArrayList<M> newItems, int i, int i1)
        {
            BaseBindingAdapter.this.onItemRangeChanged(newItems,i,i1);
        }

        @Override
        public void onItemRangeInserted(ObservableArrayList<M> newItems, int i, int i1)
        {
            BaseBindingAdapter.this.onItemRangeInserted(newItems,i,i1);
        }

        @Override
        public void onItemRangeMoved(ObservableArrayList<M> newItems, int i, int i1, int i2)
        {
            BaseBindingAdapter.this.onItemRangeMoved(newItems);
        }

        @Override
        public void onItemRangeRemoved(ObservableArrayList<M> sender, int positionStart, int itemCount)
        {
            BaseBindingAdapter.this.onItemRangeRemoved(sender,positionStart,itemCount);
        }
    }

    public void setOnItemClickListener(OnItemClickListener<M> mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener<M> mItemLongClickListener) {
        this.mItemLongClickListener = mItemLongClickListener;
    }

    public void setOnItemClickListener2(OnItemClickListener2 mItemClickListener2) {
        this.mItemClickListener2 = mItemClickListener2;
    }

    public void setOnItemClickListener3(OnItemClickListener3 mItemClickListener3) {
        this.mItemClickListener3 = mItemClickListener3;
    }

    /**
     * ItemView的点击事件
     *
     * @param viewHolder
     */
    protected void setupItemViewClickListener(RecyclerView.ViewHolder viewHolder, final M item, final int position) {
        viewHolder.itemView.setOnClickListener(new PerfectClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                if (mItemClickListener != null) {
                    mItemClickListener.onClick(item);
                }
                if(mItemClickListener2!=null){
                    mItemClickListener2.onClick(position);
                }
                if(mItemClickListener3!=null){
                    mItemClickListener3.onClick(v, position);
                }
            }
        });

        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (mItemLongClickListener != null) {
                    mItemLongClickListener.onLongClick(item);
                }
                return true;
            }
        });
    }
}
