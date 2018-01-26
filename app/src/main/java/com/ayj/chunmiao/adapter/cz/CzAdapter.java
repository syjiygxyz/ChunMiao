package com.ayj.chunmiao.adapter.cz;

import android.graphics.Color;
import android.widget.ImageView;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.BaseViewHolder;
import com.ayj.chunmiao.bean.FootZqShop;
import com.ayj.chunmiao.bean.SmallmoneyFace;
import com.ayj.chunmiao.bean.UtilityItem;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by zht-pc-09 on 2017/7/11.
 */
public class CzAdapter extends BaseQuickAdapter<SmallmoneyFace.DataBean,BaseViewHolder> {

    public CzAdapter(int layout,List<SmallmoneyFace.DataBean> data) {
        super(layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SmallmoneyFace.DataBean item) {
              helper.setText(R.id.tv1,item.getFacevalue());
              helper.setText(R.id.tv_head,"赠送"+item.getGiftemoney()+"爱e币");
              if(item.getTrue()){
                  helper.setBackgroundRes(R.id.tv1,R.drawable.cz_background_theme);
                  helper.setTextColor(R.id.tv1, Color.parseColor("#ffffff"));
              }else{
                  helper.setBackgroundRes(R.id.tv1,R.drawable.cz_back_ground);
                  helper.setTextColor(R.id.tv1,Color.parseColor("#2e5250"));
              }
    }
}
