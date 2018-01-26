package com.ayj.chunmiao.adapter.cmbz.bx;

import android.view.View;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.BaseViewHolder;
import com.ayj.chunmiao.bean.cmbz.VehicleBean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/29.
 */
public class CzyZfAdapter extends BaseQuickAdapter<VehicleBean.DataBean, BaseViewHolder> {

    private View.OnClickListener scOnClickListener;

    public CzyZfAdapter(int layoutResId, List<VehicleBean.DataBean> lists, View.OnClickListener scOnClickListener) {
        super(layoutResId, lists);
        this.scOnClickListener = scOnClickListener;
    }

    @Override
    protected void convert(BaseViewHolder helper, VehicleBean.DataBean item) {
        helper.setText(R.id.tv_name,item.getName());
        helper.setText(R.id.tv_cp,item.getCarbrandshow()+item.getCarlines());
        if (null != scOnClickListener) {
            helper.getView(R.id.iv).setTag(helper.getPosition());
            helper.getView(R.id.iv).setOnClickListener(scOnClickListener);
        }
    }
}
