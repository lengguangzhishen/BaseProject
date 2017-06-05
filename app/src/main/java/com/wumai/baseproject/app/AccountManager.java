package com.wumai.baseproject.app;

import android.content.Context;

import com.wumai.baselibrary.app.IAccountManager;
import com.wumai.baseproject.config.Const;
import com.wumai.baseproject.eventbus.LogoutSuccessEvent;
import com.wumai.baseproject.utils.ActivityManager;
import com.wumai.baseproject.utils.TextUtil;

import de.greenrobot.event.EventBus;

public class AccountManager implements IAccountManager,Const {

    private Context mContext;

    public AccountManager(Context context) {
        mContext = context;
    }

    @Override
    public CustomerAccount getAccount() {
        return ACCOUNT_STORAGE.get();
    }

    @Override
    public boolean isAccountLogin() {
        String token = getAccount() == null ? "":getAccount().getToken();
        return !TextUtil.checkNullString(token);
    }

    public void setAccount(CustomerAccount account) {
        if (account!= null) {
            cacheAccount(account);
        }
    }

    @Override
    public void logout() {//强制退出时调用
        clearAccountCache();
        ActivityManager.getInstance().clearDefaultActivity();
        EventBus.getDefault().post(new LogoutSuccessEvent());
//        MAIN_CACHE_INFO_STORAGE.delete();//退出时删除首页本地数据
    //    ThirdPartyLogin.getInstance().stopSDK();//退出时停止sharedSDK
//        DownloadManager.getInstance().clear();
    //    System.exit(0);
    }


    private void clearAccountCache() {
        ACCOUNT_STORAGE.delete();
    }

    private void cacheAccount(CustomerAccount account) {
        ACCOUNT_STORAGE.save(account);
    }

}
