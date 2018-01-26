package com.ayj.chunmiao.adapter.kc;

import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.BaseViewHolder;
import com.ayj.chunmiao.bean.kc.KcJfBean;

import java.util.List;

/**
 * Created by zht-pc-04 on 2017/8/16.
 */

public class JfAdapter extends BaseQuickAdapter<KcJfBean.DataBean , BaseViewHolder> {
    public JfAdapter(int layoutResId, List<KcJfBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, KcJfBean.DataBean item) {

    }
}
