package com.ayj.chunmiao.adapter.cg;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.BaseViewHolder;
import com.ayj.chunmiao.bean.MdClBean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/21.
 */
public class HcAdapter extends BaseQuickAdapter<MdClBean.DataBean, BaseViewHolder> {

    public HcAdapter(int layoutResId, List<MdClBean.DataBean> lists) {
        super(layoutResId, lists);
    }

    @Override
    protected void convert(BaseViewHolder helper, MdClBean.DataBean item) {
        helper.setText(R.id.tv_name, item.getMatidshow());
        helper.setText(R.id.tv_cl, item.getNum());
        helper.setText(R.id.tv_dw, item.getSaleunitidshow());
        if (helper.getPosition() % 2 == 0) {
            /*偶数*/
            helper.getView(R.id.ll).setBackgroundColor(
                    mContext.getResources().getColor(R.color.main_dialog));
        } else {
            /*基数*/
            helper.getView(R.id.ll).setBackgroundColor(
                    mContext.getResources().getColor(R.color.white));

        }
    }
}
