package com.ayj.chunmiao.adapter.kc;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.BaseViewHolder;
import com.ayj.chunmiao.bean.kc.JfPlatBean;
import com.ayj.chunmiao.utils.CommonUtils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by zht-pc-04 on 2017/9/7.
 */

public class JfPlatAdapter extends BaseQuickAdapter<JfPlatBean.DataBean,BaseViewHolder> {
    public JfPlatAdapter(int layoutResId, List<JfPlatBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, JfPlatBean.DataBean item) {
        if (item.getCreatedate()!= null && CommonUtils.isDateFormat(item.getCreatedate())){
            try{
                helper.setText(R.id.tv_date,new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(item.getCreatedate())));
            }catch (ParseException e){
                e.printStackTrace();
            }

        }else{
            helper.setText(R.id.tv_date,item.getCreatedate());
        }
        helper.setText(R.id.tv_snid,item.getOrderid());
        helper.setText(R.id.tv_num,item.getChangenum());

    }
}
