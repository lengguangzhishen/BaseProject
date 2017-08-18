package com.wumai.baseproject.mvp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wumai.baseproject.R;
import com.wumai.baseproject.app.BaseActivity;
import com.wumai.baseproject.utils.ImmerseStatusBar;

public class MainActivity extends BaseActivity {

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initTitle() {

        ImmerseStatusBar.setImmerseStatusBar(this);
        setMiddleTitle("hehehe");
    }

    @Override
    protected void initData() {

    }
}
