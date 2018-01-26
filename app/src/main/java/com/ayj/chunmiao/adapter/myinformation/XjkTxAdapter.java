package com.ayj.chunmiao.adapter.myinformation;

import android.graphics.Color;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.BaseViewHolder;
import com.ayj.chunmiao.bean.myinformation.DrawApplyBean;
import com.ayj.chunmiao.utils.Constant;

import java.util.List;

/**
 * Created by Administrator on 2017/9/22.
 */

public class XjkTxAdapter extends BaseQuickAdapter<DrawApplyBean.DataBean,BaseViewHolder> {
    public XjkTxAdapter(int layoutResId, List<DrawApplyBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DrawApplyBean.DataBean item) {
        helper.setText(R.id.tv_time,item.getCreatetime().toString());
        helper.setText(R.id.tv_title,item.getAuditstatusshow());
        helper.setText(R.id.tv_money,"￥"+item.getWithdrawnum());
        if (Constant.TX_DSH.equals(item.getAuditstatus())){
            helper.setTextColor(R.id.tv_title, Color.parseColor("#fe8c02"));
        }else if(Constant.TX_SUC.equals(item.getAuditstatus())){
            helper.setTextColor(R.id.tv_title, Color.parseColor("#2c9a15"));
        }else if(Constant.TX_FAI.equals(item.getAuditstatus())){
            helper.setTextColor(R.id.tv_title, Color.parseColor("#FD2E2E"));
        }
    }
}
