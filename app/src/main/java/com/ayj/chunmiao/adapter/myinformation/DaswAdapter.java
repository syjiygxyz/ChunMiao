package com.ayj.chunmiao.adapter.myinformation;

import android.view.View;
import android.widget.ImageView;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.BaseViewHolder;
import com.ayj.chunmiao.bean.MdClBean;
import com.ayj.chunmiao.bean.myinformation.CxjBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by zht-pc-04 on 2017/8/31.
 */

public class DaswAdapter extends BaseQuickAdapter<CxjBean.DataBean,BaseViewHolder> {

    OnDsClickListener clickListener;
    public DaswAdapter(int layoutResId, List<CxjBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, CxjBean.DataBean item) {
        helper.setText(R.id.tv_snid,"编号:"+item.getMatid());
        helper.setText(R.id.tv_name,"名称:"+item.getMatidshow());
        helper.setText(R.id.tv_guige,"规格:"+item.getStandard());
        helper.setText(R.id.tv_total_num,"数量:"+item.getNum());
        helper.setText(R.id.tv_receive_way,item.getOnlinetypeshow());
        if (null == item.getImgurlcompressshow() | "".equals(item.getImgurlcompressshow())){
            helper.setImageResource(R.id.iv_goods,R.mipmap.jiazaishibia);
        }else{
            Picasso.with(mContext).load(item.getImgurlcompressshow())
                    .placeholder(R.mipmap.zhanweitu).error(R.mipmap.jiazaishibia).into((ImageView)helper.getView(R.id.iv_goods));
        }
        helper.getView(R.id.btn_dashang).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onClick(helper.getLayoutPosition());
            }
        });
    }
    public void setOnDsClickListener(OnDsClickListener clickListener){
        this.clickListener = clickListener;
    }
    public interface OnDsClickListener{
        void onClick(int pos);
    }
}
