package com.ayj.chunmiao.adapter.cmbz.bx;

import android.graphics.Color;
import android.widget.ImageView;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.BaseViewHolder;
import com.ayj.chunmiao.bean.bx.CommonBxListBean;
import com.ayj.chunmiao.bean.bx.TimeIsCheckBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2017/7/26.
 */
public class CommonTimeAdapter extends BaseQuickAdapter<TimeIsCheckBean, BaseViewHolder> {

    String dw;

    public CommonTimeAdapter(int layoutResId, List<TimeIsCheckBean> lists,String dw) {
        super(layoutResId, lists);
        this.dw = dw;
    }

    @Override
    protected void convert(BaseViewHolder helper, TimeIsCheckBean item) {
        helper.setText(R.id.tv1,item.getTimee()+dw);
        if(item.getCheck()){
            helper.setBackgroundRes(R.id.tv1,R.drawable.cz_background_theme);
            helper.setTextColor(R.id.tv1, Color.parseColor("#ffffff"));
        }else{
            helper.setBackgroundRes(R.id.tv1,R.drawable.cz_back_ground);
            helper.setTextColor(R.id.tv1,Color.parseColor("#2e5250"));
        }
    }
}
