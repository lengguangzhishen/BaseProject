/*******************************************************************************
 * Copyright 2011, 2012 Chris Banes.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.wumai.baseproject.customview.recycleviewrefresh.pullrefresh.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView.ScaleType;

import com.wumai.baseproject.R;
import com.wumai.baseproject.customview.recycleviewrefresh.pullrefresh.PullToRefreshBase;


public class RotateLoadingLayout extends LoadingLayout {

	static final int ROTATION_ANIMATION_DURATION = 1200;

	private final Animation mRotateAnimation;
	private final Matrix mHeaderImageMatrix;
	private final boolean mRotateDrawableWhilePulling;
	private float mRotationPivotX, mRotationPivotY;

	public RotateLoadingLayout(Context context, PullToRefreshBase.Mode mode, PullToRefreshBase.Orientation scrollDirection, TypedArray attrs) {
		super(context, mode, scrollDirection, attrs);

		mRotateDrawableWhilePulling = attrs.getBoolean(R.styleable.PullToRefresh_ptrRotateDrawableWhilePulling, true);

		mHeaderImage.setScaleType(ScaleType.MATRIX);
		mHeaderImageMatrix = new Matrix();
		mHeaderImage.setImageMatrix(mHeaderImageMatrix);

		mRotateAnimation = new RotateAnimation(0, 720, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		mRotateAnimation.setInterpolator(ANIMATION_INTERPOLATOR);
		mRotateAnimation.setDuration(ROTATION_ANIMATION_DURATION);
		mRotateAnimation.setRepeatCount(Animation.INFINITE);
		mRotateAnimation.setRepeatMode(Animation.RESTART);
	}

	public void onLoadingDrawableSet(Drawable imageDrawable) {
		if (null != imageDrawable) {
			mRotationPivotX = Math.round(imageDrawable.getIntrinsicWidth() / 2f);
			mRotationPivotY = Math.round(imageDrawable.getIntrinsicHeight() / 2f);
		}
	}

	protected void onPullImpl(float scaleOfLayout) {
//		float angle;
//		if (mRotateDrawableWhilePulling) {
//			angle = scaleOfLayout * 90f;
//		} else {
//			angle = Math.max(0f, Math.min(180f, scaleOfLayout * 360f - 180f));
//		}
//        System.out.println("======================");
//		mHeaderImageMatrix.setRotate(angle, mRotationPivotX, mRotationPivotY);
//		mHeaderImage.setImageMatrix(mHeaderImageMatrix);
//		System.out.println("============rotate==onPull");
//		mHeaderImage.setVisibility(View.VISIBLE);
//		mHeaderImage.setImageResource(R.drawable.img_down_arrows);
////		mHeaderText.setText("下拉刷新");
//		mHeaderProgress.setVisibility(View.GONE);

	}

	/**
	 * 正在刷新
	 */
	@Override
	protected void refreshingImpl() {
//		mHeaderImage.startAnimation(mRotateAnimation);
		mHeaderImage.setVisibility(View.GONE);
		mHeaderText.setText("正在加载");
		mHeaderProgress.setVisibility(View.VISIBLE);
		if(!mAnimationDrawable.isRunning()){
			mAnimationDrawable.start();
		}
	}

	/**
	 *  重置刷新
	 */
	@Override
	protected void resetImpl() {
//		mHeaderImage.clearAnimation();
//		resetImageRotation();
		mHeaderImage.setVisibility(View.VISIBLE);
		mHeaderImage.setBackgroundResource(R.mipmap.img_down_arrows);
		mHeaderText.setText("下拉刷新");
		mHeaderProgress.setVisibility(View.GONE);
		if(mAnimationDrawable.isRunning()){
			mAnimationDrawable.stop();
		}
	}

	private void resetImageRotation() {
		if (null != mHeaderImageMatrix) {
			mHeaderImageMatrix.reset();
			mHeaderImage.setImageMatrix(mHeaderImageMatrix);
		}
	}

	/**
	 * 开始下拉刷新
	 */
	@Override
	protected void pullToRefreshImpl() {
		// NO-OP
//		mHeaderImage.setVisibility(View.VISIBLE);
//		mHeaderImage.setBackgroundResource(R.drawable.img_down_arrows);
		mHeaderImage.setVisibility(View.VISIBLE);
		mHeaderImage.setBackgroundResource(R.mipmap.img_down_arrows);
		mHeaderText.setText("下拉刷新");
		mHeaderProgress.setVisibility(View.GONE);
		if(mAnimationDrawable.isRunning()){
			mAnimationDrawable.stop();
		}
	}

	/**
	 * 松开刷新
	 */
	@Override
	protected void releaseToRefreshImpl() {
		// NO-OP
		mHeaderImage.setVisibility(View.VISIBLE);
		mHeaderImage.setBackgroundResource(R.mipmap.img_up_arrows);
		mHeaderText.setText("松开刷新");
		mHeaderProgress.setVisibility(View.GONE);
		if(mAnimationDrawable.isRunning()){
			mAnimationDrawable.stop();
		}
	}

	@Override
	protected int getDefaultDrawableResId() {
		return R.drawable.refresh_frame_anim;
	}

}
