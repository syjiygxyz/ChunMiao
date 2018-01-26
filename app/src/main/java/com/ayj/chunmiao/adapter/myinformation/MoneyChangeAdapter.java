package com.ayj.chunmiao.adapter.myinformation;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.BaseViewHolder;
import com.ayj.chunmiao.bean.myinformation.ChangeMoneyBean;

import java.util.List;

/**
 * Created by zht-pc-04 on 2017/8/12.
 */

public class MoneyChangeAdapter extends BaseQuickAdapter<ChangeMoneyBean.DataBean,BaseViewHolder> {
    public MoneyChangeAdapter(int layoutResId, List<ChangeMoneyBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ChangeMoneyBean.DataBean item) {
        helper.setText(R.id.tv_change_time,item.getCreatetime().toString());
        helper.setText(R.id.tv_change_comment,item.getHiscomment());
        helper.setText(R.id.tv_change_num,item.getChangenum());
    }
}
