package com.wumai.baseproject.app;


import com.wumai.baselibrary.app.IAccount;

import java.io.Serializable;

public class CustomerAccount implements IAccount, Serializable {

    private String token;
    private String account;//注册成功后保存正确的手机号码
    private int userId;//注册成功时在里面保存userId
    private String name;
    private String headportrait;
    private String memberType;

    public void setToken(String token) {
        this.token = token;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHeadportrait(String headportrait) {
        this.headportrait = headportrait;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    @Override
    public String getToken() {
        return token;
    }

    public String getAccount() {
        return account;
    }

    @Override
    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getHeadportrait() {
        return headportrait;
    }

    public String getMemberType() {
        return memberType;
    }
}
