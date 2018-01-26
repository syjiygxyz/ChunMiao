package com.ayj.chunmiao.adapter.ykfx;

import android.graphics.Color;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.BaseViewHolder;
import com.ayj.chunmiao.bean.xsls.XslsBean;
import com.ayj.chunmiao.bean.ykfx.XseDetailBean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/21.
 */
public class XseAdapter extends BaseQuickAdapter<XseDetailBean.DataBean,BaseViewHolder> {

    public XseAdapter(int layoutResId, List<XseDetailBean.DataBean> lists) {
        super(layoutResId, lists);
    }

    @Override
    protected void convert(BaseViewHolder helper, XseDetailBean.DataBean item) {
        helper.setText(R.id.tv_dh, item.getCreatedate());
        helper.setText(R.id.tv_static, "¥"+item.getItemmoney());
        helper.setTextColor(R.id.tv_static, Color.parseColor("#d50b0b"));
        helper.setText(R.id.tv_time,item.getItemdesc());
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
