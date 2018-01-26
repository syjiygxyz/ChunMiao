package com.ayj.chunmiao.adapter.cmbz.bx;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.BaseViewHolder;
import com.ayj.chunmiao.bean.bx.BzfwBean;
import com.ayj.chunmiao.bean.bx.CommonBxListBean;
import com.ayj.chunmiao.view.DividerItemDecoration;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/26.
 */
public class CommonBxDetailsAdapter extends BaseQuickAdapter<BzfwBean.DataBean, BaseViewHolder> {

    List<BzfwBean.DataBean> lists = new ArrayList<>();

    CommonTwoAdapter commonTwoAdapter;

    public CommonBxDetailsAdapter(int layoutResId, List<BzfwBean.DataBean> lists) {
        super(layoutResId, lists);
        this.lists = lists;
    }

    @Override
    protected void convert(BaseViewHolder helper, BzfwBean.DataBean item) {
        helper.setText(R.id.tv_fangan,item.getEcomment());
        RecyclerView rlv = helper.getView(R.id.rlv);
        CheckBox cb = helper.getView(R.id.radio_btn);
        if(item.getTrue()){
            cb.setChecked(true);
            rlv.setVisibility(View.VISIBLE);
            helper.setBackgroundRes(R.id.tv_jt,R.mipmap.btn_down);
        }else{
            cb.setChecked(false);
            rlv.setVisibility(View.GONE);
            helper.setBackgroundRes(R.id.tv_jt,R.mipmap.btn_up);
        }
        rlv.setLayoutManager(new LinearLayoutManager(mContext));
        rlv.setAdapter(new CommonTwoAdapter(R.layout.item_bxcontentlist,item.getContentList()));
    }
}
