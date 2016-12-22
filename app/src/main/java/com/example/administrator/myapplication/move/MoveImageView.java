package com.example.administrator.myapplication.move;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
 * @author Admin
 * @time 2016/11/18 0018.16:00
 */
public class MoveImageView extends ImageView {

    private int lastX = 0;
    private int lastY = 0;

    public MoveImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int x = (int) event.getX();
        int y = (int) event.getY();
        int action = event.getAction();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = x - lastX;
                int offsetY = y - lastY;
                layout(getLeft() + offsetX, getTop() + offsetY, getRight() + offsetX, getBottom() + offsetY);

                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }
}
