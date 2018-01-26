package com.ayj.chunmiao.adapter.khq;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.khq.yyb.YybActivity;
import com.ayj.chunmiao.bean.FootZqShop;
import com.ayj.chunmiao.bean.khq.FootYyb;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by zht-pc-09 on 2016/7/26.
 */
public class YybGridViewAdapter extends BaseAdapter {

    int count;
    private List<FootZqShop.DataBean> footyyb;
    Context context;
    String shopid;
    String iscm;

    public YybGridViewAdapter(Context context) {
        // TODO Auto-generated constructor stub
        this.context = context;
    }


    public YybGridViewAdapter(Context context, int count, List<FootZqShop.DataBean> footyyb, String shopid, String iscm) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.count = count;
        this.footyyb = footyyb;
        this.shopid = shopid;
        this.iscm = iscm;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.item_yyb_recycler, null);
            holder = new ViewHolder();

            holder.linearlayout_yyb_recycler = (LinearLayout) convertView.findViewById(R.id.linearlayout_yyb_recycler);
            holder.iv_yyb_recycler = (ImageView) convertView.findViewById(R.id.iv_yyb_recycler);
            holder.tv_yyb_recycler = (TextView) convertView.findViewById(R.id.tv_yyb_recycler);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_yyb_recycler.setText(footyyb.get(position).getMatidshow());

        String url = footyyb.get(position).getLogoimgurlcompressshow();
        Picasso.with(context).load(url).placeholder(R.mipmap.menber_loading).error(R.mipmap.menber_error).into(holder.iv_yyb_recycler);
        holder.linearlayout_yyb_recycler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                YybActivity.jumpActivity(context,footyyb.get(position));
            }
        });
        return convertView;
    }

    class ViewHolder {

        private LinearLayout linearlayout_yyb_recycler;
        private ImageView iv_yyb_recycler;
        private TextView tv_yyb_recycler;
    }
}
