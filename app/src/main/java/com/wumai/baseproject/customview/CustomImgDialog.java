package com.wumai.baseproject.customview;

import android.app.Dialog;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.wumai.baseproject.R;
import com.wumai.baseproject.network.NetApi;

/**
 * Created by litengfei on 2017/5/27.
 * 邮箱: litengfeilo@163.com
 */
public class CustomImgDialog extends Dialog {
    private Window window = null;
    Context mContext;

    public CustomImgDialog(Context context)
    {
        super(context);
        mContext = context;
    }

    public CustomImgDialog(Context context, int themeResId) {
        super(context, themeResId);
        mContext = context;
    }

    public void showDialog(int layoutResID, String url, int x, int y){
        View view = View.inflate(mContext, layoutResID, null);
        ImageView iv = (ImageView) view.findViewById(R.id.iv);
        NetApi.bindImgWithoutFailureDrawable(iv, url);
        setContentView(view);

        windowDeploy(x, y);

        //设置触摸对话框意外的地方取消对话框
        setCanceledOnTouchOutside(true);
        show();
    }

    //设置窗口显示
    public void windowDeploy(int x, int y){
        window = getWindow(); //得到对话框
        window.setWindowAnimations(R.style.dialogWindowAnim); //设置窗口弹出动画
        window.setBackgroundDrawableResource(R.color.color_black); //设置对话框背景为透明
        WindowManager.LayoutParams wl = window.getAttributes();
        //根据x，y坐标设置窗口需要显示的位置
        WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        int screenWidth = windowManager.getDefaultDisplay().getWidth();
        int screenHeight = windowManager.getDefaultDisplay().getHeight();

        wl.x = x - screenWidth / 2; //x小于0左移，大于0右移
        wl.y = y - screenHeight / 2; //y小于0上移，大于0下移
//        wl.width = mContext.getResources().getDimensionPixelSize(R.dimen.dp100);
//        wl.height = mContext.getResources().getDimensionPixelSize(R.dimen.dp100);
//            wl.alpha = 0.6f; //设置透明度
//            wl.gravity = Gravity.BOTTOM; //设置重力
        window.setAttributes(wl);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            dismiss();
        }
        return super.onTouchEvent(event);

    }
}
