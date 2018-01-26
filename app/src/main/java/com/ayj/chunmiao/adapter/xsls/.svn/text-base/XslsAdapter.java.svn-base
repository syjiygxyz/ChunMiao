package com.ayj.chunmiao.adapter.xsls;

import android.util.Log;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.BaseViewHolder;
import com.ayj.chunmiao.bean.UtilityItem;
import com.ayj.chunmiao.bean.xsls.XslsBean;

import java.util.List;

/**
 * Created by zht-pc-09 on 2017/7/6.
 */
public class XslsAdapter extends BaseQuickAdapter<XslsBean.DataBean,BaseViewHolder> {

    public XslsAdapter(int layoutResId, List<XslsBean.DataBean> lists) {
        super(layoutResId, lists);
    }

    @Override
    protected void convert(BaseViewHolder helper, XslsBean.DataBean item) {
        helper.setText(R.id.tv_time, item.getCreatedate());
        helper.setText(R.id.tv_money, "¥"+item.getSubtotal());
        helper.setText(R.id.tv_dd, item.getSnid()+item.getPidshow());
    }
}
