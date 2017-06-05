package com.wumai.baselibrary.app;

//import com.lidroid.xutils.HttpUtils;

import android.app.Activity;
import android.content.Context;

public interface INetworkManager {

   public boolean isConnected(Context context);

  public boolean isWifi(Context context);

    public void openSetting(Activity activity);


}
