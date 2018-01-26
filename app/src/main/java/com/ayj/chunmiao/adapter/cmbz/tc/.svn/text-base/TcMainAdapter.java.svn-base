package com.ayj.chunmiao.adapter.cmbz.tc;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.BaseViewHolder;
import com.ayj.chunmiao.bean.MemberCard;
import com.ayj.chunmiao.bean.cmbz.PackageContent;

import java.util.List;

/**
 * Created by Administrator on 2017/7/15.
 */
public class TcMainAdapter extends BaseQuickAdapter<PackageContent.DataBean,BaseViewHolder> {

    public TcMainAdapter(int layout, List<PackageContent.DataBean> data) {
        super(layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PackageContent.DataBean item) {
        helper.setText(R.id.tv_package_name,item.getTcname());
        helper.setText(R.id.tv_package_price,"¥" + Integer.parseInt(item.getNum()) * Double.parseDouble(item.getTcmoney()));
    }
}
