package com.ayj.chunmiao.adapter.mdmanage;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.BaseViewHolder;
import com.ayj.chunmiao.bean.MdCgBean;
import com.ayj.chunmiao.bean.MyYgDetailsBean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by zht-pc-09 on 2017/6/28.
 * 进货适配器
 */
public class CgAdapter extends BaseQuickAdapter<MdCgBean.DataBean,BaseViewHolder> {

    private String s;

    public CgAdapter(int layoutResId, List<MdCgBean.DataBean> lists) {
        super(layoutResId, lists);
    }

    @Override
    protected void convert(BaseViewHolder helper, MdCgBean.DataBean item) {
        helper.setText(R.id.tv_dh, item.getSnid());
        String d = item.getRequestdate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdff = new SimpleDateFormat("yyyy-MM-dd");
        Date showD = null;
        if (null != item.getRequestdate()){
            try {
                showD = sdf.parse(d);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            s = sdff.format(showD);
        }

        helper.setText(R.id.tv_time,s);
        helper.setText(R.id.tv_static, item.getStatusshow());
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