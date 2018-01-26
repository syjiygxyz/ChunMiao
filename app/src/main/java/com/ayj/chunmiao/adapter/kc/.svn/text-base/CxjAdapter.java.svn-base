package com.ayj.chunmiao.adapter.kc;

import android.support.v4.content.ContextCompat;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.BaseViewHolder;
import com.ayj.chunmiao.bean.MdClBean;

import java.util.List;

/**
 * Created by zht-pc-04 on 2017/9/4.
 */

public class CxjAdapter extends BaseQuickAdapter<MdClBean.DataBean,BaseViewHolder> {
    public CxjAdapter(int layoutResId, List<MdClBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MdClBean.DataBean item) {
        helper.setText(R.id.tv_cx_name,item.getMatidshow());
        helper.setText(R.id.tv_cx_no,item.getNum());
        helper.setBackgroundColor(R.id.ll_cxj,helper.getLayoutPosition() % 2 == 0 ? ContextCompat.getColor(mContext,R.color.white) : ContextCompat.getColor(mContext,R.color.main_dialog));
    }
}
