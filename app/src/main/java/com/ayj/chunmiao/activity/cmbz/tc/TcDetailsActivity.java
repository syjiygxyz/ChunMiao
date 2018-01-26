package com.ayj.chunmiao.activity.cmbz.tc;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.activity.cmbz.hyk.HykZfActivity;
import com.ayj.chunmiao.adapter.cmbz.tc.TcDetailsAdapter;
import com.ayj.chunmiao.bean.cmbz.PackageContent;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
* 套餐详情
* */
public class TcDetailsActivity extends BaseActivity {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_packageinfo_name)
    TextView tvPackageinfoName;
    @BindView(R.id.tv_packageinfo_price)
    TextView tvPackageinfoPrice;
    @BindView(R.id.rlv)
    RecyclerView rlv;
    @BindView(R.id.tv_post)
    TextView tvPost;

    private PackageContent.DataBean packageContent;

    @Override
    public int getLayoutId() {
        return R.layout.activity_tc_details;
    }

    @Override
    public void initDatas() {
        tvTitle.setText("健康包购买");
    }

    @Override
    public void configViews() {
        packageContent = (PackageContent.DataBean) getIntent().getSerializableExtra("package");
        tvPackageinfoName.setText(packageContent.getTcname());
        tvPackageinfoPrice.setText("¥" + Integer.parseInt(packageContent.getNum()) * Double.parseDouble(packageContent.getTcmoney()));
        rlv.setLayoutManager(new LinearLayoutManager(mContext));
        rlv.setAdapter(new TcDetailsAdapter(R.layout.item_package1,packageContent.getSalespromotiondetailList()));
    }

    @OnClick({R.id.iv_back, R.id.tv_post})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_post:
                HykZfActivity.jumpActivity(mContext,packageContent.getTcname(),new DecimalFormat("0.00").format(Integer.parseInt(packageContent.getNum()) * Double.parseDouble(packageContent.getTcmoney()))+"",
                        packageContent.getMatid(),"MEMBERORDERTYPE004",packageContent.getNum(),"","","");
                break;
        }
    }
}
