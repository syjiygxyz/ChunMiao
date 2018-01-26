package com.ayj.chunmiao.adapter.cg;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.BaseViewHolder;
import com.ayj.chunmiao.bean.cg.CgGwcBean;
import com.ayj.chunmiao.bean.cg.Shoppingmall;
import com.ayj.chunmiao.bean.shopping.ShopCart;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.view.AddAndSub.MyAddAndSubEditTextView;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by zht-pc-04 on 2017/7/28.
 */

public class XhjShopCartAdapter extends BaseQuickAdapter<Shoppingmall.DataBean,BaseViewHolder> {
    private MyAddAndSubEditTextView addandsub_shopcart;
    private Map<Integer, Integer> map = new HashMap<>();//记录数量控件的数据
    Handler handler;

    public XhjShopCartAdapter(int layoutResId, List<Shoppingmall.DataBean> data, Handler handler) {
        super(layoutResId, data);
        this.handler = handler;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final Shoppingmall.DataBean item) {
        //initMap();
        map.put(helper.getLayoutPosition(),Integer.parseInt(item.getNum()));
        helper.setText(R.id.tv_content,item.getMatidshow());
        helper.setText(R.id.tv_total,"x "+item.getNum());
        helper.setText(R.id.tv_price,"￥"+ CommonUtils.douFormat(item.getShoppurchaseprice()));
        if("".equals(item.getImgurlcompressshow())){
            helper.setImageResource(R.id.iv_foot,R.mipmap.jiazaishibia);
        }else{
            Picasso.with(mContext).load(item.getImgurlcompressshow()).placeholder(R.mipmap.zhanweitu).error(R.mipmap.jiazaishibia).into((ImageView)helper.getView(R.id.iv_foot));

        }
        helper.getView(R.id.tv_bianji).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.getView(R.id.rl_bianji).setVisibility(View.GONE);
                helper.getView(R.id.rl_xiugai).setVisibility(View.VISIBLE);
            }
        });
        /*发送修改购物车消息*/
        helper.getView(R.id.tv_wancheng).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.getView(R.id.rl_xiugai).setVisibility(View.GONE);
                helper.getView(R.id.rl_bianji).setVisibility(View.VISIBLE);
                Message msg = new Message();
                msg.what = 0 ;
                Bundle b = new Bundle();
                b.putInt("position",helper.getLayoutPosition());
                b.putString("snid",item.getSnid());
                b.putString("num",map.get(helper.getLayoutPosition()).toString());
                msg.setData(b);
                handler.sendMessage(msg);
            }
        });
        /*发送删除购物车消息*/
        helper.getView(R.id.tv_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message msgde = new Message();
                msgde.what = 1 ;
                Bundle a = new Bundle();
                a.putString("snid",item.getSnid());
                a.putInt("position",helper.getLayoutPosition());
                helper.getView(R.id.rl_bianji).setVisibility(View.VISIBLE);
                helper.getView(R.id.rl_xiugai).setVisibility(View.GONE);
                msgde.setData(a);
                handler.sendMessage(msgde);
            }
        });
        addandsub_shopcart = helper.getView(R.id.addandsub_shopcart);
        addandsub_shopcart.setMAX_MARK(200);
        addandsub_shopcart.setOnAmountChangeListener(new MyAddAndSubEditTextView.OnAmountChangeListener() {
            @Override
            public void onAmountChange(View view, int count) {
                map.put(helper.getLayoutPosition(),count); //记录数据
                helper.setText(R.id.tv_total,"x "+count);
            }
        });
        addandsub_shopcart.setCount(Integer.parseInt(item.getNum()));
    }

    private void initMap() {
        for(int i=0 ; i < mData.size() ; i++){
            map.put(i,Integer.parseInt(mData.get(i).getNum()));
        }
    }
}
