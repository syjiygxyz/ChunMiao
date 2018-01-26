package com.ayj.chunmiao.adapter;

import android.widget.ImageView;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.BaseViewHolder;
import com.ayj.chunmiao.bean.FootZqShop;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2017/8/21.
 */
public class JbdhAdapter extends BaseQuickAdapter<FootZqShop.DataBean,BaseViewHolder> {

    String type;

    public JbdhAdapter(List<FootZqShop.DataBean> data, String type) {
        super(R.layout.item_chxd_dh, data);
        this.type = type;
    }

    @Override
    protected void convert(BaseViewHolder helper, FootZqShop.DataBean item) {
        String url = item.getLogoimgurlcompressshow();
        Picasso.with(mContext).load(url).placeholder(R.mipmap.zhanweitu).error(
                R.mipmap.jiazaishibia).into(
                (ImageView) helper.getView(R.id.iv_foot_xd));
        helper.setText(R.id.tv_foot_xd_name, item.getMatidshow());
        if(type.equals("jbdh")){
            helper.setText(R.id.tv_foot_xd_price, item.getNowprice()+"金币");
        }else if(type.equals("aebdh")){
            helper.setText(R.id.tv_foot_xd_price, item.getNowprice()+"爱e币");
        }else if(type.equals("wjdh")){
            helper.setVisible(R.id.tv_foot_xd_price,false);
        }

        helper.setText(R.id.tv_foot_xd_count, "限量"+item.getTotalnum());
    }
}
