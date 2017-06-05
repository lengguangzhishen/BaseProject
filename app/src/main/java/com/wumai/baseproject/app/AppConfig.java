package com.wumai.baseproject.app;

import android.content.Context;

import com.wumai.baseproject.R;
import com.wumai.baselibrary.app.IAppConfig;
import com.wumai.baseproject.utils.FileUtils;

import java.io.File;

public class AppConfig implements IAppConfig {

    private String mHostUrl = BaseProjectApplication.getInstance().getApplicationContext().getString(R.string.base_url);

    private String mUrlApi;

    private String mBaseDataDir = "";

    public AppConfig(final Context context) {
        mBaseDataDir = FileUtils.getBaseDataDir(context) + File.separator + ".wumai";
    }

    @Override
    public void setHostUrl(String hostUrl) {
        mHostUrl = hostUrl;
    }

    @Override
    public String getHostUrl() {

        return mHostUrl;
    }

    @Override
    public synchronized String getApiUrl() {
        return mUrlApi;
    }

    @Override
    public void setApiUrl(String apiUrl) {

        mUrlApi = apiUrl;
    }
}
