package com.ayj.chunmiao.adapter.mdmanage;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.BaseViewHolder;
import com.ayj.chunmiao.bean.MdClBean;
import com.ayj.chunmiao.bean.MdXsBean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by zht-pc-09 on 2017/6/28.
 */
public class ClAdapter extends BaseQuickAdapter<MdClBean.DataBean, BaseViewHolder> {

    public ClAdapter(int layoutResId, List<MdClBean.DataBean> lists) {
        super(layoutResId, lists);
    }

    @Override
    protected void convert(BaseViewHolder helper, MdClBean.DataBean item) {
        helper.setText(R.id.tv_dh, item.getMatidshow());
        helper.setText(R.id.tv_time, item.getNum());
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

