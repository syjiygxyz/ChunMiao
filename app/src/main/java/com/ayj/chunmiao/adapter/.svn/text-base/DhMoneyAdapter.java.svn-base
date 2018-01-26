package com.ayj.chunmiao.adapter;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.BaseViewHolder;
import com.ayj.chunmiao.bean.DhMoneyBean;

import java.util.List;

/**
 * Created by Administrator on 2017/9/7.
 */
public class DhMoneyAdapter extends BaseQuickAdapter<DhMoneyBean.DataBean,BaseViewHolder> {

    public DhMoneyAdapter(List<DhMoneyBean.DataBean> data) {
        super(R.layout.dh_money_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DhMoneyBean.DataBean item) {
        helper.setImageResource(R.id.iv_foot_xd,R.mipmap.dh_money);
        helper.setText(R.id.tv_foot_xd_name,"会员钱包");
        helper.setText(R.id.tv_foot_xd_price, item.getParamname());
        helper.setText(R.id.tv_foot_xd_count,"¥"+item.getParamremark());
    }
}
