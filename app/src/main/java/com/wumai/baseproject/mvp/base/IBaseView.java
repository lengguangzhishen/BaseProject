package com.wumai.baseproject.mvp.base;

import com.wumai.baseproject.customview.MultiStateView;

/**
 * Created by litengfei on 2016/12/14.
 */
public interface IBaseView {

    /**
     * 获取MultiStateView, 如果没有用到默认可以返回null
     * @return
     */
    MultiStateView getMultiStateView();

    /**
     * 使用MultiStateView出现网络异常等情况时, 点击刷新按钮的时候调用
     */
    void getNetData();

    /**
     * 非MultiStateView的界面请求数据成功之后的逻辑实现
     */
    void onSuccess();

    /**
     * 非MultiStateView的界面请求数据是啊比之后的逻辑实现
     */
    void onError();

    /**
     * 设置界面loadingdialog的显示与否
     * @param isShow
     */
    void setWaitingDialogStatus(boolean isShow);

    /**
     * 设置界面loadingdialog的显示与否
     * @param isShow
     */
    void setWaitingDialogStatus(boolean isShow, String message);

}
