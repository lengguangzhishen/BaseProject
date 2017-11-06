package com.wumai.baseproject.app;

import android.os.Bundle;

import com.wumai.baseproject.customview.MultiStateView;
import com.wumai.baseproject.mvp.base.BasePresenter;
import com.wumai.baseproject.mvp.base.IBaseView;


/**
 * Created by litengfei on 2016/12/14.
 */
public abstract class MVPBaseFragment<V extends IBaseView, T extends BasePresenter<V>> extends BaseFragment {

    protected T mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = createPresenter();
        mPresenter.attachView((V)this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    protected abstract T createPresenter();

    public void setWaitingDialogStatus(boolean isShow) {
        try {
            if (isShow) {
                showDialog();
            } else {
                dismissDialog();
            }
        } catch (Exception e) {

        }

    }

    public void setWaitingDialogStatus(boolean isShow, String message) {
        try {
            if (message == null) {
                return;
            }
            if (isShow) {
                showDialog(message);
            } else {
                dismissDialog();
            }
        } catch (Exception e) {

        }

    }

    public MultiStateView getMultiStateView() {
        return null;
    }
}
