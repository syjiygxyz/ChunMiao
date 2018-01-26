package com.ayj.chunmiao.adapter.myinformation;

import android.widget.ImageView;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.BaseViewHolder;
import com.ayj.chunmiao.bean.myinformation.TicketBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by zht-pc-04 on 2017/8/30.
 */

public class WjAdapter extends BaseQuickAdapter<TicketBean.DataBean, BaseViewHolder> {
    public WjAdapter(int layoutResId, List<TicketBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TicketBean.DataBean item) {
        helper.setText(R.id.tv_snid,"编号:"+item.getSnid());
        helper.setText(R.id.tv_type_name,item.getTickettypeshow());
        helper.setText(R.id.tv_name,"名称:"+item.getTicketusescopeshow());
        helper.setText(R.id.tv_guige,"规格:"+item.getStandard());
        helper.setText(R.id.tv_total_num,"数量:"+"1");
        if (null != item.getImgurlcompressshow() && !"".equals(item.getImgurlcompressshow())){
            Picasso.with(mContext).load(item.getImgurlcompressshow())
                    .placeholder(R.mipmap.zhanweitu).error(R.mipmap.jiazaishibia).into((ImageView) helper.getView(R.id.iv_goods));
        }else {
            helper.setImageResource(R.id.iv_goods,R.mipmap.jiazaishibia);
        }
    }
}
