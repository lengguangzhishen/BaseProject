package com.wumai.baseproject.customview.recycleviewrefresh.itemholder;

import android.view.View;

import com.wumai.baseproject.customview.recycleviewrefresh.base.BaseRecyclerViewAdapter;


/**
 * 使用findViewById()适配器viewHolder
 * Created by admin on 15-11-25.
 */
public abstract class CommonViewHolder extends BaseRecyclerViewAdapter.BaseViewHolder {
    public CommonViewHolder(View itemView) {
        super(itemView);
        findView();
    }

    @Override
    protected void findView() {
        findViews();
    }

    protected abstract void findViews();

    protected <T extends View> T findViewById(int id) {
        return (T) itemView.findViewById(id);
    }
}
