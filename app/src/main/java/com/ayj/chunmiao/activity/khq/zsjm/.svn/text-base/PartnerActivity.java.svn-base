package com.ayj.chunmiao.activity.khq.zsjm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.bean.UtilityItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PartnerActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_agent)
    RelativeLayout rlAgent;
    @BindView(R.id.rl_joinus)
    RelativeLayout rlJoinus;

    List<UtilityItem> itemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner);
        ButterKnife.bind(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_partner;
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void configViews() {

    }

    @OnClick({R.id.iv_back, R.id.rl_agent, R.id.rl_joinus})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            /*我要代理*/
            case R.id.rl_agent:
                startActivity(new Intent(mContext,DaiLiActivity.class));
                break;
            /*我要加盟*/
            case R.id.rl_joinus:
                startActivity(new Intent(mContext,JiaMengActivity.class));
                break;
        }
    }


}
