package com.ayj.chunmiao.adapter.txl;

import android.content.Context;
import android.widget.ImageView;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.BaseViewHolder;
import com.ayj.chunmiao.bean.MyYgDetailsBean;
import com.ayj.chunmiao.bean.txl.TxBean;
import com.ayj.chunmiao.bean.txl.TxBodyBean;
import com.ayj.chunmiao.bean.txl.TxHeadBean;
import com.ayj.chunmiao.utils.CircleTransform;
import com.ayj.chunmiao.utils.txl.CommonAdapter;
import com.ayj.chunmiao.utils.txl.ViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by zht-pc-09 on 2017/6/30.
 */
public class TxlAdapter extends CommonAdapter<TxBodyBean> {

    public TxlAdapter(Context context,int layoutResId, List<TxBodyBean> lists) {
        super(context,layoutResId, lists);
    }

    @Override
    public void convert(ViewHolder holder, TxBodyBean item) {
        if(null==item.getCity().getName()){
            holder.setText(R.id.tv_name,"未命名");
        }else{
            holder.setText(R.id.tv_name, item.getCity().getName().toString());
        }
        if(null==item.getCity().getMobile()){
            holder.setText(R.id.tv_phone,"未绑定手机号");
        }else{
            holder.setText(R.id.tv_phone,item.getCity().getMobile().toString());
        }
        holder.setText(R.id.tv_vip,item.getCity().getSmallmoneylevelshow().equals("")?"VIP0":item.getCity().getSmallmoneylevelshow());
        if("".equals(item.getCity().getPhotonameshow())){
            holder.setImageResource(R.id.imageView,R.mipmap.menber_loading);
        }else{
            Picasso.with(mContext).load(item.getCity().getPhotonameshow()).placeholder(R.mipmap.menber_loading).error(R.mipmap.menber_error).transform(new CircleTransform()).into(
                    (ImageView) holder.getView(R.id.imageView));
        }
    }


}