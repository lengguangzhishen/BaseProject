package com.wumai.baseproject.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.PowerManager;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.TouchDelegate;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.wumai.baseproject.R;
import com.wumai.baseproject.app.BaseProjectApplication;

import org.xutils.common.util.DensityUtil;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 获取屏幕宽高,px与dp之间的转换，使用 xutils自带的DensityUtil
 */
public class UiUtils {

    static Toast toast = null;

    static PowerManager.WakeLock mWakeLock;

    static Timer timer;
    static TimerTask autoDismissTask;

    /**
     * 扩大view的点击区域
     *
     * @param view
     * @param top
     * @param bottom
     * @param left
     * @param right
     */
    public static void expandViewTouchDelegate(final View view, final int top,
                                               final int bottom, final int left, final int right) {

        ((View) view.getParent()).post(new Runnable() {
            @Override
            public void run() {
                Rect bounds = new Rect();
                view.setEnabled(true);
                view.getHitRect(bounds);

                bounds.top -= top;
                bounds.bottom += bottom;
                bounds.left -= left;
                bounds.right += right;

                TouchDelegate touchDelegate = new TouchDelegate(bounds, view);

                if (View.class.isInstance(view.getParent())) {
                    ((View) view.getParent()).setTouchDelegate(touchDelegate);
                }
            }
        });
    }

