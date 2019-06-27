package com.social.huakai.ui.find.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.social.basecommon.fragment.BaseFragment;
import com.social.huakai.R;
import com.social.huakai.databinding.FragmentFindBinding;
import com.social.huakai.databinding.FragmentHomeBinding;
import com.social.huakai.databinding.FragmentTestBinding;
import com.social.huakai.ui.find.adapter.FindAdapter;
import com.social.huakai.ui.find.interfaces.FindNavigator;
import com.social.huakai.widget.DividerGridItemDecoration;

/**
 * @author Administrator
 * @date 2019/6/26 0026
 * @description:发现页面
 */
public class FindFragment extends BaseFragment<FragmentFindBinding> implements FindNavigator {
    private FindAdapter findAdapter;

    public static FindFragment newInstance() {

        Bundle args = new Bundle();

        FindFragment fragment = new FindFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        binding.refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {

            }
        });


        findAdapter = new FindAdapter(activity);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(activity, 2));
        DividerGridItemDecoration divider = new DividerGridItemDecoration(activity);
        divider.setDrawable(ContextCompat.getDrawable(activity, R.drawable.divider_grid_layout_manager_transparent2));
        binding.recyclerView.addItemDecoration(divider);
        binding.recyclerView.setAdapter(findAdapter);
    }

    @Override
    public int setContent() {
        return R.layout.fragment_find;
    }

    @Override
    public void showLoadSuccessView() {

    }

    @Override
    public void showAdapterView() {

    }

    @Override
    public void showListNoMoreLoading() {

    }

    @Override
    public void showLoadFailedView() {

    }
}
