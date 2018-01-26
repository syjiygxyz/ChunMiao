package com.ayj.chunmiao.activity.khq;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.activity.base.NewsActivity;
import com.ayj.chunmiao.activity.cmbz.hyk.HykZfActivity;
import com.ayj.chunmiao.bean.MemberCard;
import com.ayj.chunmiao.utils.WebUtils;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
* 富人水details
* */
public class FrsDetailsActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_water_name)
    TextView tvWaterName;
    @BindView(R.id.tv_water_time)
    TextView tvWaterTime;
    @BindView(R.id.tv_water_price)
    TextView tvWaterPrice;
    @BindView(R.id.relativeLayout_water)
    RelativeLayout relativeLayoutWater;
    @BindView(R.id.tv_package_name)
    TextView tvPackageName;
    @BindView(R.id.tv_water_detail)
    TextView tvWaterDetail;
    @BindView(R.id.tv_water_time2)
    TextView tvWaterTime2;
    @BindView(R.id.btn_buy_water)
    Button btnBuyWater;

    private MemberCard.DataBean mMemberCard;

    public static void jumpActivity(Context context, MemberCard.DataBean item) {
        Intent intent = new Intent(context, FrsDetailsActivity.class);
        intent.putExtra("water", item);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_frs_details;
    }

    @Override
    public void initDatas() {
        tvTitle.setText("富人水购买");
    }

    @Override
    public void configViews() {
        mMemberCard = (MemberCard.DataBean) getIntent().getSerializableExtra("water");
        tvWaterName.setText(mMemberCard.getMatidshow());
        tvWaterTime.setText("有效期：" + mMemberCard.getValiddays() + "天");
        tvWaterTime2.setText("②有效期" + mMemberCard.getValiddays() + "天");
        tvWaterPrice.setText("¥" + mMemberCard.getNowprice());
    }

    @OnClick({R.id.iv_back, R.id.tv_water_detail, R.id.btn_buy_water})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_water_detail:
                String h5URL = WebUtils.H5_DETAIL + "imgtype3=COMMONIMAGETYPETH005";
                String title = "富人水调理";
                NewsActivity.jumpActivity(mContext, h5URL, title,"1","");
                break;
            case R.id.btn_buy_water:
                /*购买*/
                HykZfActivity.jumpActivity(mContext,mMemberCard.getMatidshow() ,mMemberCard.getNowprice(),
                        mMemberCard.getMatid(),"MEMBERORDERTYPE011","1","", "", "");
                break;
        }
    }
}
