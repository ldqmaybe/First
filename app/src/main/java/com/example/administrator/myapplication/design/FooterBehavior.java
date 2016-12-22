package com.example.administrator.myapplication.design;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/10/19 0019.
 */

public class FooterBehavior extends CoordinatorLayout.Behavior<View> {
    public FooterBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof AppBarLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        float scaleY = Math.abs(dependency.getY()) / dependency.getHeight();
        child.setTranslationY(child.getHeight() * scaleY);
        return true;
    }
}
