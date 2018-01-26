package com.ayj.chunmiao.view.TimePicker1;

import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Created by zht-pc-04 on 2016/9/13.
 */
public class LoopViewGestureListener extends GestureDetector.SimpleOnGestureListener {
    final WheelView loopView;

    LoopViewGestureListener(WheelView loopview) {
        loopView = loopview;
    }

    @Override
    public final boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        loopView.scrollBy(velocityY);
        return true;
    }
}
