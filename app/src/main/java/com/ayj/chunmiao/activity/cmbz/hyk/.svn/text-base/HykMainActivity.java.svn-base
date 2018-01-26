package com.ayj.chunmiao.activity.cmbz.hyk;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.listener.OnItemClickListener;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.Constant;

import butterknife.BindView;
import butterknife.OnClick;

/*会员卡*/
public class HykMainActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rlv_a_card)
    RecyclerView rlvACard;
    @BindView(R.id.rlv_b_card)
    RecyclerView rlvBCard;

    @Override
    public int getLayoutId() {
        return R.layout.activity_hyk_main;
    }

    @Override
    public void initDatas() {
        tvTitle.setText("会员卡");
        rlvACard.setLayoutManager(new GridLayoutManager(mContext, 3));
        rlvBCard.setLayoutManager(new GridLayoutManager(mContext, 3));
    }

    @Override
    public void configViews() {
        CommonUtils.getCard(mContext,Constant.CARD_A_KL,rlvACard,"康乐会员A卡",R.mipmap.hyk_zs);
        CommonUtils.getCard(mContext,Constant.CARD_B_KL,rlvBCard,"康乐会员B卡",R.mipmap.hyk_zs);

    }

    @OnClick(R.id.iv_back)
    public void onClick() {
        finish();
    }
}
