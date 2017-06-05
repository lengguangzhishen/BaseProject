package com.wumai.baseproject.utils;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.content.Intent;

import com.wumai.baseproject.app.BaseProjectApplication;
import com.wumai.baselibrary.log.Logger;
import com.wumai.baseproject.config.AppInfo;

import java.util.List;

/**
 * 切换到后台的工具类 *
 */
public class BackgroundUtils {

    private static BackgroundUtils mBackgroundUtils = new BackgroundUtils();

    private boolean mCurrentState;

    private boolean mOldState = false;// false为后台，true为前台

    private String mPackageName = "";

    private BackgroundUtils() {
        mPackageName = BaseProjectApplication.getInstance().getPackageName();
    }

    public static BackgroundUtils getInstance() {
        return mBackgroundUtils;
    }

    public void dealAppRunState() {
        background();
    }

    /**
     * @param isActive 是否是主动运行
     */
    public void dealAppRunState(String awokeWay, boolean isActive) {
        boolean show = isAppOnForeground();
        if (show) {
            foreground(show, awokeWay, isActive);
        } else {
            background();
        }
    }

    private void foreground(boolean show, String awokeWay, boolean isActive) {
        mCurrentState = show;
        if (mCurrentState != mOldState) {// 切换到前台
            mOldState = mCurrentState;
            dealForeground(awokeWay, isActive);
        }
    }

    private void background() {
        mCurrentState = isAppOnForeground();
        if (!mCurrentState && mCurrentState != mOldState) {// 切换到后台
            mOldState = false;
            dealBackground();
        }
    }

    // 前台切换到后台
    private void dealBackground() {
        Logger.d("background");
        AppInfo.isLastStatusBackground = true;
        Intent intent = new Intent(ActionName.ACTION_APP_ENTER_BACKGROUND_BROADCAST);
        BaseProjectApplication.getInstance().sendBroadcast(intent);
    }

    /**
     * 后台切换到前台
     *
     * @param awokeWay 唤醒方式（在isActive为false的情况下）
     * @param isActive 是否是主动运行
     */
    private void dealForeground(String awokeWay, boolean isActive) {
        Logger.d("foreground");
        Intent intent = new Intent(ActionName.ACTION_APP_ENTER_FOREGROUND_BROADCAST);
        BaseProjectApplication.getInstance().sendBroadcast(intent);
    }

    /**
     * 程序是否在前台运行
     *
     * @return
     */
    public boolean isAppOnForeground() {
        // Returns a list of application processes that are running on the
        // device
        ActivityManager activityManager = (ActivityManager) BaseProjectApplication.getInstance().getSystemService(Context
                .ACTIVITY_SERVICE);
        List<RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        if (appProcesses == null)
            return false;

        List<RunningTaskInfo> tasksInfo = activityManager.getRunningTasks(1);

        for (RunningAppProcessInfo appProcess : appProcesses) {
            // The name of the process that this object is associated with.
            if (appProcess.processName.equals(mPackageName) && appProcess.importance == RunningAppProcessInfo
                    .IMPORTANCE_FOREGROUND && tasksInfo.size() > 0 && mPackageName.equals(tasksInfo.get(0)
                    .topActivity.getPackageName())) {
                return true;
            }
        }
        return false;
    }
}
