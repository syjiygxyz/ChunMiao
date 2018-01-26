package com.ayj.chunmiao.adapter.myinformation;

import android.widget.ImageView;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.BaseViewHolder;
import com.ayj.chunmiao.bean.myinformation.StockDetailBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by zht-pc-04 on 2017/8/28.
 */

public class StockDetailAdapter extends BaseQuickAdapter<StockDetailBean.DataBean,BaseViewHolder> {
    public StockDetailAdapter(int layoutResId, List<StockDetailBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, StockDetailBean.DataBean item) {
        helper.setText(R.id.tv_lxsc_content,item.getMatidshow());
        helper.setText(R.id.tv_lxsc_price,item.getSubtotalmoney());
        if (null != item.getSuppliershopidshow()){
            helper.setText(R.id.tv_lxsc_shop,item.getSuppliershopidshow());
        }
        if (null != item.getImgurlcompressshow() && "" != item.getImgurlcompressshow()){
            Picasso.with(mContext).load(item.getImgurlcompressshow()).error(R.mipmap.jiazaishibia).placeholder(R.mipmap.zhanweitu)
                    .into((ImageView) helper.getView(R.id.iv_lxsc_img));
        }else {
            helper.setImageResource(R.id.iv_lxsc_img,R.mipmap.jiazaishibia);
        }
    }
}
