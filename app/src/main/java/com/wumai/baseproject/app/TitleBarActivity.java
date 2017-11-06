package com.wumai.baseproject.app;

import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentActivity;
import android.text.InputFilter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wumai.baseproject.R;
import com.wumai.baseproject.utils.ImmerseStatusBar;
import com.wumai.baseproject.utils.UiUtils;

import org.xutils.common.util.DensityUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class TitleBarActivity extends FragmentActivity {

    public LinearLayout mContentView;
    TitleBar mBar;
    private View mTitleBarView;
    private Unbinder bind;

    @Override
    public void setContentView(int layoutResID) {
        initTitle();
        if (layoutResID <= 0) {
            return;
        }
        // mContentView 必须是 LinearLayout
        mContentView = (LinearLayout) getLayoutInflater().inflate(layoutResID,
                null);
        mContentView.addView(mTitleBarView, 0);
        super.setContentView(mContentView);
        bind = ButterKnife.bind(this);
    }


    @Override
    public void setContentView(View view) {
        initTitle();
        // mContentView 必须是 LinearLayout
        mContentView = (LinearLayout) view;
        mContentView.addView(mTitleBarView, 0);
        super.setContentView(mContentView);
        bind = ButterKnife.bind(this);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        bind = ButterKnife.bind(this);
    }
    public View getTitleBar() {
        return mTitleBarView;
    }

    private void initTitle() {
        mBar = new TitleBar();
        mBar.setListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                onTitleBarItemClicked(v);
            }
        });
    }

    protected abstract void onTitleBarItemClicked(View v);


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bind != null) {
            bind.unbind();
        }
        System.gc();
    }

    @Override
    public void onBackPressed() {
        UiUtils.finishActivityWithAnim(this);
    }

    /**
     * 设置左边按钮是否显示，默认是back,不显示
     *
     * @param isVisible
     */
    public void setLeftImageVisible(boolean isVisible) {
        if (isVisible) {
            mBar.mLeftImage.setVisibility(View.VISIBLE);
        } else {
            mBar.mLeftImage.setVisibility(View.GONE);
        }
    }

    /**
     * 设置沉浸式状态栏是否显示
     * @param visible
     */
    public void setImmerseStatusVisible(boolean visible) {
        mContentView.setClipToPadding(visible);
        mContentView.setFitsSystemWindows(visible);
        if (visible) {
            ImmerseStatusBar.setImmerseStatusBar(this);
        }
    }

    /**
     * 设置左边图案
     *
     * @param drawableResult
     */
    public void setLeftImage(int drawableResult) {
        mBar.mLeftImage.setImageResource(drawableResult);
    }

    /**
     * 设置左边按钮背景
     *
     * @param drawableResult
     */
    public void setLeftBackgroud(int drawableResult) {

        mBar.mLeftImage.setBackgroundResource(drawableResult);
    }
    /**
     * 设置左边文字
     *
     * @param text
     */
