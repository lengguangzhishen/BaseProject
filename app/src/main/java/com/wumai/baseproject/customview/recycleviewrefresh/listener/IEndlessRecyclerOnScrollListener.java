package com.wumai.baseproject.customview.recycleviewrefresh.listener;

/*
 * Copyright (C) 2015 Jorge Castillo Pérez
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
 *
 * modify from https://github.com/JorgeCastilloPrz/Mirage
 */


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * 上拉加载更多实现OnScrollListener接口
 */
public abstract class IEndlessRecyclerOnScrollListener extends RecyclerView.OnScrollListener {
    public static String TAG = IEndlessRecyclerOnScrollListener.class.getSimpleName();
    // The minimum amount of items to have below your current scroll position before loading more.
    int firstVisibleItem, visibleItemCount, totalItemCount;
    private int previousTotal = 0; // The total number of items in the dataset after the last load
    private boolean loading = false;
    //list到达 最后一个item的时候 触发加载
    private int visibleThreshold = 1;
    //默认第一页
    private int current_page = 0;

    private LinearLayoutManager mLinearLayoutManager;
    private int lastCompletelyVisibleItemPosition;
    private int lastVisibleItemPosition;


    public IEndlessRecyclerOnScrollListener(LinearLayoutManager linearLayoutManager) {
        this.mLinearLayoutManager = linearLayoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = mLinearLayoutManager.getItemCount();
        firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();
        lastCompletelyVisibleItemPosition = mLinearLayoutManager.findLastCompletelyVisibleItemPosition();
        lastVisibleItemPosition = mLinearLayoutManager.findLastVisibleItemPosition();

        //判断加载完成了...
        if (loading) {
            if (totalItemCount > previousTotal) {
//                loading = false;
                previousTotal = totalItemCount;
            }
        }
        // // TODO: 15/10/7 可能把 Toolbar 的高度也算上了
        //totalItemCount > visibleItemCount 超过一个页面才有加载更多
//        if (loading && totalItemCount > visibleItemCount && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
        if (loading && lastCompletelyVisibleItemPosition == (totalItemCount - 1)) {
            // End has been reached

            // Do something
            current_page++;


            onLoadMore(current_page);

//            loading = true;
        }
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);

        switch (newState) {
            case RecyclerView.SCROLL_STATE_IDLE:
                //Const.IMAGE_LOADER.resume();
                break;
            case RecyclerView.SCROLL_STATE_DRAGGING:
               // Const.IMAGE_LOADER.pause();
                break;

            case RecyclerView.SCROLL_STATE_SETTLING:
                //Const.IMAGE_LOADER.pause();
                break;

            default:
                break;
        }
    }

    public int getVisibleThreshold() {
        return visibleThreshold;
    }

    public void setVisibleThreshold(int visibleThreshold) {
        this.visibleThreshold = visibleThreshold;
    }

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public abstract void onLoadMore(int current_page);

    public boolean isLoading() {
        return loading;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
        previousTotal = 0;
        current_page = 1;
        visibleThreshold = 1;
        firstVisibleItem = 0;
        visibleItemCount = 0;
        totalItemCount = 0;
    }


    public LinearLayoutManager getmLinearLayoutManager() {
        return mLinearLayoutManager;
    }

    public void setmLinearLayoutManager(LinearLayoutManager mLinearLayoutManager) {
        this.mLinearLayoutManager = mLinearLayoutManager;
    }
}
