package com.ayj.chunmiao.adapter.cmbz.yy;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.bean.cmbz.MyMemberCard;

import java.util.List;

/**
 * Created by leo on 16/5/7.
 */
public class PageAdapter extends PagerAdapter {
    private List<MyMemberCard.DataBean> memberCards;
    private Context context;

    public PageAdapter(Context context, List<MyMemberCard.DataBean> memberCards) {
        this.context = context;
        this.memberCards = memberCards;
    }

    @Override
    public int getCount() {
        return memberCards.size();
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_viewpager, null);
        RelativeLayout relativeLayout_viewpager = (RelativeLayout) view.findViewById(R.id.relativeLayout_viewpager);
        TextView tv_viewpager_name = (TextView) view.findViewById(R.id.tv_viewpager_name);
        TextView tv_viewpager_time = (TextView) view.findViewById(R.id.tv_viewpager_time);
//        String sid = memberCards.get(position).getValiddays();  //根据有效天数判断卡的类型
//        if ("CARDTYPE004".equals(memberCards.get(position).getCardtype())) {
//            if ("182".equals(sid)) {
//                relativeLayout_viewpager.setBackgroundResource(R.mipmap.kanglebnhy);
//            } else if ("365".equals(sid)) {
//                relativeLayout_viewpager.setBackgroundResource(R.mipmap.klqnhy);
//            } else {
//                relativeLayout_viewpager.setBackgroundResource(R.mipmap.kljk);
//            }
//        } else if ("CARDTYPE005".equals(memberCards.get(position).getCardtype())) {
//            relativeLayout_viewpager.setBackgroundResource(R.mipmap.frensk);
//        } else {
//            if ("90".equals(sid)) {//社员季卡
//                relativeLayout_viewpager.setBackgroundResource(R.mipmap.kljk);
//            } else if ("182".equals(sid)) {//社员半年卡
//                relativeLayout_viewpager.setBackgroundResource(R.mipmap.kanglebnhy);
//            } else {//社员年卡
//                relativeLayout_viewpager.setBackgroundResource(R.mipmap.klqnhy);
//            }
//        }
        if(memberCards.get(position).getCardtypeshow().contains("A卡")){
            relativeLayout_viewpager.setBackgroundResource(R.mipmap.kanglebnhy);
        }else if(memberCards.get(position).getCardtypeshow().contains("B卡")){
            relativeLayout_viewpager.setBackgroundResource(R.mipmap.klqnhy);
        }else if(memberCards.get(position).getCardtypeshow().contains("百姓富人水卡")){
            relativeLayout_viewpager.setBackgroundResource(R.mipmap.kljk);
        }
        tv_viewpager_name.setText(memberCards.get(position).getCardtypeshow());
        tv_viewpager_time.setText("有效期至" + memberCards.get(position).getValidatetime().split(" ")[0]);

        container.addView(view);
        return view;
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }

}
