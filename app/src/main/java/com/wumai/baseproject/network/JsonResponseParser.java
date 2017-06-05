package com.wumai.baseproject.network;

import com.wumai.baselibrary.log.Logger;
import com.wumai.baseproject.config.Const;

import org.xutils.http.app.ResponseParser;
import org.xutils.http.request.UriRequest;

import java.lang.reflect.Type;

/**
 * Created by litengfei on 16/4/1.
 */
public class JsonResponseParser implements ResponseParser {

    @Override
    public void checkResponse(UriRequest request) throws Throwable {

    }

    /**
     * 转换result为resultType类型的对象
     *
     * @param resultType  返回值类型(可能带有泛型信息)
     * @param resultClass 返回值类型
     * @param result      字符串数据
     * @return
     * @throws Throwable
     */

    @Override
    public Object parse(Type resultType, Class<?> resultClass, String result) throws Throwable {
        Logger.e("response=%s" +  result);
        Object response= Const.GSON.fromJson(result,resultClass);
        return response;
    }
}
