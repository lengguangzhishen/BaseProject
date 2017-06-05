package com.wumai.baseproject.mvp.base;

import com.wumai.baseproject.network.ICallBack;
import com.wumai.baseproject.network.NetParams;

/**
 * 这个类主要是去除多余的泛型, 简化代码, 比IBaseModel好用, 原理一样
 * Created by litengfei on 2017/5/9.
 */
public interface IBaseNetModel<T> extends IBaseModel<T> {

    void getNetData(NetParams params, Class<T> clazz, ICallBack.NetCallBack<T> commonCallback);

}
