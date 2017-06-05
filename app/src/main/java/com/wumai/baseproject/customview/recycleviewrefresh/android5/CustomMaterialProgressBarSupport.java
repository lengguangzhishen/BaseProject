package com.wumai.baseproject.customview.recycleviewrefresh.android5;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.wumai.baseproject.R;


/**
 * Created on 15/11/9
 */
public class CustomMaterialProgressBarSupport extends ViewGroup {
    // Default background for the progress spinner
    private static final int CIRCLE_BG_LIGHT = 0xFFFAFAFA;
    private static final int CIRCLE_LIGHT = Color.BLACK;
    // Default offset in dips from the top of the view to where the progress spinner should stop
    private static final int DEFAULT_CIRCLE_TARGET = 64;
    private static final int CIRCLE_DIAMETER = 40;
    private static final int CIRCLE_DIAMETER_LARGE = 56;
    private static final int MAX_ALPHA = 255;
    private MaterialProgressDrawableSupport mProgress;
    //    private CircleImageViewSupport mCircleView;
    private ImageView mCircleView;
    private AnimationDrawable mAnimationDrawable;


    public CustomMaterialProgressBarSupport(Context context) {
        this(context, null);
    }

    public CustomMaterialProgressBarSupport(Context context, AttributeSet attrs) {
        super(context, attrs);
        createProgressView(context, attrs);
    }


    private void createProgressView(Context context, AttributeSet attrs) {
        int progressSize = CIRCLE_DIAMETER;
        int bgColor = CIRCLE_BG_LIGHT;
        int progressColor = CIRCLE_LIGHT;

//        final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.MaterialProgressBarSupport);
//        if (a.hasValue(R.styleable.MaterialProgressBarSupport_progressSmallSize)) {
//            boolean isSmallSize = a.getBoolean(R.styleable.MaterialProgressBarSupport_progressSmallSize, true);
//            progressSize = isSmallSize ? CIRCLE_DIAMETER : CIRCLE_DIAMETER_LARGE;
//        }
//        if (a.hasValue(R.styleable.MaterialProgressBarSupport_progressBackgroundColor)) {
//            bgColor = a.getColor(R.styleable.MaterialProgressBarSupport_progressBackgroundColor, CIRCLE_BG_LIGHT);
//        }
//
//        if (a.hasValue(R.styleable.MaterialProgressBarSupport_progressColor)) {
//            progressColor = a.getColor(R.styleable.MaterialProgressBarSupport_progressColor, CIRCLE_LIGHT);
//        }
        //TypedArray使用完回收
//        a.recycle();

//        mCircleView = new CircleImageViewSupport(getContext(), bgColor, progressSize / 2);
//        mProgress = new MaterialProgressDrawableSupport(getContext(), this);
//        mProgress.setColorSchemeColors(progressColor);
//        mCircleView.setImageDrawable(mProgress);
        mCircleView = new ImageView(getContext());
//        int height = UIUtils.dip2px(getContext(), 12);
//        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(height, height);
//        mCircleView.setLayoutParams(lp);
        mCircleView.setBackgroundResource(R.drawable.load_more_amin);
        mAnimationDrawable = (AnimationDrawable) mCircleView.getBackground();
        addView(mCircleView);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        mCircleView.layout(l, t, r, b);
    }


    public void startProgress() {
        mCircleView.setVisibility(View.VISIBLE);
        mProgress.setAlpha(MAX_ALPHA);
        mProgress.start();
    }

    public void stopProgress() {
        mCircleView.setVisibility(View.GONE);
        mProgress.stop();
    }

    public void startAnim() {
        if (mAnimationDrawable != null) {
            if (!mAnimationDrawable.isRunning()) {
                mAnimationDrawable.start();
            }
        }
    }

    public void stopAnim() {
        if (mAnimationDrawable != null) {
            if (mAnimationDrawable.isRunning()) {
                mAnimationDrawable.stop();
            }
        }
    }
}
