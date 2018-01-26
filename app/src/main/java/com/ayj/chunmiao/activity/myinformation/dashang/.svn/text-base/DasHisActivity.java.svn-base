package com.ayj.chunmiao.activity.myinformation.dashang;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.fragment.myinfo.DasEbHisFragment;
import com.ayj.chunmiao.fragment.myinfo.DaswHisFragment;
import butterknife.BindView;
import butterknife.OnClick;

public class DasHisActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.rb_zengwu)
    RadioButton rbZengwu;
    @BindView(R.id.rb_aiyib)
    RadioButton rbAiyib;
    @BindView(R.id.radio_group)
    RadioGroup radioGroup;
    @BindView(R.id.dashang_his_content)
    FrameLayout dashangHisContent;

    private Fragment[] mFragments;
    private int mIndex;

    @Override
    public int getLayoutId() {
        return R.layout.activity_das_his;
    }

    @Override
    public void initDatas() {
        initFragments();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId){
                    case R.id.rb_zengwu:
                        showFragmentChecked(0);
                        break;
                    case R.id.rb_aiyib:
                        showFragmentChecked(1);
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

    private void initFragments() {
        DaswHisFragment daswHisFragment = new DaswHisFragment();
        DasEbHisFragment dasEbHisFragment = new DasEbHisFragment();
        mFragments = new Fragment[]{daswHisFragment,dasEbHisFragment};
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.dashang_his_content,mFragments[0]).commit();
        showFragmentChecked(0);
    }

    private void showFragmentChecked(int index) {
        if (mIndex == index)
            return;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (mFragments[index].isAdded()) {
            fragmentTransaction.show(mFragments[index]);
        }else {
            fragmentTransaction.add(R.id.dashang_his_content,mFragments[index]).show(mFragments[index]);
        }
        fragmentTransaction.hide(mFragments[mIndex]);
        fragmentTransaction.commit();
        mIndex = index;
    }
}
