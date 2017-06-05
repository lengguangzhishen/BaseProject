package com.wumai.baseproject.network;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.wumai.baseproject.app.BaseProjectApplication;
import com.wumai.baselibrary.ToastUtil;
import com.wumai.baselibrary.log.Logger;
import com.wumai.baseproject.config.Settings;
import com.wumai.baseproject.utils.JsonUtil;
import com.wumai.baseproject.utils.Utils;

import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.common.util.DensityUtil;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;

/**
 * Created by litengfei on 2016/12/14.
 */
public class NetApi {

    public static <T, V> Object commonGet(NetParams params, final Class<T> clazz, final ICallBack.NetCallBack<V> commonCallback) {

        Logger.e(params.toString());
        if (commonCallback == null) {
            return null;
        }

        Callback.Cancelable cancelable = x.http().get((RequestParams) params.getParams(), new NetCommonCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                Logger.e("NetApiResult: " + result + "");
                if (result == null) {
                    onError(new NullPointerException("net result is null"), false);
                    return;
                }

                if (result.charAt(0) == '[') {
                    try {
                        ArrayList<T> list = JsonUtil.fromJsonArray(result, clazz);
                        commonCallback.onSuccess((V) list);
                    } catch (Exception e) {
                        onError(e, false);
                    }
                } else if (result.charAt(0) == '{') {
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        if (jsonObject.has("code")) {
                            String code = jsonObject.optString("code");
                            if (code.equals(Settings.CODE_SUCCESS)) {
                                T t = JsonUtil.parseJson(result, clazz);
                                commonCallback.onSuccess((V) t);
                            } else {
                                commonCallback.onOtherCode(code);
                            }
                        } else {
                            T t = JsonUtil.parseJson(result, clazz);
                            commonCallback.onSuccess((V) t);
                        }

                    } catch (Exception e) {
                        onError(e, false);
                    }
                } else {
                    onError(new Exception("not support net result exception"), false);
                }

            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                Logger.e(Utils.generateCrashInfo(throwable));
                try {
                    commonCallback.onError(throwable);

                } catch (Exception e) {
                }
            }

            @Override
            public void onCancelled(Callback.CancelledException e) {
                try {
                    commonCallback.onCancelled(e);

                } catch (Exception e1) {

                }
            }

