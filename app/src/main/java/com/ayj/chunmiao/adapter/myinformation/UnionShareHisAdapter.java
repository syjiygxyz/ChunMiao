package com.ayj.chunmiao.adapter.myinformation;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.BaseViewHolder;
import com.ayj.chunmiao.bean.myinformation.UnionShareHisBean;

import java.util.List;

/**
 * Created by zht-pc-04 on 2017/9/1.
 */

public class UnionShareHisAdapter extends BaseQuickAdapter<UnionShareHisBean.DataBean,BaseViewHolder> {
    public UnionShareHisAdapter(int layoutResId, List<UnionShareHisBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, UnionShareHisBean.DataBean item) {
        helper.setText(R.id.tv_order_id,"订单号:"+item.getSnid());
        helper.setText(R.id.tv_matidshow,"商品名称:"+item.getMatidshow());
        helper.setText(R.id.tv_order_type,"订单类型:"+item.getOrdertypeshow());
        helper.setText(R.id.tv_aplly_time,"分享时间:"+item.getCreatetime());
        helper.setVisible(R.id.tv_order_status,false);
        helper.setVisible(R.id.tv_total_money,false);
        helper.setVisible(R.id.ll_btn,false);
    }
}