    /**
     * 自定义toast(3000毫秒显示)
     */
    public static void showLongCustomToast(Context context, String msg) {
        try {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.custom_toast, null);
            TextView toastContent = (TextView) view.findViewById(R.id.tvTextToast);
            if (!TextUtils.isEmpty(msg)) {
                toastContent.setText(msg);
            } else {
                return;
            }
            if (toast == null) {
                toast = new Toast(context);
            }
            int marginBotton = DensityUtil.getScreenHeight() / 4;
            toast.setGravity(Gravity.BOTTOM, 0, marginBotton);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(view);
            toast.show();
        } catch (Exception e) {
            if (toast != null) {
                toast.cancel();
            }
        }
    }

    /**
     * 自定义toast(1500毫秒显示 传String)
     */
    public static void showShortCustomToast(Context context, String msg) {

        try {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.custom_toast, null);
            TextView toastContent = (TextView) view.findViewById(R.id.tvTextToast);
            if (!TextUtils.isEmpty(msg)) {
                toastContent.setText(msg);
            } else {
                return;
            }
            if (toast == null) {
                toast = new Toast(context);
            }
            int marginBotton = DensityUtil.getScreenHeight() / 4;
            toast.setGravity(Gravity.BOTTOM, 0, marginBotton);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(view);
            toast.show();
        } catch (Exception e) {
            if (toast != null) {
                toast.cancel();
            }
        }
    }

    /**
     * 自定义toast(1500毫秒显示,传String的ID)
     */
    public static void showShortCustomToast(Context context, int stringID) {
        try {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.custom_toast, null);
            TextView toastContent = (TextView) view.findViewById(R.id.tvTextToast);
            if (!TextUtils.isEmpty(String.valueOf(stringID))) {
                toastContent.setText(context.getResources().getString(stringID));
            } else {
                return;
            }
            if (toast == null) {
                toast = new Toast(context);
            }
            int marginBotton = DensityUtil.getScreenHeight() / 4;
            toast.setGravity(Gravity.BOTTOM, 0, marginBotton);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(view);
            toast.show();
        } catch (Exception e) {
            if (toast != null) {
                toast.cancel();
            }
        }
    }

    /**
     * 自定义toast(1500毫秒显示,传String的ID)
     */
    public static void showShortSpecialToast(Context context, String msg) {
        Toast sToast = null;
        try {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.custom_toast, null);
            TextView toastContent = (TextView) view.findViewById(R.id.tvTextToast);
            if (!TextUtil.checkNullString(msg)) {
                toastContent.setText(msg);
            } else {
                return;
            }
            if (sToast == null) {
                sToast = new Toast(context);
            }
            int marginBotton = DensityUtil.getScreenHeight() / 4;
            sToast.setGravity(Gravity.BOTTOM, 0, marginBotton);
            sToast.setDuration(Toast.LENGTH_SHORT);
            sToast.setView(view);
            sToast.show();
        } catch (Exception e) {
            if (sToast != null) {
                sToast.cancel();
            }
        }
    }

    /**
     * 销毁toast
     */
    public static void cancelToast() {
        if (toast != null) {
            toast.cancel();
            toast = null;
        }
    }

    /**
     * 显示键盘；
     */
    public static void showKeyboard(final View v) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                ((InputMethodManager) BaseProjectApplication.getGlobalContext().getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(v, 0);
            }
        }, 800);
    }

    /**
     * 隐藏软键盘
     */
    public static void hideKeyboard(final Activity activity) {
        if (activity != null) {
            View view = activity.getWindow().peekDecorView();
            if (view != null) {
                InputMethodManager inputmanger = (InputMethodManager) BaseProjectApplication.getGlobalContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    /**
     * 开始抖动动画效果
     *
     * @param view 需要进行抖动效果的文本
     */
    public static void startShakeAnimation(Context context, View view) {
        view.startAnimation(AnimationUtils.loadAnimation(context, R.anim.shake));
        view.requestFocus();
    }


    public static void finishActivityWithAnim(Activity activity) {
        finishActivityWithAnim(activity, R.anim.slide_from_left, R.anim.slide_to_right);
    }

    /**
     * 结束activity
     *
     * @param activity  老activity，用于调起finish()方法
     * @param enterAnim 新activity的进入动画
     * @param exitAnim  老activity的退出动画
     */
    public static void finishActivityWithAnim(Activity activity, int enterAnim, int exitAnim) {
        activity.finish();
        activity.overridePendingTransition(enterAnim, exitAnim);
        activity = null;
    }

    //判断当前activity是否存活
    public static boolean isActivityAlive(Activity activity) {
        if (activity != null && !activity.isFinishing())
            return true;
        return false;
    }

    /**
     * 保持播放时,屏幕常亮;
     */
    public static void acquireWakeLock(@NonNull Activity activity) {
        if (mWakeLock == null) {
            PowerManager pm = (PowerManager) activity.getSystemService(Context.POWER_SERVICE);
            mWakeLock = pm.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK, activity.getClass().getCanonicalName());
            mWakeLock.acquire();
        }
    }

    /**
     * 释放wakeLock;
     */
    public static void releaseWakeLock() {
        if (mWakeLock != null && mWakeLock.isHeld()) {
            mWakeLock.release();
            mWakeLock = null;
        }
    }

    /**
     * 控制试图3秒钟后隐藏;
     *
     * @param views 传入的view集合;
     */
    public static void autoDismiss(final Activity activity, int second, final View... views) {
        if (timer == null) {
            timer = new Timer();
        }
        if (autoDismissTask != null) {
            autoDismissTask.cancel();
        }

        autoDismissTask = new TimerTask() {
            @Override
            public void run() {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        for (View v : views) {
                            if (v.getVisibility() == View.VISIBLE) {
                                v.setVisibility(View.GONE);
                            }
                        }
                    }
                });
            }
        };
        timer.schedule(autoDismissTask, second * 1000);
    }

    public static void cancelDismissTask() {
        if (autoDismissTask != null)
            autoDismissTask.cancel();
    }


    /**
     * 设置view是否可点击;
     *
     * @param isEnable
     * @param views
     */
    public static void setViewIsEnable(boolean isEnable, View... views) {

        for (View view : views) {
            view.setEnabled(isEnable);
        }
    }

    /**
     * 设置View是否隐藏;
     *
     * @param isVisible
     * @param views
     */
    public static void setViewIsVisible(boolean isVisible, View... views) {
        for (View view : views) {
            view.setVisibility(isVisible ? View.VISIBLE : View.GONE);
        }
    }

    /**
     * 获取屏幕的宽度
     */
    public static int getWindowWidth() {
        WindowManager wm = (WindowManager) BaseProjectApplication.getService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay().getWidth();
    }

    /**
     * 获取屏幕的高度
     */
    public static int getWindowHeight() {
        WindowManager wm = (WindowManager) BaseProjectApplication.getService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay().getHeight();
    }

    public static int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, BaseProjectApplication.getGlobalContext().getResources().getDisplayMetrics());
    }

    /**
     * 给屏幕设置蒙版背景(虚化)
     * @param window
     * @param bgcolor
     */
    public static void darkenBackgroud(Window window, Float bgcolor) {
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.alpha = bgcolor;
        window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        window.setAttributes(lp);
        window.setWindowAnimations(R.style.pop_animation_shadow);
    }

    /**
     * 局部背景模糊
     * @param context
     * @param view
     */
    public static void applyBlur(Context context, View view) {

        // 获取壁纸管理器
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(context);
        // 获取当前壁纸
        Drawable wallpaperDrawable = wallpaperManager.getDrawable();
        // 将Drawable,转成Bitmap
        Bitmap bmp = ((BitmapDrawable) wallpaperDrawable).getBitmap();

        blur(bmp, context, view);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private static void blur(Bitmap bkg, Context context, View view) {
        long startMs = System.currentTimeMillis();
        float radius = 20;

        bkg = small(bkg);
        Bitmap bitmap = bkg.copy(bkg.getConfig(), true);

        final RenderScript rs = RenderScript.create(context);
        final Allocation input = Allocation.createFromBitmap(rs, bkg, Allocation.MipmapControl.MIPMAP_NONE,
                Allocation.USAGE_SCRIPT);
        final Allocation output = Allocation.createTyped(rs, input.getType());
        final ScriptIntrinsicBlur script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        script.setRadius(radius);
        script.setInput(input);
        script.forEach(output);
        output.copyTo(bitmap);

        bitmap = big(bitmap);
        view.setBackground(new BitmapDrawable(context.getResources(), bitmap));
        rs.destroy();
    }

    private static Bitmap big(Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.postScale(4f,4f); //长和宽放大缩小的比例
        Bitmap resizeBmp = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
        return resizeBmp;
    }

    private static Bitmap small(Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.postScale(0.25f,0.25f); //长和宽放大缩小的比例
        Bitmap resizeBmp = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
        return resizeBmp;
    }



}
