package com.ayj.chunmiao.activity.cmbz.insurance.myBx;

import android.content.Intent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.bean.bx.BxBdxqBean;

import butterknife.BindView;
import butterknife.OnClick;

public class MyBxDetailActivity extends BaseActivity {


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

    BxBdxqBean.DataBean item;

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_bx_detail;
    }

    @Override
    public void initDatas() {
        tvTitle.setText("投保信息");
        item = (BxBdxqBean.DataBean) getIntent().getSerializableExtra("list");
        tv_icsnidshow.setText(item.getIcsnidshow());
        tv_issnidshow.setText(item.getIssnidshow());
        tv_price.setText(null==item.getYjmoneytotal()?"0.00":item.getYjmoneytotal()+"");
        tv_tbr_name.setText(item.getTbrname());
        tv_tbr_sex.setText(item.getTbrsexshow());
        tv_tbr_idcard.setText(item.getTbrcardno());
        tv_tbr_email.setText(null==item.getTbremail()?"":item.getTbremail()+"");
        tv_tbr_phonenum.setText(null==item.getTbrmobile()?"":item.getTbrmobile()+"");
        tv_tbr_birth.setText(null==item.getTbrbirthday()?"":item.getTbrbirthday()+"");
        tv_bbr_name.setText(null==item.getBbrname()?"":item.getBbrname()+"");
        tv_bbr_idcard.setText(null==item.getBbrcardno()?"":item.getBbrcardno()+"");
        tv_relationship.setText(null==item.getRelationshipshow()?"":item.getRelationshipshow()+"");
        tv_btime.setText(null==item.getBtime()?"":item.getBtime()+"");
        tv_etime.setText(null==item.getEtime()?"":item.getEtime()+"");
        tv_bbr_sex.setText(null==item.getBbrsexshow()?"":item.getBbrsexshow()+"");
        tv_bbr_birth.setText(null==item.getBbrbirthday()?"":item.getBbrbirthday()+"");
        if("INSORDERSTATUS003".equals(item.getStatus())){
            tvEnsure.setVisibility(View.VISIBLE);
            tvEnsure.setText("立即支付");
        } else if("INSORDERSTATUS007".equals(item.getStatus())){
            tvEnsure.setVisibility(View.VISIBLE);
            tvEnsure.setText("查看保单");
        } else {
            tvEnsure.setVisibility(View.GONE);
        }
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
                    if("INSORDERSTATUS003".equals(item.getStatus())){
                   /*立即支付*/
                        Intent intent = new Intent();
                        intent.putExtra("list",item);
                        intent.setClass(mContext, BxZfActivity.class);
                        startActivity(intent);
                    } else if("INSORDERSTATUS007".equals(item.getStatus())){
                   /*查看表单*/
                        Intent intent = new Intent();
                        intent.putExtra("url",item.getIssuingattachmenturlshow());
                        intent.setClass(mContext, BxBdShowActivity.class);
                        startActivity(intent);
                    }
                }else{
                    showToast("请遵守协议");
                }
                break;
        }
    }


}
