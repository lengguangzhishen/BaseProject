package com.wumai.baseproject.customview.recycleviewrefresh.refresh;

import android.content.Context;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.wumai.baseproject.customview.recycleviewrefresh.base.BaseLoadMoreRecycleViewAdapter;
import com.wumai.baseproject.customview.recycleviewrefresh.commonrefresh.PullRefreshLayout;
import com.wumai.baseproject.customview.recycleviewrefresh.listener.IEndlessRecyclerOnScrollListener;
import com.wumai.baseproject.customview.recycleviewrefresh.listener.IOnLoadMoreListener;
import com.wumai.baseproject.customview.recycleviewrefresh.listener.IOnRefreshListener;
import com.wumai.baseproject.customview.recycleviewrefresh.pullrefresh.PullToRefreshBase;
import com.wumai.baseproject.customview.recycleviewrefresh.pullrefresh.PullToRefreshRecyclerView;
import com.wumai.baseproject.customview.recycleviewrefresh.recycleviewstyle.HorizontalDividerItemDecoration;
import com.wumai.baseproject.customview.recycleviewrefresh.recycleviewstyle.VerticalDividerItemDecoration;


/**
 * 主要用于对刷新样式控制，获取刷新对象
 * Created by admin on 15-11-5.
 */
public class RefreshManager {
    private PullRefreshLayout mPullRefreshLayout;//android5.0普通刷新样式
    private SwipeRefreshLayout mSwipeRefreshLayout;//android5.0刷新样式
    private PullToRefreshRecyclerView mPullToRefreshRecyclerView;//普通下拉刷新recycleview
    private RecyclerView mRecycleView;//recycleview对象
    private IOnRefreshListener mIOnRefreshListener;//下拉刷新
    private IOnLoadMoreListener mIOnLoadMoreListener;//上拉加载更多
    private RecycleViewCommonRefresh mRefreshLayout;//通用控制刷新样式view
    private LinearLayoutManager mLinearLayoutManager;//recycleview样式控制对象
    private RecyclerViewEndlessRecyclerOnScrollListener mRecyclerViewEndlessRecyclerOnScrollListener;
    private HorizontalDividerItemDecoration mHorizontalDividerItemDecoration;//水平分割线对象
    private VerticalDividerItemDecoration mVerticalDividerItemDecoration;
    public boolean isLoading = false;

    public RefreshManager() {

    }

    public RefreshManager(RecycleViewCommonRefresh refreshLayout, LinearLayoutManager linearLayoutManager, IOnRefreshListener iOnRefreshListener, IOnLoadMoreListener iOnLoadMoreListener) {
        this.mRefreshLayout = refreshLayout;
        this.mLinearLayoutManager = linearLayoutManager;
        this.mIOnRefreshListener = iOnRefreshListener;
        this.mIOnLoadMoreListener = iOnLoadMoreListener;
        mRecyclerViewEndlessRecyclerOnScrollListener = new RecyclerViewEndlessRecyclerOnScrollListener(mLinearLayoutManager);

    }

