package com.wumai.baseproject.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

/**
 * Created by litengfei on 16/4/17.
 */
public class CustomScrollView extends ScrollView {

    private float xDistance, yDistance, xLast, yLast;

    public CustomScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomScrollView(Context context) {
        super(context);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xDistance = yDistance = 0f;
                xLast = ev.getX();
                yLast = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                final float curX = ev.getX();
                final float curY = ev.getY();
                xDistance += Math.abs(curX - xLast);
                yDistance += Math.abs(curY - yLast);
                xLast = curX;
                yLast = curY;
                if (xDistance > yDistance) {
                    return false;
                }
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt){
//        if(t + getHeight() >=  computeVerticalScrollRange()){
//            //ScrollView滑动到底部了
//            if (scrollBottomListener != null) {
//                scrollBottomListener.scrollBottom();
//            }
//        }
            View view = (View)getChildAt(getChildCount()-1);
            int d = view.getBottom();
            d -= (getHeight()+getScrollY());
            if(d==0) {
                if (scrollBottomListener != null) {
                scrollBottomListener.scrollBottom();
            }
            } else {
                super.onScrollChanged(l,t,oldl,oldt);
            }
    }

    private OnScrollBottomListener scrollBottomListener;
    public void setOnScrollBottomListener(OnScrollBottomListener scrollBottomListener){
        this.scrollBottomListener = scrollBottomListener;
    }

    public interface OnScrollBottomListener{
        void scrollBottom();
    }


}

