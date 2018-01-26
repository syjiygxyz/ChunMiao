package com.ayj.chunmiao.adapter.cmbz.bx;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.BaseViewHolder;
import com.ayj.chunmiao.bean.cmbz.InsurancePlanBean;

import java.util.List;

/**
 * Created by zht-pc-04 on 2017/7/20.
 */

public class InsuranceDetailAdapter extends BaseQuickAdapter<InsurancePlanBean.DataBean.ContentListBean,BaseViewHolder> {
    public InsuranceDetailAdapter(List<InsurancePlanBean.DataBean.ContentListBean> data) {
        super(R.layout.item_insurance_detail,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, InsurancePlanBean.DataBean.ContentListBean item) {
        helper.setText(R.id.tv_protectcontent,item.getProtectcontent());
        helper.setText(R.id.tv_protectlimit,item.getProtectlimit()+"万元");
    }
}
