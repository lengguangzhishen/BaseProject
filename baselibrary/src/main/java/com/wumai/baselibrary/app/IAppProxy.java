package com.wumai.baselibrary.app;

import android.content.Context;

public interface IAppProxy {

    public void init(Context ctx);

    public IAppConfig getAppConfig();

    public Context getContext();

    public IAccountManager getAccountManager();

    public INetworkManager getNetworkManager();

}
