package com.ayj.chunmiao.adapter.kc;

import android.widget.ImageView;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.BaseViewHolder;
import com.ayj.chunmiao.bean.MdClBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2017/7/29.
 */
public class KcXhjAdapter extends BaseQuickAdapter<MdClBean.DataBean, BaseViewHolder> {

    public KcXhjAdapter(int layoutResId, List<MdClBean.DataBean> lists) {
        super(layoutResId, lists);
    }

    @Override
    protected void convert(BaseViewHolder helper, MdClBean.DataBean item) {
        helper.setText(R.id.tv_foot_xd_name, item.getMatidshow());
        helper.setText(R.id.tv_foot_xd_count,"存量:"+item.getNum());
        helper.setText(R.id.tv_foot_dw,"单位:"+item.getSaleunitidshow());
        helper.setText(R.id.tv_foot_xd_price,"规格:"+item.getStandard());
        if("".equals(item.getImgurlcompressshow())){
            helper.setImageResource(R.id.iv_foot_xd,R.mipmap.jiazaishibia);
        }else {
            Picasso.with(mContext).load(item.getImgurlcompressshow()).placeholder(R.mipmap.zhanweitu).error(
                    R.mipmap.jiazaishibia).into((ImageView) helper.getView(R.id.iv_foot_xd));
        }
    }
}
