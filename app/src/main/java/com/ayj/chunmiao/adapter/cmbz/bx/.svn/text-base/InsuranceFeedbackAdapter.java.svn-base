package com.ayj.chunmiao.adapter.cmbz.bx;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.BaseViewHolder;
import com.ayj.chunmiao.bean.cmbz.InsurancePriceBean;

import java.util.List;

/**
 * Created by zht-pc-04 on 2017/7/13.
 */

public class InsuranceFeedbackAdapter extends BaseQuickAdapter<InsurancePriceBean.DataBean,BaseViewHolder> {


    public InsuranceFeedbackAdapter(List<InsurancePriceBean.DataBean> data){
        super(R.layout.item_insurance_feedback,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, InsurancePriceBean.DataBean item) {
        helper.setText(R.id.tv_company_name,item.getOrderDetailList().get(0).getInsurancebrandshow());
        helper.setText(R.id.tv_username,item.getTbrname());
        helper.setText(R.id.tv_time,item.getCreatedate());
        helper.setText(R.id.tv_state,item.getStatusshow());
        if(item.getOrderDetailList().get(0).getInsurancebrandshow().equals("中国人保")){
            helper.setImageResource(R.id.iv_company_logo,R.mipmap.bjxt_picc);
        }else if(item.getOrderDetailList().get(0).getInsurancebrandshow().equals("永安保险")){
            helper.setImageResource(R.id.iv_company_logo,R.mipmap.bjxt_yongan);
        }else if(item.getOrderDetailList().get(0).getInsurancebrandshow().equals("太平洋保险")){
            helper.setImageResource(R.id.iv_company_logo,R.mipmap.bjxt_cpic);
        }else if(item.getOrderDetailList().get(0).getInsurancebrandshow().equals("天安保险")){
            helper.setImageResource(R.id.iv_company_logo,R.mipmap.bjxt_tian);
        }
    }
}
