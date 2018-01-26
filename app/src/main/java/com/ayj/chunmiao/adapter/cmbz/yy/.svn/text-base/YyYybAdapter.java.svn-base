package com.ayj.chunmiao.adapter.cmbz.yy;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.BaseViewHolder;
import com.ayj.chunmiao.bean.FootZqShop;
import com.ayj.chunmiao.bean.cmbz.YybBean;
import com.ayj.chunmiao.view.NumberButton;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2017/8/4.
 */
public class YyYybAdapter extends BaseQuickAdapter<YybBean.DataBean,BaseViewHolder> {

    private View.OnClickListener checkOnClickListener;

    public YyYybAdapter(int layout,List<YybBean.DataBean> data,View.OnClickListener checkOnClickListener) {
        super(layout, data);
        this.checkOnClickListener = checkOnClickListener;
    }

    @Override
    protected void convert(BaseViewHolder helper, YybBean.DataBean item) {
        Picasso.with(mContext).load(item.getImgurlshow()).placeholder(R.mipmap.menber_loading).error(R.mipmap.menber_error).into(
                (ImageView) helper.getView(R.id.img));
        helper.setText(R.id.tv_title,item.getTickettypeshow());
        NumberButton nb = helper.getView(R.id.number_button);
        nb.setBuyMax(Integer.parseInt(item.getCount1()));
        nb.setOnWarnListener(new NumberButton.OnWarnListener() {
            @Override
            public void onWarningForInventory(int inventory) {

            }

            @Override
            public void onWarningForBuyMax(int buyMax) {
                Toast.makeText(mContext, "超过最大购买数:" + buyMax, Toast.LENGTH_SHORT).show();
            }
        });
        helper.getView(R.id.cb).setTag(nb.getNumber()+","+helper.getPosition());
        helper.getView(R.id.cb).setOnClickListener(checkOnClickListener);
        CheckBox cb = helper.getView(R.id.cb);
        if(item.isornotClick()){
            cb.setChecked(true);
        }else{
            cb.setChecked(false);
        }
    }
}

