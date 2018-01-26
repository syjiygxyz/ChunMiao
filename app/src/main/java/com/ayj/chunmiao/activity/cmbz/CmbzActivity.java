package com.ayj.chunmiao.activity.cmbz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.activity.cmbz.cz.CzActivity;
import com.ayj.chunmiao.activity.cmbz.yy.YdActivity;
import com.ayj.chunmiao.activity.common.CommonSpListActivity;
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
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
* 春苗帮助
* */
public class CmbzActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.rlv)
    RecyclerView mRlv;

    List<UtilityItem> list = new ArrayList<>();

    CommonGridAdapter mCommonGridAdapter;
    @BindView(R.id.iv_right)
    ImageView mIvRight;

    @Override
    public int getLayoutId() {
        return R.layout.activity_cmbz;
    }

    @Override
    public void initDatas() {
        mTvTitle.setText("选择帮助项目");
        list = UtilityItem.getCmHelpList();
        mRlv.setHasFixedSize(true);
        mRlv.setLayoutManager(new GridLayoutManager(mContext, 3));
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
                        //预约
                        CustomActivity.startActivty(mContext,"1");
                        break;
                    case 2:
                        //会员卡
                        CustomActivity.startActivty(mContext, "9");
                        break;
                    case 3://充值
                        CustomActivity.startActivty(mContext,"12");
                        break;
                    case 4://套餐
                        CustomActivity.startActivty(mContext, "10");
                        break;
                    case 5://兑换
                        CustomActivity.startActivty(mContext, "6");
                        break;
                    case 6://邻家小店
                        CustomActivity.startActivty(mContext, "7");
                        break;
                    case 7://折扣商铺
                        CustomActivity.startActivty(mContext, "5");
                        break;
                    case 8://保险
                        CustomActivity.startActivty(mContext,"8");
                        break;
                    case 9://服务券
                        CustomActivity.startActivty(mContext,"3");
                        break;
                    case 10://注册
                        startActivity(new Intent(mContext, DzcActivity.class));
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
