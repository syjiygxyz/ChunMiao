package com.ayj.chunmiao.activity.myinformation.jinku;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.adapter.tab.CommonPagerAdapter;
import com.ayj.chunmiao.fragment.myinfo.XjkFragment;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.Constant;
import com.ayj.chunmiao.view.magicindicator.MagicIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*小金库*/
public class XJKActivity extends BaseActivity {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_chongzhi)
    TextView tvChongzhi;
    @BindView(R.id.tv_tixian)
    TextView tvTixian;
    @BindView(R.id.jk_indicator)
    MagicIndicator jkIndicator;
    @BindView(R.id.jk_pager)
    ViewPager jkPager;
    @BindView(R.id.tv_total)
    TextView tvTotal;

    private List<Fragment> listFragment = new ArrayList<>();
    private List<String> titles = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_xjk;
    }

    @Override
    public void initDatas() {
        tvTitle.setText("小金库");
        tvTotal.setText(getIntent().getStringExtra("total"));
        titles.add("消费");
        titles.add("充值");
        titles.add("提现");
        setFragment();
        jkPager.setOffscreenPageLimit(3);
        jkPager.setAdapter(new CommonPagerAdapter(getSupportFragmentManager(), titles, listFragment));
        CommonUtils.initJfMagicIndicator(jkIndicator, titles, jkPager, mContext);
    }

    private void setFragment() {
        listFragment.add(new XjkFragment().newInstance(Constant.CHANGE_TYPE_XF));
        listFragment.add(new XjkFragment().newInstance(Constant.CHANGE_TYPE_CZ));
        listFragment.add(new XjkFragment().newInstance(Constant.CHANGE_TYPE_TX));
    }

    @Override
    public void configViews() {

    }

    @OnClick({R.id.iv_back, R.id.tv_chongzhi, R.id.tv_tixian})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_chongzhi:
                Intent czIntent = new Intent(mContext, JkCzorTxActivity.class);
                czIntent.putExtra("type", "cz");
                startActivity(czIntent);
                break;
            case R.id.tv_tixian:
                Intent txIntent = new Intent(mContext, JkCzorTxActivity.class);
                txIntent.putExtra("type", "tx");
                startActivity(txIntent);
                break;
        }
    }
}
