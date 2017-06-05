package com.wumai.baseproject.app;

import android.content.Context;

import com.wumai.baselibrary.app.IAppConfig;


public class CustomerAppProxy extends AppProxy {

    private AppConfig mAppConfig;

    private AccountManager mAccountManager;

    @Override
    public void init(Context ctx) {
        super.init(ctx);
        mAppConfig = new AppConfig(ctx);
    }

    @Override
    public IAppConfig getAppConfig() {
        return mAppConfig;
    }

    @Override
    public Context getContext() {
        return super.getContext();
    }

    @Override
    public AccountManager getAccountManager() {
        if(mAccountManager==null){
            mAccountManager=new AccountManager(getContext());
        }
        return mAccountManager;
    }

    public static CustomerAppProxy getProxy() {
        return (CustomerAppProxy) AppProxyFactory.getProxy();
    }

}
