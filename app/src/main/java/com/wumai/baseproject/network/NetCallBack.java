package com.wumai.baseproject.network;

import com.wumai.baseproject.app.CustomerAppProxy;
import com.wumai.baselibrary.ToastUtil;
import com.wumai.baselibrary.log.Logger;
import com.wumai.baseproject.config.Const;
import com.wumai.baseproject.config.Settings;

/**
 * Created by litengfei on 2017/5/9.
 */
public abstract class NetCallBack<T> implements ICallBack.NetCallBack<T>, Const {

    @Override
    public void onOtherCode(String code) {

        switch (code) {

            case Settings.CODE_FAILURE://操作失败, 稍后重试
                ToastUtil.showMessage("操作失败, 稍后重试");
                break;
            case Settings.CODE_NODATA: //数据为空, 稍后重试
                ToastUtil.showMessage("数据为空");
                break;
            case Settings.CODE_PARAMS_ERROR: //参数错误
                ToastUtil.showMessage("网络错误, 稍后重试");
                break;
            case Settings.CODE_VERIFICATION_TIMEOUT: //验证码超时
                ToastUtil.showMessage("验证码超时, 稍后重试");
                break;
            case Settings.CODE_VERIFICATION_ERROR: //验证码错误
                ToastUtil.showMessage("验证码错误, 请重试");
                break;
            case Settings.CODE_NO_USER: //无此用户
                ToastUtil.showMessage("无此用户信息");
                break;
            case Settings.CODE_PWD_ERROR: //密码错误
                ToastUtil.showMessage("密码错误, 请重试");
                break;
            case Settings.CODE_PHONE_ERROR: //手机号格式错误
                ToastUtil.showMessage("手机号格式错误, 稍后重试");
                break;
            case Settings.CODE_ACCOUNT_LOCK: //账号锁定
                ToastUtil.showMessage("账号被锁定, 请联系管理员解锁");
                break;
            case Settings.CODE_HAS_REGISTED: //手机号已注册
                ToastUtil.showMessage("手机号已注册");
                break;
            case Settings.CODE_NONE: //手机号或者密码不能为空
                ToastUtil.showMessage("手机号或者密码不能为空");
                break;
            case Settings.CODE_LOGIN_ERROR: //账号在其他地方登录
                ToastUtil.showMessage("账号在其他地方登录, 请重新登录");
                CustomerAppProxy.getProxy().getAccountManager().logout();
                break;
            default: //未知的code
                ToastUtil.showMessage("未知的错误码：" + code);
                Logger.i("错误码：" + code);
                break;
        }
    }
}
