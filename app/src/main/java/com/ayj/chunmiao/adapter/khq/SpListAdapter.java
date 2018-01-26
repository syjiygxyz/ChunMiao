package com.ayj.chunmiao.adapter.khq;

import android.widget.ImageView;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.BaseViewHolder;
import com.ayj.chunmiao.bean.FootZqShop;
import com.ayj.chunmiao.utils.CircleTransform;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/18.
 */
public class SpListAdapter extends BaseQuickAdapter<FootZqShop.DataBean,BaseViewHolder> {

    List<FootZqShop.DataBean> data = new ArrayList<>();

    public SpListAdapter(int layout, List<FootZqShop.DataBean> data) {
        super(layout, data);
        this.data = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, FootZqShop.DataBean item) {
        helper.setText(R.id.tv_foot_xd_name,item.getMatidshow());
        helper.setText(R.id.tv_price,"¥"+item.getNowprice()+"/"+item.getUnitidshow());
        Picasso.with(mContext).load(item.getLogoimgurlshow()).placeholder(R.mipmap.menber_loading).error(R.mipmap.menber_error).into(
                (ImageView) helper.getView(R.id.iv_foot_xd));
        if(item.getSnid().equals(data.get(data.size()-1).getSnid())){
            helper.setVisible(R.id.view_xian,false);
        }
    }

}
