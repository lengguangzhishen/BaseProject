package com.wumai.baseproject.customview.recycleviewrefresh.base;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wumai.baseproject.R;
import com.wumai.baselibrary.log.Logger;
import com.wumai.baseproject.customview.recycleviewrefresh.android5.CustomMaterialProgressBarSupport;

import java.util.List;


public abstract class BaseLoadMoreRecycleViewAdapter<T, VH extends RecyclerView.ViewHolder> extends BaseRecyclerViewAdapter {
    /**
     * footerView返回类型标识
     */
    public static final int TYPE_FOOTER = Integer.MIN_VALUE;
    /**
     * 普通类型view
     */
    public static final int TYPE_ITEM = 0;
    /**
     * 是否显示footerView
     */
    private boolean hasFooter;
    /**
     * 设置是否可以继续加载数据
     */
    private boolean hasMoreData;

    private View mFooterView;

    /**
     * 适配器构造方法
     */
    public BaseLoadMoreRecycleViewAdapter(List<T> list) {
        super(list);
    }

    private boolean mHasNoData = false;//此标识主要用于recyclerview没数据的时候显示

    protected T getItem(int position) {
        if (position > mList.size() - 1) {
            return null;
        }
        return (T) mList.get(position);
    }

    @Override
    public int getItemCount() {
        return getBasicItemCount() + (hasFooter ? 1 : 0);
    }

    public int getBasicItemCount() {
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getBasicItemCount() && hasFooter) {
            return TYPE_FOOTER;
        }
        return TYPE_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_FOOTER) {//底部加载view
            if (mFooterView == null) {
                if (mHasNoData) {//如果没有数据就将footerview变为emptyview（特殊需求==订单界面）
                    mFooterView = LayoutInflater.from(parent.getContext()).inflate(R.layout.empty_no_data, parent, false);
                } else {
                    mFooterView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_load_more, parent, false);
                }
            }
            return new FooterViewHolder(mFooterView);
        } else {
            //数据itemViewHolder
            return onCreateItemViewHolder(parent, viewType);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FooterViewHolder) {
            if (mHasNoData) {
                return;
            }
            //没有更多数据
            if (hasMoreData) {
                ((FooterViewHolder) holder).mProgressView.setVisibility(View.VISIBLE);
//                ((FooterViewHolder) holder).mProgressView.startProgress();
                ((FooterViewHolder) holder).mProgressView.startAnim();
                //((FooterViewHolder) holder).mProgressView.setIndeterminate(true);
                ((FooterViewHolder) holder).mTextView.setText("正在加载...");
            } else {
                Logger.e("hasMoreData :没有更多内容!");
//                ((FooterViewHolder) holder).mProgressView.stopProgress();
                ((FooterViewHolder) holder).mProgressView.setVisibility(View.GONE);
                ((FooterViewHolder) holder).mProgressView.stopAnim();
                //((FooterViewHolder) holder).mProgressView.st;
                ((FooterViewHolder) holder).mTextView.setText("没有更多内容");
            }
            return;
        } else {
            final T item = getItem(position);
            onBindItemViewHolder((VH) holder, item, position);
            bindItemViewClickListener((VH) holder, item, position);
        }
    }

    /**
     * 主要用于recycleview实现gridview判断底部加载更多view横铺的问题
     *
     * @param position
     * @return
     */
    public boolean isFooterPosition(int position) {
        return position == mList.size();
    }

    /**
     * 正常数据itemViewHolder 实现
     */
    public abstract void onBindItemViewHolder(final VH holder, T item, int position);

    /**
     * 创建正常的viewHolder
     *
     * @param parent
     * @param viewType
     * @return
     */
    public abstract VH onCreateItemViewHolder(ViewGroup parent, int viewType);

    /**
     * 获取是否有footerView
     *
     * @return
     */
    public boolean hasFooter() {
        return hasFooter;
    }

    /**
     * 设置是否显示footerView
     *
     * @param hasFooter
     */
    public void setHasFooter(boolean hasFooter) {
        if (this.hasFooter != hasFooter) {
            this.hasFooter = hasFooter;
            notifyDataSetChanged();
        }
    }

    /**
     * 获取是否还有更多数据
     *
     * @return
     */
    public boolean hasMoreData() {
        return hasMoreData;
    }

    public void setHasMoreData(boolean isMoreData) {
        if (this.hasMoreData != isMoreData) {
            this.hasMoreData = isMoreData;
            notifyDataSetChanged();
        }
    }

    /**
     * 设置有更多数据并且有footerView
     *
     * @param hasMoreData
     * @param hasFooter
     */
    public void setHasMoreDataAndFooter(boolean hasMoreData, boolean hasFooter) {
        if (this.hasMoreData != hasMoreData || this.hasFooter != hasFooter) {
            this.hasMoreData = hasMoreData;
            this.hasFooter = hasFooter;
            notifyDataSetChanged();
        }

    }

    public View getmFooterView() {
        return mFooterView;
    }

    public void setmFooterView(View mFooterView) {
        this.mFooterView = mFooterView;
    }

    /**
     * footerView(底部加载更多)
     */
    public static class FooterViewHolder extends BaseRecyclerViewAdapter.BaseViewHolder {
        public CustomMaterialProgressBarSupport mProgressView;
        public TextView mTextView;

        public FooterViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void findView() {
            mProgressView = (CustomMaterialProgressBarSupport) findViewById(R.id.progress_view);
            mTextView = (TextView) findViewById(R.id.tv_content);
        }
    }

    public boolean ismHasNoData() {
        return mHasNoData;
    }

    public void setmHasNoData(boolean mHasNoData) {
        this.mHasNoData = mHasNoData;
    }
}
