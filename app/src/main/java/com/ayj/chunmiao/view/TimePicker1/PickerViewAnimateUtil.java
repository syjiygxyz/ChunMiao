package com.ayj.chunmiao.view.TimePicker1;

import android.view.Gravity;

import com.ayj.chunmiao.R;

/**
 * Created by zht-pc-04 on 2016/8/16.
 */
public class PickerViewAnimateUtil {
    private static final int INVALID = -1;

    public static int getAnimationResource(int gravity, boolean isInAnimation)
    {
        switch (gravity)
        {
            case Gravity.BOTTOM:
                return isInAnimation ? R.anim.slide_in_bottom : R.anim.slide_out_bottom;
        }
        return INVALID;
    }
}