    /**
     * android5.0普通刷新样式管理，初始化recycleview和样式，上拉加载，下拉刷新等功能
     */
    public void setPullRefreshLayout() {
        if (mRefreshLayout != null) {
            PullRefreshLayout pullRefreshLayout = mRefreshLayout.getmPullRefreshLayout();
            if (pullRefreshLayout != null) {
                mPullRefreshLayout = pullRefreshLayout;
                if (mIOnRefreshListener != null) {
                    pullRefreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
                        @Override
                        public void onRefresh() {
                            if (mIOnRefreshListener != null) {
                                mIOnRefreshListener.onRefresh();
                            }
                        }
                    });
                }
                RecyclerView recyclerView = mRefreshLayout.getmRecycleView();
                if (recyclerView != null) {
                    recyclerView.setLayoutManager(mLinearLayoutManager);
                    if (mIOnLoadMoreListener != null) {
                        recyclerView.addOnScrollListener(mRecyclerViewEndlessRecyclerOnScrollListener);
                    }
                    mRecycleView = recyclerView;
                }

            }

        }
    }

    /**
     * android5.0刷新样式管理，初始化recycleview和样式，上拉加载，下拉刷新等功能
     */
    public void setSwipeRefreshLayout() {
        if (mRefreshLayout != null) {
            SwipeRefreshLayout swipeRefreshLayout = mRefreshLayout.getmSwipeRefreshLayout();
            if (swipeRefreshLayout != null) {
                mSwipeRefreshLayout = swipeRefreshLayout;
                if (mIOnRefreshListener != null) {
                    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                        @Override
                        public void onRefresh() {
                            if (mIOnRefreshListener != null) {
                                mIOnRefreshListener.onRefresh();
                            }
                        }
                    });
                }
                RecyclerView recyclerView = mRefreshLayout.getmRecycleView();
                if (recyclerView != null) {
                    recyclerView.setLayoutManager(mLinearLayoutManager);
                    if (mIOnLoadMoreListener != null) {
                        recyclerView.addOnScrollListener(mRecyclerViewEndlessRecyclerOnScrollListener);
                    }
                }
                mRecycleView = recyclerView;
            }

        }
    }

    /**
     * 普通下拉刷新样式
     */
    public void setPullToRefreshRecyclerView() {
        if (mRefreshLayout != null) {
            PullToRefreshRecyclerView pullToRefreshRecyclerView = mRefreshLayout.getmPullToRefreshRecyclerView();
            if (pullToRefreshRecyclerView != null) {
                mPullToRefreshRecyclerView = pullToRefreshRecyclerView;
                if (mIOnRefreshListener != null) {
                    mPullToRefreshRecyclerView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<RecyclerView>() {
                        @Override
                        public void onRefresh(PullToRefreshBase<RecyclerView> refreshView) {
                            if (mIOnRefreshListener != null) {
                                mIOnRefreshListener.onRefresh();
                            }
                        }
                    });
                }
                RecyclerView recyclerView = mRefreshLayout.getmRecycleView();
                if (recyclerView != null) {
                    recyclerView.setLayoutManager(mLinearLayoutManager);
                    if (mIOnLoadMoreListener != null) {
                        recyclerView.addOnScrollListener(mRecyclerViewEndlessRecyclerOnScrollListener);
                    }
                }
                mRecycleView = recyclerView;
            }

        }
    }

    private class RecyclerViewEndlessRecyclerOnScrollListener extends IEndlessRecyclerOnScrollListener {
        public RecyclerViewEndlessRecyclerOnScrollListener(LinearLayoutManager linearLayoutManager) {
            super(linearLayoutManager);
        }

        @Override
        public void onLoadMore(int current_page) {
            if (mIOnLoadMoreListener != null && !isLoading) {
                isLoading = true;
                mIOnLoadMoreListener.onLoadMore(current_page);
            }
        }
    }

    /**
     * 主要用于recycleview实现gridview，加载更多问题
     *
     * @param adapter
     */
    public void setGridLayoutSpan(final BaseLoadMoreRecycleViewAdapter adapter) {
        if (mRecycleView != null) {
            final RecyclerView.LayoutManager manager = (GridLayoutManager) mRecycleView.getLayoutManager();
            if (manager != null) {
                if (manager instanceof GridLayoutManager) {
                    final GridLayoutManager gridLayoutManager = (GridLayoutManager) manager;
                    if (gridLayoutManager != null) {
                        if (adapter != null) {
                            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                                @Override
                                public int getSpanSize(int position) {
                                    return adapter.isFooterPosition(position) ? gridLayoutManager.getSpanCount() : 1;
                                }
                            });
                            adapter.notifyDataSetChanged();
                        }
                    }
                } else {
                    return;
                }

            }
        }
    }

    /**
     * 数据不足设定值的时候删除加载更多的view
     *
     * @param recyclerView
     * @param adapter
     */
    public void removeLoadMoreView(RecyclerView recyclerView, BaseLoadMoreRecycleViewAdapter adapter) {
        if (adapter != null) {
            if (adapter.getmFooterView() != null) {
                if (recyclerView != null) {
                    recyclerView.removeView(adapter.getmFooterView());
                    adapter.notifyDataSetChanged();
                }
            }
        }
    }

    /**
     * 当切换筛选条件时需要给recyclerview重新设置一些变量，不然加载更多的回调无法执行
     *
     * @param flag
     */
    public void setLoadingStatus(boolean flag) {
        if (mRecyclerViewEndlessRecyclerOnScrollListener != null) {
            mRecyclerViewEndlessRecyclerOnScrollListener.setLoading(flag);
        }
    }

    /**
     * 给recyclerview设置简单分割线
     */
    public void setRecyclerViewSimpleDivider(Context context, int colorId, float strokeSize) {
        Paint paint = new Paint();
        paint.setStrokeWidth(strokeSize);
        paint.setColor(context.getResources().getColor(colorId));
        paint.setAntiAlias(true);
        paint.setPathEffect(new DashPathEffect(new float[]{25.0f, 25.0f}, 0));
        mHorizontalDividerItemDecoration = new HorizontalDividerItemDecoration.Builder(context).paint(paint).showLastDivider().build();
        mRecycleView.addItemDecoration(mHorizontalDividerItemDecoration);
        mRecycleView.invalidateItemDecorations();
    }

    /**
     * 给recyclerview设置垂直分割线
     */
    public void setRecyclerViewVerticalDivider(Context context, int colorId, float strokeSize) {
        Paint paint = new Paint();
        paint.setStrokeWidth(strokeSize);
        paint.setColor(context.getResources().getColor(colorId));
        paint.setAntiAlias(true);
        paint.setPathEffect(new DashPathEffect(new float[]{25.0f, 25.0f}, 0));
        mVerticalDividerItemDecoration = new VerticalDividerItemDecoration.Builder(context).paint(paint).showLastDivider().build();
        mRecycleView.addItemDecoration(mVerticalDividerItemDecoration);
        mRecycleView.invalidateItemDecorations();
    }

    /**
     * 删除分割线
     */
    public void removeRecyclerViewDivider() {
        mRecycleView.removeItemDecoration(mHorizontalDividerItemDecoration);
        mRecycleView.invalidateItemDecorations();
        mHorizontalDividerItemDecoration = null;
    }

    /**
     * 删除垂直分割线
     */
    public void removeRecyclerViewVerticalDivider() {
        mRecycleView.removeItemDecoration(mVerticalDividerItemDecoration);
        mRecycleView.invalidateItemDecorations();
        mVerticalDividerItemDecoration = null;
    }

    /**
     * 设置滑动的时候不加载图片，停止的时候再加载，可以提升滑动效率，较少卡顿
     *
     * @param adapter
     */
    public void setLoadImgWay(BaseLoadMoreRecycleViewAdapter adapter) {
        if (adapter != null) {
            if (mRecyclerViewEndlessRecyclerOnScrollListener != null) {
               // mRecyclerViewEndlessRecyclerOnScrollListener.setBitmapUtils(adapter.getBitmapUtils());
            }
        }
    }

    public PullRefreshLayout getmPullRefreshLayout() {
        return mPullRefreshLayout;
    }

    public SwipeRefreshLayout getmSwipeRefreshLayout() {
        return mSwipeRefreshLayout;
    }

    public RecyclerView getmRecycleView() {
        return mRecycleView;
    }

    public PullToRefreshRecyclerView getmPullToRefreshRecyclerView() {
        return mPullToRefreshRecyclerView;
    }

    public void setmPullToRefreshRecyclerView(PullToRefreshRecyclerView mPullToRefreshRecyclerView) {
        this.mPullToRefreshRecyclerView = mPullToRefreshRecyclerView;
    }

    public void setmIOnLoadMoreListener(IOnLoadMoreListener mIOnLoadMoreListener) {
        this.mIOnLoadMoreListener = mIOnLoadMoreListener;
    }

    public void setmIOnRefreshListener(IOnRefreshListener mIOnRefreshListener) {
        this.mIOnRefreshListener = mIOnRefreshListener;
    }

    public void setmLinearLayoutManager(LinearLayoutManager mLinearLayoutManager) {
        this.mLinearLayoutManager = mLinearLayoutManager;
        if (mRecyclerViewEndlessRecyclerOnScrollListener != null) {
            mRecyclerViewEndlessRecyclerOnScrollListener.setmLinearLayoutManager(mLinearLayoutManager);
        }
    }
    public LinearLayoutManager getmLinearLayoutManager(){
        return mLinearLayoutManager;
    }

}
