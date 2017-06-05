package com.wumai.baselibrary.util;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Process;

import java.util.List;

public final class Processes {

    private static String sProcessName;

    private Processes() {
    }

    public static String getName(Context context) {
        if (sProcessName == null) {
            synchronized (Processes.class) {
                if (sProcessName == null) {
                    sProcessName = getName(context, Process.myPid());
                }
            }
        }
        return sProcessName;
    }

    public static String getName(Context context, int pid) {
        ActivityManager mActivityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> allProcess = mActivityManager.getRunningAppProcesses();
        if (allProcess != null) {
            for (ActivityManager.RunningAppProcessInfo aInfo : allProcess) {
                if (aInfo.pid == pid) {
                    return aInfo.processName;
                }
            }
        }
        return "";
    }

    public static boolean isApplicationProcess(Context context) {
        return getName(context).equals(context.getPackageName());
    }
}
