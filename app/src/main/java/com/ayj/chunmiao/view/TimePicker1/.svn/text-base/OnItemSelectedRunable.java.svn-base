package com.ayj.chunmiao.view.TimePicker1;

/**
 * Created by zht-pc-04 on 2016/9/13.
 */
public class OnItemSelectedRunable implements Runnable {
    final WheelView loopView;

    OnItemSelectedRunable(WheelView loopview) {
        loopView = loopview;
    }

    @Override
    public final void run() {
        loopView.onItemSelectedListener.onItemSelected(loopView.getCurrentItem());
    }

}