//    public void setLeftText(String text) {
//        if (mBar.mLeftText != null && text != null) {
//            mBar.mLeftText.setVisibility(View.VISIBLE);
//            mBar.mLeftText.setText(text);
//        }
//    }

    /**
     * 隐藏titlebar
     */
    public void goneBar() {
        mBar.setGone();
    }

    /**
     * 显示titlebar
     */
    public void showBar() {
        mTitleBarView.setVisibility(View.VISIBLE);
        ;
    }

    /**
     * 设置右边文字
     *
     * @param text
     */
    public void setRightBarText(String text) {
        if (text != null) {
            mBar.mRightText.setVisibility(View.VISIBLE);
            mBar.mRightText.setText(text);
        }
        mBar.mRightText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTitleBarItemClicked(v);
            }
        });
    }
    public void setRightBarText(String text, boolean isShow) {
        if (text != null) {

            mBar.mRightText.setVisibility(isShow? View.VISIBLE: View.INVISIBLE);
            mBar.mRightText.setText(text);
        }
        mBar.mRightText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTitleBarItemClicked(v);
            }
        });
    }

    public void setRightBarShow(boolean isShow) {
        mBar.mRightText.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    public void goneRightBar() {
        mBar.mRightText.setVisibility(View.GONE);
    }

    public TextView getMiddleTitleBar() {
        return mBar.mMiddleText;
    }

    public void setRightBarTextClickable(boolean isClicable) {
        mBar.mRightText.setClickable(isClicable);
    }

    /**
     * 设置中间文本
     *
     * @param title
     */
    public void setMiddleTitle(String title) {
        if (mBar.mMiddleText != null) {
            mBar.mMiddleText.setVisibility(View.VISIBLE);
            mBar.mMiddleText.setText(title);
        }

    }
    /**
     * 设置中间文本大小
     *
     * @param size
     */
    public void setMiddleTitleSize(int size) {
        if (mBar.mMiddleText != null) {

            mBar.mMiddleText.setTextSize(size);
            mBar.mMiddleText.setFilters(new InputFilter[] { new InputFilter.LengthFilter(12) });


        }

    }
    /**
     * 设置中间文本颜色
     *
     * @param color
     */
    public void setMiddleTitleColor(int color) {

        mBar.mMiddleText.setTextColor(getResources().getColor(color));


    }

    /**
     * 设置中间标题右侧的图标
     *
     * @param rightDrable
     */
    public void setMiddleTitleRightImage(int rightDrable) {
        Drawable drawable = getResources().getDrawable(rightDrable);
        drawable.setBounds(0, 0, (int) getResources().getDimension(R.dimen.dp20), (int) getResources().getDimension(R.dimen.dp20));
        mBar.mMiddleText.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
        mBar.mMiddleText.setCompoundDrawablePadding(DensityUtil.dip2px(6));
    }

    public void setMiddleTitleRightImageClick(View.OnClickListener listener) {
        mBar.mMiddleText.setOnClickListener(listener);
    }

    /**
     * 设置左侧按钮点击监听
     *
     * @param listener
     */
    public void setLeftImageClick(View.OnClickListener listener) {
        mBar.mLeftImage.setOnClickListener(listener);
    }

    /**
     * 设置tab的背景色
     *
     * @param color
     */
    public void setTabBackgroundClolr(int color) {
        mBar.titleRoot.setBackgroundColor(getResources().getColor(color));
    }

    /**
     * 设置右边图片是否显示
     * @param isShow
     */
    public void setRightDrawableShow(boolean isShow) {
        mBar.mRightDrawable.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    /**
     * 设置右边图片的资源id
     * @param drawableResource
     */
    public void setRightDrawableResource(int drawableResource) {
        mBar.mRightDrawable.setImageResource(drawableResource);
    }

    /**
     * 设置右边图片的资源id以及是否显示
     * @param drawableResource
     * @param isShow
     */
    public void setRightDrawableResource(int drawableResource, boolean isShow) {
        mBar.mRightDrawable.setVisibility(isShow ? View.VISIBLE : View.GONE);
        mBar.mRightDrawable.setImageResource(drawableResource);
//        mBar.mRightDrawable.setBackgroundResource(drawableResource);
    }

    /**
     * 返回标题栏右边的图片
     * @return
     */
    public ImageView getRightDrawable() {
        return mBar.mRightDrawable;
    }

    class TitleBar {

        ImageView mLeftImage;
        TextView mMiddleText;
        TextView mRightText;
        FrameLayout titleRoot;
        private View.OnClickListener mClickListener;
        private final ImageView mRightDrawable;

        public TitleBar() {
            mTitleBarView = getLayoutInflater()
                    .inflate(R.layout.titlebar, null);
            titleRoot = (FrameLayout) mTitleBarView
                    .findViewById(R.id.titleRoot);
            RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                    DensityUtil.dip2px(45));
            titleRoot.setLayoutParams(p);
            mLeftImage = (ImageView) mTitleBarView.findViewById(R.id.left_image);
            mMiddleText = (TextView) mTitleBarView.findViewById(R.id.middle_title);
            mRightText = (TextView) mTitleBarView.findViewById(R.id.right_text);
            mRightDrawable = (ImageView) mTitleBarView.findViewById(R.id.right_drawable);
        }

        public void setListener(View.OnClickListener listener) {
            mClickListener = listener;
            mBar.mLeftImage.setOnClickListener(mClickListener);
            mBar.mRightText.setOnClickListener(mClickListener);
            mBar.mMiddleText.setOnClickListener(mClickListener);
            mBar.mRightDrawable.setOnClickListener(mClickListener);
        }

        public void setGone() {
            mTitleBarView.setVisibility(View.GONE);
        }


    }
}
