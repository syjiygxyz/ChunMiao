package com.ayj.chunmiao.adapter.mdmanage;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.BaseViewHolder;
import com.ayj.chunmiao.bean.MdCgBean;
import com.ayj.chunmiao.bean.MdXsBean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by zht-pc-09 on 2017/6/28.
 */
public class XsAdapter extends BaseQuickAdapter<MdXsBean.DataBean,BaseViewHolder> {

    public XsAdapter(int layoutResId, List<MdXsBean.DataBean> lists) {
        super(layoutResId, lists);
    }

    @Override
    protected void convert(BaseViewHolder helper, MdXsBean.DataBean item) {
        helper.setText(R.id.tv_dh, item.getMatidshow());
        if(null==item.getUsedate()){
            helper.setText(R.id.tv_static,"");
        }else{
            helper.setText(R.id.tv_static,item.getUsedate()+"");
        }
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
