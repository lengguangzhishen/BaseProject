package com.wumai.baseproject.app;

import android.os.Bundle;

import com.wumai.baseproject.mvp.base.BasePresenter;
import com.wumai.baseproject.mvp.base.IBaseView;


/**
 * Created by litengfei on 2016/12/14.
 */
public abstract class MVPBaseFragment<V extends IBaseView, T extends BasePresenter<V>> extends BaseFragment implements IBaseView {

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

    @Override
    public void setWaitingDialogStatus(boolean isShow) {
        if (isShow) {
            showDialog();
        } else {
            dismissDialog();
        }
    }

    @Override
    public void setWaitingDialogStatus(boolean isShow, String message) {
        if (message == null) {
            return;
        }
        if (isShow) {
            showDialog(message);
        } else {
            dismissDialog();
        }
    }
}
