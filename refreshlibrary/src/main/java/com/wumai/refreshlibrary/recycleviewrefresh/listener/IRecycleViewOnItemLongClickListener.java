package com.wumai.refreshlibrary.recycleviewrefresh.listener;

import android.view.View;

/**
 * 自定义item长按点击事件接口
 * Created by renyajie on 15-11-5.
 */
public interface IRecycleViewOnItemLongClickListener<T> {
    void onLongClick(View view, T item, int position);
}
