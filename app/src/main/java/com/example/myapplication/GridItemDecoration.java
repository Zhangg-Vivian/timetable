package com.example.myapplication;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class GridItemDecoration extends RecyclerView.ItemDecoration {
    private int distance;

    //传入 dp 间距，自动转 px 适配屏幕
    public GridItemDecoration(Context context, int dpValue) {
        this.distance = (int) (dpValue * context.getResources().getDisplayMetrics().density + 0.5f);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
        //网格列数，七列
        int spanCount = layoutManager.getSpanCount();
        //获取当前项位置
        int pos = parent.getChildAdapterPosition(view);

        //给所有项默认设置右边和下边的间距
        outRect.right = distance;
        outRect.bottom = distance;

        //避免左边距重复
        if (pos % spanCount == 0) {
            outRect.left = distance;
        } else {
            outRect.left = 0;
        }


        //避免上边距重复
        if (pos < spanCount) {
            outRect.top = distance;
        } else {
            outRect.top = 0;
        }

    }
}
