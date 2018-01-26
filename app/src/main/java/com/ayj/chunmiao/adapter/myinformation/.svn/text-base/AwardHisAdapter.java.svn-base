package com.ayj.chunmiao.adapter.myinformation;

import android.support.v4.content.ContextCompat;
import android.view.View;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.BaseViewHolder;
import com.ayj.chunmiao.adapter.cg.XhjHroAdapter;
import com.ayj.chunmiao.bean.myinformation.AwardListBean;
import com.ayj.chunmiao.bean.myinformation.TicketBean;
import com.ayj.chunmiao.utils.Constant;

import java.util.List;

/**
 * Created by zht-pc-04 on 2017/9/4.
 */

public class AwardHisAdapter extends BaseQuickAdapter<AwardListBean.DataBean, BaseViewHolder> {

    RecyclerViewOnItemClickListener onItemClickListener;

    public AwardHisAdapter(int layoutResId, List<AwardListBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, AwardListBean.DataBean item) {
        helper.setText(R.id.tv_time, item.getCreatetime());
        helper.setText(R.id.tv_state, item.getStatusshow());
        if (Constant.AWARD_DLQ.equals(item.getStatus())) {
            helper.setTextColor(R.id.tv_state, ContextCompat.getColor(mContext, R.color.app_theme));
            helper.setText(R.id.tv_content, "查看详情");
        } else if (Constant.AWARD_DZC.equals(item.getStatus())) {
            helper.setTextColor(R.id.tv_state, ContextCompat.getColor(mContext, R.color.orange));
            helper.setText(R.id.tv_content, "查看详情");
        } else if (Constant.AWARD_DZ.equals(item.getStatus())) {
            helper.setTextColor(R.id.tv_state, ContextCompat.getColor(mContext, R.color.light_green));
            helper.setText(R.id.tv_content,"领取人:"+ item.getTomembermobile());
        }
        helper.getView(R.id.rl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClickListener(helper.getLayoutPosition());
            }
        });
    }

    public void setOnItemClickListener(RecyclerViewOnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
    public interface RecyclerViewOnItemClickListener{
        void onItemClickListener(int pos );
    }
}
