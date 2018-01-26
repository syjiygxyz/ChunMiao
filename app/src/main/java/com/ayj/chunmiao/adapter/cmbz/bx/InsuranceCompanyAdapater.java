package com.ayj.chunmiao.adapter.cmbz.bx;

import android.widget.ImageView;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.BaseViewHolder;
import com.ayj.chunmiao.bean.cmbz.InsuranceBrandBean;
import com.ayj.chunmiao.bean.cmbz.InsuranceCompanyBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by zht-pc-04 on 2017/7/18.
 */

public class InsuranceCompanyAdapater extends BaseQuickAdapter<InsuranceBrandBean.DataBean,BaseViewHolder> {
    public InsuranceCompanyAdapater(List<InsuranceBrandBean.DataBean> data) {
        super(R.layout.item_insurance_company, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, InsuranceBrandBean.DataBean item) {
        //helper.setText(R.id.tv_company_name,item.getInsurancebrandshow());
        if (item.getInsurancebrand().equals("INSURANCEBRAND002")){
            helper.setImageResource(R.id.iv_company_logo,R.mipmap.insurance_picc);
        }else if (item.getInsurancebrand().equals("INSURANCEBRAND003")){
            helper.setImageResource(R.id.iv_company_logo,R.mipmap.insurance_cpic);
        }else if (item.getInsurancebrand().equals("INSURANCEBRAND005")){
            helper.setImageResource(R.id.iv_company_logo,R.mipmap.insurance_yongan);
        }else if (item.getInsurancebrand().equals("INSURANCEBRAND006")){
            helper.setImageResource(R.id.iv_company_logo,R.mipmap.insurance_tianan);
        }else if (item.getInsurancebrand().equals("INSURANCEBRAND001")){
            helper.setImageResource(R.id.iv_company_logo,R.mipmap.insurance_tianan);
        }else if (item.getInsurancebrand().equals("INSURANCEBRAND004")){
            helper.setImageResource(R.id.iv_company_logo,R.mipmap.insurance_tianan);
        }
    }
}
