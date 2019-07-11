package com.social.happychat.ui.home.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.social.basecommon.adapter.OnItemClickListener;
import com.social.basecommon.databinding.FragmentRefreshListBinding;
import com.social.basecommon.fragment.BaseFragment;
import com.social.happychat.R;
import com.social.happychat.event.RefreshCommentNumEvent;
import com.social.happychat.event.RefreshTrendListEvent;
import com.social.happychat.ui.home.adapter.CommentAdapter;
import com.social.happychat.ui.home.adapter.TrendAdapter;
import com.social.happychat.ui.home.bean.CommentListBean;
import com.social.happychat.ui.home.interfaces.CommentNavigator;
import com.social.happychat.ui.home.interfaces.DialogFragmentDataCallback;
import com.social.happychat.ui.home.present.CommentPresent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;
import java.util.Map;

import rx.Subscription;

/**
 * @author Administrator
 * @date 2019/7/2 0002
 * @description:评论列表
 */
public class CommentListFragment extends BaseFragment<FragmentRefreshListBinding> implements CommentNavigator {
    private CommentAdapter CommentAdapter;
    private CommentPresent present;
    private int dynamicId;
    private DialogFragmentDataCallback dataCallback;


    public static CommentListFragment newInstance(int dynamicId) {

        Bundle args = new Bundle();
        args.putInt("dynamicId", dynamicId);

        CommentListFragment fragment = new CommentListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        dataCallback = (DialogFragmentDataCallback) getActivity();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        dynamicId = getArguments().getInt("dynamicId");
        present = new CommentPresent(this, dynamicId);
        binding.refreshLayout.setEnableRefresh(false);
        binding.refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                int page = present.getPage();
                page++;
                present.setPage(page);
                present.loadCommentData();
            }
        });

        CommentAdapter = new CommentAdapter(activity);
        CommentAdapter.setOnPraiseClickListener(new TrendAdapter.OnPraiseClickListener() {
            @Override
            public void onPraise(int id,int type) {
                present.praiseAction(id,type,2);
            }
        });
        CommentAdapter.setOnItemClickListener(new OnItemClickListener<CommentListBean.ListBean>() {
            @Override
            public void onClick(CommentListBean.ListBean item) {
                dataCallback.alertCommentSbDialog(item.getUserId(),item.getUserName());
            }
        });
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        binding.recyclerView.setAdapter(CommentAdapter);
        present.loadCommentData();
    }

    @Override
    public void showLoadSuccessView() {
        showContentView();
    }

    @Override
    public void showAdapterView(List<CommentListBean.ListBean> dataBeans) {
        binding.refreshLayout.setNoMoreData(false);

        if (present.getPage() == 1) {
            CommentAdapter.getItems().clear();
            binding.refreshLayout.finishRefresh();
        }else{
            binding.refreshLayout.finishLoadMore();
        }
        CommentAdapter.getItems().addAll(dataBeans);
        if (CommentAdapter.getItemCount() == 0) {
            showError();
        }

    }

    @Override
    public void showListNoMoreLoading() {
        binding.refreshLayout.finishLoadMoreWithNoMoreData();
    }

    @Override
    public void showLoadFailedView() {
        binding.refreshLayout.finishRefresh();
        if (CommentAdapter.getItemCount() == 0) {
            showError();
        }
    }

    @Override
    public void addRxSubscription(Subscription subscription) {
        addSubscription(subscription);
    }

    @Override
    protected void onRefresh() {
        present.setPage(1);
        present.loadCommentData();
    }

    @Override
    public int setContent() {
        return R.layout.fragment_refresh_list;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(RefreshCommentNumEvent event) {
        onRefresh();
    }
}
