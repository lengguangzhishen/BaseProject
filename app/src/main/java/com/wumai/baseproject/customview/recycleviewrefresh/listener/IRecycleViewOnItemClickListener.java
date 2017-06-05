package com.wumai.baseproject.customview.recycleviewrefresh.listener;

import android.view.View;

/**
 * 自定义item点击事件接口
 * Created by renyajie on 15-11-5.
 */
public interface IRecycleViewOnItemClickListener<T> {
    void onClick(View view, T item, int position);
}
