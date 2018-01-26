package com.ayj.chunmiao.adapter.cmbz.bx;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.BaseViewHolder;
import com.ayj.chunmiao.bean.bx.BzfwBean;
import com.ayj.chunmiao.view.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/26.
 */
public class CommonTwoAdapter extends BaseQuickAdapter<BzfwBean.DataBean.ContentListBean, BaseViewHolder> {

    public CommonTwoAdapter(int layoutResId, List<BzfwBean.DataBean.ContentListBean> lists) {
        super(layoutResId, lists);
    }

    @Override
    protected void convert(BaseViewHolder helper, BzfwBean.DataBean.ContentListBean item) {
        helper.setText(R.id.tv_protectcontent,item.getProtectcontent());
        helper.setText(R.id.tv_protectlimit,item.getProtectlimit()+"万年");
    }
}