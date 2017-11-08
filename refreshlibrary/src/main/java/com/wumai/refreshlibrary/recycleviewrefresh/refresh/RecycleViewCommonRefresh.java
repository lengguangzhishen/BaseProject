package com.wumai.refreshlibrary.recycleviewrefresh.refresh;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.wumai.refreshlibrary.R;
import com.wumai.refreshlibrary.recycleviewrefresh.commonrefresh.PullRefreshLayout;
import com.wumai.refreshlibrary.recycleviewrefresh.pullrefresh.PullToRefreshRecyclerView;
import com.wumai.refreshlibrary.recycleviewrefresh.recycleview.AutofitRecyclerView;


/**
 * Created by admin on 15-11-3.
 */
public class RecycleViewCommonRefresh extends ViewGroup {
    private RecyclerView mRecycleView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private PullRefreshLayout mPullRefreshLayout;
    private PullToRefreshRecyclerView mPullToRefreshRecyclerView;
    private View mRefreshView;

    public RecycleViewCommonRefresh(Context context) {
        super(context);
    }

    public RecycleViewCommonRefresh(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomRecycleviewMode);
        int mode = a.getInteger(R.styleable.CustomRecycleviewMode_flag_mode, 1);
        boolean isGridLayout = a.getBoolean(R.styleable.CustomRecycleviewMode_grid_layout, false);
        a.recycle();
        if (mode > 0) {
            ViewGroup viewGroup = makeViewGroup(context, attrs, mode, isGridLayout);
            if (mode == 1) {
                if (viewGroup instanceof SwipeRefreshLayout) {
                    mSwipeRefreshLayout = (SwipeRefreshLayout) viewGroup;
                }
            } else if (mode == 2) {
                if (viewGroup instanceof PullRefreshLayout) {
                    mPullRefreshLayout = (PullRefreshLayout) viewGroup;
                }
            } else {
                if (viewGroup instanceof PullToRefreshRecyclerView) {
                    mPullToRefreshRecyclerView = (PullToRefreshRecyclerView) viewGroup;
                }
            }
            mRefreshView = viewGroup;
        } else {
            mode = 1;
            ViewGroup viewGroup = makeViewGroup(context, attrs, mode, isGridLayout);
            if (viewGroup instanceof SwipeRefreshLayout) {
                mSwipeRefreshLayout = (SwipeRefreshLayout) viewGroup;
            }
            mRefreshView = viewGroup;
        }
    }

    /**
     * 创建view
     *
     * @param context
     * @param attrs
     * @param mode
     * @return
     */
    private ViewGroup makeViewGroup(Context context, AttributeSet attrs, int mode, boolean isGridLayout) {
        ViewGroup viewGroup = init(mode, context, attrs, -1);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        viewGroup.setLayoutParams(params);
        if (mode != 3) {
            if (!isGridLayout) {
                mRecycleView = new RecyclerView(context);
            } else {
                mRecycleView = new AutofitRecyclerView(context);
            }
            LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            mRecycleView.setLayoutParams(lp);
            viewGroup.addView(mRecycleView, 0);
            this.addView(viewGroup, 0);
        } else {
            if (viewGroup instanceof PullToRefreshRecyclerView) {
                mPullToRefreshRecyclerView = (PullToRefreshRecyclerView) viewGroup;
            }
            mRecycleView = mPullToRefreshRecyclerView.getRefreshableView();
            this.addView(mPullToRefreshRecyclerView, 0);
        }
        return viewGroup;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int height = getMeasuredHeight();
        int width = getMeasuredWidth();
        int left = getPaddingLeft();
        int top = getPaddingTop();
        int right = getPaddingRight();
        int bottom = getPaddingBottom();
        mRefreshView.layout(left, top, left + width - right, top + height - bottom);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        widthMeasureSpec = MeasureSpec.makeMeasureSpec(getMeasuredWidth() - getPaddingRight() - getPaddingLeft(), MeasureSpec.EXACTLY);
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(getMeasuredHeight() - getPaddingTop() - getPaddingBottom(), MeasureSpec.EXACTLY);
        mRefreshView.measure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 初始化三种不同的view
     *
     * @return
     */
    private ViewGroup init(int tag, Context context, AttributeSet attrs, int defStyleAttr) {
        if (tag == 1) {//android5.0刷新样式
            return new SwipeRefreshLayout(context);
        } else if (tag == 2) {//android5.0 普通刷新样式
            return new PullRefreshLayout(context);
        } else {//普通刷新样式
            return new PullToRefreshRecyclerView(context);
        }
    }

    public RecyclerView getmRecycleView() {
        return mRecycleView;
    }

    public void setmRecycleView(RecyclerView mRecycleView) {
        this.mRecycleView = mRecycleView;
    }

    public PullRefreshLayout getmPullRefreshLayout() {
        return mPullRefreshLayout;
    }

    public void setmPullRefreshLayout(PullRefreshLayout mPullRefreshLayout) {
        this.mPullRefreshLayout = mPullRefreshLayout;
    }

    public SwipeRefreshLayout getmSwipeRefreshLayout() {
        return mSwipeRefreshLayout;
    }

    public void setmSwipeRefreshLayout(SwipeRefreshLayout mSwipeRefreshLayout) {
        this.mSwipeRefreshLayout = mSwipeRefreshLayout;
    }

    public View getmRefreshView() {
        return mRefreshView;
    }

    public void setmRefreshView(View mRefreshView) {
        this.mRefreshView = mRefreshView;
    }

    public PullToRefreshRecyclerView getmPullToRefreshRecyclerView() {
        return mPullToRefreshRecyclerView;
    }

    public void setmPullToRefreshRecyclerView(PullToRefreshRecyclerView mPullToRefreshRecyclerView) {
        this.mPullToRefreshRecyclerView = mPullToRefreshRecyclerView;
    }
}