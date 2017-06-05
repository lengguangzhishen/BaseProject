package com.wumai.baseproject.utils;

import android.app.Activity;
import android.app.Dialog;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.wumai.baseproject.R;

/**
 * Created by xzw on 2017/4/21.
 */

public class BottomDialogHelper {
    public static Dialog displayTiem(Activity context, View viewDialog) {
        Dialog dialog = new Dialog(context, R.style.Dialog2);
        Window dialogWindow = dialog.getWindow();
        /*实例化Window*/
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();

        Display display = context.getWindowManager().getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();
        dialogWindow.setGravity(Gravity.BOTTOM);
        dialogWindow.setWindowAnimations(R.style.mystyle);
        //设置dialog的宽高为屏幕的宽高
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setContentView(viewDialog, layoutParams);
        dialog.show();
        return dialog;
    }

}
