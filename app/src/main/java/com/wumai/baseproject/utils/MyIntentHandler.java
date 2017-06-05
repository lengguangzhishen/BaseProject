package com.wumai.baseproject.utils;

import android.app.Activity;
import android.content.Intent;

import com.wumai.baseproject.app.CustomerAppProxy;
import com.wumai.baselibrary.jumper.IntentHandler;
import com.wumai.baseproject.config.Const;

import java.lang.reflect.Method;

/**
 * Created by litengfei on 15/12/8.
 */
public class MyIntentHandler extends IntentHandler {

    public final static int REQ_LOGIN = 1025;
    final boolean mNeedCheckToken;

    public MyIntentHandler(final Intent intent, final Method method) {
        super(intent, method);
        mNeedCheckToken = method.getAnnotation(Token.class) != null;
    }

    @Override
    public void startActivity(final Activity context) {
        if (!mNeedCheckToken) {
            super.startActivity(context);
        } else if(CustomerAppProxy.getProxy().getAccountManager().isAccountLogin()) {
            super.startActivity(context);
        }else {
            Const.JUMPER.gotoLogin(REQ_LOGIN,false).startActivity(context);
        }
    }

}
