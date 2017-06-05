package com.wumai.baseproject.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * 网络变化的广播接受者;
 * 注:当手机没有任何网的时候,会执行两次.发送两次Event;
 * Created by litengfei on 16/5/25.
 */
public class NetworkChangeReceiver extends BroadcastReceiver {

    public static int NETWORK_STATUS = 2;

    @Override
    public void onReceive(Context context, Intent intent) {
//        {
//            NetworkInfo.State wifiState = null;
//            NetworkInfo.State mobileState = null;
//            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//            if (cm == null) {
//                return;
//            }
//            NetworkInfo networkInfo = null;
//            try {
//                networkInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            if (networkInfo != null) {
//                wifiState = networkInfo.getState();
//            }
//            try {
//                networkInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            if (networkInfo != null) {
//                mobileState = networkInfo.getState();
//            }
//            if (wifiState != null && mobileState != null && NetworkInfo.State.CONNECTED != wifiState && NetworkInfo.State.CONNECTED == mobileState) {
//                // 手机网络连接成功
//                NETWORK_STATUS = Const.NETWORK_MOBILE;
//                EventBus.getDefault().post(new NetWorkChangeEvent(Const.NETWORK_MOBILE));
//            } else if (wifiState != null && mobileState != null && NetworkInfo.State.CONNECTED != wifiState
//                    && NetworkInfo.State.CONNECTED != mobileState) {
//                // 手机没有任何的网络
//                if (NETWORK_STATUS != Const.NETWORK_NONE) {
//                    EventBus.getDefault().post(new NetWorkChangeEvent(Const.NETWORK_NONE));
//                    NETWORK_STATUS = Const.NETWORK_NONE;
//                }
//            } else if (wifiState != null && NetworkInfo.State.CONNECTED == wifiState) {
//                // 无线网络连接成功
//                NETWORK_STATUS = Const.NETWORK_WIFI;
//                EventBus.getDefault().post(new NetWorkChangeEvent(Const.NETWORK_WIFI));
//            }
//        }
    }
}
