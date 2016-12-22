package com.cn.baselib.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 自定义Recycleview 分割线
 *
 * @author Admin
 * @time 2016/12/8 0008.14:57
 */
public class CustomDecoration extends RecyclerView.ItemDecoration {
    private int mydevider;
    private Paint dividerPaint;

    public CustomDecoration(Context context, int colorAccent, int divider_bottom) {
        dividerPaint = new Paint();
        //设置分割线颜色
        dividerPaint.setColor(context.getResources().getColor(colorAccent));
        //设置分割线宽度
        mydevider = context.getResources().getDimensionPixelSize(divider_bottom);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom = mydevider;
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        int childCount = parent.getChildCount();
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        for (int i = 0; i < childCount - 1; i++) {
            View view = parent.getChildAt(i);
            float top = view.getBottom();
            float bottom = view.getBottom() + mydevider;
            c.drawRect(left, top, right, bottom, dividerPaint);
        }
    }

}
