package com.ayj.chunmiao.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.utils.AliPay.PayResult;
import com.ayj.chunmiao.utils.ValidationUtils;


/*模板*/
public class DemoActivity extends BaseActivity {





    @Override
    public int getLayoutId() {
        return R.layout.activity_demo;
    }

    @Override
    public void initDatas() {
    }

    @Override
    public void configViews() {

    }
}
