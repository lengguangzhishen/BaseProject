package com.wumai.baseproject.app;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wumai.baselibrary.log.Logger;
import com.wumai.baseproject.R;
import com.wumai.baseproject.config.AppInfo;
import com.wumai.baseproject.utils.HttpLoadNum;
import com.wumai.baseproject.utils.Utils;
import com.wumai.refreshlibrary.recycleviewrefresh.base.BaseLoadMoreRecycleViewAdapter;
import com.wumai.refreshlibrary.recycleviewrefresh.itemholder.ItemViewHolder;
import com.wumai.refreshlibrary.recycleviewrefresh.listener.IOnLoadMoreListener;
import com.wumai.refreshlibrary.recycleviewrefresh.listener.IOnRefreshListener;
import com.wumai.refreshlibrary.recycleviewrefresh.listener.IRecycleViewOnItemClickListener;
import com.wumai.refreshlibrary.recycleviewrefresh.listener.IRecycleViewOnItemLongClickListener;
import com.wumai.refreshlibrary.recycleviewrefresh.refresh.RecycleViewCommonRefresh;
import com.wumai.refreshlibrary.recycleviewrefresh.refresh.RefreshManager;

import java.util.List;

/**
 * Created by litengfei on 2017/11/8.
 * Email: litengfeilo@163.com
 * 泛型K: 列表条目的bean
 */

public abstract class MVPBaseRefreshLayoutActivity<V extends IBaseRefreshLayoutView<K>, T extends BaseRefreshPresenter<V, K>, K> extends MVPBaseLoadingActivity<V, T> {
    protected RecycleViewCommonRefresh recyclerView;
    protected RefreshManager mRefreshManager;
    protected RecyclerView mRecyclerView;
    protected BaseLoadMoreRecycleViewAdapter<K, ItemViewHolder> mAdapter;
    private boolean loadMoreable = true;

    @Override
    protected void initRefreshLayout() {

        try {
            init();
        } catch (Exception e) {
            Logger.e(Utils.generateCrashInfo(e));
        }
    }

    @Override
    protected int getContentViewId() {
        return R.layout.view_refresh_layout;
    }

    private void init() {
        recyclerView = (RecycleViewCommonRefresh) findViewById(R.id.recyclerView);

        mRefreshManager = new RefreshManager(recyclerView, new LinearLayoutManager(mContext), new IOnRefreshListener() {
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
        if (mRefreshManager == null || mRefreshManager.getmPullToRefreshRecyclerView() == null) {
            return;
        }
        mRefreshManager.getmPullToRefreshRecyclerView().setPullToRefreshEnabled(pullToRefreshEnabled);
    }

    protected void setNestedScrollingEnabled (boolean nestedScrollingEnabled) {
        if (mRecyclerView == null) {
            return;
        }
        mRecyclerView.setNestedScrollingEnabled(nestedScrollingEnabled);
    }

    protected void setLoadMoreable(boolean loadMoreable) {
        this.loadMoreable = loadMoreable;
        mPresenter.setLoadMoreable(loadMoreable);
    }

    public void refreshComplete() {
        if (recyclerView == null)
            return;
        recyclerView.getmPullToRefreshRecyclerView().onRefreshComplete();

    }

    public BaseLoadMoreRecycleViewAdapter getAdapter() {
        return mAdapter;
    }

    public void setLoadStatus(boolean loadStatus) {
        if (mRefreshManager == null)
            return;
        mRefreshManager.setLoadingStatus(loadStatus);

    }

    public void setHasMoreData(boolean hasMoreData) {
        if (mAdapter == null)
            return;
        mAdapter.setHasMoreData(hasMoreData);

    }

    public void setHasFooter(boolean hasFooter) {
        if (mAdapter == null)
            return;
        mAdapter.setHasFooter(hasFooter);

    }

    public void replaceDataAndNotify(List<K> datas) {
        if (mAdapter == null)
            return;
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
        if (mAdapter == null)
            return;
        mAdapter.appendToListAndNotify(datas);
    }

    protected abstract BaseLoadMoreRecycleViewAdapter<K, ItemViewHolder> initAdapter();
    protected void onItemClick(View view, K item, int position) {

    }
    protected void onItemLongClick(View view, K item, int position) {

    }
}
