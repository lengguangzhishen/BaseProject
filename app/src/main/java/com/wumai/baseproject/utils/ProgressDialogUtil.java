package com.wumai.baseproject.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wumai.baseproject.R;
import com.wumai.baseproject.customview.ProgressDialogView;


public class ProgressDialogUtil {

    private Context context;

    private Dialog dialog;

    private ProgressDialogView dialogView;

    public ProgressDialogUtil(Context context) {
        this.context = context;

        dialogView = new ProgressDialogView(context);
        dialog = new Dialog(context, R.style.loading_dialog);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(dialogView);
    }

    public void show() {
        show(null);
    }

    public void show(String text) {
        show(text, true);
    }

    public void show(String text, final boolean cancelable) {
        if (TextUtils.isEmpty(text)) {
            text = context.getResources().getString(R.string.loading);
        }
        dialog.setCancelable(cancelable);
        dialogView.setText(text);
        if (dialog.isShowing()) {
            return;
        }
        dialog.show();
    }

    public boolean isShowing() {
        if (dialog == null) {
            return false;
        }

        return dialog.isShowing();
    }

    public void showReturn(String message) {
        dialog = new Dialog(context, R.style.loading_dialog) {
            @Override
            public boolean onKeyDown(int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    dismiss();
                    ((Activity) context).finish();
                }
                return false;
            }
        };
        dialog.show();
        View view = LayoutInflater.from(context).inflate(R.layout.toast_layout, null);
        dialog.setContentView(view);
        LinearLayout progressLayout = (LinearLayout) view.findViewById(R.id.ll_progress);
        progressLayout.setVisibility(View.VISIBLE);
        ImageView mJuHua = (ImageView) view.findViewById(R.id.pb_loading);
        LinearInterpolator linePolator = new LinearInterpolator();
        Animation am = new android.view.animation.RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation
                .RELATIVE_TO_SELF, 0.5f);
        am.setDuration(3000);
        am.setRepeatCount(Animation.INFINITE);
        am.setInterpolator(linePolator);
        mJuHua.setAnimation(am);
        mJuHua.startAnimation(am);
        TextView messageText = (TextView) view.findViewById(R.id.tv_loading_message);
        messageText.setText(message);
        if (!TextUtils.isEmpty(message)) {
            messageText.setVisibility(View.VISIBLE);
        }
        dialog.setCancelable(true);
    }

    public void hide() {

        try {
            dialog.dismiss();
            dialog.setCancelable(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public View getRootView() {
        return dialogView;
    }
}
