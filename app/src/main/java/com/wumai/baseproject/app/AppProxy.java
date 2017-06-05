package com.wumai.baseproject.app;

import android.content.Context;

import com.wumai.baselibrary.app.IAccountManager;
import com.wumai.baselibrary.app.IAppConfig;
import com.wumai.baselibrary.app.IAppProxy;
import com.wumai.baselibrary.app.INetworkManager;

public class AppProxy implements IAppProxy {

    private Context mContext;

    private NetworkManager mNetworkManager;

    @Override
    public void init(Context ctx) {
       mContext=ctx;
    }

    @Override
    public IAppConfig getAppConfig() {
        return null;
    }

    @Override
    public Context getContext() {
        return mContext;
    }

    @Override
    public IAccountManager getAccountManager() {
        return null;
    }

    @Override
    public INetworkManager getNetworkManager() {
        if(mNetworkManager==null){
            mNetworkManager=new NetworkManager();
        }
        return mNetworkManager;
    }
}
