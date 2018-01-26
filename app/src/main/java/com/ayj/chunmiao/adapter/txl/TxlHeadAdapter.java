package com.ayj.chunmiao.adapter.txl;

import android.widget.ImageView;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.BaseViewHolder;
import com.ayj.chunmiao.bean.txl.TxBean;
import com.ayj.chunmiao.bean.txl.TxBodyBean;
import com.ayj.chunmiao.bean.txl.TxHeadBean;
import com.ayj.chunmiao.utils.CircleTransform;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by zht-pc-09 on 2017/6/30.
 */
public class TxlHeadAdapter extends BaseQuickAdapter<TxBean.DataBean,BaseViewHolder> {

    public TxlHeadAdapter(int layoutResId, List<TxBean.DataBean> lists) {
        super(layoutResId, lists);
    }

    @Override
    protected void convert(BaseViewHolder helper, TxBean.DataBean item) {
        if(null==item.getName()){
            helper.setText(R.id.tv_name,"未命名");
        }else{
            helper.setText(R.id.tv_name, item.getName().toString());
        }
        if(null==item.getMobile()){
            helper.setText(R.id.tv_phone,"未绑定手机号");
        }else{
            helper.setText(R.id.tv_phone,item.getMobile().toString());
        }
        helper.setText(R.id.tv_vip,item.getSmallmoneylevelshow().equals("")?"VIP0":item.getSmallmoneylevelshow());
        if("".equals(item.getPhotonameshow())){
            helper.setImageResource(R.id.imageView,R.mipmap.menber_loading);
        }else{
            Picasso.with(mContext).load(item.getPhotonameshow()).placeholder(R.mipmap.menber_loading).error(R.mipmap.menber_error).transform(new CircleTransform()).into(
                    (ImageView) helper.getView(R.id.imageView));
        }
    }
}
