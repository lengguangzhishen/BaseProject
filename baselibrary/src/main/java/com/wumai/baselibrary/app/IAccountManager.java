package com.wumai.baselibrary.app;

public interface IAccountManager {

    public boolean isAccountLogin();

    public IAccount getAccount();

    public void logout();

}
