package com.ayj.chunmiao.activity.mdmanger;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.adapter.tab.CommonPagerAdapter;
import com.ayj.chunmiao.fragment.mdmanage.CgFragment;
import com.ayj.chunmiao.fragment.mdmanage.ClFragment;
import com.ayj.chunmiao.fragment.mdmanage.XsFragment;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.view.magicindicator.MagicIndicator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/*
* 门店管理进销存
* */
public class MdManageActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tp_ly)
    MagicIndicator mTpLy;
    @BindView(R.id.ly_pages)
    ViewPager mLyPages;

    private List<Fragment> listFragment = new ArrayList<>();

    List<String> titles = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_qq_pd;
    }

    @Override
    public void initDatas() {
        mTvTitle.setText("进销存");
        titles = Arrays.asList(getResources().getStringArray(R.array.md_manage_titles));
    }

    @Override
    public void configViews() {
        setFragments();
        mLyPages.setOffscreenPageLimit(3);
        CommonPagerAdapter adapter = new CommonPagerAdapter(getSupportFragmentManager(), titles,
                listFragment);
        mLyPages.setAdapter(adapter);
        CommonUtils.initJfMagicIndicator(mTpLy,titles,mLyPages,mContext);
    }

    private void setFragments() {
        listFragment.add(new CgFragment());
        listFragment.add(new XsFragment());
        listFragment.add(new ClFragment());
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
