package com.wumai.baseproject.app;

import android.view.LayoutInflater;
import android.view.View;

import com.wumai.baseproject.R;
import com.wumai.baseproject.customview.MultiStateView;
import com.wumai.baseproject.mvp.base.BasePresenter;
import com.wumai.baseproject.mvp.base.IBaseView;


/**
 * Created by litengfei on 2017/11/6.
 * Email: litengfeilo@163.com
 */

public abstract class MVPBaseLoadingFragment<V extends IBaseView, T extends BasePresenter<V>> extends MVPBaseFragment<V, T> {
    private MultiStateView multiStateView;

    @Override
    protected View getRootView(LayoutInflater inflater) {
        View view = inflater.inflate( R.layout.view_loading, null);
        multiStateView = (MultiStateView) view.findViewById(R.id.multiStateView);
        multiStateView.addView(getContentView(inflater));
        return view;
    }

    @Override
    public MultiStateView getMultiStateView() {
        return multiStateView;
    }

    protected abstract View getContentView(LayoutInflater inflater);
}
