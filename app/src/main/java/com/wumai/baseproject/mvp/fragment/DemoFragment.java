package com.wumai.baseproject.mvp.fragment;

import android.view.LayoutInflater;
import android.view.View;

import com.wumai.baseproject.app.MVPBaseFragment;
import com.wumai.baseproject.customview.MultiStateView;
import com.wumai.baseproject.mvp.presenter.DemoPresenter;
import com.wumai.baseproject.mvp.view.IDemoView;

/**
 * Created by OFFICE on 2017/8/18.
 */

public class DemoFragment extends MVPBaseFragment<IDemoView, DemoPresenter> implements IDemoView {

    @Override
    protected DemoPresenter createPresenter() {
        return null;
    }

    @Override
    protected View getRootView(LayoutInflater inflater) {
        return null;
    }

    @Override
    protected void initData() {

    }

    @Override
    public MultiStateView getMultiStateView() {
        return null;
    }

    @Override
    public void getNetData() {

    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onError() {

    }
}
