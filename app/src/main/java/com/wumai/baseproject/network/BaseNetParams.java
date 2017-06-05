package com.wumai.baseproject.network;

import com.wumai.baseproject.app.AppProxyFactory;
import com.wumai.baseproject.app.CustomerAppProxy;
import com.wumai.baselibrary.log.Logger;
import com.wumai.baseproject.config.Const;
import com.wumai.baseproject.config.Settings;
import com.wumai.baseproject.utils.TextUtil;
import com.wumai.baseproject.utils.Utils;

import org.xutils.http.RequestParams;

/**
 * Created by litengfei on 16/6/3.
 */
public class BaseNetParams extends RequestParams implements Const {

    public BaseNetParams(String api, boolean isAddDefaultParam) {
        super(AppProxyFactory.getProxy().getAppConfig().getHostUrl() + "/" + api);
        Logger.e("Net_HOst:%s" + AppProxyFactory.getProxy().getAppConfig().getHostUrl() + "/" + api);

        if (isAddDefaultParam) {
            addCommonParameters();
        }
    }

    /**
     * 直接传入地址全路径, 旨在链接本机调试
     *
     * @param api
     */
    public BaseNetParams(String api) {
        super(api);
    }

    public void addCommonParameters() {

        // 添加公共参数
//        addParameter("oSversion", BaseProjectApplication.getInstance().getVersionName());
        if (CustomerAppProxy.getProxy().getAccountManager().isAccountLogin()) {
            addParameter("token", CustomerAppProxy.getProxy().getAccountManager().getAccount().getToken() + "@=@" + (TextUtil.checkNullString(Utils.getImei()) ? "" : Utils.getImei()));

            if (SP.getBoolean(Settings.IS_RECRUIT_KEY, false)) {
                addParameter("companyid", CustomerAppProxy.getProxy().getAccountManager().getAccount().getUserId());
            } else {
                Logger.i("===参数用户id:" + CustomerAppProxy.getProxy().getAccountManager().getAccount().getUserId());
                addParameter("memberid", CustomerAppProxy.getProxy().getAccountManager().getAccount().getUserId());
            }

        }

        addBodyParameter("secret", "android");
//        addBodyParameter("model", Build.MANUFACTURER + "-" + Build.MODEL);
//        addBodyParameter("timestamp", "" + System.currentTimeMillis());
        addBodyParameter("equipmentId", TextUtil.checkNullString(Utils.getImei()) ? "" : Utils.getImei());
        // 将post请求的body参数以json形式提交
        setAsJsonContent(false);
        // 或者query参数和body参数都json形式
//        String json = params.toJSONString();
//        params.clearParams();// 清空参数
//        if (params.getMethod() == HttpMethod.GET) {
//            params.addQueryStringParameter("xxx", json);
//        } else {
//            params.setBodyContent(json);
//        }
    }
}
