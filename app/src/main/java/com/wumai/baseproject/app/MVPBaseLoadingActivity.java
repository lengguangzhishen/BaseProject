package com.wumai.baseproject.app;

import android.view.View;

import com.wumai.baseproject.R;
import com.wumai.baseproject.customview.MultiStateView;
import com.wumai.baseproject.mvp.base.BasePresenter;
import com.wumai.baseproject.mvp.base.IBaseView;


/**
 * Created by litengfei on 2017/11/6.
 * Email: litengfeilo@163.com
 */

public abstract class MVPBaseLoadingActivity<V extends IBaseView, T extends BasePresenter<V>> extends MVPBaseActivity<V, T> {

    private MultiStateView multiStateView;

    @Override
    protected View getRootView() {
        View view = View.inflate(this, R.layout.view_loading, null);
        multiStateView = (MultiStateView) view.findViewById(R.id.multiStateView);
        multiStateView.addView(View.inflate(this, getContentViewId(), null));
        return view;

    }

    @Override
    public MultiStateView getMultiStateView() {
        return multiStateView;
    }
}