            @Override
            public void onFinished() {
                try {
                    commonCallback.onFinished();

                } catch (Exception e) {

                }
            }
        });
        return cancelable;
    }

    public static <T, V> Object commonPost(NetParams params, final Class<T> clazz, final ICallBack.NetCallBack<V> commonCallback) {

        Logger.e(params.toString());
        if (commonCallback == null) {
            return null;
        }

        Callback.Cancelable cancelable = x.http().post((RequestParams) params.getParams(), new NetCommonCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                Logger.e("NetApiResult: %s" + result + "");
                if (result == null) {
                    onError(new NullPointerException("net result is null"), false);
                    return;
                }
                if (result.charAt(0) == '[') {
                    try {
                        ArrayList<T> list = JsonUtil.fromJsonArray(result, clazz);
                        commonCallback.onSuccess((V) list);
                    } catch (Exception e) {
                        onError(e, false);
                    }
                } else if (result.charAt(0) == '{') {
                    try {

                        JSONObject jsonObject = new JSONObject(result);
                        if (jsonObject.has("code")) {
                            String code = jsonObject.optString("code");
                            if (code.equals(Settings.CODE_SUCCESS)) {
                                T t = JsonUtil.parseJson(result, clazz);
                                commonCallback.onSuccess((V) t);
                            } else {
                                commonCallback.onOtherCode(code);
                            }
                        } else {
                            T t = JsonUtil.parseJson(result, clazz);
                            commonCallback.onSuccess((V) t);
                        }

                    } catch (Exception e) {
                        onError(e, false);
                    }
                } else {
                    onError(new Exception("not support net result exception"), false);
                }

            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                Logger.e(Utils.generateCrashInfo(throwable));
                try {
                    commonCallback.onError(throwable);

                } catch (Exception e) {

                }
            }

            @Override
            public void onCancelled(Callback.CancelledException e) {
                try {
                    commonCallback.onCancelled(e);

                } catch (Exception e1){

                }
            }

            @Override
            public void onFinished() {
                try {
                    commonCallback.onFinished();

                } catch (Exception e) {

                }
            }
        });
        return cancelable;
    }

    public static <T, V> Object get(NetParams params, final Class<T> clazz, final ICallBack.CommonCallback<V> commonCallback) {

        Logger.e(params.toString());
        if (commonCallback == null) {
            return null;
        }

        Callback.Cancelable cancelable = x.http().get((RequestParams) params.getParams(), new NetCommonCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                Logger.e("NetApiResult: " + result + "");
                if (result == null) {
                    onError(new NullPointerException("net result is null"), false);
                    return;
                }

                if (result.charAt(0) == '[') {
                    try {
                        ArrayList<T> list = JsonUtil.fromJsonArray(result, clazz);
                        commonCallback.onSuccess((V) list);
                    } catch (Exception e) {
                        onError(e, false);
                    }
                } else if (result.charAt(0) == '{') {
                    try {
                        T t = JsonUtil.parseJson(result, clazz);
                        commonCallback.onSuccess((V) t);
                    } catch (Exception e) {
                        onError(e, false);
                    }
                } else {
                    onError(new Exception("not support net result exception"), false);
                }

            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                Logger.e(Utils.generateCrashInfo(throwable));
                try {
                    commonCallback.onError(throwable);

                } catch (Exception e) {

                }
            }

            @Override
            public void onCancelled(Callback.CancelledException e) {
                try {
                    commonCallback.onCancelled(e);

                } catch (Exception e1) {

                }
            }

            @Override
            public void onFinished() {
                try {
                    commonCallback.onFinished();

                } catch (Exception e) {

                }
            }
        });
        return cancelable;
    }

    public static <T, V> Object post(NetParams params, final Class<T> clazz, final ICallBack.CommonCallback<V> commonCallback) {

        Logger.e(params.toString());
        if (commonCallback == null) {
            return null;
        }

        Callback.Cancelable cancelable = x.http().post((RequestParams) params.getParams(), new NetCommonCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                Logger.e("NetApiResult: %s" + result + "");
                if (result == null) {
                    onError(new NullPointerException("net result is null"), false);
                    return;
                }
                if (result.charAt(0) == '[') {
                    try {
                        ArrayList<T> list = JsonUtil.fromJsonArray(result, clazz);
                        commonCallback.onSuccess((V) list);
                    } catch (Exception e) {
                        onError(e, false);
                    }
                } else if (result.charAt(0) == '{') {
                    try {

                        T t = JsonUtil.parseJson(result, clazz);
                        commonCallback.onSuccess((V) t);

                    } catch (Exception e) {
                        onError(e, false);
                    }
                } else {
                    onError(new Exception("not support net result exception"), false);
                }

            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                Logger.e(Utils.generateCrashInfo(throwable));
                try {
                    commonCallback.onError(throwable);

                } catch (Exception e) {

                }
            }

            @Override
            public void onCancelled(Callback.CancelledException e) {
                try {
                    commonCallback.onCancelled(e);

                } catch (Exception e1) {

                }
            }

            @Override
            public void onFinished() {
                try {
                    commonCallback.onFinished();

                } catch (Exception e) {

                }
            }
        });
        return cancelable;
    }

    public static void bindImg(ImageView imageView, String url) {
        x.image().bind(imageView, url, BaseProjectApplication.getInstance().getImageOptions());
    }

    /**
     * 绑定的图片没有loading failure等过度图片
     *
     * @param imageView
     * @param url
     */
    public static void bindImgWithoutFailureDrawable(ImageView imageView, String url) {
        ImageOptions imageOptions = new ImageOptions.Builder()
                .setCrop(false)
                .setUseMemCache(true)
                .build();
        x.image().bind(imageView, url, imageOptions);
    }

    public static void bingLoadDrawable(final ImageView imageView, String url) {
        final ImageOptions imageOptions = new ImageOptions.Builder()
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .build();
        x.image().loadDrawable(url, imageOptions, new Callback.CommonCallback<Drawable>() {
            @Override
            public void onSuccess(Drawable result) {
                imageView.setImageDrawable(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                ToastUtil.showMessage("图片加载失败");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 将请求的图片进行压缩
     *
     * @param imageView
     * @param url
     * @param width     压缩后的宽度 单位px
     * @param height    压缩后的高度 单位 px
     */
    public static void bindCompressImg(ImageView imageView, String url, int width, int height) {
        ImageOptions imageOptions = new ImageOptions.Builder()
                .setSize(DensityUtil.dip2px(width), DensityUtil.dip2px(height))
                .setCrop(false)
                .setUseMemCache(true)
                .build();
        x.image().bind(imageView, url, imageOptions);
    }
}
