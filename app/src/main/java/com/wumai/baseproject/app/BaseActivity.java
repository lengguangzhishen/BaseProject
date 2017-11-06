package com.wumai.baseproject.app;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.wumai.baseproject.R;
import com.wumai.baseproject.config.Const;
import com.wumai.baseproject.config.IntentNames;
import com.wumai.baseproject.network.NetApi;
import com.wumai.baseproject.utils.ActivityManager;
import com.wumai.baseproject.utils.BackgroundUtils;
import com.wumai.baseproject.utils.ProgressDialogUtil;
import com.wumai.baseproject.utils.UiUtils;

import org.xutils.common.Callback;

import de.greenrobot.event.EventBus;

public abstract class BaseActivity extends TitleBarActivity implements Const, IntentNames {

    protected Context mContext;

    protected ProgressDialogUtil waitingDialog;

    protected Callback.Cancelable cancelable;

    public static BaseActivity sBaseActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        if (getRootView() == null) {
            setContentView(getContentViewId());
        } else {
            setContentView(getRootView());
        }
        ActivityManager.getInstance().putActivity(this);
        sBaseActivity = this;
        waitingDialog = new ProgressDialogUtil(this);
        initTitle();
        initData();

    }


    protected abstract int getContentViewId();

    protected View getRootView() {
        return null;
    }

    protected abstract void initTitle();

    protected abstract void initData();

    protected void onDestroy() {

        dismissDialog();
        NetApi.stopRequest(this);
        ActivityManager.getInstance().removeActivity(this);

        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        BackgroundUtils.getInstance().dealAppRunState("", true);
        super.onStart();
    }

    @Override
    public void onBackPressed() {
        UiUtils.hideKeyboard(this);
        if (cancelable != null && !cancelable.isCancelled()) {
            cancelable.cancel();
        }

//        if (this instanceof ClanActivity || this instanceof MsgActivity || this instanceof MineActivity) {
//            EventBus.getDefault().post(new GoToPositionTabEvent());
//            return;
//        }
        if (waitingDialog.isShowing()) {
            dismissDialog();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onStop() {
        BackgroundUtils.getInstance().dealAppRunState();
        super.onStop();
    }

    public void showDialog(String message) {
        if (waitingDialog != null) {
            waitingDialog.show(message);
        }
    }

    public void showDialog() {
        if (waitingDialog != null) {
            waitingDialog.show();
        }
    }

    public void dismissDialog() {
        if (waitingDialog != null) {
            waitingDialog.hide();
        }
    }

    @Override
    protected void onTitleBarItemClicked(View v) {
        switch (v.getId()) {
            case R.id.left_image:
                onBackPressed();
                break;
        }
    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config=new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config,res.getDisplayMetrics());
        return res;
    }

    /**
     * 点击空白位置 隐藏软键盘
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (null != this.getCurrentFocus()) {
            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }
        return super.onTouchEvent(event);
    }
}
