package com.wumai.baselibrary.exception;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.os.Process;
import android.widget.Toast;


import com.wumai.baselibrary.R;
import com.wumai.baselibrary.log.Logger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.Thread.UncaughtExceptionHandler;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DebugUncaughtExceptionHandler implements UncaughtExceptionHandler {

    private static DebugUncaughtExceptionHandler sInstance;

    private UncaughtExceptionHandler mDefaultHandler;

    private Context mContext;

    private String mVersionName;

    private String mChannel;

    private DebugUncaughtExceptionHandler(Context context) {
        mContext = context;
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    public static void init(Context context, String versionName, String channel) {
        if (sInstance != null) {
            throw new IllegalStateException("DefaultUncaughtExceptionHandler has already been initialized");
        }
        sInstance = new DebugUncaughtExceptionHandler(context);
        sInstance.mVersionName = versionName;
        sInstance.mChannel = channel;
    }

    private static String getDeviceOSVersion() {
        return Build.VERSION.RELEASE;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        if (!handleException(throwable) && mDefaultHandler != null) {
            mDefaultHandler.uncaughtException(thread, throwable);
        } else {
            try {
                showToast();
                TimeUnit.SECONDS.sleep(2);
                Process.killProcess(Process.myPid());
                System.exit(10);
            } catch (Throwable e) {
                Exceptions.printStackTrace(e);
            }

        }
    }

    private void showToast() {
        final Thread thread = new Thread() {
            @Override
            public void run() {
                setUncaughtExceptionHandler(null);
                Looper.prepare();
                Toast.makeText(mContext, R.string.error_crash, Toast.LENGTH_SHORT)
                        .show();
                Looper.loop();
            }
        };
        thread.start();
    }

    private boolean handleException(final Throwable throwable) {
        if (throwable == null) {
            return false;
        }

        final String message = generateCrashMessage(throwable);
        saveCrashMessage(message);
        return true;
    }

    private void saveCrashMessage(String message) {
        File logFile;
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            logFile = new File(getCrashLogPath());
            Logger.d("crashLogPath:%s" + getCrashLogPath());
            if (!logFile.exists()) {
                logFile.createNewFile();
            }
            br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(message.getBytes())));
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(logFile, true)));

            String bStr;
            while ((bStr = br.readLine()) != null) {
                bw.write(bStr);
                bw.write("\n");
            }
            bw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                Exceptions.printStackTrace(e);
            }
        }
    }

    private String getCrashLogPath() {
        File crashDir;
        if (Environment.getExternalStorageState()
                .equals(Environment.MEDIA_MOUNTED)) {
            crashDir = new File(mContext.getExternalFilesDir(null), "crash");
        } else {
            crashDir = new File(mContext.getFilesDir(), "crash");
        }

        if (!crashDir.exists()) {
            crashDir.mkdirs();
        }

        return new File(crashDir, getCrashLogFileName()).getPath();
    }

    private String getCrashLogFileName() {
        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        return format.format(new Date(System.currentTimeMillis())) + ".txt";
    }

    private String generateCrashMessage(Throwable throwable) {
        StringBuilder buffer = new StringBuilder();
        buffer.append("device: ")
                .append(getDeviceName())
                .append("\n");
        buffer.append("app_ver: ")
                .append(mVersionName)
                .append("\n");
        buffer.append("channel: ")
                .append(mChannel)
                .append("\n");
        buffer.append("crash_time: ")
                .append(getCurrentTimes())
                .append("\n");
        buffer.append(generateCrashInfo(throwable));
        Throwable cause = throwable.getCause();
        while (cause != null) {
            buffer.append(generateCrashInfo(cause));
            cause = cause.getCause();
        }

        return buffer.toString();
    }

    private String getDeviceName() {
        return Build.MANUFACTURER + "-" + Build.MODEL + "(" + getDeviceOSVersion() + ")";
    }

    private String getCurrentTimes() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date(System.currentTimeMillis()));
    }

    private String generateCrashInfo(Throwable throwable) {
        if (throwable == null) {
            return "";
        }

        StringBuilder buffer = new StringBuilder();
        buffer.append(throwable.getClass()
                .getName())
                .append(": ")
                .append(throwable.getLocalizedMessage())
                .append("\n");

        StackTraceElement[] t = throwable.getStackTrace();
        for (StackTraceElement ste : t) {
            buffer.append("\tat: ")
                    .append(ste.getClassName())
                    .append(".")
                    .append(ste.getMethodName())
                    .append("(")
                    .append(ste.getFileName())
                    .append(":")
                    .append(ste.getLineNumber())
                    .append(")\n");
        }
        return buffer.toString();
    }
}
