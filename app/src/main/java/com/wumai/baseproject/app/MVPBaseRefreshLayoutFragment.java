package com.wumai.baseproject.app;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.wumai.baselibrary.log.Logger;
import com.wumai.refreshlibrary.recycleviewrefresh.base.BaseLoadMoreRecycleViewAdapter;
import com.wumai.refreshlibrary.recycleviewrefresh.itemholder.ItemViewHolder;
import com.wumai.refreshlibrary.recycleviewrefresh.listener.IOnLoadMoreListener;
import com.wumai.refreshlibrary.recycleviewrefresh.listener.IOnRefreshListener;
import com.wumai.refreshlibrary.recycleviewrefresh.listener.IRecycleViewOnItemClickListener;
import com.wumai.refreshlibrary.recycleviewrefresh.listener.IRecycleViewOnItemLongClickListener;
import com.wumai.refreshlibrary.recycleviewrefresh.refresh.RecycleViewCommonRefresh;
import com.wumai.refreshlibrary.recycleviewrefresh.refresh.RefreshManager;
import com.yuguo.thecarpenter.thecarpenterrepair.Presenter.Adapter.WorkOrderPoolAdapter;
import com.yuguo.thecarpenter.thecarpenterrepair.R;
import com.yuguo.thecarpenter.thecarpenterrepair.Tools.HttpLoadNum;
import com.yuguo.thecarpenter.thecarpenterrepair.Tools.Utils;
import com.yuguo.thecarpenter.thecarpenterrepair.config.AppInfo;
import com.yuguo.thecarpenter.thecarpenterrepair.config.Settings;
import com.yuguo.thecarpenter.thecarpenterrepair.mvp.bean.WorkOrderPoolBean;

import java.util.List;

/**
 * Created by litengfei on 2017/11/7.
 * Email: litengfeilo@163.com
 * 单列表下拉刷新和上拉加载的fragment
 * 泛型K是列表条目数据类型
 */

public abstract class MVPBaseRefreshLayoutFragment<V extends IBaseRefreshLayoutView<K>, T extends BaseRefreshPresenter<V, K>, K> extends MVPBaseLoadingFragment<V, T>{

    protected RecycleViewCommonRefresh recyclerView;
    protected RefreshManager mRefreshManager;
    protected RecyclerView mRecyclerView;
    protected BaseLoadMoreRecycleViewAdapter<K, ItemViewHolder> mAdapter;
    private boolean loadMoreable = true;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        try {
            initRecyclerView(view);

        } catch (Exception e) {
            Logger.e(Utils.generateCrashInfo(e));
        }
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected View getContentView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.view_refresh_layout, null);
    }

    protected void initRecyclerView(View view) {
        recyclerView = (RecycleViewCommonRefresh) view.findViewById(R.id.recyclerView);

        mRefreshManager = new RefreshManager(recyclerView, new LinearLayoutManager(mActivity), new IOnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getData(HttpLoadNum.LOADFIRST);
            }

        }, new IOnLoadMoreListener() {
            @Override
            public void onLoadMore(int current_page) {
                if (loadMoreable) {
                    mPresenter.getData(HttpLoadNum.LOADMORE);
                }
            }
        });

        mRefreshManager.setPullToRefreshRecyclerView();
        mRefreshManager.getmPullToRefreshRecyclerView().setPullToRefreshEnabled(true);
        mRecyclerView = mRefreshManager.getmRecycleView();
        mRecyclerView.setNestedScrollingEnabled(false);

        mAdapter = initAdapter();

        mAdapter.setOnItemClickListener(new IRecycleViewOnItemClickListener<K>() {
            @Override
            public void onClick(View view, K item, int position) {

                onItemClick(view, item, position);
            }
        });

        mAdapter.setOnItemLongClickListener(new IRecycleViewOnItemLongClickListener<K>() {
            @Override
            public void onLongClick(View view, K item, int position) {
                onItemLongClick(view, item, position);
            }
        });

        mRefreshManager.setLoadImgWay(mAdapter);
        mRefreshManager.setLoadingStatus(false);
        mAdapter.setHasMoreData(false);
        mAdapter.setHasFooter(false);
        mRecyclerView.setAdapter(mAdapter);
    }

    protected void setPullToRefreshEnabled (boolean pullToRefreshEnabled) {
        mRefreshManager.getmPullToRefreshRecyclerView().setPullToRefreshEnabled(pullToRefreshEnabled);
    }

    protected void setNestedScrollingEnabled (boolean nestedScrollingEnabled) {
        mRecyclerView.setNestedScrollingEnabled(nestedScrollingEnabled);
    }

    protected void setLoadMoreable(boolean loadMoreable) {
        this.loadMoreable = loadMoreable;
        mPresenter.setLoadMoreable(loadMoreable);
    }

    public void refreshComplete() {
        recyclerView.getmPullToRefreshRecyclerView().onRefreshComplete();

    }

    public BaseLoadMoreRecycleViewAdapter getAdapter() {
        return mAdapter;
    }

    public void setLoadStatus(boolean loadStatus) {
        mRefreshManager.setLoadingStatus(loadStatus);

    }

    public void setHasMoreData(boolean hasMoreData) {
        mAdapter.setHasMoreData(hasMoreData);

    }

    public void setHasFooter(boolean hasFooter) {
        mAdapter.setHasFooter(hasFooter);

    }

    public void replaceDataAndNotify(List<K> datas) {
        mAdapter.replaceListAndNotify(datas);
        try {
            if (mAdapter.getList().size() >= AppInfo.PAGE_SIZE &&
                    mRefreshManager.getmLinearLayoutManager().findLastVisibleItemPosition() == mAdapter.getList().size()) {
                mRefreshManager.getmLinearLayoutManager().smoothScrollToPosition(recyclerView.getmRecycleView(), null, mAdapter.getList().size() - 1);
            }
        } catch (Exception e) {

        }
    }

    public void appendDataAndNotify(List<K> datas) {
        mAdapter.appendToListAndNotify(datas);
    }

    protected abstract BaseLoadMoreRecycleViewAdapter<K, ItemViewHolder> initAdapter();
    protected void onItemClick(View view, K item, int position) {

    }
    protected void onItemLongClick(View view, K item, int position) {

    }

}
