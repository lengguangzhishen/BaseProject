package com.wumai.baseproject.network;

import android.os.Build;

import com.wumai.baseproject.app.AppProxyFactory;
import com.wumai.baseproject.app.BaseProjectApplication;
import com.wumai.baseproject.app.CustomerAppProxy;
import com.wumai.baseproject.utils.TextUtil;
import com.wumai.baseproject.utils.Utils;

import org.xutils.http.RequestParams;
import org.xutils.http.annotation.HttpRequest;
import org.xutils.http.app.DefaultParamsBuilder;

import java.util.HashMap;

/**
 * Created by litengfei on 16/4/1.
 */

/*
*{@link org.xutils.http.annotation.HttpRequest} 注解的参数构建的模板接口
 */
public class JsonParamsBuilder extends DefaultParamsBuilder {

    public static final String SERVER_URL = "server_url";

    public static final String SERVER_API = "server_api";

    private static HashMap<String, String> SERVER_MAP = new HashMap<String, String>();

    private static final HashMap<String, String> API_MAP = new HashMap<>();

    private final static Object LOCK = new Object();

    static {
        SERVER_MAP.put(SERVER_URL, AppProxyFactory.getProxy().getAppConfig().getHostUrl());
    }

    /**
     * 根据@HttpRequest构建请求的url
     *
     * @param requestParams
     * @param httpRequest
     * @return
     */
    @Override
    public synchronized String buildUri(RequestParams requestParams, HttpRequest httpRequest) {
        API_MAP.put(SERVER_API, AppProxyFactory.getProxy().getAppConfig().getApiUrl());
        String url = getHost(httpRequest.host());
        url += "/" + getApiUrl(httpRequest.path());
        return url;
    }

    /**
     * 为请求添加通用参数等操作,如版本号,token值等信息的封装
     *
     * @param requestParams
     */
    @Override
    public void buildParams(RequestParams requestParams) {

        // 添加公共参数
        requestParams.addParameter("oSversion", BaseProjectApplication.getInstance().getVersionName());
        if (CustomerAppProxy.getProxy().getAccountManager().isAccountLogin()) {
            requestParams.addParameter("token", CustomerAppProxy.getProxy().getAccountManager().getAccount().getToken());
            requestParams.addParameter("userId", CustomerAppProxy.getProxy().getAccountManager().getAccount().getUserId());
        }
        requestParams.addBodyParameter("os", "android");
        requestParams.addBodyParameter("model", Build.MANUFACTURER + "-" + Build.MODEL);
        requestParams.addBodyParameter("timestamp", "" + System.currentTimeMillis());
        requestParams.addBodyParameter("uuid", TextUtil.checkNullString(Utils.getImei()) ? "" : Utils.getImei());
        // 将post请求的body参数以json形式提交
        requestParams.setAsJsonContent(false);
        // 或者query参数和body参数都json形式
        /*String json = params.toJSONString();
        params.clearParams();// 清空参数
        if (params.getMethod() == HttpMethod.GET) {
            params.addQueryStringParameter("xxx", json);
        } else {
            params.setBodyContent(json);
        }*/
    }

    /**
     * 自定义参数签名
     *
     * @param params
     * @param signs
     */
    @Override
    public void buildSign(RequestParams params, String[] signs) throws Throwable {
        super.buildSign(params, signs);
    }

    private String getHost(String host) {
        return SERVER_MAP.get(host);
    }

    private String getApiUrl(String path) {
        return API_MAP.get(path);
    }
}
