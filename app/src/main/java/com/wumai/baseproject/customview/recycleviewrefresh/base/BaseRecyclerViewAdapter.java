package com.wumai.baseproject.customview.recycleviewrefresh.base;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.wumai.baseproject.customview.recycleviewrefresh.listener.IRecycleViewOnItemClickListener;
import com.wumai.baseproject.customview.recycleviewrefresh.listener.IRecycleViewOnItemLongClickListener;

import java.util.ArrayList;
import java.util.List;


/**
 * BaseLoadMoreRecycleViewAdapter基类，提供主要提供添加数据，刷新数据，设置点击事件等
 */
public abstract class BaseRecyclerViewAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    /**
     * item点击事件
     */
    protected IRecycleViewOnItemClickListener mOnItemClickListener;
    /**
     * item长按点击事件
     */
    protected IRecycleViewOnItemLongClickListener mOnItemLongClickListener;
    /**
     * 数据源
     */
    protected List<T> mList = new ArrayList<>();

    /**
     * 父类的构造方法
     */
    public BaseRecyclerViewAdapter(List<T> list) {
        appendToList(list);
    }

    /**
     * 返回数据源集合
     *
     * @return
     */
    public List<T> getList() {
        return mList;
    }

    /**
     * 给list添加数据
     *
     * @param list
     */
    public void appendToList(List<T> list) {
        if (list == null) {
            return;
        }
        mList.addAll(list);
    }

    /**
     * 给list添加数据并刷新
     *
     * @param list
     */
    public void appendToListAndNotify(List<T> list) {
        if (list == null) {
            return;
        }
        mList.addAll(list);
        this.notifyDataSetChanged();
    }

    public void replaceListAndNotify(List<T> list) {
        if (list == null)
            return;
        mList = list;
        this.notifyDataSetChanged();
    }

    /**
     * 给数据源添加一个对象
     *
     * @param t
     */
    public void append(T t) {
        if (t == null) {
            return;
        }
        mList.add(t);
    }

    /**
     * 给数据源添加一个对象并刷新
     *
     * @param t
     */
    public void appendAndNotify(T t) {
        if (t == null) {
            return;
        }
        mList.add(t);
    }

    /**
     * 将对象添加到集合的第一个位置
     *
     * @param item
     */
    public void appendToTop(T item) {
        if (item == null) {
            return;
        }
        mList.add(0, item);
    }

    /**
     * 将对象添加到集合的第一个位置并刷新
     *
     * @param item
     */
    public void appendToTopAndNotify(T item) {
        if (item == null) {
            return;
        }
        mList.add(0, item);
        this.notifyDataSetChanged();
    }

    /**
     * 添加一个list到数据源的头部
     *
     * @param list
     */
    public void appendToTopList(List<T> list) {
        if (list == null) {
            return;
        }
        mList.addAll(0, list);
    }

    /**
     * 添加一个list到数据源的头部并刷新
     *
     * @param list
     */
    public void appendToTopListAndNotify(List<T> list) {
        if (list == null) {
            return;
        }
        mList.addAll(0, list);
        this.notifyDataSetChanged();
    }

    /**
     * 添加一个list到数据源的头部并刷新
     *
     * @param list
     */
    public void appendToTopListandNotify(List<T> list) {
        if (list == null) {
            return;
        }
        mList.addAll(0, list);
        this.notifyDataSetChanged();
    }

    /**
     * 删除一个对象（根据索引删除）
     *
     * @param position
     */
    public void remove(int position) {
        if (position < mList.size() - 1 && position >= 0) {
            mList.remove(position);
        }
    }

    /**
     * 删除一个对象（根据对象删除）并刷新
     *
     * @param item
     */
    public void removeAndNotify(T item) {
        if (mList.contains(item)) {
            mList.remove(item);
        }
        this.notifyDataSetChanged();
    }

    /**
     * 删除一个对象（根据对象删除）
     *
     * @param item
     */
    public void remove(T item) {
        if (mList != null) {
            if (mList.contains(item)) {
                mList.remove(item);
            }
        }
    }

    /**
     * 清除数据
     */
    public void clear() {
        mList.clear();
    }

    /**
     * 清除数据并刷新
     */
    public void clearAndNotify() {
        mList.clear();
        this.notifyDataSetChanged();
    }

    /**
     * 设置长按点击事件
     *
     * @param onItemLongClickListener
     */
    public void setOnItemLongClickListener(IRecycleViewOnItemLongClickListener onItemLongClickListener) {
        mOnItemLongClickListener = onItemLongClickListener;
    }

    /**
     * 设置点击事件
     *
     * @param onItemClickListener
     */
    public void setOnItemClickListener(IRecycleViewOnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    /**
     * 转化xml为view
     *
     * @param viewGroup
     * @param layoutId
     * @return
     */
    protected View inflateItemView(ViewGroup viewGroup, int layoutId) {
        return inflateItemView(viewGroup, layoutId, false);
    }

    /**
     * 转化xml为view
     *
     * @param viewGroup
     * @param layoutId
     * @param attach
     * @return
     */
    protected View inflateItemView(ViewGroup viewGroup, int layoutId, boolean attach) {
        return LayoutInflater.from(viewGroup.getContext()).inflate(layoutId, viewGroup, attach);
    }

    /**
     * 绑定监听事件
     *
     * @param vh
     * @param item
     * @param position
     */
    protected void bindItemViewClickListener(VH vh, final T item, final int position) {
        if (mOnItemClickListener != null) {
            vh.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.onClick(view, item, position);
                }
            });
        }
        if (mOnItemLongClickListener != null) {
            vh.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnItemLongClickListener.onLongClick(v, item, position);
                    return true;
                }
            });
        }
    }

    /**
     * BaseViewHolder可以继承该类，使用findViewById()方法去初始化控件
     */
    public abstract static class BaseViewHolder extends RecyclerView.ViewHolder {

        public BaseViewHolder(View itemView) {
            super(itemView);
            findView();
        }

        protected abstract void findView();

        protected <T extends View> T findViewById(int id) {
            return (T) itemView.findViewById(id);
        }

    }

    /**
     * 可以继承该类使用链式获取控件对象
     */
    public static class SparseArrayViewHolder extends RecyclerView.ViewHolder {
        private final SparseArray<View> views;

        public SparseArrayViewHolder(View itemView) {
            super(itemView);
            views = new SparseArray<View>();
        }

        public <T extends View> T getView(int id) {
            View view = views.get(id);
            if (view == null) {
                view = itemView.findViewById(id);
                views.put(id, view);
            }
            return (T) view;
        }

        public SparseArrayViewHolder setText(int viewId, String value) {
            TextView view = getView(viewId);
            view.setText(value);
            return SparseArrayViewHolder.this;
        }

        public SparseArrayViewHolder setTextColor(int viewId, int textColor) {
            TextView view = getView(viewId);
            view.setTextColor(textColor);
            return SparseArrayViewHolder.this;
        }

        public SparseArrayViewHolder setImageResource(int viewId, int imageResId) {
            ImageView view = getView(viewId);
            view.setImageResource(imageResId);
            return SparseArrayViewHolder.this;
        }

        public SparseArrayViewHolder setBackgroundColor(int viewId, int color) {
            View view = getView(viewId);
            view.setBackgroundColor(color);
            return SparseArrayViewHolder.this;
        }

        public SparseArrayViewHolder setBackgroundResource(int viewId, int backgroundRes) {
            View view = getView(viewId);
            view.setBackgroundResource(backgroundRes);
            return SparseArrayViewHolder.this;
        }

        public SparseArrayViewHolder setRating(int viewId, float value) {
            RatingBar ratingBar = getView(viewId);
            ratingBar.setRating(value);
            return SparseArrayViewHolder.this;
        }

        public SparseArrayViewHolder setVisible(int viewId, boolean visible) {
            View view = getView(viewId);
            view.setVisibility(visible ? View.VISIBLE : View.GONE);
            return SparseArrayViewHolder.this;
        }

        public SparseArrayViewHolder setOnClickListener(int viewId, View.OnClickListener listener) {
            View view = getView(viewId);
            view.setOnClickListener(listener);
            return SparseArrayViewHolder.this;
        }

        public SparseArrayViewHolder setOnTouchListener(int viewId, View.OnTouchListener listener) {
            View view = getView(viewId);
            view.setOnTouchListener(listener);
            return SparseArrayViewHolder.this;
        }

        public SparseArrayViewHolder setOnLongClickListener(int viewId, View.OnLongClickListener listener) {
            View view = getView(viewId);
            view.setOnLongClickListener(listener);
            return SparseArrayViewHolder.this;
        }

        public SparseArrayViewHolder setTag(int viewId, Object tag) {
            View view = getView(viewId);
            view.setTag(tag);
            return SparseArrayViewHolder.this;
        }
    }
}
