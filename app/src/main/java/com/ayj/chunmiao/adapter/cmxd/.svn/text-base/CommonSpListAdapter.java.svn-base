package com.ayj.chunmiao.adapter.cmxd;
import android.widget.ImageView;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.BaseViewHolder;
import com.ayj.chunmiao.bean.FootZqShop;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by zht-pc-09 on 2017/6/7.
 * 商品列表通用适配器界面
 */
public class CommonSpListAdapter extends BaseQuickAdapter<FootZqShop.DataBean,BaseViewHolder> {

    public CommonSpListAdapter(List<FootZqShop.DataBean> data) {
        super(R.layout.item_linjia, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FootZqShop.DataBean item) {
        String url = item.getLogoimgurlcompressshow();
        Picasso.with(mContext).load(url).placeholder(R.mipmap.zhanweitu).error(R.mipmap.jiazaishibia).into(
                (ImageView) helper.getView(R.id.iv_foot_xd));
        helper.setText(R.id.tv_foot_xd_name,item.getMatidshow());
        helper.setText(R.id.tv_foot_xd_price,"¥"+item.getNowprice());
    }


}
