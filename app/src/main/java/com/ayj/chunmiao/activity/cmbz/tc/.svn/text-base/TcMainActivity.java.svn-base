package com.ayj.chunmiao.activity.cmbz.tc;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.utils.CommonUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
* 套餐主界面
* */
public class TcMainActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rlv_tc)
    RecyclerView rlvTc;

    @Override
    public int getLayoutId() {
        return R.layout.activity_tc_main;
    }

    @Override
    public void initDatas() {
        tvTitle.setText("套餐购买");
        rlvTc.setLayoutManager(new GridLayoutManager(mContext, 2));
    }

    @Override
    public void configViews() {
        CommonUtils.getTc(mContext,rlvTc,"健康包购买",R.mipmap.yhqqqq);
    }

    @OnClick(R.id.iv_back)
    public void onClick() {
        finish();
    }
}
