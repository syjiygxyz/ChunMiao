package com.ayj.chunmiao.activity.myinformation.dashang;

import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.bean.myinformation.AwardListBean;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.OnClick;

public class DaswDetailActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_snid)
    TextView tvSnid;
    @BindView(R.id.tv_online)
    TextView tvOnline;
    @BindView(R.id.iv_lxsc_img)
    ImageView ivLxscImg;
    @BindView(R.id.tv_lxsc_content)
    TextView tvLxscContent;
    @BindView(R.id.tv_standard)
    TextView tvStandard;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.tv_lxsc_data)
    TextView tvLxscData;

    AwardListBean.DataBean info;

    @Override
    public int getLayoutId() {
        return R.layout.activity_dasw_detail;
    }

    @Override
    public void initDatas() {
        tvTitle.setText("记录详情");
        info =(AwardListBean.DataBean) getIntent().getSerializableExtra("info");
        tvSnid.setText("编号:"+info.getSnid());
        tvLxscContent.setText(info.getAwardticketmatidshow());
        tvStandard.setText("规格:"+info.getStandard());
        tvNum.setText("数量:"+info.getAwardnum());
        tvOnline.setText(info.getAwardticketonlinetypeshow());
        tvLxscData.setText("有效期:"+info.getBtime()+"-"+info.getEtime());
        if (null!=info.getImgurlcompressshow() && !TextUtils.isEmpty(info.getImgurlcompressshow())){
            Picasso.with(mContext).load(info.getImgurlcompressshow()).error(R.mipmap.jiazaishibia)
                    .placeholder(R.mipmap.zhanweitu).into(ivLxscImg);
        }else {
            ivLxscImg.setImageResource(R.mipmap.jiazaishibia);
        }
    }

    @Override
    public void configViews() {

    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
