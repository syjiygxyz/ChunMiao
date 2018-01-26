package com.ayj.chunmiao.activity.myinformation.wujuan;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.adapter.tab.CommonPagerAdapter;
import com.ayj.chunmiao.fragment.myinfo.WjFragment;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.Constant;
import com.ayj.chunmiao.view.magicindicator.MagicIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WjActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.wj_indicator)
    MagicIndicator wjIndicator;
    @BindView(R.id.wj_pager)
    ViewPager wjPager;

    List<Fragment> fragmentList = new ArrayList<>();
    private List<String> titles = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_wj;
    }

    @Override
    public void initDatas() {
        tvTitle.setText("促销券");
        titles.add("未使用");
        titles.add("已使用");
        initFragments();
        wjPager.setOffscreenPageLimit(2);
        wjPager.setAdapter(new CommonPagerAdapter(getSupportFragmentManager(),titles,fragmentList));
        CommonUtils.initJfMagicIndicator(wjIndicator,titles,wjPager,mContext);
    }

    private void initFragments() {
        fragmentList.add(new WjFragment().newInstance(Constant.TICKET_KY));
        fragmentList.add(new WjFragment().newInstance(Constant.TICKET_YY));
    }

    @Override
    public void configViews() {

    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
