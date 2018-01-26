package com.ayj.chunmiao.activity.cmxd;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.activity.common.CommonSpListActivity;
import com.ayj.chunmiao.activity.common.CustomActivity;
import com.ayj.chunmiao.activity.xsjl.XslsDetailActivity;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.listener.OnItemClickListener;
import com.ayj.chunmiao.adapter.main.CommonGridAdapter;
import com.ayj.chunmiao.bean.UtilityItem;
import com.ayj.chunmiao.utils.Constant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
* 春苗小店
* */
public class CmxdMainActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.rlv)
    RecyclerView mRlv;

    List<UtilityItem> list = new ArrayList<>();

    CommonGridAdapter mCommonGridAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_cmxd_main;
    }

    @Override
    public void initDatas() {
        mTvTitle.setText("春苗小店");
        list = UtilityItem.getCmxdList();
        mRlv.setHasFixedSize(true);
        mRlv.setLayoutManager(new GridLayoutManager(mContext, 2));
    }

    @Override
    public void configViews() {
        mCommonGridAdapter = new CommonGridAdapter(R.layout.cmbz_item, list);
        mRlv.setAdapter(mCommonGridAdapter);
        mRlv.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (list.get(position).getType()) {
                    case 1:
                        //春苗展柜
                        CustomActivity.startActivty(mContext, "4");
                        break;
                    case 2:
                        //折扣商铺
                        CustomActivity.startActivty(mContext, "5");
                        break;
                    case 3://兑换中心
                        CustomActivity.startActivty(mContext, "6");
                        break;
                    case 4://邻家小店
                        CustomActivity.startActivty(mContext, "7");
                        break;
                }
            }
        });
    }


    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
