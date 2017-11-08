package com.wumai.baseproject.app;

import com.wumai.baselibrary.log.Logger;
import com.wumai.baseproject.config.AppInfo;
import com.wumai.baseproject.mvp.base.BasePresenter;
import com.wumai.baseproject.utils.HttpLoadNum;
import com.wumai.baseproject.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by litengfei on 2017/11/7.
 * Email: litengfeilo@163.com
 * 泛型T: 列表条目需要的bean
 */

public abstract class BaseRefreshPresenter<V extends IBaseRefreshLayoutView<T>, T> extends BasePresenter<V> {

    protected List<T> mDatas = new ArrayList<>();
    private boolean loadMoreable = true;

    public void setLoadMoreable(boolean loadMoreable) {
        this.loadMoreable = loadMoreable;
    }


    public void getData(HttpLoadNum httpEnum) {

        if (httpEnum == HttpLoadNum.LOADFIRST) {//清除之前的信息
            if (isLoading) {
                getView().refreshComplete();
                return;
            }
            index = 1;
            isFirstGetNetData = true;

        }

        if (httpEnum == HttpLoadNum.LOADMORE) {
            //预留loadmore
            if (isLoading) {
                return;
            }
            if (mDatas.size() < AppInfo.PAGE_SIZE) {
                getView().setLoadStatus(false);
                getView().setHasMoreData(false);
                return;
            }
        }


        isLoading = true;
        mDatas.clear();

        getNetData();

    }

    protected abstract void getNetData();

    protected void getNetDataSuccess(List<T> datas) {
        if (datas == null || datas.size() == 0) {
            if (index == 1) {
                mHandler.sendEmptyMessage(MESSAGE_EMPTY);
            } else {
                getView().setLoadStatus(false);
                getView().setHasMoreData(false);
            }
        } else {
            if (index == 1) {
                getView().replaceDataAndNotify(datas);
            } else {
                getView().appendDataAndNotify(datas);
            }

            mDatas.addAll(datas);

            index++;

            if (loadMoreable) {
                initLoadMoreStyle(datas);
            }

            if (getView().getAdapter().getList().size() < 1) {
                mHandler.sendEmptyMessage(MESSAGE_EMPTY);
            } else {
                mHandler.sendEmptyMessage(MESSAGE_SUCESS);
            }
        }
    }

    protected void getNetDataError(Throwable throwable) {
        mHandler.sendEmptyMessage(MESSAGE_ERROR);
        Logger.e(Utils.generateCrashInfo(throwable));
    }

    protected void getNetDataOtherCode(String code, String message) {
        if (index == 1) {
            mHandler.sendEmptyMessage(MESSAGE_EMPTY);
        } else {
            getView().setLoadStatus(false);
            getView().setHasMoreData(false);
        }
    }

    protected void getNetDataFinished(int id) {
        isLoading = false;
        getView().refreshComplete();
    }

    private void initLoadMoreStyle(List list) {
        if (isFirstGetNetData && list.size() < AppInfo.PAGE_SIZE) {
            isFirstGetNetData = false;
            getView().setLoadStatus(false);
            getView().setHasMoreData(false);
            getView().setHasFooter(false);
        } else{
            if (isFirstGetNetData) {
                isFirstGetNetData = false;
            }
            getView().setLoadStatus(true);
            getView().setHasMoreData(true);
            getView().setHasFooter(true);
        }
    }

}
