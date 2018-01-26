package com.ayj.chunmiao.adapter.khq;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.BaseViewHolder;
import com.ayj.chunmiao.bean.MemberCard;
import com.ayj.chunmiao.bean.cmbz.PackageContent;

import java.util.List;

/**
 * Created by Administrator on 2017/7/18.
 */
public class MainWaterAdapter extends BaseQuickAdapter<MemberCard.DataBean,BaseViewHolder> {

    public MainWaterAdapter(int layout, List<MemberCard.DataBean> data) {
        super(layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MemberCard.DataBean item) {
        helper.setText(R.id.tv_package_name, item.getMatidshow());
        helper.setText(R.id.tv_package_price, "¥" + item.getNowprice());
    }
}
