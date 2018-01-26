package com.ayj.chunmiao.activity.cmbz.insurance.common;

import android.content.Intent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.bean.bx.CommonBxListBean;

import butterknife.BindView;
import butterknife.OnClick;

/*
*下单第三个界面
* */
public class CommonBxPostDetailActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_icsnidshow)
    TextView tv_icsnidshow;
    @BindView(R.id.tv_issnidshow)
    TextView tv_issnidshow;
    @BindView(R.id.tv_price)
    TextView tv_price;
    @BindView(R.id.tv_tbr_name)
    TextView tv_tbr_name;
    @BindView(R.id.tv_tbr_sex)
    TextView tv_tbr_sex;
    @BindView(R.id.tv_tbr_idcard)
    TextView tv_tbr_idcard;
    @BindView(R.id.tv_tbr_email)
    TextView tv_tbr_email;
    @BindView(R.id.tv_tbr_phonenum)
    TextView tv_tbr_phonenum;
    @BindView(R.id.tv_tbr_birth)
    TextView tv_tbr_birth;
    @BindView(R.id.tv_relationship)
    TextView tv_relationship;
    @BindView(R.id.tv_bbr_name)
    TextView tv_bbr_name;
    @BindView(R.id.tv_bbr_idcard)
    TextView tv_bbr_idcard;
    @BindView(R.id.tv_btime)
    TextView tv_btime;
    @BindView(R.id.tv_etime)
    TextView tv_etime;
    @BindView(R.id.tv_bbr_sex)
    TextView tv_bbr_sex;
    @BindView(R.id.tv_bbr_birth)
    TextView tv_bbr_birth;
    @BindView(R.id.cb_1)
    CheckBox cb_1;
    @BindView(R.id.cb_2)
    CheckBox cb_2;
    @BindView(R.id.tv_ensure)
    TextView tvEnsure;

    CommonBxListBean.DataBean list;
    @Override
    public int getLayoutId() {
        return R.layout.activity_common_bx_post_detail;
    }

    @Override
    public void initDatas() {
        tvTitle.setText("投保信息");
        list = (CommonBxListBean.DataBean) getIntent().getSerializableExtra("list");
        tv_icsnidshow.setText(list.getMatidshow());
        tv_issnidshow.setText(getIntent().getStringExtra("ms"));
        tv_price.setText("¥"+getIntent().getStringExtra("money"));
        tv_tbr_name.setText(getIntent().getStringExtra("tname"));
        tv_tbr_sex.setText(getIntent().getStringExtra("tsex"));
        tv_tbr_idcard.setText(getIntent().getStringExtra("tsfz"));
        tv_tbr_email.setText(getIntent().getStringExtra("tyx"));
        tv_tbr_phonenum.setText(getIntent().getStringExtra("tlx"));
        tv_tbr_birth.setText(getIntent().getStringExtra("tbirth"));
        tv_bbr_name.setText(getIntent().getStringExtra("bname"));
        tv_bbr_idcard.setText(getIntent().getStringExtra("bsfz"));
        tv_relationship.setText(getIntent().getStringExtra("bgx"));
        tv_btime.setText(getIntent().getStringExtra("btime"));
        tv_etime.setText(getIntent().getStringExtra("etime"));
        tv_bbr_sex.setText(getIntent().getStringExtra("bsex"));
        tv_bbr_birth.setText(getIntent().getStringExtra("bbirth"));
    }

    @Override
    public void configViews() {

    }
    @OnClick({R.id.iv_back, R.id.tv_ensure})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_ensure:
                if(cb_1.isChecked()&&cb_2.isChecked()){
                    Intent intent=new Intent(mContext,CommonZfActivity.class);
                    intent.putExtra("date",getIntent().getStringExtra("date"));
                    intent.putExtra("name",list.getMatidshow());
                    intent.putExtra("money",getIntent().getStringExtra("money"));
                    startActivity(intent);
                }else{
                    showToast("请遵守协议");
                }
                break;
        }
    }
}
