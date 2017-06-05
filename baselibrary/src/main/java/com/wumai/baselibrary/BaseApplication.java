package com.wumai.baselibrary;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.multidex.MultiDexApplication;

import com.google.gson.Gson;

public abstract class BaseApplication extends MultiDexApplication {
    static BaseApplication sBaseApplication;

    public static BaseApplication getGlobalContext() {
        return sBaseApplication;
    }

    public static <T> T getService(String name) {
        return (T) getGlobalContext().getSystemService(name);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sBaseApplication = this;
        //onInitializeCrashHandler();
    }

    public int getVersionCode() {
        try {
            final PackageInfo pi = getPackageManager().getPackageInfo(getPackageName(), 0);
            return pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public String getVersionName() {
        try {
            final PackageInfo pi = getPackageManager().getPackageInfo(getPackageName(), 0);
            return pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    public abstract Gson getGson();

    public abstract void onInitializeCrashHandler(boolean isDebug);

}
