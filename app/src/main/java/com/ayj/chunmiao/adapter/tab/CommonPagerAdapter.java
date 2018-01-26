package com.ayj.chunmiao.adapter.tab;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/8.
 */
public class CommonPagerAdapter extends FragmentStatePagerAdapter {

    List<String> titles = new ArrayList<>();

    List<Fragment> fragmentList;

    Fragment mCurrentFragment;


    public CommonPagerAdapter(FragmentManager fm,List<String> titles , List<Fragment> fragments) {
        super(fm);
        this.titles = titles;
        this.fragmentList = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        mCurrentFragment = (Fragment) object;
        super.setPrimaryItem(container, position, object);
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

    public Fragment getFragment(){
        return mCurrentFragment;
    }

}