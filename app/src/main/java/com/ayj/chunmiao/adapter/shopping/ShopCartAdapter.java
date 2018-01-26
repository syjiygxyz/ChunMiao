package com.ayj.chunmiao.adapter.shopping;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.bean.shopping.ShopCart;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * Created by zht-pc-09 on 2016/8/1.
 */
public class ShopCartAdapter extends BaseAdapter {

    private List<ShopCart.DataBean> shopcarts;
    LayoutInflater inflater;
    Context context;


    public ShopCartAdapter(Context context,List<ShopCart.DataBean> shopcarts) {
        // TODO Auto-generated constructor stub
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.shopcarts = shopcarts;
    }

    public void setList(List<ShopCart.DataBean> shopcarts) {
        this.shopcarts = shopcarts;
    }
    @Override
    public int getCount() {
        return shopcarts == null ? 0 : shopcarts.size();
    }

    @Override
    public Object getItem(int i) {
        return shopcarts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_shopcart, null);
            holder = new ViewHolder();

            holder.iv_foot_zq_shopcart = (ImageView) convertView.findViewById(R.id.iv_foot_zq_shopcart);
            holder.tv_foot_shopcart_name = (TextView)convertView.findViewById(R.id.tv_foot_shopcart_name);
            holder.tv_foot_shopcart_shop = (TextView)convertView.findViewById(R.id.tv_foot_shopcart_shop);
            holder.tv_foot_shopcart_guige = (TextView)convertView.findViewById(R.id.tv_foot_shopcart_guige);
            holder.tv_foot_shopcart_price = (TextView)convertView.findViewById(R.id.tv_foot_shopcart_price);
            holder.tv_foot_shopcart_num = (TextView)convertView.findViewById(R.id.tv_foot_shopcart_num);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_foot_shopcart_name.setText(shopcarts.get(position).getMatidshow());
        holder.tv_foot_shopcart_shop.setText("春苗店：" + shopcarts.get(position).getCarttypeshow());
        holder.tv_foot_shopcart_price.setText("¥" + shopcarts.get(position).getPrice_now());
        holder.tv_foot_shopcart_guige.setText("规格："+"默认款式");
        holder.tv_foot_shopcart_num.setText("×"+shopcarts.get(position).getNum());
        Picasso.with(context).load(shopcarts.get(position).getImgurlcompressshow()+"&size=L").placeholder(R.mipmap.zhanweitu).error(
                R.mipmap.jiazaishibia).into(holder.iv_foot_zq_shopcart);
        return convertView;
    }
    class ViewHolder{
        ImageView iv_foot_zq_shopcart;
        TextView tv_foot_shopcart_name ;
        TextView tv_foot_shopcart_shop ;
        TextView tv_foot_shopcart_guige ;
        TextView tv_foot_shopcart_price ;
        TextView tv_foot_shopcart_num ;
    }
}
