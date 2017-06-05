package com.wumai.baseproject.app;

import android.app.TabActivity;
import android.os.Bundle;

import com.wumai.baseproject.config.Const;
import com.wumai.baseproject.config.IntentNames;
import com.wumai.baseproject.utils.ActivityManager;
import com.wumai.baseproject.utils.ImmerseStatusBar;

import org.xutils.x;

/**
 * Created by litengfei on 16/4/8.
 */
public abstract class BaseTabActivity extends TabActivity implements Const, IntentNames {

    protected abstract int getContentViewId();

    protected abstract void initData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        ImmerseStatusBar.setImmerseStatusBarTransparency(this);
        ImmerseStatusBar.setImmerseStatusBar(this);
        setContentView(getContentViewId());
//        ActivityManager.getInstance().putActivity(this);
        ActivityManager.getInstance().putActivity(ActivityManager.MAIN_TAB_ACTIVITY, this);
        x.view().inject(this);
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        ActivityManager.getInstance().removeActivity(this);
    }

}
