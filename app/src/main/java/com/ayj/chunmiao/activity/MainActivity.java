package com.ayj.chunmiao.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.bean.eventbus.FirstEvent;
import com.ayj.chunmiao.fragment.MainFragment;
import com.ayj.chunmiao.fragment.MyYgFragment;
import com.ayj.chunmiao.fragment.SbMangerFragment;
import com.ayj.chunmiao.utils.UpDateUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.content)
    FrameLayout mContent;
    @BindView(R.id.rbHome)
    RadioButton mRbHome;
    @BindView(R.id.rbShop)
    RadioButton mRbShop;
    @BindView(R.id.rbMessage)
    RadioButton mRbMessage;
    @BindView(R.id.rgTools)
    RadioGroup mRgTools;

    private Fragment[] mFragments;

    private int mIndex;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initDatas() {
        /*在线更新*/
        UpDateUtils update = new UpDateUtils(this, false);
        update.update(true);
        initFragment();
    }

    @Override
    public void configViews() {

    }



    @OnClick({R.id.rbHome, R.id.rbShop, R.id.rbMessage})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rbHome:
                setIndexSelected(0);
                break;
            case R.id.rbShop:
                setIndexSelected(1);
                break;
            case R.id.rbMessage:
                setIndexSelected(2);
                break;
        }
    }

    private void initFragment() {
        //主页
        MainFragment mainFragment = new MainFragment();
        //设备管理
        SbMangerFragment sbMangerFragment = new SbMangerFragment();
        MyYgFragment myYgFragment = new MyYgFragment();
        //添加到数组
        mFragments = new Fragment[]{mainFragment, sbMangerFragment, myYgFragment};
        //开启事务
        FragmentTransaction ft =
                getSupportFragmentManager().beginTransaction();
        //添加首页
        ft.add(R.id.content, mainFragment).commit();
        //默认设置为第0个
        setIndexSelected(0);
    }

    private void setIndexSelected(int index) {
        if (mIndex == index) {
            return;
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        //隐藏
        ft.hide(mFragments[mIndex]);
        //判断是否添加
        if (!mFragments[index].isAdded()) {
            ft.add(R.id.content, mFragments[index]).show(mFragments[index]);
        } else {
            ft.show(mFragments[index]);
        }
        ft.commit();
        //再次赋值
        mIndex = index;
    }




}