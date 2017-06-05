package com.wumai.baseproject.customview.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wumai.baseproject.R;
import com.wumai.baselibrary.viewmapping.ViewMapUtil;
import com.wumai.baselibrary.viewmapping.ViewMapping;

import org.xutils.common.util.DensityUtil;

/**
 * Created by litengfei on 2017/4/25.
 */
@ViewMapping(R.layout.dialog_custom)
public class CustomDialog extends Dialog implements View.OnClickListener {

    @ViewMapping(R.id.btn_left)
    private Button mLeftBtn;

    @ViewMapping(R.id.btn_right)
    private Button mRightBtn;

    @ViewMapping(R.id.btn_single)
    private Button mSingleBtn;

    @ViewMapping(R.id.ll_dialog_twobuttons_container)
    private LinearLayout mTwoBtnContainer;

    @ViewMapping(R.id.tv_dialog_title)
    private TextView mTvTitle;

    @ViewMapping(R.id.tv_dialog_message)
    private TextView mTvMessage;

    private DialogBtnClickListener mListener;

    public CustomDialog(Context context, int btnNum) {
        super(context, R.style.style_dialog);
        setContentView(ViewMapUtil.map(this));

        mLeftBtn.setOnClickListener(this);
        mRightBtn.setOnClickListener(this);
        mSingleBtn.setOnClickListener(this);

        setBtnNum(btnNum);
        this.setCanceledOnTouchOutside(false);

        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        lp.width = (int) (DensityUtil.getScreenWidth() * 0.75); // 设置宽度
        this.getWindow().setAttributes(lp);
    }
    public void setMessageGravity(int i){
        mTvMessage.setGravity(i);
    }
    /**
     * 显示的按钮数量（最多只有两个）
     *
     * @param num
     */
    private void setBtnNum(int num) {
        switch (num) {
            case 2:
                mSingleBtn.setVisibility(View.GONE);
                mTwoBtnContainer.setVisibility(View.VISIBLE);
                break;
            case 1:
            default:
                mSingleBtn.setVisibility(View.VISIBLE);
                mTwoBtnContainer.setVisibility(View.GONE);
                break;
        }
    }

    /**
     * 设置左按钮文字
     *
     * @param resid
     */
    public void setLeftBtnText(int resid) {
        mLeftBtn.setText(resid);
    }

    /**
     * 设置左按钮文字
     *
     * @param text
     */
    public void setLeftBtnText(String text) {
        if(!TextUtils.isEmpty(text)) {
            mLeftBtn.setText(text);
            mTwoBtnContainer.setVisibility(View.VISIBLE);
            mSingleBtn.setVisibility(View.GONE);
        }
    }

    /**
     * 设置右按钮文字
     *
     * @param text
     */
    public void setRightBtnText(String text) {
        if(!TextUtils.isEmpty(text)) {
            mRightBtn.setText(text);
            mTwoBtnContainer.setVisibility(View.VISIBLE);
            mSingleBtn.setVisibility(View.GONE);
        }
    }

    /**
     * 设置有按钮文字和颜色
     * @param textRes 文字的id
     * @param colorRes 通过getResource得到的color 的int值
     */
    public void setRightBtnTextAndColor(int textRes, int colorRes) {
            mRightBtn.setText(textRes);
            mRightBtn.setTextColor(colorRes);
            mTwoBtnContainer.setVisibility(View.VISIBLE);
            mSingleBtn.setVisibility(View.GONE);
    }

    /**
     * 设置右按钮文字
     *
     * @param resid
     */
    public void setRightBtnText(int resid) {
        mRightBtn.setText(resid);
        mTwoBtnContainer.setVisibility(View.VISIBLE);
        mSingleBtn.setVisibility(View.GONE);
    }

    /**
     * 设置只显示一个按钮情况下的按钮文字
     *
     * @param resid
     */
    public void setSingleBtnText(int resid) {
        mSingleBtn.setText(resid);
        mSingleBtn.setVisibility(View.VISIBLE);
        mTwoBtnContainer.setVisibility(View.GONE);
    }

    /**
     * 设置是显示一个按钮情况下的按钮文字和颜色值
     * @param textRes 文字的id
     * @param colorRes 通过getResource得到的color 的int值
     */
    public void setSingleBtnTextAndColor(int textRes, int colorRes) {
        mSingleBtn.setText(textRes);
        mSingleBtn.setTextColor(colorRes);
        mSingleBtn.setVisibility(View.VISIBLE);
        mTwoBtnContainer.setVisibility(View.GONE);
    }

    /**
     * 设置只显示一个按钮情况下的按钮文字
     *
     * @param text
     */
    public void setSingleBtnText(String text) {
        if(!TextUtils.isEmpty(text)) {
            mSingleBtn.setText(text);
            mSingleBtn.setVisibility(View.VISIBLE);
            mTwoBtnContainer.setVisibility(View.GONE);
        }
    }


    /**
     * 设置标题文字
     *
     * @param text
     */
    public void setTitleText(String text) {
        if(!TextUtils.isEmpty(text)) {
            mTvTitle.setText(text);
            mTvTitle.setVisibility(View.VISIBLE);
        }
    }

    public void setTitleText(int textId) {
        if (textId > 0) {
            mTvTitle.setText(textId);
            mTvTitle.setVisibility(View.VISIBLE);
        }
    }


    /**
     * 设置Message文字
     *
     * @param text
     */
    public void setMessageText(String text) {
        if(!TextUtils.isEmpty(text)) {
            mTvMessage.setText(text);
            mTvMessage.setVisibility(View.VISIBLE);
        }
    }

    public void setMessageText(int textId) {
        if (textId > 0) {
            mTvMessage.setText(textId);
            mTvMessage.setVisibility(View.VISIBLE);
        }
    }

    public void setDialogBtnClickListener(DialogBtnClickListener listener) {
        this.mListener = listener;
    }

    @Override
    public void onClick(View v) {
        BtnType type = null;
        switch (v.getId()) {
            case R.id.btn_left:
                type = BtnType.LEFT;
                break;
            case R.id.btn_right:
                type = BtnType.RIGHT;
                break;
            case R.id.btn_single:
                type = BtnType.ONE;
                break;
            default:
                break;
        }
        if (mListener != null) {
            mListener.onClick(this, type);
        } else {
            dismiss();
        }
    }

    public enum BtnType {
        ONE, LEFT, RIGHT
    }

    public interface DialogBtnClickListener {
        void onClick(Dialog dialog, BtnType type);
    }
}
