package com.ayj.chunmiao.activity.kc.lx;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.fragment.kc.KcXhjFragment;
import com.ayj.chunmiao.utils.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LxListActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.fl_view)
    FrameLayout flView;

    private String type;

    private KcXhjFragment fragment;

    public static void startActivity(Context context,String type){
        Intent intent = new Intent(context,LxListActivity.class);
        intent.putExtra("type",type);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_lx_list;
    }

    @Override
    public void initDatas() {
        type = getIntent().getStringExtra("type");
        if (Constant.LX_CG.equals(type)){
            tvTitle.setText("量贩采购");
        }else if (Constant.LX_FT.equals(type)){
            tvTitle.setText("量贩分摊");
        }else if (Constant.LX_ZX.equals(type)){
            tvTitle.setText("自销");
        }
    }

    @Override
    public void configViews() {
        getSupportFragmentManager().beginTransaction().add(R.id.fl_view,fragment = KcXhjFragment.newInstance(type,null)).commit();
        fragment.setUserVisibleHint(true);
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
