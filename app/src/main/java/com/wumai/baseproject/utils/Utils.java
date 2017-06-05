package com.wumai.baseproject.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.widget.Toast;

import com.wumai.baseproject.app.BaseProjectApplication;
import com.wumai.baselibrary.Globals;
import com.wumai.baselibrary.exception.Exceptions;
import com.wumai.baselibrary.log.Logger;
import com.wumai.baseproject.config.AppInfo;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Locale;

/**
 * Created by litengfei on 15/12/22.
 * 通用utils
 */
public class Utils implements Globals {

    public static final int INVALID_NUMBER = -99999;
    public static final int SIZE_B = 1000;
    public static final int SIZE_KB = SIZE_B * SIZE_B;
    public static final int SIZE_M = SIZE_KB * SIZE_B;
    public static final int SIZE_G = SIZE_M * SIZE_B;

    private Utils() {
    }

    /**
     * 保留小数点后7位
     */
    public static String formatCoordinate(double number) {
        double value = AppInfo.DEF_COORDINATE;
        if (number > 0) {
            value = round(number, 7);
        }
        return String.valueOf(value);
    }

    /**
     * 提供精确的小数位四舍五入处理。
     *
     * @param value 需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */

    public static double round(double value, int scale) {
        if (scale < 0) {
            scale = 0;
        }
        BigDecimal bd = BigDecimal.valueOf(value);
        return bd.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static void printExceptionStackTrace(Throwable e) {
        if (BaseProjectApplication.getInstance().isDebugMode()) {
            Exceptions.printStackTrace(e);
            Logger.e("Utils.printExceptionStackTrace,e=%s" + e.toString());
        }
    }

    public static int getInteger(String text, boolean canNull) {
        if (TextUtils.isEmpty(text)) {
            if (canNull) {
                return 0;
            }
            return INVALID_NUMBER;
        }
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return INVALID_NUMBER;
        }
    }

    public static double getDouble(String text, boolean canNull) {
        if (TextUtils.isEmpty(text)) {
            if (canNull) {
                return 0;
            }
            return INVALID_NUMBER;
        }
        try {
            return Double.parseDouble(text);
        } catch (NumberFormatException e) {
            return INVALID_NUMBER;
        }
    }

    public static float getFloat(String text, boolean canNull) {
        if (TextUtils.isEmpty(text)) {
            if (canNull) {
                return 0;
            }
            return INVALID_NUMBER;
        }
        try {
            return Float.parseFloat(text);
        } catch (NumberFormatException e) {
            return INVALID_NUMBER;
        }
    }

    public static SpannableString getColoredString(String string,
                                                   int start,
                                                   int end,
                                                   int textColor,
                                                   float textSize,
                                                   boolean isBold) {
        SpannableString sp = new SpannableString(string);
        // 设置文字颜色

        sp.setSpan(new ForegroundColorSpan(textColor), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        // 文字大小
        if (textSize > 0) {
            sp.setSpan(new AbsoluteSizeSpan((int) textSize, true), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        if (isBold) {
            sp.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return sp;
    }

    public static void gotoGpsSystemSetting(Context context) {
        try {
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "请进入设置界面打开GPS服务", Toast.LENGTH_SHORT).show();
        }
    }

    public static void gotoWirelessSetting(Context context) {
        Intent intent = null;
        //判断手机系统的版本  即API大于10 就是3.0或以上版本
        if (android.os.Build.VERSION.SDK_INT > 10) {
            intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
        } else {
            intent = new Intent();
            ComponentName component = new ComponentName("com.android.settings", "com.android.settings" + "" +
                    ".WirelessSettings");
            intent.setComponent(component);
            intent.setAction("android.intent.action.VIEW");
        }
        context.startActivity(intent);
    }

    public static String formatNumber(String amount) {
        return String.format("￥%.2f", amount);
    }

    public static String formatNumberToUSStyle(double num) {
        Locale.setDefault(Locale.US);
        DecimalFormat myformat = new DecimalFormat("###,###,###.00");//使用美国的格式
        return myformat.format(num);
    }

    public static String mNickname = "";
    public static String mPassword = "";
    public static String mPhone = "";

    /**
     * 将一个数字转化为带B,KB,M,G的形式，用于处理文件大小，保留一位小数
     *
     * @param num
     * @return
     */
    public static String formatSize(Context c, long num) {
//        if (num < SIZE_B) {
//            return round(num / SIZE_B, 1) + "B";
//        } else if (num < SIZE_KB) {
//            return round(num / SIZE_B, 1) + "B";
//        } else if (num < SIZE_M) {
//            return round(num / SIZE_KB, 1) + "KB";
//        } else if (num < SIZE_G) {
//            return round(num / SIZE_M, 1) + "M";
//        } else {
//            return round(num / SIZE_G, 1) + "G";
//        }
        return Formatter.formatFileSize(c,num);
    }

    /**
     * 2  * 获取版本号
     * 3  * @return 当前应用的版本号
     * 4
     */
    public static String getVersion(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static Bitmap convertHeadPicture(Bitmap bitmap) {

        int w = bitmap.getWidth();
        int h = bitmap.getHeight();

        Bitmap output = Bitmap.createBitmap(w, w, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();

        int y = Math.max((h - w) / 2 - 8, 0);

        final Rect srcRect = new Rect(0, y, w, y + w);
        final Rect destRect = new Rect(0, 0, w, w);
        final RectF destRectF = new RectF(destRect);
        final float roundPx = w / 2;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(destRectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, srcRect, destRect, paint);
        return output;
    }

    public static String getImei() {
        TelephonyManager tm = (TelephonyManager) BaseProjectApplication.getInstance().getService("phone");
        return tm.getDeviceId();
    }

    /**
     * 运行一个任务在主线程
     */
    public static void runInMainThread(Runnable task){
        //判断当前线程是否是主线程
        if(isMainThread()){
            //当前是主线程
            task.run();
        }else{
            UI_HANDLER.post(task);
        }
    }

    /**
     * 判断当前线程是否是主线程
     * @return
     */
    public static boolean isMainThread(){
//		if(android.os.Process.myTid() == MyApplication.getMainThreadId()){
//			return true;
//		}else{
//			return false;
//		}

        return android.os.Process.myTid() == BaseProjectApplication.getInstance().getMainThreadId();
    }

    public static String generateCrashInfo(Throwable throwable) {
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
