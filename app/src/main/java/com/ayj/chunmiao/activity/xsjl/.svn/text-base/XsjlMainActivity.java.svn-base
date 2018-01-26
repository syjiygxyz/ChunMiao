package com.ayj.chunmiao.activity.xsjl;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.activity.common.CustomActivity;
import com.ayj.chunmiao.activity.dzc.DzcActivity;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.listener.OnItemClickListener;
import com.ayj.chunmiao.adapter.main.CommonGridAdapter;
import com.ayj.chunmiao.bean.UtilityItem;
import com.ayj.chunmiao.utils.Constant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
/*
* 销售记录
* */
public class XsjlMainActivity extends BaseActivity {


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
        return R.layout.activity_xsjl_main;
    }

    @Override
    public void initDatas() {
        mTvTitle.setText("销售记录");
        list = UtilityItem.getXslsList();
        mRlv.setHasFixedSize(true);
        mRlv.setLayoutManager(new GridLayoutManager(mContext, 2));
    }

    @Override
    public void configViews() {
        mCommonGridAdapter = new CommonGridAdapter(R.layout.cmbz_item, list);
        mRlv.setAdapter(mCommonGridAdapter);
        /*九个栏目的点击*/
        mRlv.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (list.get(position).getType()) {
                    case 1:
                        //调理服务
                        XslsDetailActivity.startActivity(mContext, Constant.MENBERORDERTYPE_TL,list.get(position).getText());
                        break;
                    case 2:
                        //商铺
                        XslsDetailActivity.startActivity(mContext, Constant.MENBERORDERTYPE_SP,list.get(position).getText());
                        break;
                    case 3://营养吧
                        XslsDetailActivity.startActivity(mContext, Constant.MENBERORDERTYPE_YY,list.get(position).getText());
                        break;
                    case 4://兑换
                        XslsDetailActivity.startActivity(mContext, Constant.MENBERORDERTYPE_DH,list.get(position).getText());
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
