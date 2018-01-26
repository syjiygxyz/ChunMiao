package com.ayj.chunmiao.activity.cmbz.insurance.myBx;

import android.widget.ImageView;
import android.widget.TextView;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.OnClick;

public class BxBdShowActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.splash_img)
    ImageView splashImg;

    @Override
    public int getLayoutId() {
        return R.layout.activity_bx_bd_show;
    }

    @Override
    public void initDatas() {
        tvTitle.setText("保单详情");
    }

    @Override
    public void configViews() {
        if(!"".equals(getIntent().getStringExtra("url"))) {
            Picasso.with(mContext).load(getIntent().getStringExtra("url")).into(splashImg);
        }
    }

    @OnClick(R.id.iv_back)
    public void onClick() {
        finish();
    }
}
