package com.ayj.chunmiao.adapter.myinformation;

import android.view.View;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.BaseViewHolder;
import com.ayj.chunmiao.bean.myinformation.ShopStockBean;
import com.ayj.chunmiao.utils.Constant;

import java.util.List;

/**
 * Created by zht-pc-04 on 2017/8/28.
 */

public class ShopStockAdapter extends BaseQuickAdapter<ShopStockBean.DataBean, BaseViewHolder>  {

    OnItemClickListener onItemClickListener;

    public ShopStockAdapter(int layoutResId, List<ShopStockBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, ShopStockBean.DataBean item) {

        helper.setText(R.id.tv_order_id, "订单号:" + item.getSnid());
        helper.setText(R.id.tv_aplly_time, "下单时间:" + item.getCreatedate());
        helper.setText(R.id.tv_order_type, "订单类型:【" + item.getOrdertypeshow() + "】");
        helper.setText(R.id.tv_total_money, "￥" + item.getTotalmoney());
        if (null != item.getStatus()){
            if (item.getStatus().equals(Constant.STOCKIN_STATUS_DFK)) {
                helper.setVisible(R.id.btn_order_cancel, true);
                helper.setVisible(R.id.btn_order_pay, true);
                helper.setVisible(R.id.btn_order_confirm, false);
                helper.setText(R.id.tv_order_status, "待付款");
            } else if (item.getStatus().equals(Constant.STOCKIN_STATUS_DFH)) {
                helper.setVisible(R.id.btn_order_cancel, false);
                helper.setVisible(R.id.btn_order_pay, false);
                helper.setVisible(R.id.btn_order_confirm, false);
                helper.setText(R.id.tv_order_status, "待发货");
            } else if (item.getStatus().equals(Constant.STOCKIN_STATUS_DSH)) {
                helper.setVisible(R.id.btn_order_cancel, false);
                helper.setVisible(R.id.btn_order_pay, false);
                helper.setVisible(R.id.btn_order_confirm, true);
                helper.setText(R.id.tv_order_status, "待收货");
            } else if (item.getStatus().equals(Constant.STOCKIN_STATUS_QR)) {
                helper.setVisible(R.id.btn_order_cancel, false);
                helper.setVisible(R.id.btn_order_pay, false);
                helper.setVisible(R.id.btn_order_confirm, false);
                helper.setText(R.id.tv_order_status, "已完成");
            }
        }else {
            helper.setVisible(R.id.btn_order_cancel, false);
            helper.setVisible(R.id.btn_order_pay, false);
            helper.setVisible(R.id.btn_order_confirm, false);
            helper.setText(R.id.tv_order_status, item.getStatusshow());
        }

        helper.getView(R.id.rl_order).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClickListener(helper.getLayoutPosition());
            }
        });
        helper.getView(R.id.btn_order_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.cancelOrder(helper.getLayoutPosition());
            }
        });
        helper.getView(R.id.btn_order_pay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.payOrder(helper.getLayoutPosition());
            }
        });
        helper.getView(R.id.btn_order_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.confirmOrder(helper.getLayoutPosition());
            }
        });
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    public interface OnItemClickListener {
        void onItemClickListener(int pos);
        void cancelOrder(int pos);
        void payOrder(int pos);
        void confirmOrder(int pos);
    }
}
