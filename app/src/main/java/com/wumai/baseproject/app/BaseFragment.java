package com.wumai.baseproject.app;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wumai.baseproject.config.Const;

import org.xutils.common.Callback;
import org.xutils.x;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment implements Const {

    private boolean injected = false;
    protected Callback.Cancelable cancelable;
    private Unbinder bind;
    protected BaseActivity mActivity;


    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle
            savedInstanceState) {
        injected = true;
        View view = x.view().inject(this, inflater, container);
        bind = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!injected) {
            bind = ButterKnife.bind(this, this.getView());
        }

        initData();
    }

    protected abstract void initData();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (BaseActivity)context;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //捆绑网络请求,如果该页面destory,未完成的网络请求也一并取消;
        if (cancelable != null && !cancelable.isCancelled()) {
            cancelable.cancel();
        }

        if (bind != null) {
            bind.unbind();
        }
    }



    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void showDialog(String message) {
        if (mActivity != null) {
            mActivity.showDialog(message);
        }
    }

    public void showDialog() {
        if (mActivity != null) {
            mActivity.showDialog();
        }
    }

    public void dismissDialog() {
        if (mActivity != null) {
            mActivity.dismissDialog();
        }
    }

}
