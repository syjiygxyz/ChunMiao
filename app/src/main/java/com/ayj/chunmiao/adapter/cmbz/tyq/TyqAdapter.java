package com.ayj.chunmiao.adapter.cmbz.tyq;

import android.widget.ImageView;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.BaseViewHolder;
import com.ayj.chunmiao.bean.FootZqShop;
import com.ayj.chunmiao.utils.Constant;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by zht-pc-09 on 2017/7/7.
 */
public class TyqAdapter extends BaseQuickAdapter<FootZqShop.DataBean,BaseViewHolder> {

    public TyqAdapter(int layout,List<FootZqShop.DataBean> data) {
        super(layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FootZqShop.DataBean item) {
         helper.setText(R.id.tv,item.getMatidshow());
         if(Constant.Wl_AJTL.equals(item.getMatid())){
             helper.setImageResource(R.id.iv,R.mipmap.fuq_dzj);
         }else if(Constant.Wl_SB.equals(item.getMatid())){
             helper.setImageResource(R.id.iv,R.mipmap.fwq_sb);
         }else if(Constant.Wl_FR.equals(item.getMatid())){
             helper.setImageResource(R.id.iv,R.mipmap.fwq_fyr);
         }else if(Constant.Wl_TP.equals(item.getMatid())){
             helper.setImageResource(R.id.iv,R.mipmap.fwq_pp);
         }
    }
}
