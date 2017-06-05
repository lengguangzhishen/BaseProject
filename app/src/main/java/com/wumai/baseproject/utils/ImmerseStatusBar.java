package com.wumai.baseproject.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

import com.wumai.baseproject.R;

public class ImmerseStatusBar {
    /**
     * 设置沉浸状态栏
     *
     * @param activity
     */
    public static void setImmerseStatusBar(Activity activity) {
        //当系统版本为4.4或者4.4以上时可以使用沉浸式状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(activity, true);
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(activity);
        // 激活状态栏
        tintManager.setStatusBarTintEnabled(true);
        // enable navigation bar tint 激活导航栏
        tintManager.setNavigationBarTintEnabled(true);
        //设置系统栏设置颜色
        tintManager.setTintColor(activity.getResources().getColor(R.color.color_44464c));
        //给状态栏设置颜色
        tintManager.setStatusBarTintResource(R.color.color_44464c);
        // 设置导航栏设置资源
        tintManager.setNavigationBarTintResource(R.color.color_44464c);
    }

    /**
     * 设置沉浸状态栏
     *
     * @param activity
     */
    public static void setImmerseStatusBarColor(Activity activity, int color) {
        //当系统版本为4.4或者4.4以上时可以使用沉浸式状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(activity, true);
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(activity);
        // 激活状态栏
        tintManager.setStatusBarTintEnabled(true);
        // enable navigation bar tint 激活导航栏
        tintManager.setNavigationBarTintEnabled(true);
        //设置系统栏设置颜色
        tintManager.setTintColor(activity.getResources().getColor(color));
        //给状态栏设置颜色
        tintManager.setStatusBarTintResource(color);
        // 设置导航栏设置资源
        tintManager.setNavigationBarTintResource(color);
    }

    @TargetApi(19)
    private static void setTranslucentStatus(Activity activity, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    /**
     * 设置沉浸状态栏透明
     *
     * @param activity
     */
    public static void setImmerseStatusBarTransparency(Activity activity) {
        //当系统版本为4.4或者4.4以上时可以使用沉浸式状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(activity, true);
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(activity);
        // 激活状态栏
        tintManager.setStatusBarTintEnabled(true);
        // enable navigation bar tint 激活导航栏
        tintManager.setNavigationBarTintEnabled(true);
        //设置系统栏设置颜色
        tintManager.setTintColor(activity.getResources().getColor(R.color.transparent));
        //给状态栏设置颜色
        tintManager.setStatusBarTintResource(R.color.transparent);
        // 设置导航栏设置资源
        tintManager.setNavigationBarTintResource(R.color.transparent);
    }


    /**
     * 初始化状态栏 不设置
     *
     * @param activity
     */
    public static void initImmerseStatusBar(Activity activity) {
        Window win = activity.getWindow();
        win.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);  //全屏
        SystemBarTintManager mTintManager = new SystemBarTintManager(activity);
        mTintManager.setStatusBarTintEnabled(false);
        mTintManager.setNavigationBarTintEnabled(false);
        mTintManager.setTintColor(activity.getResources().getColor(R.color.color_00000000));
        mTintManager.setStatusBarTintResource(R.color.color_00000000);
        mTintManager.setNavigationBarTintResource(R.color.color_00000000);
    }
}
