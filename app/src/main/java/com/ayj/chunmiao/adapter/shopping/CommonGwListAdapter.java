package com.ayj.chunmiao.adapter.shopping;
import android.widget.ImageView;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.BaseViewHolder;
import com.ayj.chunmiao.bean.shopping.ShopCart;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by zht-pc-09 on 2017/6/13.\
 * 购物车通用布局
 */
public class CommonGwListAdapter extends BaseQuickAdapter<ShopCart.DataBean,BaseViewHolder> {

    public CommonGwListAdapter(List<ShopCart.DataBean> data) {
        super(R.layout.item_shopcart, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopCart.DataBean item) {
        Picasso.with(mContext).load(item.getImgurlcompressshow()).placeholder(R.mipmap.zhanweitu).error(
                R.mipmap.jiazaishibia).into(
                (ImageView) helper.getView(R.id.iv_foot_zq_shopcart));
        helper.setText(R.id.tv_foot_shopcart_name, item.getMatidshow());
        helper.setText(R.id.tv_foot_shopcart_shop, "春苗店:"+item.getCarttypeshow());
        helper.setText(R.id.tv_foot_shopcart_price, "¥"+item.getPrice_now());
        helper.setText(R.id.tv_foot_shopcart_num, "X"+item.getNum());
    }
}

