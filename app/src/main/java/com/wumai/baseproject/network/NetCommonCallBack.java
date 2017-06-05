package com.wumai.baseproject.network;

import com.wumai.baseproject.R;
import com.wumai.baseproject.app.BaseProjectApplication;
import com.wumai.baselibrary.ToastUtil;
import com.wumai.baselibrary.log.Logger;
import com.wumai.baseproject.utils.TextUtil;

import org.xutils.common.Callback;

/**
 * Created by litengfei on 16/5/9.
 */
public abstract class NetCommonCallBack<T> implements Callback.CommonCallback<T> {

    @Override
    public void onError(Throwable throwable, boolean b) {//当网络错误，超时以及解析出错和其他异常,都会触发该方法
        if(!TextUtil.checkNullString(throwable.toString())){
            Logger.e("net_onError:%s" + throwable.toString());
        }
        ToastUtil.showMessage(BaseProjectApplication.getInstance().getString(R.string.net_fail));
    }

    @Override
    public void onCancelled(CancelledException e) {
    }

    @Override
    public void onFinished() {

    }
}
