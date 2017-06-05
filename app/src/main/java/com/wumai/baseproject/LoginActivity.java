package com.wumai.baseproject;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.wumai.baselibrary.ToastUtil;
import com.wumai.baseproject.app.BaseActivity;
import com.wumai.baseproject.utils.ImmerseStatusBar;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by litengfei on 2017/4/9.
 */
public class LoginActivity extends BaseActivity {

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


//    @Override
//    public void onSuccess() {
//        ToastUtil.showMessage("登录成功");
//        if (isFromLogout) {
////            JUMPER.gotoMainTab().startActivity(LoginActivity.this);
//            JUMPER.gotoSwitchRoleActivity().startActivity(mContext);
//            finish();
//        } else {
//            Intent intent = new Intent();
//            intent.putExtra(RADIO_INDEX, MainTabActivity.MINE_TAB_ID);
//            setResult(100, intent);
//            finish();
//        }
//    }
}
