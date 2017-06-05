package com.wumai.baseproject.app;

import android.content.Context;

import com.wumai.baselibrary.app.IAppProxy;


public class AppProxyFactory {

    static IAppProxy sProxyInstance = null;

    public static void registerProxy(Context context, Class classOfProxy) {
        try {
            sProxyInstance = (IAppProxy) classOfProxy.newInstance();
            sProxyInstance.init(context);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static IAppProxy getProxy() {
        return sProxyInstance;
    }

}
