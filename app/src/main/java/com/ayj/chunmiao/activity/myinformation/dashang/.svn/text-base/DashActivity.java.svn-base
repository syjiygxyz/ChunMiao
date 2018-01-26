package com.ayj.chunmiao.activity.myinformation.dashang;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DashActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right_head)
    TextView tvRightHead;
    @BindView(R.id.rl_zengwu)
    RelativeLayout rlZengwu;
    @BindView(R.id.rl_aiyib)
    RelativeLayout rlAiyib;

    @Override
    public int getLayoutId() {
        return R.layout.activity_dash;
    }

    @Override
    public void initDatas() {
        tvTitle.setText("店长打赏");
        tvRightHead.setText("打赏记录");
        tvRightHead.setVisibility(View.VISIBLE);
    }

    @Override
    public void configViews() {

    }

    @OnClick({R.id.iv_back, R.id.tv_right_head, R.id.rl_zengwu, R.id.rl_aiyib})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_right_head:
                startActivity(new Intent(mContext,DasHisActivity.class));
                break;
            case R.id.rl_zengwu:
                startActivity(new Intent(mContext,ZwActivity.class));
                break;
            case R.id.rl_aiyib:
                startActivity(new Intent(mContext,DasEbActivity.class));
                break;
        }
    }
}
