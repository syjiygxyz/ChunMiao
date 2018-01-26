package com.ayj.chunmiao.adapter.myinformation;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.BaseViewHolder;
import com.ayj.chunmiao.bean.myinformation.DepositBean;

import java.util.List;

/**
 * Created by zht-pc-04 on 2017/8/14.
 */

public class BzjAdapter extends BaseQuickAdapter<DepositBean.DataBean,BaseViewHolder> {
    public BzjAdapter(int layoutResId, List<DepositBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DepositBean.DataBean item) {
        helper.setText(R.id.tv_name,item.getMatname());
        helper.setText(R.id.tv_date,item.getMtime());
        helper.setText(R.id.tv_state,item.getDeposit());
    }
}
