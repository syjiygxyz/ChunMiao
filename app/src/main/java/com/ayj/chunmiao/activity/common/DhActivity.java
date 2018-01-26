package com.ayj.chunmiao.activity.common;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.listener.OnItemClickListener;
import com.ayj.chunmiao.adapter.main.CommonGridAdapter;
import com.ayj.chunmiao.bean.UtilityItem;
import com.ayj.chunmiao.utils.Constant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class DhActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rlv)
    RecyclerView clv;

    CommonGridAdapter mCommonGridAdapter;

    List<UtilityItem> gridLists = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_dh;
    }

    @Override
    public void initDatas() {
        tvTitle.setText("春苗兑换");
        clv.setLayoutManager(new GridLayoutManager(mContext, 2));
        gridLists = UtilityItem.dhGridList();
        mCommonGridAdapter = new CommonGridAdapter(R.layout.dh_main_item, gridLists);
        clv.setAdapter(mCommonGridAdapter);
        /*九个栏目的点击*/
        clv.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent();
                switch (gridLists.get(position).getType()) {
                    case 1://金币
                        CommonSpListActivity.startActivity(mContext, "no_hot", "MEMBERORDERTYPE025", Constant.MAT_CLASS1_LJXW, "jbdh");
                        break;
                    case 2://爱e币
                        CommonSpListActivity.startActivity(mContext, "no_hot", "MEMBERORDERTYPE002", Constant.MAT_CLASS1_LJXW, "aebdh");
                        break;
                    case 3://物卷
                        CommonSpListActivity.startActivity(mContext, "no_hot", "MEMBERORDERTYPE032", Constant.MAT_CLASS1_LJXW, "wjdh");
                        break;
                }
            }
        });
    }

    @Override
    public void configViews() {

    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}

