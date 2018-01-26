package com.ayj.chunmiao.adapter.khq;

import android.widget.ImageView;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.BaseViewHolder;
import com.ayj.chunmiao.bean.khq.ShopDetail;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2017/7/20.
 */
public class KhqFwyAdapter extends BaseQuickAdapter<ShopDetail.DataBean,BaseViewHolder> {


    public KhqFwyAdapter(int layout, List<ShopDetail.DataBean> data) {
        super(layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopDetail.DataBean item) {
        Picasso.with(mContext).load(item.getHeadimgurlshow()).placeholder(R.mipmap.zhanweitu).error(R.mipmap.jiazaishibia).into(
                (ImageView) helper.getView(R.id.iv_shop_member));
        helper.setText(R.id.tv_member_name,item.getZwshow()+"："+item.getName());
        helper.setText(R.id.tv_member_introduce,item.getZwshow()+"："+item.getExperience());
    }
}
