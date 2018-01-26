package com.ayj.chunmiao.adapter.myinformation;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.BaseViewHolder;
import com.ayj.chunmiao.bean.myinformation.LxdBean;
import com.ayj.chunmiao.view.SwipeMenuView;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by zht-pc-04 on 2017/8/24.
 */

public class LxdAdapter extends BaseQuickAdapter<LxdBean.DataBean,BaseViewHolder> {

    OnConfirmClickListener onConfirmClickListener;

    public LxdAdapter(int layoutResId, List<LxdBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, LxdBean.DataBean item) {
        helper.setText(R.id.tv_shop_id,item.getSuppliershopidshow());
        helper.setText(R.id.tv_order_id,"编号:"+item.getOrderid());
        helper.setText(R.id.tv_order_name,"名称:"+item.getMatidshow());
        helper.setText(R.id.tv_num,"数量:"+ item.getNum());
        helper.setText(R.id.tv_btime,"开始时间:"+item.getBtime());
        helper.setText(R.id.tv_etime,"截止有效时间:"+item.getEtime());
        if ("SFPD001".equals(item.getHaspay())){
            helper.setText(R.id.btn_confirm_share,"分享");
            helper.getView(R.id.btn_confirm_share).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onConfirmClickListener.onShareClickListener(helper.getLayoutPosition());
                }
            });
        }else if ("SFPD002".equals(item.getHaspay())){
            helper.setText(R.id.btn_confirm_share,"同意并支付");
            helper.getView(R.id.btn_confirm_share).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onConfirmClickListener.onConfirmClickListener(helper.getLayoutPosition());
                }
            });
        }
        if (null==item.getLogoimgurlcompressshow() || TextUtils.isEmpty(item.getLogoimgurlcompressshow())){
            helper.setImageResource(R.id.iv_logo,R.mipmap.jiazaishibia);
        }else {
            Picasso.with(mContext).load(item.getLogoimgurlcompressshow()).placeholder(R.mipmap.zhanweitu)
                    .error(R.mipmap.jiazaishibia).into((ImageView) helper.getView(R.id.iv_logo));
        }


    }
    public void setOnConfirmClinckListener(OnConfirmClickListener onConfirmClickListener){
        this.onConfirmClickListener = onConfirmClickListener;
    }
    public interface OnConfirmClickListener{
        void onConfirmClickListener(int pos);
        void onShareClickListener(int pos);
    }
}
