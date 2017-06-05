package com.wumai.baseproject.app;


import android.app.ActivityManager;
import android.content.Context;
import android.hardware.Camera;

import com.google.gson.Gson;
import com.wumai.baseproject.BuildConfig;
import com.wumai.baselibrary.BaseApplication;
import com.wumai.baselibrary.exception.DebugUncaughtExceptionHandler;
import com.wumai.baseproject.config.Constants;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

public class BaseProjectApplication extends BaseApplication {
    private static BaseProjectApplication sInstance;
    private ImageOptions imageOptions;

    private Gson mGson;
    public static boolean isOnLine = false;
    private boolean isDebug = !isOnLine;
    private int myTid;

    @Override
    public void onCreate() {
        sInstance = this;
        super.onCreate();

        initXutils();


        myTid = android.os.Process.myTid();//OS给线程的id
        AppProxyFactory.registerProxy(BaseProjectApplication.getGlobalContext(), CustomerAppProxy.class);

        initImageOptions();
    }

    private void initXutils() {
        x.Ext.init(this);
        x.Ext.setDebug(isDebug);
    }

    //    BaseProjectApplication
    @Override
    public Gson getGson() {
        if (mGson == null) {
            mGson = new Gson();
        }
        return mGson;
    }

    @Override
    public void onInitializeCrashHandler(boolean isDebug) {
        if (isOnLine) {
            DebugUncaughtExceptionHandler.init(getInstance(), BuildConfig.VERSION_NAME, BuildConfig.FLAVOR);
        }
    }

    public void initImageOptions() {
        imageOptions = new ImageOptions.Builder()
//                .setSize(DensityUtil.d                        ip2px(120), DensityUtil.dip2px(120))
//                .setRadius(DensityUtil.dip2px(5))
                // 如果ImageView的大小不是定义为wrap_content, 不要crop.
                .setCrop(false) // 很多时候设置了合适的scaleType也不需要它.
                // 加载中或错误图片的ScaleType
                //.setPlaceholderScaleType(ImageView.ScaleType.MATRIX)
//                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
//                .setFailureDrawableId(R.drawable.teacher_null).setUseMemCache(true)
//                .setFailureDrawableId(R.mipmap.ttjk_default_bg).setUseMemCache(true)
                .build();
    }

    public ImageOptions getImageOptions() {
        return imageOptions;
    }

    public static BaseProjectApplication getInstance() {
        return sInstance;
    }

    public int getMainThreadId() {
        return myTid;
    }

    public boolean isDebugMode() {
        return isDebug;
    }
}
