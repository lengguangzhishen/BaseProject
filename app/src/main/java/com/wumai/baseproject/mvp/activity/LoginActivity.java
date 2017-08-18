package com.wumai.baseproject.mvp.activity;

import com.wumai.baseproject.R;
import com.wumai.baseproject.app.MVPBaseActivity;
import com.wumai.baseproject.customview.MultiStateView;
import com.wumai.baseproject.mvp.presenter.LoginPresenter;
import com.wumai.baseproject.mvp.view.ILoginView;
import com.wumai.baseproject.utils.ImmerseStatusBar;


/**
 * Created by litengfei on 2017/4/9.
 */
public class LoginActivity extends MVPBaseActivity<ILoginView, LoginPresenter> implements ILoginView {

    private static final int LOGIN_REQUEST = 1;
    private boolean isFromLogout;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initTitle() {

        ImmerseStatusBar.setImmerseStatusBar(this);
        setMiddleTitle("登录");
    }

    @Override
    protected void initData() {

        getExtraData();
    }

    private void getExtraData() {
        if (getIntent() != null) {
            isFromLogout = getIntent().getBooleanExtra(ISFROMLOGOUT, false);
        }
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
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

        JUMPER.gotoLogin(1, true).startActivity(mContext);
        JUMPER.gotoLogin(1, true).startActivityForResult(this, LOGIN_REQUEST);
    }

    @Override
    public void onError() {

    }

}
