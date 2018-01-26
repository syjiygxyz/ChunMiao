package com.ayj.chunmiao.activity.kc.lx;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.activity.mdlx.lxsb.LxsbActivity;
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
*联销
* */
public class LxActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    CommonGridAdapter commonGridAdapter;

    List<UtilityItem> list = new ArrayList<>();

    String type;



    @Override
    public int getLayoutId() {
        return R.layout.activity_lx;
    }

    @Override
    public void initDatas() {
        tvTitle.setText("联销");
        list = UtilityItem.getLxList();
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
    }

    @Override
    public void configViews() {
        commonGridAdapter = new CommonGridAdapter(R.layout.cmbz_item, list);
        recyclerView.setAdapter(commonGridAdapter);
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 0://自销
                        LxListActivity.startActivity(mContext, Constant.LX_ZX);
                        break;
                    case 1://量贩分摊
                        LxListActivity.startActivity(mContext, Constant.LX_FT);
                        break;
                    case 2://量贩采购
                        LxListActivity.startActivity(mContext, Constant.LX_CG);
                        break;
                }
            }
        });
    }


    @OnClick({R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;

        }
    }
}
