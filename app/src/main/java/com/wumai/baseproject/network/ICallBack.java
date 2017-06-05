package com.wumai.baseproject.network;

/**
 * Created by litengfei on 2016/12/14.
 */
public interface ICallBack {

    public interface CommonCallback<ResultType> extends ICallBack {

        void onSuccess(ResultType result);

        void onError(Throwable throwable);

        void onCancelled(RuntimeException e);

        void onFinished();

    }

    interface NetCallBack<ResultType> extends CommonCallback<ResultType> {
        void onOtherCode(String code);
    }

}
