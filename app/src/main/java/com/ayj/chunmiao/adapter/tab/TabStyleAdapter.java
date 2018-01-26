package com.ayj.chunmiao.adapter.tab;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.ayj.chunmiao.view.magicindicator.buildins.UIUtil;
import com.ayj.chunmiao.view.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import com.ayj.chunmiao.view.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import com.ayj.chunmiao.view.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import com.ayj.chunmiao.view.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import com.ayj.chunmiao.view.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.List;

/**
 * Created by Administrator on 2017/3/26.
 * tab样式
 */
public class TabStyleAdapter extends CommonNavigatorAdapter {

    private List<String> titles;

    private ViewPager ly_pages;

    private int Mode;

    public TabStyleAdapter(List<String> titles, ViewPager ly_pages,int Mode) {
        this.titles = titles;
        this.ly_pages = ly_pages;
        this.Mode = Mode;
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    @Override
    public IPagerTitleView getTitleView(Context context, int index) {
        SimplePagerTitleView simplePagerTitleView = new SimplePagerTitleView(context);
        simplePagerTitleView.setText(titles.get(index));
        simplePagerTitleView.setTextSize(13);
        simplePagerTitleView.setNormalColor(Color.parseColor("#9e9e9e"));
        simplePagerTitleView.setSelectedColor(Color.parseColor("#2e5250"));
        simplePagerTitleView.setTag(index);
        simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = Integer.valueOf(v.getTag()+"");
                ly_pages.setCurrentItem(position);
            }
        });
        return simplePagerTitleView;
    }

    @Override
    public IPagerIndicator getIndicator(Context context) {
        LinePagerIndicator indicator = new LinePagerIndicator(context);
        indicator.setMode(Mode);
        indicator.setLineHeight(UIUtil.dip2px(context, 3));
        indicator.setLineWidth(UIUtil.dip2px(context, 15));
        indicator.setRoundRadius(UIUtil.dip2px(context, 3));
        indicator.setStartInterpolator(new AccelerateInterpolator());
        indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));
        indicator.setColors(Color.parseColor("#2e5250"));
        return indicator;
    }

}
