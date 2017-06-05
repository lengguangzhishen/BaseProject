package com.wumai.baseproject.customview.recycleviewrefresh.recycleview;

import android.content.Context;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;

import com.wumai.baseproject.customview.recycleviewrefresh.recycleviewstyle.HorizontalDividerItemDecoration;
import com.wumai.baseproject.customview.recycleviewrefresh.recycleviewstyle.VerticalDividerItemDecoration;


/**
 * 给recyclerview设置分割线管理类
 * Created by caishengyan on 2016/1/7.
 */
public class RecyclerViewManager {
    /**
     * 设置水平分割线
     *
     * @param context
     * @param recyclerView
     * @param colorId
     * @param strokeSize
     */
    public static void setHorizontalDivider(Context context, RecyclerView recyclerView, int colorId, float strokeSize) {
        Paint paint = new Paint();
        paint.setStrokeWidth(strokeSize);
        paint.setColor(context.getResources().getColor(colorId));
        paint.setAntiAlias(true);
        paint.setPathEffect(new DashPathEffect(new float[]{25.0f, 25.0f}, 0));
        HorizontalDividerItemDecoration mHorizontalDividerItemDecoration = new HorizontalDividerItemDecoration.Builder(context).paint(paint).showLastDivider().build();
        recyclerView.addItemDecoration(mHorizontalDividerItemDecoration);
        recyclerView.invalidateItemDecorations();
    }

    /**
     * 设置垂直分割线
     *
     * @param context
     * @param recyclerView
     * @param colorId
     * @param strokeSize
     */
    public static void setVerticalDivider(Context context, RecyclerView recyclerView, int colorId, float strokeSize) {
        Paint paint = new Paint();
        paint.setStrokeWidth(strokeSize);
        paint.setColor(context.getResources().getColor(colorId));
        paint.setAntiAlias(true);
        paint.setPathEffect(new DashPathEffect(new float[]{25.0f, 25.0f}, 0));
        VerticalDividerItemDecoration mHorizontalDividerItemDecoration = new VerticalDividerItemDecoration.Builder(context).paint(paint).showLastDivider().build();
        recyclerView.addItemDecoration(mHorizontalDividerItemDecoration);
        recyclerView.invalidateItemDecorations();
    }
}
