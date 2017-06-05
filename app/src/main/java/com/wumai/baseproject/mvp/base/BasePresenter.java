package com.wumai.baseproject.mvp.base;

import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.wumai.baseproject.R;
import com.wumai.baseproject.customview.MultiStateView;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by litengfei on 2016/12/14.
 */
public abstract class BasePresenter<T extends IBaseView> {

    protected static final int MESSAGE_SUCESS = 0x100;
    protected static final int MESSAGE_ERROR = 0x101;
    protected static final int MESSAGE_LOADDING = 0x102;
    protected static final int REFRESH_NO_NET = 0x103;
    protected static final int NET_TIME_OVER = 0x104;
    protected static final int MESSAGE_EMPTY = 0x105;

    protected boolean isLoading = false;
    protected int index = 0;
    protected boolean isFirstGetNetData = true;

    /**
     * MultiStateView界面成功等界面的显示逻辑
     */
    protected Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            try {
                if (getView().getMultiStateView() == null) {
                    return;
                }
                switch (msg.what) {
                    case MESSAGE_SUCESS:
                        setSuccessView();
                        break;
                    case MESSAGE_ERROR:
                    case REFRESH_NO_NET:
                    case NET_TIME_OVER:
                        setErrorView();
                        break;
                    case MESSAGE_EMPTY:
                        setEmptyView();
                        break;
                    case MESSAGE_LOADDING:
                        setLoadingView();
                        break;
                }
            } catch (Exception e) {

            }

        }
    };

    private void setLoadingView() {
        getView().getMultiStateView().setViewState(MultiStateView.ViewState.LOADING);
    }

    private void setEmptyView() {
        getView().getMultiStateView().setViewState(MultiStateView.ViewState.EMPTY);
        getView().getMultiStateView().getView(MultiStateView.ViewState.EMPTY).findViewById(R.id.textView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getView().getMultiStateView().setViewState(MultiStateView.ViewState.LOADING);
                getView().getNetData();
            }
        });
    }

    private void setErrorView() {
        getView().getMultiStateView().setViewState(MultiStateView.ViewState.ERROR);
        getView().getMultiStateView().getView(MultiStateView.ViewState.ERROR).findViewById(R.id.textView)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getView().getMultiStateView().setViewState(MultiStateView.ViewState.LOADING);
                        getView().getNetData();
                    }

                });
    }

    private void setSuccessView() {
        getView().getMultiStateView().setViewState(MultiStateView.ViewState.CONTENT);
    }

    protected Reference<T> mViewRef;

//    private T view;

    public void attachView(T view) {
        mViewRef = new WeakReference<T>(view);
//        this.view = view;
    }

    protected T getView() {
        return mViewRef.get();
//        return view;
    }

    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
//        return this.view != null;
    }

    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }

    /**
     * 从调用者想Presenter传递数据的方法, 如其他页面通过intent传递过来的
     *
     * @param objects
     */
    public void setExtralData(Object... objects) {
        if (objects == null || objects.length <= 0) {
            return;
        }
    }
}
