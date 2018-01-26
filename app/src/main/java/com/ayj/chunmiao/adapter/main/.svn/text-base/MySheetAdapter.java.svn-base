package com.ayj.chunmiao.adapter.main;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.BaseViewHolder;
import com.ayj.chunmiao.bean.UtilityItem;
import com.ayj.chunmiao.bean.YyQdBean;

import java.util.List;

/**
 * Created by zht-pc-09 on 2017/6/23.
 * 首页签到dialog适配器
 */
public class MySheetAdapter extends BaseQuickAdapter<YyQdBean.DataBean,BaseViewHolder> {

    public MySheetAdapter(int layoutResId,List<YyQdBean.DataBean> lists){
        super(layoutResId,lists);
    }

    @Override
    protected void convert(BaseViewHolder helper, YyQdBean.DataBean item) {
        helper.setText(R.id.tv_dh,item.getCreatetime()+"");
        helper.setText(R.id.tv_name,item.getMsnidshow());
        if(helper.getPosition()%2==0){
            /*偶数*/
            helper.getView(R.id.ll).setBackgroundColor(mContext.getResources().getColor(R.color.white));
        }else{
            /*基数*/
            helper.getView(R.id.ll).setBackgroundColor(mContext.getResources().getColor(R.color.main_dialog));


        }
    }
}
