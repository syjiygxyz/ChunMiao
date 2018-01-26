package com.ayj.chunmiao.adapter.cg;

import android.widget.ImageView;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.BaseViewHolder;
import com.ayj.chunmiao.bean.cg.Shoppingmall;
import com.ayj.chunmiao.utils.CommonUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by zht-pc-04 on 2017/7/29.
 */

public class XhjConfirmCgAdapter extends BaseQuickAdapter<Shoppingmall.DataBean, BaseViewHolder> {
    String type;

    public XhjConfirmCgAdapter(int layoutResId, List<Shoppingmall.DataBean> data,String type) {
        super(layoutResId, data);
        this.type = type;
    }

    @Override
    protected void convert(BaseViewHolder helper, Shoppingmall.DataBean item) {
        helper.setText(R.id.tv_content,item.getMatidshow());
        helper.setText(R.id.tv_price, "￥"+CommonUtils.douFormat(item.getShoppurchaseprice()));
        helper.setText(R.id.tv_total,"x "+item.getNum());
        if(type.equals("1")){
            if ("".equals(item.getLogoimgurlshow())) {
                helper.setImageResource(R.id.iv_foot, R.mipmap.jiazaishibia);
            } else {
                Picasso.with(mContext).load(item.getLogoimgurlshow()).placeholder(R.mipmap.zhanweitu).error(R.mipmap.jiazaishibia).into((ImageView) helper.getView(R.id.iv_foot));

            }
        }else{
            if ("".equals(item.getImgurlshow())) {
                helper.setImageResource(R.id.iv_foot, R.mipmap.jiazaishibia);
            } else {
                Picasso.with(mContext).load(item.getImgurlshow()).placeholder(R.mipmap.zhanweitu).error(R.mipmap.jiazaishibia).into((ImageView) helper.getView(R.id.iv_foot));

            }
        }

    }
}
