package com.wumai.baseproject.app;

import com.wumai.baseproject.mvp.base.IBaseRefreshListView;

import java.util.List;

/**
 * Created by litengfei on 2017/11/7.
 * Email: litengfeilo@163.com
 */

public interface IBaseRefreshLayoutView<T> extends IBaseRefreshListView {
    void replaceDataAndNotify(List<T> datas);

    void appendDataAndNotify(List<T> datas);
}
