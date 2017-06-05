package com.wumai.baseproject.network;

import java.io.File;

/**
 * Created by litengfei on 2016/12/14.
 */
public class NetParams {

    private BaseNetParams mBaseNetParams;

    /**
     *
     * @param api
     * @param isAddDefaultParam 是否有默认的参数
     */
    public NetParams(String api, boolean isAddDefaultParam) {
        mBaseNetParams = new BaseNetParams(api, isAddDefaultParam);
    }

    /**
     * 默认有默认的参数
     * @param api
     */
    public NetParams(String api) {
        mBaseNetParams = new BaseNetParams(api, true);
    }

    /**
     * 输入全路径的地址, 旨在链接艳芬儿本机调试的时候用, 或者接口不一样的时候, 一般用上边那两个
     * @param isAbsolutePath
     * @param api
     */
    public NetParams(boolean isAbsolutePath, String api) {
        mBaseNetParams = new BaseNetParams(api);
    }

    public Object getParams() {
        return mBaseNetParams;
    }

    public void addBodyParameter(String name, String value) {
        mBaseNetParams.addBodyParameter(name, value);
    }

    public void addBodyParameter(String name, File value) {
        mBaseNetParams.addBodyParameter(name, value);
    }

    public void addParameter(String name, File value) {
        mBaseNetParams.addParameter(name, value);
    }

    public void addParameter(String name, String value) {
        mBaseNetParams.addParameter(name, value);
    }

    public void addHeader(String name, String value) {
        mBaseNetParams.addHeader(name, value);
    }

    @Override
    public String toString() {
        return mBaseNetParams.toString();
    }
}
