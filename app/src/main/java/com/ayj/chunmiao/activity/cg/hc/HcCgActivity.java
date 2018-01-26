package com.ayj.chunmiao.activity.cg.hc;

import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.fragment.cg.XhjFragment;
import com.ayj.chunmiao.utils.Constant;

import butterknife.BindView;
import butterknife.OnClick;

public class HcCgActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.fl)
    FrameLayout fl;
    XhjFragment xhjFragment;

    @Override
    public int getLayoutId() {
        return R.layout.activity_hc_cg;
    }

    @Override
    public void initDatas() {
        tvTitle.setText("耗材");

    }

    @Override
    public void configViews() {
        getSupportFragmentManager().beginTransaction().add(R.id.fl,xhjFragment = XhjFragment.newInstance(Constant.XHJ_HC,null)).commit();
        xhjFragment.setUserVisibleHint(true);
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
