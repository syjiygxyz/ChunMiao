package com.ayj.chunmiao.activity;

import android.content.Intent;

import com.ayj.chunmiao.AyjSwApplication;
import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.bean.UserBean;
import com.ayj.chunmiao.utils.ACache;

/*
* 过场动画
* */
public class SplashActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initDatas() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (null!=aCache.getAsObject(ACache.USER_INFO_KEY)) {
                    AyjSwApplication.getsInstance().setUserInfo(
                            (UserBean) aCache.getAsObject(ACache.USER_INFO_KEY));
                    Intent intent = new Intent(mContext, MainActivity.class);
                    startActivity(intent);
                    finish();
                    } else {
                        Intent intent = new Intent(mContext,
                                LoginActivity.class);
                        startActivity(intent);
                        finish();
                }
            }
        }).start();
    }

    @Override
    public void configViews() {

    }
}
