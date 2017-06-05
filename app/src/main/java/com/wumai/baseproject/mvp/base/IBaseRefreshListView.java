package com.wumai.baseproject.mvp.base;

import com.wumai.baseproject.customview.recycleviewrefresh.base.BaseLoadMoreRecycleViewAdapter;

/**
 * Created by litengfei on 2017/4/27.
 */
public interface IBaseRefreshListView extends IBaseView{
    /**
     * 停止刷新(一般在网络请求结束之后使用)
     */
    void refreshComplete();

    /**
     * 获取到adapter
     * @return
     */
    BaseLoadMoreRecycleViewAdapter getAdapter();

    /**
     * 设置加载更多的显示(正在加载或者是加载完成)
     * @param loadStatus
     */
    void setLoadStatus(boolean loadStatus);

    /**
     * 是否还有更多数据
     * @param hasMoreData
     */
    void setHasMoreData(boolean hasMoreData);

    /**
     * 是否应该有加载更多的条目
     * @param hasFooter
     */
    void setHasFooter(boolean hasFooter);
}
