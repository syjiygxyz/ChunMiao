package com.ayj.chunmiao.adapter.myinformation;

import android.support.v4.content.ContextCompat;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.BaseViewHolder;
import com.ayj.chunmiao.bean.myinformation.AwardListBean;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.Constant;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by zht-pc-04 on 2017/9/5.
 */

public class AwardEbHisAdapter extends BaseQuickAdapter<AwardListBean.DataBean,BaseViewHolder> {
    public AwardEbHisAdapter(int layoutResId, List<AwardListBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AwardListBean.DataBean item) {
        if (null != item.getCreatetime() && CommonUtils.isDateFormat(item.getCreatetime())){
            try {
                Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(item.getCreatetime());
                helper.setText(R.id.tv_time,new SimpleDateFormat("HH:mm").format(date));
                helper.setText(R.id.tv_date,new SimpleDateFormat("yyyy/MM/dd").format(date));
            }catch (ParseException e){
                e.printStackTrace();
            }
        }
        helper.setText(R.id.tv_num,item.getAwardnum());
        helper.setText(R.id.tv_state, item.getStatusshow());
        helper.setText(R.id.tv_mobile,item.getTomembermobile());
        if (Constant.AWARD_DLQ.equals(item.getStatus())) {
            helper.setTextColor(R.id.tv_state, ContextCompat.getColor(mContext, R.color.app_theme));
        } else if (Constant.AWARD_DZC.equals(item.getStatus())) {
            helper.setTextColor(R.id.tv_state, ContextCompat.getColor(mContext, R.color.orange));
        } else if (Constant.AWARD_DZ.equals(item.getStatus())) {
            helper.setTextColor(R.id.tv_state, ContextCompat.getColor(mContext, R.color.light_green));
        }
    }
}
